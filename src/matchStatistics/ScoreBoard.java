package matchStatistics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoard {

	private List<Match> matches;
	
	private List<Match> addedMatches;
	
	private Exception exception;
	
	// Mapping from user name to player id
	private Map<String, Integer> playerIds;
	// Mapping from id to player
	private Map<Integer, Player> players;
	
	public ScoreBoard() {
		addedMatches = new ArrayList<Match>();
		matches = new ArrayList<Match>();
		playerIds = new HashMap<String, Integer>();
		players = new HashMap<Integer, Player>();
	}
	
	public Exception getException() {
		return exception;
	}
	
	public boolean configIsLoaded() {
		return Config.SQLURL != null;
	}
	
	public void addMatch(Match m) {
		matches.add(m);
		addedMatches.add(m);
	}
	
	public int getNumberOfMatches() {
		return matches.size();
	}
	
	public Match getMatch(int i) {
		return matches.get(i);
	}
	
	public int getNumberOfPlayers() {
		return players.size();
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
	
	public boolean loadPlayers() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Config.SQLURL, Config.SQLUsername, Config.SQLPassword);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + Config.PLAYER_TABLE_NAME);
			while (rs.next()) {
				String playerName = rs.getString(Config.PLAYER_NAME);
				int playerId = rs.getInt(Config.PLAYER_ID);
				playerIds.put(playerName, playerId);
				players.put(playerId, new Player(playerName));
			}
			return true;
		} catch(Exception e) {
			exception = e;
			return false;
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public void addPlayer(Player p) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Config.SQLURL, Config.SQLUsername, Config.SQLPassword);
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO " + Config.PLAYER_TABLE_NAME + 
					"(" + Config.PLAYER_NAME + ") VALUES (" + p.getName() + ")");
			ResultSet rs = st.executeQuery("SELECT " + Config.PLAYER_ID + " FROM " +
					Config.PLAYER_TABLE_NAME + " WHERE " + Config.PLAYER_NAME + "=" + p.getName());
			playerIds.put(p.getName(), rs.getInt(Config.PLAYER_ID));
		} catch(Exception e) {
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public void saveMatches() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Config.SQLURL, Config.SQLUsername, Config.SQLPassword);
			Statement st = con.createStatement();
			for (Match m : addedMatches) {
				st.executeUpdate("INSERT INTO " + Config.MATCH_TABLE_NAME + " (" +
						Config.MATCH_HOME_PLAYER_ID + ", " + Config.MATCH_AWAY_PLAYER_ID +
						", " + Config.MATCH_HOME_GOALS + ", " + Config.MATCH_AWAY_GOALS + ") " +
						"VALUES (" + playerIds.get(m.getHomePlayer()) + ", " + playerIds.get(m.getAwayPlayer()) + ", " +
						m.getHomeGoals() + ", " + m.getAwayGoals() + ")");
			}
			addedMatches.clear();
		} catch(Exception e) {
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public boolean loadMatches() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Config.SQLURL, Config.SQLUsername, Config.SQLPassword);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + Config.MATCH_TABLE_NAME);
			while (rs.next()) {
				matches.add(new Match(
						players.get(rs.getInt(Config.MATCH_HOME_PLAYER_ID)), 
						players.get(rs.getInt(Config.MATCH_AWAY_PLAYER_ID)), 
						rs.getInt(Config.MATCH_HOME_GOALS), 
						rs.getInt(Config.MATCH_AWAY_GOALS)));
			}
			return true;
		} catch(Exception e) {
			exception = e;
			return false;
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
}
