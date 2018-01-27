/**
This program:
Allows users (Players) to play a trivia game. The game will ask random trivia questions,
evaluate their answers and keep score. The program also has an administrative module that
allows for managing the question bank. Question bank management includes adding new
questions, deleting questions, and displaying all the questions, answers and point values.

@author Timothy McWatters
@version 1.0

COP3022    Project 4
File Name: QuestionPointValueError.java
*/

public class QuestionPointValueError extends Exception {

	/**
	 * Default constructor for the QuestionPointValueError class
	 */
	public QuestionPointValueError() {
		super("ERROR: The point value needs to be 1 - 5 inclusive.");
	} // end of default QuestionPointValueError constructor
	
	/**
	 * Parameterized constructor for the QuestionPointValueError class
	 */
	public QuestionPointValueError(String message) {
		super(message);
	} // end of parameterized QuestionPointValueError constructor
	
} // end of QuestionPointValueError class

