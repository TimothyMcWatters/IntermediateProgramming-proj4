
/**
This program:
Allows users (Players) to play a trivia game. The game will ask random trivia questions,
evaluate their answers and keep score. The program also has an administrative module that
allows for managing the question bank. Question bank management includes adding new
questions, deleting questions, and displaying all the questions, answers and point values.

@author Timothy McWatters
@version 1.0

COP3022    Project 4
File Name: Player.java
*/

public class Player implements java.io.Serializable {

	private String playerName = "";
	private String playerNickName = "";
	private int playerTotalScore = 0;
	
	/**
	 * Default constructor for the Player class
	 */
	public Player() {
		setPlayerName("");
		setPlayerNickName("");
		setPlayerTotalScore(0);
	} // end of default constructor for the Player class
	
	/**
	 * Parameterized constructor for the Player class
	 * @param playerName = the players real name
	 * @param playerNickName = the players nick name
	 * @param playerTotalScore = the players total score is the summation of all the players game sessions
	 */
	public Player(String playerName, String playerNickName, int playerTotalScore) {
		setPlayerName(playerName);
		setPlayerNickName(playerNickName);
		setPlayerTotalScore(playerTotalScore);
	} // end of Parameterized constructor for the Player class

	/**
	 * Returns the players real name
	 * @return the playerName = the players real name
	 */
	public String getPlayerName() {
		return playerName;
	} // end of getPlayerName method

	/**
	 * Sets the players real name
	 * @param playerName = the players real name
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	} // end of setPlayerName method

	/**
	 * Returns the players nick name
	 * @return the playerNickName = the players nick name
	 */
	public String getPlayerNickName() {
		return playerNickName;
	} // end of getPlayerNickName method

	/**
	 * Sets the players nick name
	 * @param playerNickName = the players nick name
	 */
	public void setPlayerNickName(String playerNickName) {
		this.playerNickName = playerNickName;
	} // end of setPlayerNickName method

	/**
	 * Returns the summation of all the players game session scores
	 * @return the playerTotalScore = the players total score is the summation of all the players game sessions
	 */
	public int getPlayerTotalScore() {
		return playerTotalScore;
	} // end of getPlayerTotalScore method

	/**
	 * Sets the summation of all the players game session scores
	 * @param playerTotalScore = the players total score is the summation of all the players game sessions
	 */
	public void setPlayerTotalScore(int playerTotalScore) {
		this.playerTotalScore = playerTotalScore;
	} // end of setPlayerTotalScore method
	
	/**
	 * Adds your a new games score to the rest of the players game session scores
	 * @param newScore = the players total score is the summation of all the players game sessions
	 */
	public void addToPlayerTotalScore(int newScore) {
		int newPlayerTotalScore = getPlayerTotalScore() + newScore;
		setPlayerTotalScore(newPlayerTotalScore);
	} // end of addToPlayerTotalScore method

	/* Returns a String representation of a Player object
	 * @return String = the String representation of a Player object
	 */
	@Override
	public String toString() {
		return "Player [playerName=" + getPlayerName() + ", playerNickName=" + getPlayerNickName() + ", playerTotalScore="
				+ getPlayerTotalScore() + "]";
	} // end of toString method
	
} // end of Player class