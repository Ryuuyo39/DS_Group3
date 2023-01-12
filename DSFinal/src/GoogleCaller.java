import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleCaller{
	public String searchKeyword;
	public String url;
	public String content;
	public URL u;
	public Website link;
	public WebHeap resultTree;
	public ArrayList<String> rs;

	public GoogleCaller() {
		// TODO Auto-generated constructor stub
	}
	public GoogleCaller(GetInput input) throws MalformedURLException {

		String name=connectWithPlus(input.getName());
		String singer=connectWithPlus(input.getSinger());
		searchKeyword=name+"+"+singer;
		searchKeyword+="+VOCALOID";
		searchKeyword+="+ボカロ";
		resultTree=new WebHeap(new Website("end of result!", "", 0));
		addMVLinks(name,singer);
		addLyricsLinks(name,singer);
		this.url = "http://www.google.com/search?q=" + searchKeyword+ "&oe=utf8&num=50";
		u = new URL(url);

	}

	private void addMVLinks(String name,String singer) {

		String ytKName="MVs on Youtube";
		String ytLink="https://www.youtube.com/results?search_query="+searchKeyword; 
		Website yt= new Website(ytKName, ytLink, 100);
		resultTree.addMany(yt.getSubLinks());

		String nicoKName="MVs on NicoNico of your inputs";
		String nicoLink="https://www.nicovideo.jp/search/"+searchKeyword+"?ref=nicotop_search"; 
		Website nico= new Website(nicoKName, nicoLink, 100);
		resultTree.addMany(nico.getSubLinks());
	}

	private void addLyricsLinks(String name,String singer) {

		String unKNameN="Lyrics on Uta-Net by Song's name";
		String unLinkN="https://www.uta-net.com/search/?Keyword="+name+"&sort=6"; 
		Website utN= new Website(unKNameN, unLinkN, 100);
		resultTree.addMany(utN.getSubLinks());

		String unKNameS="Lyrics on Uta-Net by Singer";
		String unLinkS="https://www.uta-net.com/search/?keyword="+singer+"&target=art&type=in"; 
		Website utS= new Website(unKNameS, unLinkS, 100);
		resultTree.addMany(utS.getSubLinks());

		String utKName="Lyrics on UtaTen of your inputs";
		String utLink="https://utaten.com/search?title="+name+"&artist_name="+singer+"&sort=release_date_desc"; 
		Website ut= new Website(utKName, utLink, 100);
		resultTree.addMany(ut.getSubLinks());
	}

	private String connectWithPlus(String words){
		if(!words.equals(" ")) {
			while(words.contains(" ")) {
				words=words.substring(0,words.indexOf(" "))+"+"+words.substring(words.indexOf(" ")+1);
			}
		}else {
			words="";
		}
		return words;
	}

	public String fetchContent() throws IOException {
		String retVal = "";

		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while ((line = bufReader.readLine()) != null) {
			retVal += line;
		}
		return retVal;
	}

	public void query() throws IOException {

		if (content == null) {
			content = fetchContent();
		}

		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");

		for (Element li : lis) {
			try {
				String citeUrl = li.select("a").get(0).attr("href");
				citeUrl = java.net.URLDecoder.decode(citeUrl, "UTF-8");
				String[] url = citeUrl.split("&sa");
				citeUrl = url[0];
				citeUrl = citeUrl.substring(7);
				String title = li.select("a").get(0).select(".vvjwJb").text();

				if (title.equals("")) {
					continue;
				}
				int w = 10;

				if(citeUrl.contains("www.youtube.com")||citeUrl.contains("https://www.nicovideo.jp")) {
					w += 90;
				}else if(citeUrl.contains("https://utaten.com/lyric/") || citeUrl.contains("https://www.uta-net.com/song/")||citeUrl.contains("bilibili")) {
					w += 75;
				} else if(citeUrl.contains("https://www.jpmarumaru.com/tw/JPSongPlay")) {
					w += 60;
				}
				String wContent=new HTMLHandler(citeUrl).fetchContent();
				if(wContent.contains("ボカロ")) {
					w+= 100;
				}else if(wContent.contains("歌詞")||wContent.contains("ミク")||wContent.contains("リン")||wContent.contains("レン")||wContent.contains("Meiko")||wContent.contains("KAITO")) {
					w+= 40;
				}else if(wContent.contains("Miku")||wContent.contains("Lin")||wContent.contains("Len")) {
					w+= 40;
				}

				link= new Website(title, citeUrl, w);
				resultTree.add(link);
				

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		relationalKeywords();
	}

	public ArrayList<Website> getResult() {
		try {
			query();	
		} catch (IOException e) {	
			e.printStackTrace();
		}
		return sort();
	}

	public  ArrayList<Website> sort() {

		return resultTree.output();
	}

	public void relationalKeywords() throws IOException {

		Document doc = Jsoup.parse(fetchContent());
		Elements lis = doc.select("div");
//		lis = lis.select("span");
//		lis = lis.select("div.BNeawe");
		lis = lis.select("d3PE6e");
		String[] a = lis.toString().split("</div>");
		rs = new ArrayList<String>();
		for(int i = 0; i < a.length; i++) {
			if(a[i].contains("2ahUKEw") && !rs.contains(a[i].substring(44))) {
				rs.add(a[i].substring(44));
			}
		}
		
		for(String r:rs) {
			System.out.println(r);
		}
		
		for(String r:rs) {
			String name = r;
			if(r.contains(" ")) {
				r = r.replace(" ", "+");	
			}
			Website k = new Website("相關搜尋: "+name, "http://www.google.com/search?q=" + r + "&oe=utf8&num=50", 900);
			resultTree.add(k);
			}
		}
}

