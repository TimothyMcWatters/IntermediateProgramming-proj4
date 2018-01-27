import java.io.IOException;
import java.io.RandomAccessFile;
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
File Name: QuestionAdmin.java
*/

public class QuestionAdmin {
	
	private static final int NOTFOUND = -1;
	private static final int MAX_NUMBER_OF_QUESTIONS = 50;
	private int numOfQuestions = 0;
	Question[] questions = null; // package
	private Scanner keyboard = new Scanner(System.in).useDelimiter("\n|[\r\n]+");
	
	private String questionBankFileName = "";
	
	/**
	 * constructor for the QuestionAdmin class
	 */
	public QuestionAdmin(String questionBankFileName) {
		setQuestionBankFileName(questionBankFileName);
		questions = new Question[MAX_NUMBER_OF_QUESTIONS];
	} // end of QuestionAdmin default constructor
	
	/**
	 * Sets the question bank files name for the Random Access File
	 * @param questionBankFileName = the name of the Random Access File that the question bank is stored.
	 */
	public void setQuestionBankFileName(String questionBankFileName) {
		this.questionBankFileName = questionBankFileName;
	} // end of setQuestionBankFileName method
	
	/**
	 * Returns the question bank files name for the Random Access File
	 * @return questionBankFileName = the name of the Random Access File that the question bank is stored.
	 */
	public String getQuestionBankFileName() {
		return this.questionBankFileName;
	} // end of getQuestionBankFileName method
	
	/**
	 * Returns the number of questions in the question bank
	 * @return numOfQuestions = the number of questions in the question bank
	 */
	public int numOfQuestions() {
		return this.numOfQuestions;
	} // end of numOfQuestions method

	/**
	 * Populates the question bank from the Random Access File
	 * @param questionBankFileName = the name of the Random Access File that the question bank is stored.
	 */
	public void populateQuestionBank() throws IOException {
		RandomAccessFile ioStream = new RandomAccessFile(getQuestionBankFileName(), "rw");
		String output;
		final int QUESTION_DELETED_SIZE = 3;
		final int QUESTION_SIZE = 52;
		final int ANSWER_SIZE = 22;
		final int VALUE_SIZE = 3;
		final int RECORD_SIZE = QUESTION_DELETED_SIZE + QUESTION_SIZE + ANSWER_SIZE + VALUE_SIZE;
		long fileSize = ioStream.length();
		int numberOfQuestions = (int)(fileSize / RECORD_SIZE);
		
		for (int i = 0; i < numberOfQuestions; i++) {
				output = ioStream.readUTF().trim();
				String flag = output;
				
				output = ioStream.readUTF().trim();
				String question = output;
				
				output = ioStream.readUTF().trim();
				String answer = output;
		
				output = ioStream.readUTF().trim();
				int value = Integer.parseInt(output);
				
				if (flag.equalsIgnoreCase("f")) {
					Question newQuestion = new Question(question, answer, value);
					addQuestion(newQuestion);
				} else {
					continue;
				}
		}
		ioStream.close();
	} // end of populateQuestionBank method
	
	/**
	 * Prints a menu for questions, Then takes in a response and directs the player to the selected menu
	 * option for the input given.
	 * @throws IOException 
	 */
	public void questionMenu() throws IOException {
		int menuResponse;
		boolean again = false;
		do {
			again = false;
			System.out.println("\nTrivia Game Administration\n   1. List all questions\n   2. Delete question\n   3. Add question\n   4. Quit\nEnter choice:\n");
			menuResponse = keyboard.nextInt();
			again = false;
			if (menuResponse == 1) {
				System.out.println(toString());
			} else if (menuResponse == 2) {
				System.out.println("Enter the Question ID of the question to delete");
				int questionIDToDelete = keyboard.nextInt();
				boolean questionDeleted = deleteQuestion(questionIDToDelete);
				if (!questionDeleted) {
					System.out.println("\nThe Question could not be deleted, make sure you entered the correct Question ID.");
				} else {
					System.out.println("The Question was deleted successfully.");
				}
				break;
			} else if (menuResponse == 3) {
				addNewQuestion();
				break;
			} else if (menuResponse == 4) {
				again = false;
			} else {
				System.out.println("You have made an invalid selection, please enter an integer number (1-4)");
				again = true;
			}
		} while (again);
	} // end of questionMenu method
	
	/**
	 * Adds a question to the question bank.
	 * @param question = the question object to add to the question bank.
	 */
	private void addQuestion(Question question) {
		questions[numOfQuestions++] = question;
	} // end of addQuestion method
	
