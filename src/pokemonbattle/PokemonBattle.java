package pokemonbattle;

import java.util.Scanner;

/**
 * Pokemon battle. This class contains the necessary
 * object classes and the methods to simulate the battle.
 * There will be two trainers in the battle and the user will take control
 * of both trainer's partys / pokemon. 
 * @version 2.5
 * @author Andrew Kassab
 */

// TODO: Change constructor values to be more easily readable.
public class PokemonBattle 
{

    /** 
     * Class to create, initialize, and store pokemon moves with their
     * respective varibles. 
     * TODO: Implement physical and special labels for moves
     */
    public static class Move
    {
        private String name;
        private String type;
        private int damage;
        private double accuracy;
        private int powerPoints;
        private int maxPowerPoints;
        private boolean priority; // label for speed priority
        private boolean physical; // physical label
        private boolean special; // special label
          
        public Move(String n, String a, int d, double h, int pp, boolean p, boolean ph, boolean sp){
            name = n;
            type = a;
            damage = d;
            accuracy = h;
            powerPoints = pp;
            maxPowerPoints = pp;
            priority = p;
            physical = ph;
            special = sp;
        }
        
        public String getType(){
            return type;
        }
        
        public String getName(){
            return name;
        }
        
        public int getDamage(){
            return damage;
        }
        
        public double getAccuracy() {
        	return accuracy;
        }
        public void setAccuracy(int a) {
        	accuracy = a;
        }
        
        public int getPP() {
        	return powerPoints;
        }
        public int getMaxPP() {
        	return maxPowerPoints;
        }
        public void setPP(int pp) {
        	powerPoints = pp;
        }
        
        /**
         * Checks if a move is a physical move.
         * @return true if the move is a physical move.
         */
        public boolean isPhysical() {
        	return physical;
        }
        
        /**
         * Checks if a move is a special move.
         * @return true if the move is a special move.
         */
        public boolean isSpecial() {
        	return special;
        }
        
        /**
         * Checks if a move can be used (if it has any power points left)
         * @return true if a move can be used.
         */
        public boolean hasPP() {
        	if (powerPoints >= 1) {
        		return true;
        	}
        	else return false;
        }
        /**
         * Determines whether a move has priority over speed attributes
         * @return true if a move has priority
         */
        public boolean hasPriority(){
            return priority;
        }
             
        /**
         * Determines if a move hits in its attack turn by
         * generating a random number and checking the accurary value
         * @return whether the attack landed or not.
         */
        public boolean hit() 
        {
        	double randy = Math.random();
        	if (randy <= accuracy/100){
               return true;
        	} 
        	else return false;
        }
        
          
    }
    
    /**
     * Class to create, initialize, and store a Pokemon and their moves. 
     * TODO: Add and implement the defense attribute, attack attribute, and 
     * their respective getters and setters. 
     * TODO: Change health to a double and update all methods and code accordingly, 
     * make a decimal formatter for getHealth that rounds to 2 decimals
     * TODO: Make 'type' an array of size 2, so that we can have Pokemon with two types 
     * (Pokemon with 1 type will have a 2nd type of "", alternatively, an ArrayList could be used 
     * so that size 1 and 2 would both be possible.
     * Andrew TODO: Implement the Attack and Defense variables, add and implement Special Attack 
     * and Special Defense.
    */
    public static class Pokemon
    {
        
        private String name;
        private double health;
        private double maxHealth;
        private String[] type;
        private int speed;
        private Move[] moves = new Move[4];
        private Move activeMove;
        
        public Pokemon(String n, int h, String[] a, int s, Move[] m){
            name = n;
            health = h;
            type = a;
            speed = s;
            moves = m;
            maxHealth = h;
        }
        
        public String getName(){
            return name;
        }
        
        public double getHealth(){
            return health;
        }
        
        public double getMaxHealth() {
        	return maxHealth;
        }
        
        public void setHealth(double h){
            health = h;
        }
        
        public String[] getType(){
            return type;
        }
        
        public int getSpeed(){
            return speed;
        }   
        
        public Move[] getMoves() {
        	return moves;
        }
        
