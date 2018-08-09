package moves;

import pokemonbattle.PokemonBattle.Move;

/**
 * A class containing a database of Pokemon Moves, separated by type. 
 * Moves should be structured as follows:
 * Move(NAME, TYPE, BASE DAMAGE, ACCURACY, POWER POINTS, PRIORITY, ISPHYSICAL, ISSPECIAL) 
 * @author PreciseMotion
 */
public class Moves {

	class Fire{	
		Move fireBlast = new Move("Fire Blast","fire",110,85,5,false,false,true);
		Move flareBlitz = new Move("Flare Blitz","fire",120,100,15,false,true,false); // Has Recoil.
	}
	
	class Water{	
		Move hydroPump = new Move("Hydro Pump","water",110,80,5,false,false,true);	
		Move aquaJet = new Move("Aqua Jet","water",40,100,20,true,true,false); // priority +1
	}
	
	class Grass{	
		Move solarBeam = new Move("Solar Beam", "grass", 120,100,10,false,false,true); // Charges for 1 turn
		Move woodHammer = new Move("Wood Hammer","grass",120,100,15,false,true,false);
		Move leafBlade = new Move("Leaf Blade","grass",90,100,15,false,true,false);
	}
	
	class Ground{
		Move earthquake = new Move("Earthquake","ground",100,100,10,false,true,false); 
		Move earthPower = new Move("Earth Power","ground",90,100,10,false,false,true); // May lower spDef
	}
	
	class Ice{
		Move iceBeam = new Move("Ice Beam","ice",90,100,10,false,false,true); // May Freeze
	}
	
	class Dark{
		Move darkPulse = new Move("Dark Pulse", "dark", 80,100,15,false,false,true); // May cause Flinch.
	}
	
	class Fighting{
		Move closeCombat = new Move("Close Combat","fighting",120,100,5,false,true,false); // Lowers user's attack
	}
	
	class Bug{
		Move bulletPunch = new Move("Bullet Punch","bug", 40,100,30,true,true,false); // priority +1
	}
	
	class Steel{
		Move meteorMash = new Move("Meteor Mash", "steel", 90,90,10,false,true,false);
	}
	
	class Electric{
		Move thunderPunch = new Move("Thunder Punch","electric",75,100,15,false,true,false);
		Move thunderbolt = new Move("Thunderbolt","electric",90,100,15,false,false,true);
	}
	
	class Psychic{
		Move zenHeadbutt = new Move("Zen Headbutt","psychic",80,90,15,false,true,false);
	}
	
	class Dragon{
		
	}
	
	class Flying{
		
	}
	
	class Ghost{
		Move shadowBall = new Move("Shadow Ball","ghost",80,100,15,false,false,true); // May lower defense
	}
	
	class Normal{
		Move bodySlam = new Move("Body Slam","normal",85,100,15,false,true,false); // May Paralyze
	}
	
	class Poison{
		Move sludgeWave = new Move("Sludge Wave","poison",95,100,10,false,false,true); // May poison
	}
	
	class Rock{
		
	}
	
	class Fairy{
		
	}
}
