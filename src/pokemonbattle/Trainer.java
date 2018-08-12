package pokemonbattle;

import java.util.Scanner;

/**
 * Class to create and initialize the Trainer object. 
 * A trainer has a party of Pokemon, and an identifier for which
 * Pokemon is currently active and in battle. 
 * @version 5.1
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
        
        // Calculate damage (before type effectiveness)
        double calculation = ((22 * base * (attack/defense))/50 + 2);    
        int damage = (int) Math.round(calculation);
        
        move.setPP(move.getPP() - 1);
        
        for (int i = 0; i < 2; i++) {
        	// Same type attack bonus (STAB)
            if (activePokemon.getType()[i] == move.getType()) {
            	damage = (int) Math.round(damage * 1.5);
            }
        }     
        
        // TODO: Consolidate to separate method (isEffective())
        for (int j = 0; j < 2; j++) {
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
            
            // If the move type does not work against the pokemon type at all
            if (pokeType[j].equals(zero)){
                damage = 0;
            }
        }   
        
        boolean crit = move.isCritical();
        if (crit) {
        	damage = damage * 2;
        }
        
        // Check if the attack has landed
        if (move.hit()){
            System.out.println(activePokemon.getName() + " used " + move.getName() + "!");
            
            // Applys an attack's effect (if it has one) 
            if (move.hasEffect()) {
            	move.applyEffect(this,target,damage);
            }
           	
            // Super Effective
            if ((!crit && damage >= 1.6 * calculation) || (crit && damage >= 3.2 * calculation)){
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
            else if (damage < ((22 * base * (attack/defense))/50 + 2)){
                System.out.println("It's not very effective...");
                System.out.println(target.getActivePokemon().getName() + " took " + damage + " damage!\n");
                if (move.getMessage() != null) {
                	move.printMessage();
                	move.resetMessage();
                }
                System.out.println();
            }
            else {
            	System.out.println(target.getActivePokemon().getName() + " took " + damage + " damage!\n");
            	if (move.getMessage() != null) {
                	move.printMessage();
                	move.resetMessage();
                }
                System.out.println();
            }
              
            target.getActivePokemon().setHealth(target.getActivePokemon().getHealth() - damage);
            
            // Prevent negative health values
            if (target.getActivePokemon().getHealth() < 0) {
            	target.getActivePokemon().setHealth(0);
            }
                    
            // If the pokemon has fainted
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
