package jElo;

public class Player {

	public static final int DEFAULT_STARTING_RATING = 1500;
	
	private int rating;
	private String name;
	
	/**
	 * Creates a new player with the given name, and rating 
	 * equal to the DEFAULT_STARTING_RATING
	 * 
	 * @param name
	 */
	public Player(String name) {
		this(name, DEFAULT_STARTING_RATING);
	}
	
	public Player(String name, int rating) {
		this.name = name;
		this.rating = rating;
	}
	
	public int getRating() {
		return rating;
	}
	
	public String getName() { 
		return name;
	}
	
	/**
	 * Changes this player's rating by adding the value given as argument
	 * 
	 * @param change	The rating change
	 */
	public void changeRating(int change) {
		rating += change;
	}
}
