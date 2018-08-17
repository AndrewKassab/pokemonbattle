package pokemonbattle;

import java.util.Scanner;

/**
 * The main class. This class contains the methods and objects to handle 
 * a battle. There will be two trainers in the battle and the 
 * user will take control of both trainer's partys / pokemon. 
 * TODO: Consolidate code in main to separate methods ( Game class ).
 * TODO: BUG: Dark pulse did not do anything to Infernape?? 
 * TODO: BUG: Infernape at 0 health and didn't faint but match ended?
 * @version 7.0
 * @author Andrew Kassab
 */
public class Battle 
{   
    
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
    	int pokemonOneSpeed = pa.getSpeed();
    	int pokemonTwoSpeed = pb.getSpeed();
    	
    	// Paralysis lowers speed by 1/2
    	if (pa.getStatus() != null && pa.getStatus().equals("paralysis")) {
    		pokemonOneSpeed = (int) Math.round(pokemonOneSpeed/2.0);
    	}
    	if (pb.getStatus() != null && pb.getStatus().equals("paralysis")) {
    		pokemonTwoSpeed = (int) Math.round(pokemonTwoSpeed/2.0);
    	}
    	
    	if ( ma == null ) {
    		return 1;
    	}
    	else if (mb == null ) {
    		return 2;
    	}
    	else if ( ma.getPriority() > mb.getPriority()){
            return 1;
        }
        else if ( mb.getPriority() > ma.getPriority()){
            return 2;
        }
        else if (pokemonOneSpeed > pokemonTwoSpeed){
            return 1;
        }
        else if (pokemonTwoSpeed > pokemonOneSpeed){
            return 2;
        }
      