        public Move getActiveMove() {
        	return activeMove;
        }
        
        /**
         * Sets activeMove to null before next turn.
         */
        public void resetMove() {
        	activeMove = null;
        }
        /**
         * Prints the Pokemon's move list and their current 
         * PP values. 
         */
        public void printMoves() {
        	System.out.printf("%-20s%-8s%-8s%n", "Attack", "PP","Accuracy");
        	System.out.println("-------------------------------------");
        	for (int i = 0; i < moves.length; i++) {
        		System.out.printf("%-20s%-8s%-8s%n", moves[i].getName(), moves[i].getPP() + 
        				"/" + moves[i].getMaxPP(), (int) moves[i].getAccuracy() + "%");
        	}
        }   
        
        /**
         * Handles move selection for trainer.
         * @return the move selected
         */
        public void selectMove(Trainer t) {
        	
        	Scanner keyboard = new Scanner(System.in);
        	printMoves();
        	String selection;
        	System.out.println();
        	System.out.print("Select a move for " + name + " (by name), or enter swap to switch Pokemon: ");
        	     		
    		selection = keyboard.nextLine();
    		for (int i = 0; i < moves.length; i++) {
    			if (selection.equalsIgnoreCase(moves[i].getName())) {
    				if (moves[i].hasPP()) {
    					System.out.println();
    					activeMove = moves[i];
    					return;
    				}
    				else {
    					System.out.println("Out of power points, please try again.");
    					System.out.println();
    					selectMove(t);
    				}
    			}
    				// Chooses to switch out Pokemon
    			if (selection.equalsIgnoreCase("swap")) {
    				System.out.println();
					t.selectPokemon();
					return;
				}
    		}
    		
    	    System.out.println("Invalid move, please try again.");    
    	    selectMove(t);
        }
        
        /**
         * Checks if a Pokemon can battle (if it is above 0 health)
         * @return true if Pokemon can be called to battle
         */
        public boolean canBattle() {
        	if (health > 0) {
        		return true;
        	}
        	else return false;
        }
    }
    
    /**
     * Class to create and initialize the Trainer object. 
     * A trainer has a party of Pokemon, and an identifier for which
     * Pokemon is currently active and in battle. 
     * @author precisemotion
     */
    public static class Trainer{
    	
    	private Pokemon activePokemon;
    	private Pokemon[] party;
    	private String[] inventory;
    	private int activeIndex;
    	private boolean canAttack;
    	
    	public Trainer(Pokemon[] team) {
    		party = team;
    		canAttack = true;
    	}
    	
    	public Pokemon getActivePokemon() {
    		return activePokemon;
    	}
    	
    	public void setCanAttack(boolean b) {
    		canAttack = b;
    	}
    	
    	/**
    	 * Checks if the trainer can continue their attack turn. If
    	 * their Pokemon has just fainted, canAttack will be false and
    	 * the battle moves onto the next turn.
    	 * @return if the turn can continue
    	 */
    	public boolean canAttack() {
    		return canAttack;
    	}
    	
    	/**
    	 * Makes sure the Pokemon in the party is up to date
    	 * with the activePokemon variable 
    	 */
    	public void updateParty() {
    		party[activeIndex] = activePokemon;
    	}
    	
    	/**
         * Displays a trainers current party Pokemon and their status.
         */
        public void displayPokemon() {
        	System.out.printf("%-15s%7s%n","POKEMON","HEALTH");
        	System.out.println("-----------------------");
        	for (int i = 0; i < party.length; i++) {
        		System.out.printf("%-15s%-8s%n", party[i].getName(), 
        				party[i].getHealth() + "/" + party[i].getMaxHealth());
        	}
        	System.out.println();
        }
    	
