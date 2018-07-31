package pokemonbattle;

import java.util.Scanner;

/**
 * Pokemon battle. This class contains the necessary
 * object classes and the methods to simulate the battle.
 * There will be two trainers in the battle and the user will take control
 * of both trainer's partys / pokemon. 
 * @version 2.2
 * @author Andrew Kassab
 */

// TODO: Change constructor values to be more easily readable.
public class PokemonBattle 
{

    /** 
     * Class to create, initialize, and store pokemon moves with their
     * respective varibles. 
     * TODO: implement the accuracy variable, which determines the
     * hit chance for a specific move (see hit() method).
     * TODO: implement the PP variable, determining how many times
     * a move can be used during a battle.
     */
    public static class Move
    {
        private String name;
        private String type;
        private int damage;
        private double accuracy;
        private int powerPoints;
        private int maxPowerPoints;
        private boolean priority;
        private boolean physical;
        private boolean special;
          
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
        private int health;
        private int maxHealth;
        private String type;
        private int speed;
        private Move[] moves = new Move[4];
        private Move activeMove;
        
        public Pokemon(String n, int h, String a, int s, Move[] m){
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
        
        public int getHealth(){
            return health;
        }
        
        public int getMaxHealth() {
        	return maxHealth;
        }
        
        public void setHealth(int h){
            health = h;
        }
        
        // TODO: Make into String[] for 2 typed Pokemon
        public String getType(){
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
         * Prints the Pokemon's move list and their current 
         * PP values. 
         * TODO: Print out the accuracy for each move
         */
        public void printMoves() {
        	System.out.printf("%-20s%-5s%n", "Attack", "PP");
        	System.out.println("---------------------------");
        	for (int i = 0; i < moves.length; i++) {
        		System.out.printf("%-20s%-5s%n", moves[i].getName(), moves[i].getPP() + 
        				"/" + moves[i].getMaxPP());
        	}
        }   
        
        /**
         * Handles move selection for player 
         * @param p Pokemon a move is being selected for
         * @return the move selected
         */
        public void selectMove() {
        	
        	Scanner keyboard = new Scanner(System.in);
        	printMoves();
        	String selection;
        	System.out.println();
        	System.out.print("Select a move for " + name + " (by name): ");
        	     		
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
    					selectMove();
    				}
    			}
    		}
    		
    	    System.out.print("Invalid move, please try again.");    
    	    selectMove();
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
     * TODO: 
     * @author precisemotion
     *
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
         * Displays a trainers current party Pokemon
         * TODO: Add in code to display whether a Pokemon is fainted
         * and unable to battle.
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
         * Handles active Pokemon selection for a trainer. 
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
            String pokeType = t.getActivePokemon().getType();
            Move move = activePokemon.getActiveMove();
            String[] positive = getPosEffects(move);
            String[] negative = getNegEffects(move);
            String zero = getZeroEffects(move);
            int damage = move.getDamage();
            move.setPP(move.getPP() - 1);
            
            // Multiply damage by 2 for every positive match
            for (int i = 0; i < positive.length; i++){
                if ( positive[i].equals(pokeType) )
                {      
                    damage = damage * 2;
                }
            }
            
            // Divide damage by 2 for every negative match made
            for (int i = 0; i < negative.length; i++){
                if ( negative[i].equals(pokeType) )
                {
                    damage = damage/2;
                }
            }   
            
            // If the move type does not work against the pokemon type at all
            if (pokeType.equals(zero)){
                damage = 0;
            }
            
            // Check if the attack has landed
            // TODO: If an attack lands, set PP value for that move down by 1.
            if (move.hit()){
                System.out.println(activePokemon.getName() + " used " + move.getName() + "!");
                // Super Effective
                if (damage > move.getDamage()){
                    System.out.println("It's super effective!");
                }
                // Not very effective
                if (damage < move.getDamage()){
                    System.out.println("It's not very effective...");
                }
                // Not at all effective
                if (damage == 0){
                    System.out.println("But it didn't work!");
                }
                
                t.getActivePokemon().setHealth(t.getActivePokemon().getHealth() - damage);
                
                // Prevent negative health values
                if (t.getActivePokemon().getHealth() < 0) {
                	t.getActivePokemon().setHealth(0);
                }
                
                t.updateParty(); 
                
                System.out.println(t.getActivePokemon().getName() + " took " + damage + " damage!\n");
                        
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
            
            party[activeIndex] = activePokemon;
            t.updateParty();
            
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
        String result = "";
        
        // Check type of move
        switch (m.getType()){
            case "normal": 
                result = "ghost";
            case "electric": 
                result = "ground";
            case "fighting": 
                result = "ghost";
            case "poison": 
                result = "steel";
            case "ground": 
                result = "fly";
            case "psychic": 
                result = "dark";
            case "ghost": 
                result = "normal";        
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
        if (ma.hasPriority()){
            if (mb.hasPriority()){
                if (Math.random() >= 0.5){
                    return 1;
                }
                else return 2;
            }
            else return 1;
        }
        else if (mb.hasPriority()){
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
        
        // Temporary filler Move
        Move empty = new Move("TEMPORARY FILLER","normal",200,100,99,false,true,true);  
        
        Move[] pokeOneMoves = new Move[]{moveOne,moveTwo,moveFour,empty};
        Move[] pokeTwoMoves = new Move[]{moveThree,moveTwo,moveFour,empty};
        Move[] pokeThreeMoves = new Move[]{empty,empty,empty,empty};
        Move[] pokeFourMoves = new Move[]{empty,empty,empty,empty};
        
        // Initialize and construct the Pokemon to battle
        // TODO: Account for double typed Pokemon
        Pokemon pokemonOne = new Pokemon("Charmander", 115, "fire", 6, pokeOneMoves);
        Pokemon pokemonTwo = new Pokemon("Squirtle", 100, "water", 5, pokeTwoMoves);
        Pokemon pokemonThree = new Pokemon("Bulbasaur", 120,"grass",4, pokeThreeMoves);
        Pokemon pokemonFour = new Pokemon("Pikachu", 90, "electric",7,pokeFourMoves);
        
        // Creating the parties for each trainer
        Pokemon[] partyOne = new Pokemon[] {pokemonOne,pokemonThree};
        Pokemon[] partyTwo = new Pokemon[] {pokemonTwo,pokemonFour};
        
        // Trainer's and their party Pokemon
        Trainer trainerOne = new Trainer(partyOne);
        Trainer trainerTwo = new Trainer(partyTwo);
        
        // Select the active Pokemon for each trainer
        trainerOne.selectPokemon();
        trainerTwo.selectPokemon();
        
        // Battle loop
        do{
            
            trainerOne.getActivePokemon().selectMove();
            trainerTwo.getActivePokemon().selectMove();
 
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
