/**
This program:
Allows users (Players) to play a trivia game. The game will ask random trivia questions,
evaluate their answers and keep score. The program also has an administrative module that
allows for managing the question bank. Question bank management includes adding new
questions, deleting questions, and displaying all the questions, answers and point values.

@author Timothy McWatters
@version 1.0

COP3022    Project 4
File Name: QuestionLengthError.java
*/

public class QuestionLengthError extends Exception {

	/**
	 * Default constructor for the QuestionLengthError class
	 */
	public QuestionLengthError() {
		super("ERROR: The question supplied exceeds the maximum 50 byte limit.");
	} // end of default QuestionLengthError constructor
	
	/**
	 * Parameterized constructor for the QuestionLengthError class
	 */
	public QuestionLengthError(String message) {
		super(message);
	} // end of parameterized QuestionLengthError constructor
	
} // end of QuestionLengthError class
