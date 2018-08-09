package pokemon;

import pokemonbattle.PokemonBattle.Move;
import pokemonbattle.PokemonBattle.Pokemon;

/**
 * A class containing a database of Pokemon, separated by type. 
 * Pokemon should be structured as follows:
 * Move(NAME, TYPE(S)[], HEALTH, ATTACK, DEFENSE, SPECIAL ATTACK, SPECIAL DEFENSE, SPEED, MOVESET[]) 
 * @author PreciseMotion
 */
public class PokemonData {

	public static class Fire{	
		
		public static Pokemon Charizard(Move[] moves) {
			Pokemon charizard = new Pokemon("Charizard",new String[]{"fire","flying"},360,348,293,295,295,328,moves);
			return charizard;
		}	
		
		public Pokemon Infernape(Move[] moves) {
			Pokemon infernape = new Pokemon("Infernape",new String[] {"fire","fighting"},356,337,265,337,265,346,moves);
			return infernape;
		}
	}
	
	public class Water{	
		
		public Pokemon Blastoise(Move[] moves) {
			Pokemon blastoise = new Pokemon("Blastoise",new String[] {"water",""},362,291,328,295,339,280,moves);
			return blastoise;
		}
		
		public Pokemon Greninja(Move[] moves) {
			Pokemon greninja = new Pokemon("Greninja",new String[] {"water","dark"},348,317,356,335,265,377,moves);
			return greninja;
		}
	}
	
	public class Fighting{
		
		public Pokemon Lucario(Move[] moves) {
			Pokemon lucario = new Pokemon("Lucario",new String[] {"fighting","steel"},344,350,262,361,262,306,moves);
			return lucario;
		}	
	}
	
	public class Steel{
		
		public Pokemon Metagross(Move[] moves) {
			Pokemon metagross = new Pokemon("Metagross",new String[] {"psychic","steel"},364,405,394,317,306,262,moves);
			return metagross;
		}	
	}
	
	public class Grass{
		
		public Pokemon Venusaur(Move[] moves) {
			Pokemon venusaur = new Pokemon("Venusaur",new String[] {"grass",""},364,289,291,328,328,284,moves);
			return venusaur;
		}
	}
	
	public class Bug{
		
		public Pokemon Scizor(Move[] moves) {
			Pokemon scizor = new Pokemon("Scizor",new String[] {"grass",""},344,394,328,229,284,251,moves);
			return scizor;
		}
	}
	
	public class Ghost{
		
		public Pokemon Gengar(Move[] moves) {
			Pokemon gengar = new Pokemon("Gengar",new String[] {"ghost","poison"},324,251,240,294,273,350,moves);
			return gengar;
		}
	}
	
	public class Normal{
		
		public Pokemon Snorlax(Move[] moves) {
			Pokemon snorlax = new Pokemon("Snorlax",new String[] {"normal",""},524,350,251,251,250,174,moves);
			return snorlax;
		}
	}
	
	public class Electric{
		
		public Pokemon Jolteon(Move[] moves) {
			Pokemon jolteon = new Pokemon("Jolteon",new String[] {"electric",""},334,251,240,350,317,394,moves);
			return jolteon;
		}
	}
}
