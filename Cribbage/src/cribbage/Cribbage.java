package cribbage;

// Cribbage.java

import ch.aplu.jcardgame.*;
import ch.aplu.jgamegrid.*;

import java.awt.Color;
import java.awt.Font;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import cribbage.scoring.*;

public class Cribbage extends CardGame {
	static Cribbage cribbage;  // Provide access to singleton

	public enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}

	public enum Rank {
		// Order of cards is tied to card images
		ACE(1,1), KING(13,10), QUEEN(12,10), JACK(11,10), TEN(10,10), NINE(9,9), EIGHT(8,8), SEVEN(7,7), SIX(6,6), FIVE(5,5), FOUR(4,4), THREE(3,3), TWO(2,2);
		public final int order;
		public final int value;
		Rank(int order, int value) {
			this.order = order;
			this.value = value;
		}
	}

	static int cardValue(Card c) { return ((Cribbage.Rank) c.getRank()).value; }

	/*
	Canonical String representations of Suit, Rank, Card, and Hand
	*/
	static String canonical(Suit s) { return s.toString().substring(0, 1); }

	static String canonical(Rank r) {
		switch (r) {
			case ACE:case KING:case QUEEN:case JACK:case TEN:
				return r.toString().substring(0, 1);
			default:
				return String.valueOf(r.value);
		}
	}

    static String canonical(Card c) { return canonical((Rank) c.getRank()) + canonical((Suit) c.getSuit()); }

    public static String canonical(Hand h) {
		Hand h1 = new Hand(h.getFirst().getDeck()); // Clone to sort without changing the original hand
		for (Card C: h.getCardList()) h1.insert(C.getSuit(), C.getRank(), false);
		h1.sort(Hand.SortType.POINTPRIORITY, false);
		return "[" + h1.getCardList().stream().map(Cribbage::canonical).collect(Collectors.joining(",")) + "]";
    }

	class MyCardValues implements Deck.CardValues { // Need to generate a unique value for every card
		public int[] values(Enum suit) {  // Returns the value for each card in the suit
			return Stream.of(Rank.values()).mapToInt(r -> (((Rank) r).order-1)*(Suit.values().length)+suit.ordinal()).toArray();
		}
	}

	static Random random;

	public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
      int x = random.nextInt(clazz.getEnumConstants().length);
      return clazz.getEnumConstants()[x];
  }

	static boolean ANIMATE;

	void transfer(Card c, Hand h) {
		if (ANIMATE) {
			c.transfer(h, true);
		} else {
			c.removeFromHand(true);
			h.insert(c, true);
		}
  }
  
  private void dealingOut(Hand pack, Hand[] hands) {
	  for (int i = 0; i < nStartCards; i++) {
		  for (int j=0; j < nPlayers; j++) {
			  Card dealt = randomCard(pack);
			  dealt.setVerso(false);  // Show the face
			  transfer(dealt, hands[j]);
		  }
	  }
  }

	static int SEED;

	public static Card randomCard(Hand hand){
      int x = random.nextInt(hand.getNumberOfCards());
      return hand.get(x);
  }

  private final String version = "0.1";
  static public final int nPlayers = 2;
  public final int nStartCards = 6;
  public final int nDiscards = 2;
  private final int handWidth = 400;
  private final int cribWidth = 150;
  private final int segmentWidth = 180;
  private final Deck deck = new Deck(Suit.values(), Rank.values(), "cover", new MyCardValues());
  private final Location[] handLocations = {
			  new Location(360, 75),
			  new Location(360, 625)
	  };

  private final Location[] segmentLocations = {  // need at most three as 3x31=93 > 2x4x10=80
			new Location(150, 350),
			new Location(400, 350),
			new Location(650, 350)
	};
  private final Location starterLocation = new Location(50, 625);
  private final Location cribLocation = new Location(700, 625);
  private final Location seedLocation = new Location(5, 25);
  // private final TargetArea cribTarget = new TargetArea(cribLocation, CardOrientation.NORTH, 1, true);
  private final Location textLocation = new Location(350, 450);
  private final Hand[] hands = new Hand[nPlayers];
  private final Hand[] showHands = new Hand[nPlayers];
  private Hand starter;
  private Hand crib;
  private final int DEALER = 1;
  private final int NONDEALER = 0;

  // Used for logging the actions taken in the game
  private Logger logger;

  // Store the properties
  private static Properties cribbageProperties;

  public static void setStatus(String string) { cribbage.setStatusText(string); }

static private final IPlayer[] players = new IPlayer[nPlayers];

final Font normalFont = new Font("Serif", Font.BOLD, 24);
final Font bigFont = new Font("Serif", Font.BOLD, 36);





