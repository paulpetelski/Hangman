import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 * Paul Petelski
 *
 * Hangman
 *
 * Guess the word before you run out of tries
 *
 * Drawing of hangman
 *
 *   __________             top of pole 10 lines ---- 3 spaces before each pole  12 poles
 *   |       -----
 *   |      |  - - |         head
 *   |      |   _  |
 *   |       -----
 *   |          |
 *   |        / | \         arms
 *   |       /  |  \        body
 *   |          |
 *   |          |
 *   |         / \          legs
 *   |        /   \
 *   |
 * -----            5 lines bottom of pole
 *
 *
 */

public class Hangman {
    /* SETUP */

    // array of words to be chosen
    private static String[] listOfWords = {"apple", "banana", "cookie", "drink"};

    // number of wrong guesses from 0-5
    // increases for incorrect guesses
    private static int numberOfWrongGuesses = 0;

    // create random variable
    private static final Random random = new Random();

    // randomNumber picks a random number with the length of the list as ceiling
    private static int randomNumber = random.nextInt(listOfWords.length);

    // the word chosen to guess using the random number
    private static String chosenWord = listOfWords[randomNumber];

    // replaces each letter with an underscore ex. apple will be _____ and car will be ___
    private static String dash = new String(new char[chosenWord.length()]).replace("\0", "_");

    // runs again if user wants to play again
    private static boolean playAgain = true;

    private static String guessedLetters = "";


    // MAIN DRIVER
    public static void main(String[] args) throws FileNotFoundException {



            // print instructions
            welcomeMessage();

            // start playing the game
            playGame();

            // display stats after game is over
            stats();



    }


    /* play game */
    public static void playGame(){
        // declare variable to store letters guessed by user


        // setup scanner to take user's guess
        Scanner input = new Scanner(System.in);

        // while we didn't reach round 5 yet and the word still has dashes covering up the letter
        while (numberOfWrongGuesses < 5 && dash.contains("_")){
            // print out word with dashes replacing the letters
            System.out.println("\n" + dash+ "\n");

            // print the guessed letters after each turn in uppercase
            System.out.println("Letters guessed: " + guessedLetters.toUpperCase());

            // tell user to make a guess
            System.out.print("Guess a letter: ");

            // take input from user and save the letter to variable letterGuess
            String letterGuess = input.next();

            // only accept 1 letter and no numbers -- asks to try again if user enters too many letters or not a letter
            while(letterGuess.length() != 1 || !Character.isLetter(letterGuess.charAt(0))){
                System.out.print("Enter only one letter per guess. Try again: ");
                letterGuess = input.next();
            }

            System.out.println();

            // save guessed letter --- add it to previous letters guessed
            guessedLetters += letterGuess;


            // replace dash with letter if letter matches
            reveal(letterGuess);

        }
    }

    /* Reveal the letters if guessed correctly */

    public static void reveal(String letterGuess){
        // string that will be updated if letter is guessed correctly, then the rest of the spaces will remain as underscores
        String replaceWithLetter = "";

        // run a loop for number of letters in the word
        for (int i = 0; i < chosenWord.length(); i++){

            if (chosenWord.charAt(i) == letterGuess.charAt(0)){ // if the letter matches at position i, then replace the dash with letter
                replaceWithLetter += letterGuess.charAt(0);
            } else if (dash.charAt(i) != '_'){                  // if the space at i was previously replaced with a dash, keep that letter
                replaceWithLetter += chosenWord.charAt(i);
            } else {
                replaceWithLetter += "_";                       // if the letter is not correct or previously was not guessed, leave it as an underscore
            }
        }



        // if there are no new letters revealed after the guess, add 1 to round variable. Once round reaches 5, the game is over. Only 1 is added for wrong guesses
        if(dash.equals(replaceWithLetter)){
            numberOfWrongGuesses++;    // add 1 to round for incorrect guess
            hangmanDrawing();
        } else {
            dash = replaceWithLetter;
        }

        // if all the letters are revealed
        if (dash.equals(chosenWord)){
            System.out.println("*** You guessed all the letters, great job! ***\n\n*** The word was: " + chosenWord + " ***");
        }

        // if the user runs out of turns, by making 5 wrong guesses, print out the chosen word
        if(numberOfWrongGuesses == 5){
            System.out.println("\n*** You are out of turns....the correct word was: " + chosenWord + " ***");
        }

    }

