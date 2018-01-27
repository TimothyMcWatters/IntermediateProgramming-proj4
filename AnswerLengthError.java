/**
This program:
Allows users (Players) to play a trivia game. The game will ask random trivia questions,
evaluate their answers and keep score. The program also has an administrative module that
allows for managing the question bank. Question bank management includes adding new
questions, deleting questions, and displaying all the questions, answers and point values.

@author Timothy McWatters
@version 1.0

COP3022    Project 4
File Name: AnswerLengthError.java
*/

public class AnswerLengthError extends Exception {

	/**
	 * Default constructor for the AnswerLengthError class
	 */
	public AnswerLengthError() {
		super("ERROR: The answer supplied exceeds the maximum 20 byte limit.");
	} // end of default AnswerLengthError constructor
	
	/**
	 * Parameterized constructor for the AnswerLengthError class
	 */
	public AnswerLengthError(String message) {
		super(message);
	} // end of parameterized AnswerLengthError constructor
	
} // end of AnswerLengthError class
