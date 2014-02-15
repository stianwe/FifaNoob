package seeding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.SystemException;

public class Seeding {

	public final static String URL = "jdbc:mysql://localhost/users";
	public final static String USERNAME = "stianwe_test";
	public final static String PASSWORD = "SENSORED";
	
	private List<Player> players;
	
	public Seeding() {
	}
	
	public void init() {
		Connection con = null;
		players = new ArrayList<Player>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM players");
			while(rs.next()) {
				players.add(new Player(rs.getInt("id"), rs.getString("name"), rs.getInt("points")));
			}
		} catch(SQLException e) {
//			status = "Exception: " + e;
		} catch(ClassNotFoundException e) {
//			status = "Exception: " + e;
		} catch(SystemException e) {
//			status = "Exception: " + e;
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public void save() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement st = con.createStatement();
			for (Player p : players) {
				st.executeUpdate("UPDATE players SET points='" + p.getPoints() + "' WHERE id='" + p.getId() + "'");
			}
		} catch(Exception e) {
//			status = "Exception: " + e;
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public void createNewPlayer(String name) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO players (name, points) VALUES ('" + name + "', 0)");
			ResultSet rs = st.executeQuery("SELECT id FROM players WHERE name='" + name + "'");
			if(!rs.next()) {
				// SOMETHING WENT WRONG!
				return;
			}
			players.add(new Player(rs.getInt("id"), name, 0));
		} catch(Exception e) {
//			status = "Exception: " + e;
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public String increasePoints(int id, int points) {
		String status = "";
		for (Player p : players) {
			status += p.getName() + " : " + p.getId() + ", ";
			if(p.getId() == id) {
				p.increasePoints(points);
//				status = p.getName() + " : " + p.getPoints();
				break;
			}
		}
		this.save();
		return status;
	}
}
