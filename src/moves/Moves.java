package moves;

import pokemonbattle.PokemonBattle.Move;

/**
 * A class containing a database of Pokemon Moves, separated by type. 
 * Moves should be structured as follows:
 * Move(NAME, TYPE, BASE DAMAGE, ACCURACY, POWER POINTS, PRIORITY, ISPHYSICAL, ISSPECIAL) 
 * @author PreciseMotion
 */
public class Moves {

	public static class Fire{	
		public static Move fireBlast = 
				new Move("Fire Blast","fire",110,85,5,false,false,true); // May burn
		public static Move flareBlitz = 
				new Move("Flare Blitz","fire",120,100,15,false,true,false); // Has Recoil.
	}
	
	public static class Water{	
		public static Move hydroPump = 
				new Move("Hydro Pump","water",110,80,5,false,false,true);	
		public static Move aquaJet = 
				new Move("Aqua Jet","water",40,100,20,true,true,false); // priority +1
	}
	
	public static class Grass{	
		public static Move solarBeam = 
				new Move("Solar Beam", "grass", 120,100,10,false,false,true); // Charges for 1 turn
		public static Move woodHammer = 
				new Move("Wood Hammer","grass",120,100,15,false,true,false);
		public static Move leafBlade = 
				new Move("Leaf Blade","grass",90,100,15,false,true,false);
	}
	
	public static class Ground{
		public static Move earthquake = 
				new Move("Earthquake","ground",100,100,10,false,true,false); 
		public static Move earthPower = 
				new Move("Earth Power","ground",90,100,10,false,false,true); // May lower spDef
	}
	
	public static class Ice{
		public static Move iceBeam = 
				new Move("Ice Beam","ice",90,100,10,false,false,true); // May Freeze
	}
	
	public static class Dark{
		public static Move darkPulse = 
				new Move("Dark Pulse", "dark", 80,100,15,false,false,true); // May cause Flinch.
	}
	
	public static class Fighting{
		public static Move closeCombat = 
				new Move("Close Combat","fighting",120,100,5,false,true,false); // Lowers user's attack
		public static Move focusBlast =
				new Move("Focus Blast","fighting",120,70,5,false,false,true); // May lower SpDef
	}
	
	public static class Bug{
		Move bulletPunch = 
				new Move("Bullet Punch","bug", 40,100,30,true,true,false); // priority +1
	}
	
	public static class Steel{
		public static Move meteorMash = 
				new Move("Meteor Mash", "steel", 90,90,10,false,true,false);
	}
	
	public static class Electric{
		public static Move thunderPunch = 
				new Move("Thunder Punch","electric",75,100,15,false,true,false);
		public static Move thunderbolt = 
				new Move("Thunderbolt","electric",90,100,15,false,false,true);
	}
	
	public static class Psychic{
		public static Move zenHeadbutt = 
				new Move("Zen Headbutt","psychic",80,90,15,false,true,false);
	}
	
	public static class Dragon{
		
	}
	
	public static class Flying{
		
	}
	
	public static class Ghost{
		public static Move shadowBall = 
				new Move("Shadow Ball","ghost",80,100,15,false,false,true); // May lower defense
	}
	
	public static class Normal{
		public static Move bodySlam = 
				new Move("Body Slam","normal",85,100,15,false,true,false); // May Paralyze
	}
	
	public static class Poison{
		public static Move sludgeWave = 
				new Move("Sludge Wave","poison",95,100,10,false,false,true); // May poison
	}
	
	public static class Rock{
		
	}
	
	public static class Fairy{
		
	}
	
	public static class Ultimate{
		public static Move instantKO = 
				new Move("InstantKO","ultimate",999,100,99,true,true,true); // FILLER FOR TESTING
	}
}
