package matchStatistics;

public class Match {

	private Player home, away;
	
	private int homeGoals, awayGoals;
	
	public Match(Player home, Player away, int homeGoals, int awayGoals) {
		this.home = home;
		this.away = away;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
	}
	
	public Player getHomePlayer() {
		return home;
	}
	
	public Player getAwayPlayer() {
		return away;
	}
	
	public int getHomeGoals() {
		return homeGoals;
	}
	
	public int getAwayGoals() {
		return awayGoals;
	}
}
