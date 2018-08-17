package pokemonbattle;

import java.util.Scanner;

/**
 * Class to create and initialize the Trainer object. 
 * A trainer has a party of Pokemon, and an identifier for which
 * Pokemon is currently active and in battle. 
 * TODO: Bugs, damage values not consistent with earthquake ? (charizard vs blastoise)
 * @version 7.0
 * @author precisemotion
 */
public class Trainer{
	
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
    	
    	// If the trainer has no Pokemon left to battle 
    	// TODO: Doesn't account for when both Pokemon die to a status
    	if (!canContinue()) {
    		battleEnded();
    	}
    	
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
    				else {
    					activePokemon.resetStages();
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
     * appropriate calculationTwo values by comparing types. Outputs
     * the results.
     * @param t the trainer being attacked
     */
    public void Attack (Trainer target)
    {   
    	
    	double attack;
    	double defense;
        String[] pokeType = target.getActivePokemon().getType();
        Move move = activePokemon.getActiveMove();     
           
        // If the trainer chose to swap Pokemon rather than attack.
        if (move == null) {
        	System.out.println("The trainer has swapped to " + activePokemon.getName() + "!\n");
        	return;
        }
        
        // If the move is not an attack move
        if (move.getDamage() == 0) {
        	move.useEffect(this, target);
        	move.setPP(move.getPP() - 1);
        	return;
        }
        
        if (move.isPhysical()) {
        	attack = activePokemon.getAttack();
            defense = target.getActivePokemon().getDefense();
        }
        else {
        	attack = activePokemon.getSpAttack();
        	defense = target.getActivePokemon().getSpDefense();         			
        }
        
        int base = move.getDamage();
        String[] positive = move.getPosEffects();
        String[] negative = move.getNegEffects();
        String zero = move.getZeroEffects();
        
        // Calculate calculationTwo (before type effectiveness)
        int calculationOne = (int) Math.round(((22 * base * (attack/defense))/50.0 + 2));    
        int calculationTwo = calculationOne;
        
        move.setPP(move.getPP() - 1);
        
        for (int i = 0; i < 2; i++) {
        	// Same type attack bonus (STAB)
            if (activePokemon.getType()[i] == move.getType()) {
            	calculationTwo = (int) Math.round(calculationTwo * 1.5);
            }
        }     
        
        // TODO: Consolidate to separate method (isEffective())
        for (int j = 0; j < 2; j++) {
        	 // Multiply calculationTwo by 2 for every positive match
            for (int i = 0; i < positive.length; i++){
                if ( positive[i].equals(pokeType[j]) )
                {      
                    calculationTwo = calculationTwo * 2;
                }
            }
            
            // Divide calculationTwo by 2 for every negative match made
            for (int i = 0; i < negative.length; i++){
                if ( negative[i].equals(pokeType[j]) )
                {
                    calculationTwo = calculationTwo/2;
                }
            }        
            
            // If the move type does not work against the pokemon type at all
            if (pokeType[j].equals(zero)){
                calculationTwo = 0;
            }
        }   
        
        boolean crit = move.isCritical();
        if (crit) {
        	calculationTwo = calculationTwo * 2;
        }
        
        // Burn reduces physical calculationTwo by half
        if (activePokemon.getStatus() != null && move.isPhysical() && activePokemon.getStatus().equals("burn")) {
        	calculationTwo = (int) Math.round(calculationTwo/2.0);
        }
        
        int damage = calculationTwo;
        if (damage > target.getActivePokemon().getHealth()) {
        	damage = target.getActivePokemon().getHealth();
        }
        
        // Check if the attack has landed
        // TODO: Move above damage calculationTwo to save resources if the move misses
        if (move.hit()){
            System.out.println(activePokemon.getName() + " used " + move.getName() + "!");
            
            // Applys an attack's effect (if it has one) 
            if (move.hasEffect()) {
            	move.applyEffect(this,target,damage);
            }
           	
            // Super Effective
            if ((!crit && calculationTwo >= (int) Math.round(1.6 * calculationOne)) 
            		|| (crit && calculationTwo >= (int) Math.round(3.2 * calculationTwo))){
                System.out.print("It's super effective!");      
                if (crit) {
                	System.out.print(" A critical hit!");
                }
                System.out.println();
                System.out.println(target.getActivePokemon().getName() + " took " + damage + " damage!");
                if (move.getMessage() != null) {
                	move.printMessage();
                	move.resetMessage();
                }
                System.out.println();
                
            }
            // Not at all effective
            else if (damage == 0){
                System.out.println("But it didn't work!");
                System.out.println();
            }
            // Not very effective
            // TODO: Don't display when pokemon is burned and physical damage is halved
            else if (calculationTwo < calculationOne){
                System.out.println("It's not very effective...");
                System.out.println(target.getActivePokemon().getName() + " took " + damage + " damage!\n");
                if (move.getMessage() != null) {
                	move.printMessage();
                	move.resetMessage();
                }
                System.out.println();
            }
            // Normal effectiveness
            else {
            	System.out.println(target.getActivePokemon().getName() + " took " + damage + " damage!\n");
            	if (move.getMessage() != null) {
                	move.printMessage();
                	move.resetMessage();
                }
                System.out.println();
            }
              
            target.getActivePokemon().setHealth(target.getActivePokemon().getHealth() - damage);
                    
            // If the target pokemon has fainted
            if (target.getActivePokemon().getHealth() <= 0)
            {
                System.out.println(target.getActivePokemon().getName() + " has fainted!");
                if (target.canContinue()) {
                	System.out.println("Select another Pokemon from your party.");
                	System.out.println();
                	target.selectPokemon();
                	target.setCanAttack(false);
                	System.out.println("Let's go, " + target.getActivePokemon().getName() + "!");
                
                }
                else {
                	battleEnded();
                }
            }
            
            // When a fire move is used on a frozen opponent
            if (target.getActivePokemon().getStatus() != null && 
            		target.getActivePokemon().getStatus().equals("frozen") && move.getType().equals("fire")) {
            	System.out.println(target.getActivePokemon().getName() + " thawed out!");
            	System.out.println();
            	target.getActivePokemon().setStatus(null);
            }
            
            // If the attacking Pokemon has fainted from recoil 
            if (getActivePokemon().getHealth() <= 0)
            {
                System.out.println(getActivePokemon().getName() + " has fainted!");
                if (canContinue()) {
                	System.out.println("Select another Pokemon from your party.");
                	System.out.println();
                	selectPokemon();
                	setCanAttack(false);
                	System.out.println("Let's go, " + getActivePokemon().getName() + "!");
                
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
    
    /**
     * Handles an event where a trainer is unable to continue battling
     * and the battle must end.
    */
    public static void battleEnded(){
    	// TODO: Add names for trainers??
        System.out.println("The battle has ended!");
        System.exit(0);
    }
	
}
