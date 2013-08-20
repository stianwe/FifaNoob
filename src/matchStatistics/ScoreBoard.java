package matchStatistics;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard extends SQLClass {

	private List<Match> matches;
	
	public ScoreBoard() {
		matches = new ArrayList<Match>();
	}
	
	public void addMatch(Match m) {
		matches.add(m);
	}
	
	public int getNumberOfMatches() {
		return matches.size();
	}
	
	public Match getMatch(int i) {
		return matches.get(i);
	}
	
	public MatchStatistics getMatchStatistics(Player player) {
		MatchStatistics stats = new MatchStatistics(player);
		for (Match m : matches) {
			boolean home = m.getHomePlayer().equals(player);
			if (home || m.getAwayPlayer().equals(player)) {
				stats.goalsScored += (home ? m.getHomeGoals() : m.getAwayGoals());
				stats.goalsConceded += (home ? m.getAwayGoals() : m.getHomeGoals());
				if (m.getHomeGoals() > m.getAwayGoals()) {
					stats.wins++;
				} else if (m.getHomeGoals() < m.getAwayGoals()) {
					stats.losses++;
				} else {
					stats.draws++;
				}
			}
		}
		return stats;
	}
}
