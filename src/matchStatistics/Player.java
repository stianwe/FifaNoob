package matchStatistics;

public class Player {

	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Player)) {
			return super.equals(other);
		}
		
		return ((Player) other).getName().equalsIgnoreCase(name);
	}
}
