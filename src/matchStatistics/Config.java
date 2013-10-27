package matchStatistics;

public class Config {

	// Of the form "jdbc:mysql://hostname/DBName"
	public static String SQLURL;
	
	public static String SQLUsername;
	
	public static String SQLPassword;
	
	
	
	
	
	public static final String PLAYER_TABLE_NAME = "player";
	
	public static final String PLAYER_NAME = "name";
	
	public static final String PLAYER_ID = "id";
	
	public static final String PLAYER_PICTURE = "picture";
	
	
	public static final String MATCH_TABLE_NAME = "mmatch";
	
	public static final String MATCH_ID = "id";
	
	public static final String MATCH_HOME_PLAYER_ID = "homePId";
	
	public static final String MATCH_AWAY_PLAYER_ID = "awayPId";
	
	public static final String MATCH_HOME_GOALS = "homeGoals";
	
	public static final String MATCH_AWAY_GOALS = "awayGoals";
	
	public static final String MATCH_TIME = "time";
}