    /* Welcome Message for user */
    public static void welcomeMessage(){
        System.out.println();
        System.out.println("=".repeat(50));
        System.out.println("Welcome! Try to guess the word!");
        System.out.println("Take turns guessing letters,\nyou will have 5 wrong attempts allowed.");
        System.out.println("=".repeat(50));
        System.out.println();
    }

    /* Display Stats at the end */
    public static void stats(){

        System.out.println("=".repeat(50));
        String title1 = "Letters Guessed";
        System.out.printf("\n%25s\n", title1);
        System.out.printf("%20s\n\n", guessedLetters.toUpperCase());
        System.out.println("=".repeat(50));

    }

    /* DRAWINGS OF THE HANGMAN*/
    // drawing depends on number of incorrect guesses
    public static void hangmanDrawing(){
        if (numberOfWrongGuesses == 1){
            System.out.println("  ----------");    // top bar
            System.out.print("  |\n".repeat(9)); // 12 poles total
            System.out.print("  |\n".repeat(3));
            System.out.println("-----");         // bottom bar
        } else if (numberOfWrongGuesses == 2){
            System.out.println("  ----------");    // top bar
            System.out.println("  |      ---------   ");
            System.out.println("  |      |  -  - |   ");
            System.out.println("  |      |    -  |   ");
            System.out.println("  |      ---------   ");
            System.out.print("  |\n".repeat(6)); // 12 poles total
            System.out.print("  |\n".repeat(3));
            System.out.println("-----");         // bottom bar
        } else if (numberOfWrongGuesses == 3) {
            System.out.println("  ----------");    // top bar
            System.out.println("  |      ---------   ");
            System.out.println("  |      |  -  - |   ");
            System.out.println("  |      |    -  |   ");
            System.out.println("  |      ---------   ");
            System.out.println("  |           |      ");
            System.out.println("  |           |      ");
            System.out.println("  |           |      ");
            System.out.println("  |           |      ");
            System.out.println("  |           |      ");
            System.out.print("  |\n".repeat(1)); // 12 poles total
            System.out.print("  |\n".repeat(3));
            System.out.println("-----");         // bottom bar
        } else if (numberOfWrongGuesses == 4){
            System.out.println("  ----------");    // top bar
            System.out.println("  |      ---------   ");
            System.out.println("  |      |  -  - |   ");
            System.out.println("  |      |    -  |   ");
            System.out.println("  |      ---------   ");
            System.out.println("  |           |      ");
            System.out.println("  |         / | \\    ");
            System.out.println("  |        /  |  \\   ");
            System.out.println("  |           |      ");
            System.out.println("  |           |      ");
            System.out.print("  |\n".repeat(1)); // 12 poles total
            System.out.print("  |\n".repeat(3));
            System.out.println("-----");         // bottom bar
        } else if (numberOfWrongGuesses == 5){
            System.out.println("  ----------");    // top bar
            System.out.println("  |      ---------   ");
            System.out.println("  |      |  -  - |   ");
            System.out.println("  |      |    -  |   ");
            System.out.println("  |      ---------   ");
            System.out.println("  |           |      ");
            System.out.println("  |         / | \\    ");
            System.out.println("  |        /  |  \\   ");
            System.out.println("  |           |      ");
            System.out.println("  |           |      ");
            System.out.println("  |          / \\      ");
            System.out.println("  |         /   \\      ");
            System.out.print("  |\n".repeat(1));
            System.out.println("-----");         // bottom bar
        }
    } // end of hangmanDrawing

}