	/**
	 * Allows for a user to build a question, answer, and value then add it to the question bank.
	 * Since the question, answer and value all have parameters they must meet, the method will 
	 * require the user to enter the proper inputs meeting the parameters before moving on.
	 * @throws IOException 
	 */
	public void addNewQuestion() throws IOException {
		String questionDeleted = "";
		String question = "";
		String answer = "";
		String questionValue = "";
		boolean again = false;

		RandomAccessFile ioStream = new RandomAccessFile(getQuestionBankFileName(), "rw");
		
		questionDeleted = "f";

		// question
		do{
			again = false;
			try {
				System.out.println("Please enter a question limited to 50 characters: ");
				question = keyboard.next();
				int lengthOfQuestion = question.getBytes().length;
				if (lengthOfQuestion > Question.MAX_QUESTION_LENGTH) {
					throw new QuestionLengthError();
				} 
			} catch (QuestionLengthError e) {
				System.out.println(e.getMessage());
				again = true;
			}
		} while (again);

		// answer
		do{
			again = false;
			try {
				System.out.println("Enter the answer, limited to 20 characters: ");
				answer = keyboard.next();
				int lengthOfAnswer = answer.getBytes().length;
				if (lengthOfAnswer > Question.MAX_ANSWER_LENGTH) {
					throw new AnswerLengthError();
				}
			} catch (AnswerLengthError e) {
				System.out.println(e.getMessage());
				again = true;
			}
		} while (again);
		
		// questionValue
		do{
			again = false;
			try {
				System.out.println("Enter the value of the question answered (1 being easy though 5 being difficult): ");
				questionValue = keyboard.next();
				if ((Integer.parseInt(questionValue) < 1) || (Integer.parseInt(questionValue) > 5)) {
					throw new QuestionPointValueError();
				} 
			} catch (QuestionPointValueError e) {
				System.out.println(e.getMessage());
				again = true;
			}
		} while (again);

		Question newQuestion = new Question(question, answer, Integer.parseInt(questionValue));
		addQuestion(newQuestion);

		ioStream.seek( ( (numOfQuestions - 1) * 80) );
		ioStream.writeUTF(String.format("%s", questionDeleted)); //3
		ioStream.writeUTF(String.format("%50s", question)); //52
		ioStream.writeUTF(String.format("%20s", answer)); //22
		ioStream.writeUTF(String.format("%s", questionValue)); //3

		ioStream.close();
	} // end of addNewQuestion method
	
	/**
	 * attempts to find a question ID in the questions array, returns NOTFOUND constant if not found
	 * @param accountId = the ID of the account to find
	 * @return int = the array index number that was found, or NOTFOUND constant if the account ID was not found
	 */
	private int findQuestion(int questionID) {
		for (int indexNumber = 0; indexNumber < questions.length; indexNumber++) {
			if (questions[indexNumber] == null) {
				continue;
			}
			else if (questions[indexNumber].getQuestionID() == questionID) {
				return indexNumber;
			}
			else { 
				continue;
			}
		}
		return NOTFOUND;
	} // end of findQuestion method
	
	/**
	 * uses findQuestion() method to find and delete a question
	 * this method also re-organizes the users array to get rid of null or empty objects in the middle of the array 
	 * this method also changes the questionDeleted flag on in the RandomAccessFile to t for true
	 * @param questionIDToDelete = the ID of question to delete
	 * @return boolean = true if question was found and deleted, or false if not
	 * @throws IOException 
	 */
	public boolean deleteQuestion(int questionIDToDelete) throws IOException {
		int findQuestionResult = findQuestion(questionIDToDelete);
		int j = 0;
		if (findQuestionResult >= 0) {
			questions[findQuestionResult] = null;
			for (int i = 0; i < questions.length; i++) {
				if ((questions[i] != null) && (questions[i].getQuestionID() != 0)) {
					questions[j] = questions[i];
					j++;
				}
			}
			numOfQuestions = j;
			changeFlag(findQuestionResult);
			for (int k = j; k < questions.length; k++) {
				questions[k] = null;
			}
			return true;
		} else {
			return false;
		}
	} // end of deleteQuestion method
	
	/**
	 * This method changes the questionDeleted flag on in the RandomAccessFile to t for true
	 * to show that the question was deleted from the file
	 * @param questionNumber = the index number of the question to delete.
	 */
	private void changeFlag(int questionNumber) throws IOException {
		RandomAccessFile ioStream = new RandomAccessFile(getQuestionBankFileName(), "rw");
		ioStream.seek( ( (questionNumber) * 80) );
		ioStream.writeUTF("t");
		
		ioStream.close();
	} // end of changeFlag method

	/* 
	 * Prints a formated string representing the Answer Bank
	 * @return String = a formatted string representing the Answer Bank
	 */
	@Override
	public String toString() {
		String completeString = ("Question Bank:\n"); 
				for (int i = 0; i < numOfQuestions; i++) {
					completeString += questions[i].toString();
				}
		return completeString;
	} // end of toString method
	
} // end of QuestionAdmin class
