package matchStatistics;

public class Player {

	private String picture; 
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
		
		return ((Player) other).getName().equalsIgnoreCase(name);
	}
}
