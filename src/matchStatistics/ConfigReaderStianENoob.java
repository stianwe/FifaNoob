package matchStatistics;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ConfigReaderStianENoob{

	public static void readConfig(String path) {
		try {
			FileReader stian = new FileReader(path);
			BufferedReader textReader = new BufferedReader(stian);
			Config.SQLURL = textReader.readLine();
			Config.SQLUsername = textReader.readLine();
			Config.SQLPassword = textReader.readLine();
		} catch (IOException e) { }
	}
}