    	/**
         * Handles Pokemon selection for a trainer. 
         * activePokemon variable is set to the choice made.
         */
        public void selectPokemon() {
        	
        	Scanner keyboard = new Scanner(System.in);
            String selection;
            displayPokemon();
            System.out.print("Select a Pokemon (by name): ");
            
            selection = keyboard.next();
        	System.out.println();
            
        	for (int i = 0; i < party.length; i++) {
        		if (party[i].getName().equalsIgnoreCase(selection)){
        			if (party[i].canBattle()) { 
        				if (party[i] == activePokemon) {
        					System.out.println("That Pokemon is already in battle!\n");
        					selectPokemon();
        				}
        				if (activePokemon == null) {
        					System.out.println("Let's go, " + party[i].getName() + "!\n");
        				}
        				activePokemon = party[i];
        				activeIndex = i;       				
        				return;
        			}
        			else {
        				System.out.println(party[i].getName() + " is unable to battle, please try again.");
        				System.out.println();       				
        				selectPokemon();
        			}
        		}
        	}
        	
        	System.out.println("Invalid pokemon selected, please try again.");
        	System.out.println();
        	selectPokemon(); 
     
        }
        
        /**
         * Carries out an attack during the round and determines the
         * appropriate damage values by comparing types. Outputs
         * the results.
         * Andrew TODO: Implement the attack / defense stats here once the attributes
         * are added to the Pokemon class.
         * @param t the trainer being attacked
         */
        public void Attack (Trainer t)
        {    
        	
            String[] pokeType = t.getActivePokemon().getType();
            Move move = activePokemon.getActiveMove();
            
            // If the trainer chose to swap Pokemon rather than attack.
            if (move == null) {
            	System.out.println("The trainer has swapped to " + activePokemon.getName() + "!\n");
            	return;
            }
            
            String[] positive = getPosEffects(move);
            String[] negative = getNegEffects(move);
            String zero = getZeroEffects(move);
            double damage = move.getDamage();
            move.setPP(move.getPP() - 1);
            
            for (int j = 0; j < pokeType.length; j++) {
            	 // Multiply damage by 2 for every positive match
                for (int i = 0; i < positive.length; i++){
                    if ( positive[i].equals(pokeType[j]) )
                    {      
                        damage = damage * 2;
                    }
                }
                
                // Divide damage by 2 for every negative match made
                for (int i = 0; i < negative.length; i++){
                    if ( negative[i].equals(pokeType[j]) )
                    {
                        damage = damage/2;
                    }
                }        
                
                // Same type attack bonus (STAB)
                if (activePokemon.getType()[j] == move.getType()) {
                	damage = damage * 1.5;
                }
                
                // If the move type does not work against the pokemon type at all
                if (pokeType[j].equals(zero)){
                    damage = 0;
                }
            }
            
            // Check if the attack has landed
            if (move.hit()){
                System.out.println(activePokemon.getName() + " used " + move.getName() + "!");
                // Super Effective
                if (damage >= 2 * move.getDamage()){
                    System.out.println("It's super effective!");
                    System.out.println(t.getActivePokemon().getName() + " took " + damage + " damage!\n");
                }
                // Not at all effective
                else if (damage == 0){
                    System.out.println("But it didn't work!");
                }
                // Not very effective
                else if (damage < move.getDamage()){
                    System.out.println("It's not very effective...");
                    System.out.println(t.getActivePokemon().getName() + " took " + damage + " damage!\n");
                }
                else {
                	System.out.println(t.getActivePokemon().getName() + " took " + damage + " damage!\n");
                }
                  
                t.getActivePokemon().setHealth(t.getActivePokemon().getHealth() - damage);
                
                // Prevent negative health values
                if (t.getActivePokemon().getHealth() < 0) {
                	t.getActivePokemon().setHealth(0);
                }
                
                t.updateParty(); 
                        
                // If the pokemon has fainted
                if (t.getActivePokemon().getHealth() <= 0)
                {
                    System.out.println(t.getActivePokemon().getName() + " has fainted!");
                    if (t.canContinue()) {
                    	System.out.println("Select another Pokemon from your party.");
                    	System.out.println();
                    	t.selectPokemon();
                    	t.setCanAttack(false);
                    }
                    else {
                    	battleEnded();
                    }
                }
            }
            else{
                System.out.println(activePokemon.getName() + " used " + move.getName() + "!");
                System.out.println("But it missed!\n");
            }    
            
            activePokemon.resetMove();
            party[activeIndex] = activePokemon;
            
            
        }
        