private void deal(Hand pack, Hand[] hands) {
	for (int i = 0; i < nPlayers; i++) {
		hands[i] = new Hand(deck);
		// players[i] = (1 == i ? new HumanPlayer() : new RandomPlayer());
		players[i].setId(i);
		players[i].startSegment(deck, hands[i]);
	}
	RowLayout[] layouts = new RowLayout[nPlayers];
	for (int i = 0; i < nPlayers; i++)
	{
		layouts[i] = new RowLayout(handLocations[i], handWidth);
		layouts[i].setRotationAngle(0);
		// layouts[i].setStepDelay(10);
		hands[i].setView(this, layouts[i]);
		hands[i].draw();
	}
	layouts[0].setStepDelay(0);

	dealingOut(pack, hands);
	for (int i = 0; i < nPlayers; i++) {
		hands[i].sort(Hand.SortType.POINTPRIORITY, true);
		logger.logDeal(hands[i],i);
	}
	layouts[0].setStepDelay(0);
}

private void discardToCrib() {
	crib = new Hand(deck);
	RowLayout layout = new RowLayout(cribLocation, cribWidth);
	layout.setRotationAngle(0);
	crib.setView(this, layout);
	// crib.setTargetArea(cribTarget);
	crib.draw();
	for (IPlayer player: players) {
		
		// Create a discards hand in order to log
		Hand discards = new Hand(deck);
		for (int i = 0; i < nDiscards; i++) {
			Card discard = player.discard();
			discards.insert(discard.clone(), false);
			transfer(discard, crib);
		}
		logger.logDiscard(discards, player.id);
		crib.sort(Hand.SortType.POINTPRIORITY, true);
	}
}

private void starter(Hand pack) {
	starter = new Hand(deck);  // if starter is a Jack, the dealer gets 2 points
	RowLayout layout = new RowLayout(starterLocation, 0);
	layout.setRotationAngle(0);
	starter.setView(this, layout);
	starter.draw();
	Card dealt = randomCard(pack);
	dealt.setVerso(false);
	transfer(dealt, starter);
	
	// Log the starter and score the starter card
	logger.logStarter(starter.getFirst());
	logger.update(ScoringStrategyFactory.getInstance().getStarterScoringStrategy().getScores(starter), DEALER, "STARTER");

}

int total(Hand hand) {
	int total = 0;
	for (Card c: hand.getCardList()) total += cardValue(c);
	return total;
}

class Segment {
		Hand segment;
		boolean go;
		int lastPlayer;
		boolean newSegment;

		void reset(final List<Hand> segments) {
			segment = new Hand(deck);
			segment.setView(Cribbage.this, new RowLayout(segmentLocations[segments.size()], segmentWidth));
			segment.draw();
			go = false;        // No-one has said "go" yet
			lastPlayer = -1;   // No-one has played a card yet in this segment
			newSegment = false;  // Not ready for new segment yet
		}
}

private void play() {
	final int thirtyone = 31;
	List<Hand> segments = new ArrayList<>();
	int currentPlayer = 0, lastCardPlayer = -1; // Player 1 is dealer
	Segment s = new Segment();
	s.reset(segments);

	ArrayList<ScoringInstance> totalScoringInstances;

	while (!(players[0].emptyHand() && players[1].emptyHand())) {
		// System.out.println("segments.size() = " + segments.size());
		Card nextCard = players[currentPlayer].lay(thirtyone-total(s.segment));
		if (nextCard == null) {
			if (s.go) {
				// Another "go" after previous one with no intervening cards
				// lastPlayer gets 1 point for a "go"
				totalScoringInstances = ScoringStrategyFactory.getInstance().getGoScoringStrategy().getScores(s.segment);
				logger.update(totalScoringInstances, s.lastPlayer, "PLAY");

				s.newSegment = true;
			} else {
				// currentPlayer says "go"
				s.go = true;
			}
			currentPlayer = (currentPlayer+1) % 2;
		} else {
			s.lastPlayer = currentPlayer;// last Player to play a card in this segment
			lastCardPlayer = currentPlayer;
			transfer(nextCard, s.segment);
			logger.logPlay(s.segment, s.lastPlayer);

			// Update the score and log
			totalScoringInstances = ScoringStrategyFactory.getInstance().getCompositeScoringStrategy("PLAY").getScores(s.segment);
			logger.update(totalScoringInstances, s.lastPlayer, "PLAY");

			if (total(s.segment) == thirtyone) {
				// lastPlayer gets 2 points for a 31
				s.newSegment = true;
				currentPlayer = (currentPlayer+1) % 2;
			} else {
				// if total(segment) == 15, lastPlayer gets 2 points for a 15
				if (!s.go) { // if it is "go" then same player gets another turn
					currentPlayer = (currentPlayer+1) % 2;
				}
			}
		}
		if (s.newSegment) {
			segments.add(s.segment);
			s.reset(segments);
		}
	}
	
	// Log a go for the last card played
	totalScoringInstances = ScoringStrategyFactory.getInstance().getGoScoringStrategy().getScores(new Hand(deck));
	logger.update(totalScoringInstances, lastCardPlayer, "PLAY");

}

