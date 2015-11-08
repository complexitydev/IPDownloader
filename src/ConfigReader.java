import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConfigReader {
	public List < String > grabUserInfo() throws IOException {
		File configDir = new File("C:\\IPDownloader\\");
		File checkFile = new File("C:\\IPDownloader\\config.txt");
		boolean exists = configDir.exists();
		if (exists && checkFile.length() != 0) {
			System.out.println("Reading Config");
			String token = "";
			Scanner File1 = new Scanner(new File("C:\\IPDownloader\\config.txt"));
			List < String > temps = new ArrayList < String > ();
			while (File1.hasNext()) {
				token = File1.next();
				temps.add(token);
			}
			File1.close();
			return temps;
		} else {
			Scanner s = new Scanner(System. in );
			System.out.println("Config not found or it is empty");
			System.out.println("Please input your username");
			String username = s.nextLine();
			System.out.println("Please input your password");
			String password = s.nextLine();
			System.out.println("Please input the server hostname");
			String ipCon = s.nextLine();
			System.out.println("Please input server username");
			String userCon = s.nextLine();
			System.out.println("Please input the server password");
			String passCon = s.nextLine();
			System.out.println("Please input the watch directory (excluding home directory");
			String watchDir = s.nextLine();
			System.out.println("Your information has been stored at C:\\IPDownloader\\");
			new File("C:\\IPDownloader\\").mkdir();
			File configFile = new File("C:\\IPDownloader\\config.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
			writer.write(username);
			writer.newLine();
			writer.write(password);
			writer.newLine();
			writer.write(ipCon);
			writer.newLine();
			writer.write(userCon);
			writer.newLine();
			writer.write(passCon);
			writer.newLine();
			writer.write(watchDir);
			writer.close();
			String token = "";
			Scanner File1 = new Scanner(new File("C:\\IPDownloader\\config.txt"));
			List < String > temps = new ArrayList < String > ();
			while (File1.hasNext()) {
				token = File1.next();
				temps.add(token);
			}
			File1.close();
			return temps;
		}
	}
}