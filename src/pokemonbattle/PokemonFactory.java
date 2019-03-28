package pokemonbattle;

// TODO: Create Pokemon Factory
class PokemonFactory{

	// charizard from scratch
	public Pokemon Charizard() {
		return new Pokemon("Charizard",6,new Type[]{Type.FIRE,Type.FLYING},
                                            360,348,293,295,295,328,charMoves);
	}

}
