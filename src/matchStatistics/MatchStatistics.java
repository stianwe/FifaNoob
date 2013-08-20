package matchStatistics;

public class MatchStatistics {

	private Player player;
	
	public int wins, losses, draws, goalsScored, goalsConceded;
	
	public MatchStatistics(Player player) {
		this.player = player;
		wins = 0;
		losses = 0;
		draws = 0;
		goalsConceded = 0;
		goalsScored = 0;
	}
	
	public Player getPlayer() {
		return player;
	}
}