        /**
         * Checks if the trainer has Pokemon in their party that can still battle
         * @return true if the trainer can switch out another Pokemon
         */
        public boolean canContinue() {
        	for (int i = 0; i < party.length; i++) {
        		if (party[i].getHealth() > 0) {
        			return true;
        		}
        	}
        	return false;
        }
    	
    }
    /**
     * Takes in a move, and then determines what types this move
     * is super effective against.
     * @param m Move being used.
     * @return String array containing all effective types.
     */
    public static String[] getPosEffects(Move m)
    {
        String[] result = new String[]{""};
        
        // Checks type of move
        switch (m.getType()){ 
            case "normal": 
                return result;
            case "fire": 
                result = new String[]{"grass","ice","bug","steel"};
                return result;
            case "water": 
                result = new String[]{"fire","ground","rock"};
                return result;
            case "electric": 
                result = new String[]{"water","flying"};
                return result;
            case "grass": 
                result = new String[]{"water","ground","rock"};
                return result;
            case "ice": 
                result = new String[]{"grass","ground","flying","dragon"};
                return result;
            case "fighting": 
                result = new String[]{"normal","ice","rock","dark","steel"};
                return result;
            case "poison": 
                result = new String[]{"grass","fairy"};
                return result;
            case "ground": 
                result = new String[]{"fire","electric","poison","rock","steel"};
                return result;
            case "flying": 
                result = new String[]{"grass","fighting","bug"};
                return result;
            case "psychic": 
                result = new String[]{"fighting","poison"};
                return result;
            case "bug": 
                result = new String[]{"grass","psychic","dark"};
                return result;
            case "rock": 
                result = new String[]{"fire","ice","flying","bug"};
                return result;
            case "ghost": 
                result = new String[]{"psychic","ghost"};
                return result;
            case "dragon": 
                result = new String[]{"dragon"};
                return result;
            case "dark": 
                result = new String[]{"psychic","rock"};
                return result;
            case "steel": 
                result = new String[]{"ice","fairy","rock"};
                return result;
            case "fairy": 
                result = new String[]{"fighting","dragon","dark"};
                return result;                 
        }
        
        // If the type does not match any case, error displays and program ends.
        System.out.print("ERROR, NO CASE MATCHED. PLEASE CHECK TYPE NAMES"); 
        System.exit(0); 
        return result;        
    }
    
    /**
     * Takes in a move, and then determines what types this move
     * is not very effective against.
     * @param m Move being used.
     * @return String array containing all resistant types.
     */
    public static String[] getNegEffects(Move m)
    {
        String[] result = new String[]{""};
        
        // Check type of move
        switch (m.getType()){
            case "normal": 
                result = new String[]{"ground","rock","steel"};
                return result;
            case "fire": 
                result = new String[]{"fire","water","rock","dragon"};
                return result;
            case "water": 
                result = new String[]{"water","grass","dragon"};
                return result;
            case "electric": 
                result = new String[]{"ground","electric","grass","dragon"};
                return result;
            case "grass": 
                result = new String[]{"fire","grass","poison","bug","dragon","steel"};
                return result;
            case "ice": 
                result = new String[]{"fire","water","ice","steel"};
                return result;
            case "fighting": 
                result = new String[]{"ghost","poison","flying","psychic","bug","fairy",};
                return result;
            case "poison": 
                result = new String[]{"steel","ground","rock","ghost"};
                return result;
            case "ground": 
                result = new String[]{"fly","grass","bug"};
                return result;
            case "flying": 
                result = new String[]{"electric","rock","steel"};
                return result;
            case "psychic": 
                result = new String[]{"dark","psychic","steel"};
                return result;
            case "bug": 
                result = new String[]{"fire","fighting","poison","flying","ghost","steel","fairy"};
                return result;
            case "rock": 
                result = new String[]{"fighting","ground","steel"};
                return result;
            case "ghost": 
                result = new String[]{"normal","dark"};
                return result;
            case "dragon": 
                result = new String[]{"fairy","steel"};
                return result;
            case "dark": 
                result = new String[]{"fighting","dark","fairy"};
                return result;
            case "steel": 
                result = new String[]{"fire","water","electric","steel"};
                return result;
            case "fairy": 
                result = new String[]{"fire","poison","steel"};
                return result;                 
        }
        
        // If the type does not match any case, error displays and program ends.
        System.out.print("ERROR, NO CASE MATCHED. PLEASE CHECK TYPE NAMES");
        System.exit(0);
        return result;   
    }
    
