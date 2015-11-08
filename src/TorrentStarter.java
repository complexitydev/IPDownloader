import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


public class TorrentStarter {
	private String homeDirectory = "";
	private String torrent = "";
	private Session session = null;
	private JSch jsch = null;
	private Connection.Response res = null;
	private ChannelSftp channelSftp = null;
	public TorrentStarter(Object[] objectArray, Object[] objectArray2, String watchDir) {
		this.torrent = (String) objectArray2[0];
		this.jsch = (JSch) objectArray[0];
		this.session = (Session) objectArray[1];
		this.res = (Response) objectArray2[1];
		this.homeDirectory = watchDir;
	}
	public void downloadTorrent() throws JSchException, InterruptedException, IOException, SftpException {
		//iptorrents requires passkey via cookies
		res = Jsoup.connect(torrent)
			.cookies(res.cookies())
			.ignoreContentType(true)
			.execute();
		FileOutputStream out = (new FileOutputStream(new java.io.File("C:\\IPDownloader\\" + "torrent1.torrent")));
		out.write(res.bodyAsBytes());
		out.close();
		Channel channel = session.openChannel("sftp"); //we open the channel here
		channel.connect();
		channelSftp = (ChannelSftp) channel;
		channelSftp.cd(channelSftp.getHome());
		File f = new File("C:\\IPDownloader\\torrent1.torrent"); //named torrent1.torrent
		FileInputStream fInput = new FileInputStream(f);
		channelSftp.put(fInput, f.getName());
		fInput.close();
		channelSftp.rename((channelSftp.getHome() + "/torrent1.torrent"), (channelSftp.getHome() + homeDirectory + "torrent1.torrent"));
		//.rename allows to delete from first directory and then move to new directory. works well
		System.out.println("We uploaded the file");
		f.delete();
	}
}