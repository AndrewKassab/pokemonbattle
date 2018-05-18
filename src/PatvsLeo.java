
import java.util.Scanner;
import pokemonbattle.PokemonBattle;
import static pokemonbattle.PokemonBattle.*;

/**
 * Battle between Patrick and Leo
 * @author PreciseMotion
 * @version 1.0
 */
public class PatvsLeo {

    public static void main(String[] args) throws InterruptedException {
        
        Scanner keyboard = new Scanner(System.in);
           
        Move patOne = new Move("Apologize", "normal", 10, true);
        Move patTwo = new Move("Being gay", "normal", 20, false);
        Move patThree = new Move("Show dank memes", "normal", 25, false);
        Move patFour = new Move("Kills self", "normal", 0, true);
        
        Move leoOne = new Move("Rope and stool", "normal", 10, true );
        Move leoTwo = new Move("Being gay", "normal", 20, false);
        Move leoThree = new Move("Self Loathing", "normal",25,false);
        Move leoFour = new Move("Does Nothing","normal", 0, true);        
           
        int pokeOneNumber;
        int pokeTwoNumber;        
        Move pokeOneChoice = null;
        Move pokeTwoChoice = null;
         
        Move firstChoice;
        Move secondChoice;
        Pokemon first;
        Pokemon second;
        
        Move[] patrickMoves = new Move[]{patOne,patTwo,patThree,patFour};
        Move[] leoMoves = new Move[]{leoOne,leoTwo,leoThree,leoFour};
        
        Pokemon patrick = new Pokemon("Patrick", 150,"normal",1,patrickMoves);
        Pokemon leo = new Pokemon("Leo",150,"normal",1,leoMoves);
        
        // Battle loop
        do{
           
            boolean cont;
            do{
                cont = true;
                System.out.println("Select a move for " + patrick.getName() + ": (Select by index #)"); 
                for (int i = 0; i < 4; i++){
                    System.out.println((i+1) + ": " + patrickMoves[i].getName()); 
                }
                System.out.print("Selection: ");
                try{
                    pokeOneNumber = keyboard.nextInt() - 1;
                    pokeOneChoice = patrickMoves[pokeOneNumber];
                    System.out.println();
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Invalid index number, please try again\n");
                    cont = false;
                }
            } while (!cont); 
        
            do{
                cont = true;
                System.out.println("Select a move for " + leo.getName() + ": (Select by index #)");
                for (int i = 0; i < 4; i++){
                    System.out.println((i+1) + ": " + leoMoves[i].getName());
                }
                System.out.print("Selection: ");
                try{
                    pokeTwoNumber = keyboard.nextInt() - 1;
                    pokeTwoChoice = leoMoves[pokeTwoNumber];
                    System.out.println();
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("Invalid index number, please try again\n");
                    cont = false;
                }
            } while (!cont);
 
            first = whosFirst(patrick,leo,pokeOneChoice,pokeTwoChoice);
            if (first.getName().equals(patrick.getName())){
                second = leo;
                firstChoice = pokeOneChoice;
                secondChoice = pokeTwoChoice;
            }
            else{
                second = patrick;
                firstChoice = pokeTwoChoice;
                secondChoice = pokeOneChoice;
            }
            
            Attack(firstChoice, first, second);
            Thread.sleep(3000);
            Attack(secondChoice, second, first);
            Thread.sleep(3000);
            
            if (first.getName().equals(patrick.getName())){
                patrick = first;
                leo = second;
            }
            else{
                leo = first;
                patrick = second;
            }
            
            displayHealth(patrick,leo);
            
        } while (patrick.getHealth() > 0 && leo.getHealth() > 0);
        
    }
    
}
