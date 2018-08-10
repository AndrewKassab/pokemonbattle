package pokemonbattle;

import java.util.Scanner;

/**
 * The main class. This class contains the methods and objects to handle 
 * a battle. There will be two trainers in the battle and the 
 * user will take control of both trainer's partys / pokemon. 
 * TODO: Add Ability class to handle Pokemon moves that have specific abilities besides dealing damage. 
 * @version 4.0
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
     * Main method, creates two trainers, their party pokemon, with their
     * respective Moves and begins the battle
     * @param args.
     * @throws InterruptedException Thread sleeps for user to process results
     */
    public static void main(String[] args) throws InterruptedException {
        
        Scanner keyboard = new Scanner(System.in);
        
        // TODO: ADD SWORDS DANCE AND U-TURN (ability class)
        
        Move ultimate = new Move("InstantKO","fire",10000,100,99,false,true,true); // FILLER FOR TESTING
        
        Move fireBlast = new Move("Fire Blast","fire",110,85,5,false,false,true); // May burn
        Move flareBlitz = new Move("Flare Blitz","fire",120,100,15,false,true,false); // Has Recoil.
        
        Move hydroPump = new Move("Hydro Pump","water",110,80,5,false,false,true);	
        Move aquaJet = new Move("Aqua Jet","water",40,100,20,true,true,false); // priority +1
        
        Move solarBeam = new Move("Solar Beam", "grass", 120,100,10,false,false,true); // Charges for 1 turn
        Move woodHammer = new Move("Wood Hammer","grass",120,100,15,false,true,false);
        Move leafBlade = new Move("Leaf Blade","grass",90,100,15,false,true,false);
        
        Move closeCombat = new Move("Close Combat","fighting",120,100,5,false,true,false); // Lowers user's attack
        Move focusBlast = new Move("Focus Blast","fighting",120,70,5,false,false,true); // May lower SpDef
        
        Move thunderPunch = new Move("Thunder Punch","electric",75,100,15,false,true,false);
        Move thunderbolt = new Move("Thunderbolt","electric",90,100,15,false,false,true);
        
        Move shadowBall = new Move("Shadow Ball","ghost",80,100,15,false,false,true); // May lower defense
        
        Move bulletPunch = new Move("Bullet Punch","bug", 40,100,30,true,true,false); // priority +1
        
        Move darkPulse = new Move("Dark Pulse", "dark", 80,100,15,false,false,true); // May cause Flinch.
        Move crunch = new Move("Crunch","dark",80,100,15,false,true,false); // May lower defense
        
        Move zenHeadbutt = new Move("Zen Headbutt","psychic",80,90,15,false,true,false);
        
        Move iceBeam = new Move("Ice Beam","ice",90,100,10,false,false,true); // May Freeze
        
        Move bodySlam = new Move("Body Slam","normal",85,100,15,false,true,false); // May Paralyze
        
        Move earthquake = new Move("Earthquake","ground",100,100,10,false,true,false); 
        Move earthPower = new Move("Earth Power","ground",90,100,10,false,false,true); // May lower spDef
        
        Move meteorMash = new Move("Meteor Mash", "steel", 90,90,10,false,true,false);
        
        Move sludgeWave = new Move("Sludge Wave","poison",95,100,10,false,false,true); // May poison
        
        Move stoneEdge = new Move("Stone Edge","rock",100,80,5,false,true,false);
        
        Move[] venuMoves = new Move[] {earthquake,solarBeam,ultimate,ultimate};
        Move[] charMoves = new Move[] {flareBlitz,earthquake,ultimate,ultimate};
        Move[] blasMoves = new Move[] {hydroPump,iceBeam,darkPulse,ultimate};
        Move[] gengMoves = new Move[] {shadowBall,sludgeWave,focusBlast,ultimate};
        Move[] joltMoves = new Move[] {thunderbolt,shadowBall,ultimate,ultimate};
        Move[] snorMoves = new Move[] {bodySlam,earthquake,thunderPunch,ultimate};
        Move[] scizMoves = new Move[] {bulletPunch,ultimate,ultimate,ultimate};
        Move[] tyranMoves = new Move[] {earthquake,stoneEdge,crunch,ultimate};
        Move[] metaMoves = new Move[] {zenHeadbutt,earthquake,meteorMash,ultimate};
        Move[] inferMoves = new Move[] {flareBlitz,closeCombat,ultimate,ultimate};
        Move[] lucMoves = new Move[] {closeCombat,meteorMash,ultimate,ultimate};
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
            	trainerOne.Attack(trainerTwo);
            	Thread.sleep(3000);
            	if (trainerTwo.canAttack()) {
            		trainerTwo.Attack(trainerOne);
            	}
            	trainerTwo.setCanAttack(true);
            	trainerOne.setCanAttack(true);
            }
            else { // If trainer two attacks first
            	trainerTwo.Attack(trainerOne);
            	Thread.sleep(3000);
            	if (trainerOne.canAttack()) {
            		trainerOne.Attack(trainerTwo);
            	}
            	trainerOne.setCanAttack(true);
            	trainerTwo.setCanAttack(true);
            }
            
            // Print out health status
            displayHealth(trainerOne.getActivePokemon(),trainerTwo.getActivePokemon());
            
            // Loop continues as long as both Pokemon are still alive
            // TODO: Make loop continue as long as the trainers have Pokemon available
        } while (trainerOne.getActivePokemon().getHealth() > 0 && 
        		trainerTwo.getActivePokemon().getHealth() > 0);
        
    }
    
}
