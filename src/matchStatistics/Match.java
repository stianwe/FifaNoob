package matchStatistics;

public class Match {

	private Player home, away;
	
	private int homeGoals, awayGoals;
	
	public Match() {
		
	}
	
	public Match(Player home, Player away, int homeGoals, int awayGoals) {
		this.home = home;
		this.away = away;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
	}
	
	public void setHomePlayerName(String name) {
		home = new Player(name);
	}
	
	public void setAwayPlayerName(String name) {
		away = new Player(name);
	}
	
	public void setHomeGoals(int goals) {
		homeGoals = goals;
	}
	
	public void setAwayGoals(int goals) {
		awayGoals = goals;
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