     /**
     * Takes in a move, and then determines what type this move
     * is not at all effective against.
     * @param m Move being used.
     * @return String containing the resistant type (if any exists).
     */
    public static String getZeroEffects(Move m)
    {
        String result = "empty";
        
        // Check type of move
        switch (m.getType()){
            case "normal": 
                result = "ghost";
                return result;
            case "electric": 
                result = "ground";
                return result;
            case "fighting": 
                result = "ghost";
                return result;
            case "poison": 
                result = "steel";
                return result;
            case "ground": 
                result = "fly";
                return result;
            case "psychic": 
                result = "dark";
                return result;
            case "ghost": 
                result = "normal";      
                return result;
        }
  
        return result;   
    }
    
    /**
    * Checks to see which Pokemon will be attacking first in the battle. 
    * If neither move has a priority factor, then it is decided by
    * comparing the two Pokemon's speed stats.
    * @param pa Pokemon A
    * @param pb Pokemon B
    * @param ma Move used by Pokemon A
    * @param mb Move used by Pokemon B
    * @return value 1 or 2, 1 for Pokemon A going first, and 2 for Pokemon B
    */
    public static int whosFirst(Pokemon pa, Pokemon pb, Move ma, Move mb)
    {
    	if ( ma == null ) {
    		return 1;
    	}
    	else if (mb == null ) {
    		return 2;
    	}
    	else if ( ma.hasPriority()){
            if ( mb.hasPriority()){
                if (Math.random() >= 0.5){
                    return 1;
                }
                else return 2;
            }
            else return 1;
        }
        else if ( mb.hasPriority()){
            return 2;
        }
        else if (pa.getSpeed() > pb.getSpeed()){
            return 1;
        }
        else if (pb.getSpeed() > pa.getSpeed()){
            return 2;
        }
        
        // Randomize in the event of a matching speed case.
        else {
            if (Math.random() >= 0.5){
                    return 1;
                }
                else return 2;
        }     
    }
    
    /**
     * Displays current health status for both Pokemon in battle
     * @param a First Pokemon.
     * @param b Second Pokemon.
     */
    public static void displayHealth(Pokemon a, Pokemon b){
        if (a.getHealth() < 0){
            a.setHealth(0);
        }
        if (b.getHealth() < 0){
            b.setHealth(0);
        }
        System.out.println(a.getName() + ": " + a.getHealth() + " health");
        System.out.println(b.getName() + ": " + b.getHealth() + " health\n");
    }
    
    /**
     * Handles an event where a trainer is unable to continue battling
     * and the battle must end.
    */
    public static void battleEnded(){
    	// TODO: Add names for trainers??
        System.out.println("The battle has ended!");
        System.exit(0);
    }
    
