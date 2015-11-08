import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.*;


public class RemoteConnect {
	private String ipCon;
	private String userCon;
	private String passCon;
	public RemoteConnect(String ipCon, String userCon, String passCon) {
		this.ipCon = ipCon;
		this.userCon = userCon;
		this.passCon = passCon;
	}
	public Object[] getAuth() throws JSchException, InterruptedException, SftpException {
		//The idea was to add without use of ANY other 
		//plugins other than native rtorrent/deluge.
		//watch folder must be enabled, and I probably won't find your watch folder correctly
		//trade off is deluge-console requires sudo to install
		//or adding to watch folder via wget requires watch folder enabled
		//so its really a balance. 
		//thanks x0r for letting me know about fancy server sockets as a 3rd
		//alternative but that will be a later build
		/*
		 * debug testing with deluge-console
		 */
		int port = 22;
		//using JSch for this
		JSch jsch = new JSch();
		Session session = jsch.getSession(userCon, ipCon, port);
		session.setPassword(passCon);
		session.setConfig("StrictHostKeyChecking", "no"); //needed to bypass RSA checksum
		session.connect(10 * 8000);
		return new Object[] {
			jsch, session
		};
	}
}