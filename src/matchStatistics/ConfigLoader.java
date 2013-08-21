package matchStatistics;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ConfigLoader{

	private static IOException exception;
	
	private static boolean configHasBeenLoaded = false;
	
	public static boolean loadConfig(String path) {
		configHasBeenLoaded = false;
		BufferedReader textReader = null;
		try {
			FileReader stian = new FileReader(path);
			textReader = new BufferedReader(stian);
			Config.SQLURL = textReader.readLine();
			Config.SQLUsername = textReader.readLine();
			Config.SQLPassword = textReader.readLine();
			configHasBeenLoaded = true;
			return true;
		} catch (IOException e) {
			// TODO Log exception
			exception = e;
			return false;
		} finally {
			try {
				textReader.close();
			} catch (Exception e) {}
		}
	}
	
	public boolean configHasBeenLoaded() {
		return configHasBeenLoaded;
	}
	
	public static IOException getException() {
		return exception;
	}
}