        // Randomize in the event of a matching speed and priority case.
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
     * Main method, creates two trainers, their party pokemon, with their
     * respective Moves and begins the battle
     * @param args.
     * @throws InterruptedException Thread sleeps for user to process results
     */
    public static void main(String[] args) throws InterruptedException {
        
        Scanner keyboard = new Scanner(System.in);
        
        Move ultimate = new Move("InstantKO","fire",10000,100,99,5,true,true,false); // FILLER FOR TESTING
        
        Move fireBlast = new Move("Fire Blast","fire",110,85,5,0,false,true,true); // May burn
        Move flareBlitz = new Move("Flare Blitz","fire",120,100,15,0,true,false,true); // Has Recoil.
        Move will_O_Wisp = new Move("Will-O-Wisp","fire",0,85,15,0,false,true,true); // Burns Enemy
        
        Move hydroPump = new Move("Hydro Pump","water",110,80,5,0,false,true,false);	
        Move aquaJet = new Move("Aqua Jet","water",40,100,20,1,true,false,false); // priority +1
        
        Move solarBeam = new Move("Solar Beam", "grass", 120,100,10,0,false,true,false); // TODO: Charges for 1 turn
        Move leafBlade = new Move("Leaf Blade","grass",90,100,15,0,true,false,false);
        
        Move closeCombat = new Move("Close Combat","fighting",120,100,5,0,true,false,true); // Lowers user's attack
        Move focusBlast = new Move("Focus Blast","fighting",120,70,5,0,false,true,true); // May lower SpDef
        
        Move thunderPunch = new Move("Thunder Punch","electric",75,100,15,0,true,false,false); // May Paralyze
        Move thunderbolt = new Move("Thunderbolt","electric",90,100,15,0,false,true,false); // May Paralyze
        Move voltSwitch = new Move("Volt Switch","electric",70,100,20,0,true,false,true); // Allows user to switch Pokemon
        Move thunderWave = new Move("Thunder Wave","electric",0,90,20,0,false,true,true); // Paralyzes opponent
       
        Move shadowBall = new Move("Shadow Ball","ghost",80,100,15,0,false,true,true); // May lower spDefense
        
        Move bulletPunch = new Move("Bullet Punch","bug", 40,100,30,1,true,false,false); // priority +1
        Move uTurn = new Move("U-turn","bug",70,100,20,0,true,false,true); // Allows user to switch Pokemon
        
        Move darkPulse = new Move("Dark Pulse", "dark", 80,100,15,0,false,true,false); // TODO: May cause Flinch.
        Move crunch = new Move("Crunch","dark",80,100,15,0,true,false,true); // May lower defense
        
        Move zenHeadbutt = new Move("Zen Headbutt","psychic",80,90,15,0,true,false,false);
        Move rest= new Move("Rest","psychic",0,100,10,0,false,false,true); // Heals to max and puts pokemon to sleep
        
        Move iceBeam = new Move("Ice Beam","ice",90,100,10,0,false,true,true); // May Freeze
        
        Move bodySlam = new Move("Body Slam","normal",85,100,15,0,true,false,true); // May Paralyze
        Move swordsDance = new Move("Swords Dance","normal",0,100,20,0,false,false,true); // Raises Attack / SpAttack stats
        Move recover = new Move("Recover","normal",0,100,20,0,false,false,true); // Heals user by half of max HP
        
        // TODO: Create independent instances for the same move for different pokemon.
        Move earthquake = new Move("Earthquake","ground",100,100,10,0,true,false,false); 
        Move earthPower = new Move("Earth Power","ground",90,100,10,0,false,true,true); // May lower spDef
        
        Move meteorMash = new Move("Meteor Mash", "steel", 90,90,10,0,true,false,false);
        
        Move sludgeWave = new Move("Sludge Wave","poison",95,100,10,0,false,true,true); // May poison
        Move toxic = new Move("Toxic","poison",0,85,10,0,false,true,true); // Badly poisons enemy
        
        Move stoneEdge = new Move("Stone Edge","rock",100,80,5,0,true,false,false);
        
        Move roost = new Move("Roost","fly",0,100,10,0,false,false,true); // Heals the user half of max HP
        
        Move[] venuMoves = new Move[] {earthquake,solarBeam,ultimate,ultimate};
        Move[] charMoves = new Move[] {flareBlitz,earthquake,roost,ultimate};
        Move[] blasMoves = new Move[] {hydroPump,iceBeam,darkPulse,ultimate};
        Move[] gengMoves = new Move[] {shadowBall,sludgeWave,focusBlast,toxic};
        Move[] joltMoves = new Move[] {thunderbolt,shadowBall,voltSwitch,thunderWave};
        Move[] snorMoves = new Move[] {bodySlam,earthquake,thunderPunch,rest};
        Move[] scizMoves = new Move[] {bulletPunch,uTurn,swordsDance,roost};
        Move[] tyranMoves = new Move[] {earthquake,stoneEdge,crunch,ultimate};
        Move[] metaMoves = new Move[] {zenHeadbutt,earthquake,meteorMash,ultimate};
        Move[] inferMoves = new Move[] {flareBlitz,closeCombat,swordsDance,will_O_Wisp};
        Move[] lucMoves = new Move[] {closeCombat,meteorMash,swordsDance,ultimate};
        Move[] grenMoves = new Move[] {hydroPump,iceBeam,darkPulse,ultimate};
        
        Pokemon venusaur = new Pokemon("Venusaur",3,new String[] {"grass",""},364,289,291,328,328,284,venuMoves);
        Pokemon charizard = new Pokemon("Charizard",6,new String[]{"fire","fly"},360,348,293,295,295,328,charMoves); 
        Pokemon blastoise = new Pokemon("Blastoise",9,new String[] {"water",""},362,291,328,295,339,280,blasMoves);
        Pokemon gengar = new Pokemon("Gengar",94,new String[] {"ghost","poison"},324,251,240,294,273,350,gengMoves);
        Pokemon jolteon = new Pokemon("Jolteon",135,new String[] {"electric",""},334,251,240,350,317,394,joltMoves);
        Pokemon snorlax = new Pokemon("Snorlax",143,new String[] {"normal",""},524,350,251,251,250,174,snorMoves);
        Pokemon scizor = new Pokemon("Scizor",212,new String[] {"grass",""},344,394,328,229,284,251,scizMoves);
        Pokemon tyranitar = new Pokemon("Tyranitar",248,new String[]{"rock","dark"},404,403,350,317,328,243,tyranMoves);
        Pokemon metagross = new Pokemon("Metagross",376,new String[] {"psychic","steel"},364,405,394,317,306,262,metaMoves);
        Pokemon infernape = new Pokemon("Infernape",392,new String[] {"fire","fighting"},356,337,265,337,265,346,inferMoves);
        Pokemon lucario = new Pokemon("Lucario",448,new String[] {"fighting","steel"},344,350,262,361,262,306,lucMoves);
        Pokemon greninja = new Pokemon("Greninja",658,new String[] {"water","dark"},348,317,356,335,265,377,grenMoves);
      
        // Creating the parties for each trainer
        Pokemon[] partyOne = new Pokemon[] {charizard,greninja,lucario,jolteon,venusaur,snorlax};
        Pokemon[] partyTwo = new Pokemon[] {infernape,blastoise,metagross,gengar,scizor,tyranitar};
        
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
            	trainerOne.getActivePokemon().applyPreStatus(trainerOne); 
            	if (trainerOne.canAttack()) { // If paralysis, sleep, or freeze didn't stop the turn.
            		trainerOne.Attack(trainerTwo);
            	}
            	Thread.sleep(3000);
            	if (trainerTwo.canAttack()) {
            		trainerTwo.getActivePokemon().applyPreStatus(trainerTwo);
            		if (trainerTwo.canAttack()) { // If paralysis, sleep, or freeze didn't stop the turn.
            			trainerTwo.Attack(trainerOne);
            		}   	
            	}
            }
            else { // If trainer two attacks first
            	trainerTwo.getActivePokemon().applyPreStatus(trainerTwo); 
            	if (trainerTwo.canAttack()) { // If paralysis, sleep, or freeze didn't stop the turn.
            		trainerTwo.Attack(trainerOne);
            	}
            	Thread.sleep(3000);
            	if (trainerOne.canAttack()) {
            		trainerOne.getActivePokemon().applyPreStatus(trainerOne);
            		if (trainerOne.canAttack()) { // If paralysis, sleep, or freeze didn't stop the turn.
            			trainerOne.Attack(trainerTwo);
            		}
            	}
            }
            
            trainerOne.getActivePokemon().applyPostStatus(trainerOne);
        	trainerTwo.getActivePokemon().applyPostStatus(trainerTwo);

        	trainerTwo.setCanAttack(true);
        	trainerOne.setCanAttack(true);
        	
            // Print out health status
            displayHealth(trainerOne.getActivePokemon(),trainerTwo.getActivePokemon());
            
            // Loop continues as long as both Pokemon are still alive
        } while (trainerOne.canContinue() && trainerTwo.canContinue());
        
    }
    
}
