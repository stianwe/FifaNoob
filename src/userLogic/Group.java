package userLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Group {
	
	public final static String oldURL = "jdbc:mysql://mysql.stud.ntnu.no/stianwe_users";
	public final static String URL = "jdbc:mysql://localhost/users";
	public final static String USERNAME = "stianwe_test";
	public final static String PASSWORD = "48214507";
	
	private String name;
	private int id;
	private List<Character> permissions;
	
	public Group() {
	}
	
	public Group(int groupID) {
		this.init(groupID);
	}
	
	public Group(String name) {
		this.init(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean hasPermission(char c) {
		return permissions.contains(c);
	}
	
	public void savePermissions(String p) {
		permissions = new ArrayList<Character>();
		if(p.equals("")) {
			return;
		}
		String[] temp = p.split(",");
		char[] ps = new char[temp.length];
		for (String s : temp) {
			permissions.add(s.trim().charAt(0));
		}
	}

	public void init(String name) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM groups WHERE name='" + name + "'");
			if(rs.next()) {
				setName(rs.getString("name"));
				this.id = rs.getInt("id");
				savePermissions(rs.getString("permissions"));
			} else {
				setName(null);
				this.id = -1;
			}
		} catch(Exception e) {
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public static boolean isValidPermissions(String p) {
		if(p == null || p == "") {
			return true;
		}
		String[] temp = p.split(",");
		for (String s : temp) {
			s = s.trim();
			if(s.length() > 1) {
				return false;
			}
		}
		return true;
	}
	
	public static void createGroup(String name, String permissions) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			Statement st = con.createStatement();
			st.executeUpdate("INSERT INTO groups (name, permissions) VALUES('" + name + "', '" + permissions + "')");
		} catch(Exception e) {
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}

	public static String omg;
	
	public void init(int groupID) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM groups WHERE id='" + groupID + "'");
			if(rs.next()) {
				omg = "hit1";
				setName(rs.getString("name"));
				omg = "hit2";
				this.id = rs.getInt("id");
				omg = "hit3";
				savePermissions(rs.getString("permissions"));
				omg = "hit14";
			} else {
				omg = "hit4";
				setName("None");
				omg = "hit5";
				this.id = -1;
				savePermissions(" ");
				omg = "hit5";
			}
		} catch(Exception e) {
			setName("ERROR: " + e);
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
	}
	
	public static boolean groupExists(String name) {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM groups WHERE name='" + name + "'");
			return rs.next();
		} catch(Exception e) {
			// TODO!
		} finally {
			try {
				con.close();
			} catch(Exception e) {
			}
		}
		return false;
	}
	
	public int getID() {
		return id;
	}
}