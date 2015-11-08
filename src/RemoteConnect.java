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
		int port = 22;
		//using JSch for this
		JSch jsch = new JSch();
		Session session = jsch.getSession(userCon, ipCon, port);
		session.setPassword(passCon);
		session.setConfig("StrictHostKeyChecking", "no"); //needed
		session.connect(10 * 8000); //timeout
		return new Object[] {
			jsch, session
		}; //need to keep
	}
}