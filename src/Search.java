import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class Search {
	private Connection.Response res;
	private String search = "";
	public Search(Connection.Response res, String movie) {
		this.res = res;
		this.search = movie;
	}
	public Object[] searchMovie() throws IOException {
		String downloadLink = "";
		//1080p top seeded movies
		Document doc = Jsoup.connect("https://iptorrents.com/t?1080p;q=" + search + ";o=seeders#torrents") //uses jsoup to keep login
			.cookies(res.cookies())
			.get();
		//had to scrape the top DL link. works with dynamic tags. using the icon as a reference
		for (Element e: doc.select("td.ac").select("a")) {
			if (e.select("i[class=fa fa-download fa-2x]").size() > 0) {
				downloadLink = "https://iptorrents.com" + e.attr("href");
				break;
			}
		}
		System.out.println("Added " + downloadLink);
		return new Object[] {
			downloadLink, res
		};
	}
}