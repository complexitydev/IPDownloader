import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
public class Login {
	private String username;
	private String password;
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public Connection.Response grabLogin() throws IOException {
		Response loginRes = Jsoup.connect("https://iptorrents.com/")
			.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0") //robots.txt
			.method(Method.GET)
			.execute();
		Connection.Response res = Jsoup.connect("https://iptorrents.com/")
			.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
			.followRedirects(true)
			.data("username", username, "password", password)
			.method(Method.POST)
			.cookies(loginRes.cookies())
			.execute();
		//posting with cookies using connection.response
		return res;
	}
}