void showHandsCrib() {
	String phase = "SHOW";
	ArrayList<ScoringInstance> totalScoringInstances;
	IScoringStrategy strategy = ScoringStrategyFactory.getInstance().getCompositeScoringStrategy("SHOW");

	// Score player 0 (non dealer)
	logger.logShow(showHands[NONDEALER], NONDEALER);
	totalScoringInstances = strategy.getScores(showHands[NONDEALER]);
	logger.update(totalScoringInstances, NONDEALER, phase);


	// Score player 1 (dealer)
	logger.logShow(showHands[DEALER], DEALER);
	totalScoringInstances = strategy.getScores(showHands[DEALER]);
	logger.update(totalScoringInstances, DEALER, phase);

	// Score crib (for dealer)
	crib.reverse(false);
	crib.insert(starter.getFirst().clone(), false);
	crib.reverse(false);

	logger.logShow(crib, DEALER);
	totalScoringInstances = strategy.getScores(crib);
	logger.update(totalScoringInstances, DEALER, phase);

}

  public Cribbage()
  {
    super(850, 700, 30);
    cribbage = this;
    setTitle("Cribbage (V" + version + ") Constructed for UofM SWEN30006 with JGameGrid (www.aplu.ch)");
    setStatusText("Initializing...");
    this.logger = new Logger(cribbage);
	logger.logGameStart(cribbageProperties.getProperty("Player0"), cribbageProperties.getProperty("Player1"));

	  Hand pack = deck.toHand(false);
	  RowLayout layout = new RowLayout(starterLocation, 0);
	  layout.setRotationAngle(0);
	  pack.setView(this, layout);
	  pack.setVerso(true);
	  pack.draw();
	  addActor(new TextActor("Seed: " + SEED, Color.BLACK, bgColor, normalFont), seedLocation);

	  /* Play the round */
	  deal(pack, hands);
	  discardToCrib();
	  starter(pack);
	  duplicateHands();
	  play();
	  showHandsCrib();
	  logger.close();

    addActor(new Actor("sprites/gameover.gif"), textLocation);
    setStatusText("Game over.");
    refresh();
  }

  private void duplicateHands(){
	  int i = 0;
	  showHands[NONDEALER] = new Hand(deck);
	  showHands[DEALER] = new Hand(deck);
	  
	  // For each hand duplicate and add the starter card
	  for (Hand hand: showHands) {
		  hand.insert(starter.getFirst().clone(), false);
		  for(Card card : hands[i].getCardList()){
			  hand.insert(card.clone(), false);
		  }
		  i++;
	  }
  }




  public static void main(String[] args)
		  throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
		  	InstantiationException, IllegalAccessException {
	  /* Handle Properties */
	  // System.out.println("Working Directory = " + System.getProperty("user.dir"));
	  cribbageProperties = new Properties();
	  // Default properties
	  cribbageProperties.setProperty("Animate", "true");
	  cribbageProperties.setProperty("Player0", "cribbage.RandomPlayer");
	  cribbageProperties.setProperty("Player1", "cribbage.HumanPlayer");

	  // Read properties
	  try (FileReader inStream = new FileReader("cribbage.properties")) {
		  cribbageProperties.load(inStream);
	  }

	  // Control Graphics
	  ANIMATE = Boolean.parseBoolean(cribbageProperties.getProperty("Animate"));

	  // Control Randomisation
	  /* Read the first argument and save it as a seed if it exists */
	  if (args.length > 0 ) { // Use arg seed - overrides property
		  SEED = Integer.parseInt(args[0]);
	  } else { // No arg
		  String seedProp = cribbageProperties.getProperty("Seed");  //Seed property
		  if (seedProp != null) { // Use property seed
			  SEED = Integer.parseInt(seedProp);
		  } else { // and no property
			  SEED = new Random().nextInt(); // so randomise
		  }
	  }
	  random = new Random(SEED);

	  // Control Player Types
	  Class<?> clazz;
	  clazz = Class.forName(cribbageProperties.getProperty("Player0"));
	  players[0] = (IPlayer) clazz.getConstructor().newInstance();
	  clazz = Class.forName(cribbageProperties.getProperty("Player1"));
	  players[1] = (IPlayer) clazz.getConstructor().newInstance();
	  // End properties

	  new Cribbage();
  }

}

