package seeding;

public class Player {

	private String name;
	private int id;
	private int points;
	
	public Player(int id, String name, int points) {
		this.id = id;
		this.name = name;
		this.points = points;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void increasePoints(int points) {
		this.points += points;
	}
	
	public int getPoints() {
		return points;
	}
}
