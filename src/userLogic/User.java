package userLogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.omg.CORBA.SystemException;

public class User {
	
	public final static String oldURL = "jdbc:mysql://mysql.stud.ntnu.no/stianwe_users";
	public final static String URL = "jdbc:mysql://localhost/users";
	public final static String USERNAME = "stianwe_test";
	public final static String PASSWORD = "SENSORED";
	
	private String username, password, email;
	private int id;
	private Group group;
	public String status = null;
	
	public User() {
	}
	
	public static String trim(String s) {
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 39) {
				String c = "" + ((char) 39);
				String[] temp = s.split(c);
				s = User.trim(temp[0] + temp[1]);
				break;
			}
		}
		return s;
	}
	
	public void init(String username) {
		Connection con = null;
		status = "FEIL!";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username='" + User.trim(username) + "'");
			if(rs.next()) {
				setUsername(rs.getString("username"));
				setPassword(rs.getString("password"));
				setMail(rs.getString("email"));
				this.id = rs.getInt("id");
				setGroup(new Group(rs.getInt("groupID")));
				status = "OK";
			} else {
				setUsername(null);
				setPassword(null);
				setMail(null);
				status = "Ugyldig brukernavn!";
			}
		} catch(SQLException e) {
			status = "Exception: " + e;
		} catch(ClassNotFoundException e) {
			status = "Exception: " + e;
		} catch(SystemException e) {
			status = "Exception: " + e;
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public Group getGroup() {
		return group;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public static boolean usernameIsTaken(String name) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM users WHERE username='" + name + "'");
			return rs.next();
		} catch(Exception e) {
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
		return true;
	}
	
	public static void createNewUser(String username, String password, String email, String groupName) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM groups WHERE name='" + groupName + "'");
			int groupID;
			if(rs.next()) {
				groupID = rs.getInt("id");
			} else {
				// Error adding group!
				groupID = -1;
			}
			st.executeUpdate("INSERT INTO users (username, password, email, groupID) VALUES ('" + username +
					"', '" + password + "', '" + email + "', '" + groupID + "')");
		} catch(Exception e) {
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public void save() {
		Connection con = null;
		status = "FEIL!";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement st = con.createStatement();
			st.executeUpdate("UPDATE users SET username='" + getUsername() + "', password='" + getPassword() + 
					"', email='" + getMail() + "', groupID=" + getGroup().getID() + " \nWHERE id=" + this.id);
			status = "OK. mail = " + getMail();
		} catch(Exception e) {
			status = "Exception: " + e;
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMail() {
		return email;
	}
	
	public String getRegDate() {
		// TODO
		return null;
	}
	
	public void setMail(String mail) {
		this.email = mail;
	}
	
}
