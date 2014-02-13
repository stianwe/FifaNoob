package matchStatistics;

public class Player extends jElo.Player {

	private String picture; 
	
	public Player(String name) {
		super(name);
	}
	
	public Player(String name, int rating) {
		super(name, rating);
	}
	
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Player)) {
			return super.equals(other);
		}
		
		return ((Player) other).getName().equalsIgnoreCase(getName());
	}
}
