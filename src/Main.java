import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;


public class Main {

	public static void main(String[] args) throws IOException, JSchException, InterruptedException, SftpException {
		ConfigReader config = new ConfigReader();
		List < String > info = new ArrayList < String > ();
		info = config.grabUserInfo();
		Login login = new Login(info.get(0), info.get(1));
		Scanner File1 = new Scanner(new File("C:\\IPDownloader\\movies.txt"));
		String token = "";
		List < String > temps = new ArrayList < String > ();
		while (File1.hasNext()) {
			token = File1.nextLine();
			temps.add(token);
		}
		File1.close();
		RemoteConnect connect = new RemoteConnect(info.get(2), info.get(3), info.get(4));
		for (int i = 0; i <= temps.size() - 1; i++) {
		String movie = temps.get(i);
		Search search = new Search(login.grabLogin(), movie);
		TorrentStarter tor = new TorrentStarter(connect.getAuth(), search.searchMovie(), info.get(5));
		tor.downloadTorrent();
		}
		
	}
}