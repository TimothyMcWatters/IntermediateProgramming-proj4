import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
This program:
Allows users (Players) to play a trivia game. The game will ask random trivia questions,
evaluate their answers and keep score. The program also has an administrative module that
allows for managing the question bank. Question bank management includes adding new
questions, deleting questions, and displaying all the questions, answers and point values.

@author Timothy McWatters
@version 1.0

COP3022    Project 4
File Name: PlayGame.java
*/

public class PlayGame {
	Scanner keyboard = new Scanner(System.in).useDelimiter("\n|[\r\n]+");
	
	/**
	 * Default constructor for the PlayGame class
	 */
	public PlayGame() {
	
	} // end of default constructor for the PlayGame class
	
	/**
	 * Prompts the player if they are new or existing, and then either creates a new Player
	 * or recalls that players information from a serialized file
	 * @return player = the player selected or created
	 */
	public Player selectPlayer() {
		boolean again = false; 
		do {
			again = false;
			System.out.println("Enter '1' if you are a new player, or '2' if you are a returning player: ");
			int playerAnswer = keyboard.nextInt();
			if (playerAnswer == 1) {
				Player player = newPlayer();
				return player;
			} else if (playerAnswer == 2) {
				Player player = recallPlayerFromFile();
				return player;
			} else {
				again = true;
			}
		} while (again);
		return newPlayer();
	} // end of selectPlayer method
	
	/**
	 * Prompts the player for their name, nick name, and sets the total score to 0
	 * uses the new players information to also create a serialized file to store 
	 * their info
	 * @return player = the new player's Player object
	 */
	public Player newPlayer() {
		System.out.println("Enter your player name: ");
		String playerName = keyboard.next();
		System.out.println("Enter the player's nick name: ");
		String playerNickName = keyboard.next();
		Player player = new Player(playerName, playerNickName, 0);
		savePlayerToFile(player);
		return player;
	} // end of newPlayer method

	/**
	 * Saves the players information to a serialized file
	 * @param player = the player to save to the file
	 */
	public void savePlayerToFile(Player player) {
		
		try {
			FileOutputStream fileOut = new FileOutputStream(player.getPlayerName() + ".dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(player);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved to %s.dat\n", player.getPlayerName());
		} catch (IOException i) {
			i.printStackTrace();
		}
	} // end of savePlayerToFile method
	
	/**
	 * Prompts the player for their player name, and then
	 * recalls the players information from a serialized file and returns the Player object
	 * @return player = The Player object stored in the serialized file
	 */
	public Player recallPlayerFromFile() {
		boolean again = false;
		Player player = null;
		do {
			again = false;
			System.out.println("Enter your player name: ");
			String playerName = keyboard.next();
			try {
				FileInputStream fileIn = new FileInputStream(playerName + ".dat");
				ObjectInputStream input = new ObjectInputStream(fileIn);
				player = (Player) input.readObject();
				input.close();
				fileIn.close();
			} catch (IOException i) {
				System.out.println("File not found.");
				again = true;
			} catch (ClassNotFoundException c) {
				System.out.println("Player class not found.");
				again = true;
			}
		} while (again);
		return player;
	} // end of recallPlayerFromFile method
	
	/**
	 * Generates an instance of Game to play
	 * @return game = The Game to play
	 */
	private Game generateGame() {
		Game game = new Game();
		return game;
	} // end of generateGame method
	
	/**
	 * Starts a Game to play
	 * @param player = The player playing the game
	 * @param questionBank = the question bank to populate game questions from
	 */
	public void startTheGame(Player player, QuestionAdmin questionBank) {
		int currentGameScore = 0;
		boolean again = false;
		do {
			again = false;
			Game game = generateGame();
			game.populateGameQuestions(questionBank);
			for (int i = 0; i < Game.NUM_OF_QUESTIONS_PER_GAME; i++) {	
				System.out.printf("\nQuestion %d\n", i + 1);
				Question gameQuestion = game.getNextGameQuestion();
				System.out.println(gameQuestion.getQuestion());
				String answer = keyboard.next().trim();
				if (game.isAnswerCorrect(gameQuestion, answer)) {
					System.out.println("That is correct!");
					System.out.printf("You earned %d point(s) for this question.\n", gameQuestion.getValue());
					currentGameScore += gameQuestion.getValue();
					player.addToPlayerTotalScore(gameQuestion.getValue());
					System.out.printf("Your score is %d\n", currentGameScore);
				} else {
					System.out.println("That is wrong!");
					System.out.printf("The correct answer is: %s\n", gameQuestion.getAnswer());
					System.out.printf("Your score remains %d\n", player.getPlayerTotalScore());
				}
			}
			System.out.println("\nTo play again type '1', or type '2' to quit this players turn.");
			int playAgain = keyboard.nextInt();
			if (playAgain == 1) {
				again = true;
			} else {
				again = false;
			}
		} while (again);
		System.out.printf("%s, your game score was: %d, and total score is: %d! \n", player.getPlayerNickName(), currentGameScore, player.getPlayerTotalScore() );
		savePlayerToFile(player);
	} // end of startTheGame method

} // end of PlayGame class