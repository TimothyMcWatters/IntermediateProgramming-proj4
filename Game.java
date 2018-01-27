import java.util.Random;

/**
This program:
Allows users (Players) to play a trivia game. The game will ask random trivia questions,
evaluate their answers and keep score. The program also has an administrative module that
allows for managing the question bank. Question bank management includes adding new
questions, deleting questions, and displaying all the questions, answers and point values.

@author Timothy McWatters
@version 1.0

COP3022    Project 4
File Name: Game.java
*/

public class Game {
	static final int NUM_OF_QUESTIONS_PER_GAME = 5; // package
	private int nextQuestion = 0;
	private Question[] gameQuestions = null;
	
	/**
	 * constructor for the Game class
	 */
	public Game() {
		gameQuestions = new Question[NUM_OF_QUESTIONS_PER_GAME];
	} // end of Game constructor
	
	/**
	 * Populates the questions to be used in the game attempt
	 * @param questionBank = the question bank to populate game questions from
	 */
	public void populateGameQuestions(QuestionAdmin questionBank) { 
		Random random = new Random();
		for (int i = 0; i < NUM_OF_QUESTIONS_PER_GAME; i++) {
				int randomQuestion = random.nextInt(questionBank.numOfQuestions());
				gameQuestions[i] = questionBank.questions[randomQuestion];
			}
	} // end of populateGameQuestions method
	
	/**
	 * Returns the questions to be used in the game attempt
	 * @return question = the next question to be used in this game attempt
	 */
	public Question getNextGameQuestion() {
		return gameQuestions[nextQuestion++];
	} // end of getNextGameQuestion method
	
	/**
	 * Returns a boolean representation of if the answer given matches the answer
	 * for the Question object being asked.
	 * @return boolean = returns true if the answer matches the Questions answer 
	 * (case insensitive), false if not
	 */
	public boolean isAnswerCorrect(Question question, String answer) {
		if (question.getAnswer().equalsIgnoreCase(answer)) {
			return true;
		} else {
		return false;
		}
	} // end of isAnswerCorrect method
	
} // end of Game class