    /**
     * Main method, creates two trainers, their party pokemon, with their
     * respective Moves and begins the battle
     * @param args.
     * @throws InterruptedException Thread sleeps for user to process results
     */
    public static void main(String[] args) throws InterruptedException {
        
        Scanner keyboard = new Scanner(System.in);
        
        /* **CONSTRUCTOR VALUES**
         * Move(name, type, base damage, accuracy, power points, priority, physical, special)
         * Pokemon(name, health, type, speed, moves[])
         * Trainer(party[])
         */
        
        Move moveOne = new Move("Flamethrower","fire",20,80,10,false,false,true);
        
        // Move to test priority system
        Move moveTwo = new Move("Quick Attack","normal",15, 100, 15,true,true,false); 
        Move moveThree = new Move("Water gun","water",20,80,10,false,false,true);
        Move moveFour = new Move("Tackle","normal",20,100,15,false,true,false);
        
        // Charizard's Moves
        Move fireBlast = new Move("Fire Blast","fire",60,70,10,false,false,true);
        Move earthquake = new Move("Earthquake","ground",50,80,10,false,false,true);
        Move solarBeam = new Move("Solar Beam", "grass", 60,70,10,false,false,true);
        
        // Blastoise's Moves
        Move hydroPump = new Move("Hydro Pump","water",60,70,10,false,false,true);
        Move iceBeam = new Move("Ice Beam","ice",50,80,10,false,false,true);
        Move darkPulse = new Move("Dark Pulse", "dark", 50,80,10,false,false,true);
        
        // Lucario Moves
        Move closeCombat = new Move("Close Combat","fighting",60,70,10,false,true,false);
        Move bulletPunch = new Move("Bullet Punch","bug", 50,80,10,false,true,false);
        Move meteorMash = new Move("Meteor Mash", "steel", 50,80,10,false,true,false);
        
        // Metagross Moves
        Move thunderPunch = new Move("Thunder Punch","electric",50,80,10,false,true,false);
        
        // Temporary filler Move
        Move filler = new Move("TEMPORARY FILLER","ground",200,100,99,false,true,true);  
        
        Move[] charMoves = new Move[]{fireBlast,earthquake,solarBeam,filler};
        Move[] blasMoves = new Move[]{hydroPump,iceBeam,darkPulse,filler};
        Move[] lucMoves = new Move[]{closeCombat,bulletPunch,meteorMash,filler};
        Move[] metaMoves = new Move[]{meteorMash,bulletPunch,thunderPunch,filler};
        
        Pokemon charizard = new Pokemon("Charizard",250,new String[]{"fire","flying"},20,charMoves);
        Pokemon blastoise = new Pokemon("Blastoise",300,new String[] {"water",""},15,blasMoves);
        Pokemon lucario = new Pokemon("Lucario",250,new String[] {"fighting","steel"},25,lucMoves);
        Pokemon metagross = new Pokemon("Metagross",320,new String[] {"psychic","steel"},12, metaMoves);
        
        // Creating the parties for each trainer
        Pokemon[] partyOne = new Pokemon[] {charizard,lucario};
        Pokemon[] partyTwo = new Pokemon[] {blastoise,metagross};
        
        // Trainer's and their party Pokemon
        Trainer trainerOne = new Trainer(partyOne);
        Trainer trainerTwo = new Trainer(partyTwo);
        
        // Select the active Pokemon for each trainer
        trainerOne.selectPokemon();
        trainerTwo.selectPokemon();
        
        // Battle loop
        do{
            
            trainerOne.getActivePokemon().selectMove(trainerOne);
            trainerTwo.getActivePokemon().selectMove(trainerTwo);
 
            // Decide attacking order
            int result = whosFirst(trainerOne.getActivePokemon(),trainerTwo.getActivePokemon(),
            		trainerOne.getActivePokemon().getActiveMove(), 
            		trainerTwo.getActivePokemon().getActiveMove());  
           
            // Attack turns begin
            if (result == 1) { // If trainer one attacks first
            	trainerOne.Attack(trainerTwo);
            	Thread.sleep(3000);
            	if (trainerTwo.canAttack()) {
            		trainerTwo.Attack(trainerOne);
            	}
            	trainerTwo.setCanAttack(true);
            }
            else { // If trainer two attacks first
            	trainerTwo.Attack(trainerOne);
            	Thread.sleep(3000);
            	if (trainerOne.canAttack()) {
            		trainerOne.Attack(trainerTwo);
            	}
            	trainerOne.setCanAttack(true);
            }
            
            // Print out health status
            displayHealth(trainerOne.getActivePokemon(),trainerTwo.getActivePokemon());
            
            // Loop continues as long as both Pokemon are still alive
            // TODO: Make loop continue as long as the trainers have Pokemon available
        } while (trainerOne.getActivePokemon().getHealth() > 0 && 
        		trainerTwo.getActivePokemon().getHealth() > 0);
        
    }
    
}
