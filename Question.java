
/**
This program:
Allows users (Players) to play a trivia game. The game will ask random trivia questions,
evaluate their answers and keep score. The program also has an administrative module that
allows for managing the question bank. Question bank management includes adding new
questions, deleting questions, and displaying all the questions, answers and point values.

@author Timothy McWatters
@version 1.0

COP3022    Project 4
File Name: Question.java
*/

public class Question {

	private static int nextQuestionID = 1000;
	static final int MAX_QUESTION_LENGTH = 50; // package
	static final int MAX_ANSWER_LENGTH = 20; // package
	
	private int questionID;
	private String question;
	private String answer;
	private int value;
	
	/**
	 * Default constructor for the Question class
	 */
	public Question() {
		setQuestionID();
		setQuestion("");
		setAnswer("");
		setValue(0);
	} // end of default constructor for the Question class
	
	/**
	 * Parameterized constructor for the Question class
	 * @param question = The question narrative to set.
	 * @param answer = The answer narrative to set.
	 * @param value = The value of the question to set.
	 */
	public Question(String question, String answer, int value) {
		setQuestionID();
		setQuestion(question);
		setAnswer(answer);
		setValue(value);
	} // end of parameterized constructor for the Question class

	/**
	 * Gets the unique question ID for this instance of Question.
	 * @return the questionID
	 */
	public int getQuestionID() {
		return questionID;
	} // end of getQuestionID method

	/**
	 * Sets the unique question ID for each question based off the next static
	 * nextQuestionID variable, and then increments the nextQuestionID variable.
	 */
	public void setQuestionID() {
		this.questionID = nextQuestionID++;
	} // end of setQuestionID method

	/**
	 * Returns the question narrative (string) for this question.
	 * @return the question
	 */
	public String getQuestion() {
		return question.trim();
	} // end of getQuestion method

	/**
	 * Sets the question narrative (String) for this question, after 
	 * ensuring it does not exceed the question byte limit.
	 * @param question = the question to set
	 */
	public void setQuestion(String question) {
		try {
			int lengthOfQuestion = question.getBytes().length;
			if (lengthOfQuestion > MAX_QUESTION_LENGTH) {
				throw new QuestionLengthError();
			} else {
				this.question = question;
			}
		} catch (QuestionLengthError e) {
			System.out.println(e.getMessage());
		}
	} // end of setQuestion method

	/**
	 * Returns the answer to this question
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	} // end of getAnswer method

	/**
	 * Sets the answer to this question, after 
	 * ensuring it does not exceed the answer byte limit.
	 * @param answer = the answer to set
	 */
	public void setAnswer(String answer) {
		try {
			int lengthOfAnswer = answer.getBytes().length;
			if (lengthOfAnswer > MAX_ANSWER_LENGTH) {
				throw new AnswerLengthError();
			} else {
				this.answer = answer;
			}
		} catch (AnswerLengthError e) {
			System.out.println(e.getMessage());
		}	
	} // end setAnswer method

	/**
	 * Returns the value awarded to a player when they get this question
	 * correct.
	 * @return the value
	 */
	public int getValue() {
		return value;
	} // end of getValue method

	/**
	 * Sets the value awarded to a player when they get this question 
	 * correct.
	 * @param value = the value of the question to set
	 */
	public void setValue(int value) {
		try {
			if ((value < 0) || (value > 5)) {
				throw new QuestionPointValueError();
			} else {
				this.value = value;
			}
		} catch (QuestionPointValueError e) {
			System.out.println(e.getMessage());
		}
	} // end of setValue method

	/* Returns a string representation of the Question object
	 * @return String
	 */
	@Override
	public String toString() {
		return "[questionID = " + getQuestionID() + ", question = " + getQuestion() + ", answer = " + getAnswer() + ", value = "
				+ getValue() + "]\n";
	} // end of toString method
	
} // end of Question class
