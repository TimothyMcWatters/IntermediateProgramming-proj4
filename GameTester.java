import java.io.IOException;
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
File Name: GameTester.java
*/

public class GameTester {

	public static void main(String[] args) throws IOException {
		//Random access file containing the question bank
		String questionBankFileName = "UTFQuestions.dat";
		
		Scanner keyboard = new Scanner(System.in).useDelimiter("\n|[\r\n]+");
		
		// Populate the question bank from the random access file (file name specified above)
		QuestionAdmin questionBank = new QuestionAdmin(questionBankFileName);
		questionBank.populateQuestionBank();
		
		System.out.println("Welcome to Tims Wild Trivia Game!!");
		boolean mainMenuAgain = false;
		do {
			mainMenuAgain = false;
			System.out.println("\nEnter '1' to play, '2' for the Question Admin Menu, or '3' to quit the program: ");
			int menuOption = keyboard.nextInt();
			if (menuOption == 1) {
				boolean again = false;
				do {
					again = false;
					PlayGame game = new PlayGame();
					Player player = game.selectPlayer();
					System.out.println(player.toString());
					game.startTheGame(player, questionBank);
					System.out.println("\nEnter '1' for the next player to take a turn, or '2' to return to the main menu: ");
					menuOption = keyboard.nextInt();
					if (menuOption == 1) {
						again = true;
					} else {
						again = false;
						mainMenuAgain = true;
					}
				} while (again);
			} else if (menuOption == 2) {
				questionBank.questionMenu();
				mainMenuAgain = true;
				continue;
			} else {
				System.out.println("You have selected to quit the program, thanks for playing!");
				mainMenuAgain = false;
			}
		} while (mainMenuAgain);
		keyboard.close();
	} // end of main method

} // end of GameTester class
