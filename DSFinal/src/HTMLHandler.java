import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HTMLHandler extends GoogleCaller {
	private WordCounter counter;
	private ArrayList<Website>links=new ArrayList<>();
	
	public HTMLHandler(String url) throws MalformedURLException {
		super.u=new URL(url);
		super.url=url;
		counter=new WordCounter(u);
	}
	public void query() throws IOException {
		String content=fetchContent();
	
		if(url.contains("youtube")) {
			for(int t=0;t<4;t++) {
				try {
					int i=counter.BoyerMoore(content, "\"title\":{\"runs\":[{\"text\":\"");
					content=content.substring(i+26);
					int j=counter.BoyerMoore(content, "\"");
					String title=content.substring(0,j);
					
					i =counter.BoyerMoore(content, "/watch?v=");
					content=content.substring(i);
					j=counter.BoyerMoore(content, "\"");
					String citeUrl= "https://www.youtube.com/"+content.substring(0,j);
					content=content.substring(j);
					link= new Website("Youtube:"+title, citeUrl, 300);
					links.add(link);
				}catch (IndexOutOfBoundsException e) {
					continue;
				}
			}
		}else if (url.contains("nicovideo")){
			for(int t=0;t<4;t++) {
				try {
					int i=counter.BoyerMoore(content, "<a title=\"");
					content=content.substring(i+10);
					int j=counter.BoyerMoore(content, "\"");
					String title=content.substring(0,j);
					i =counter.BoyerMoore(content, "/watch/");
					content=content.substring(i);
					j=counter.BoyerMoore(content, "\"");
					String citeUrl= "https://www.nicovideo.jp"+content.substring(0,j);
					content=content.substring(j);
					if(title.length()<6||title.contains("html")||title.contains("HTML")) {
						t--;
					}else {
						link= new Website("ニコニコ:"+title, citeUrl, 300);
						links.add(link);
					}
				}catch (IndexOutOfBoundsException e) {
					continue;
				}
			}
		}else if (url.contains("utaten")){
			for(int t=0;t<4;t++) {
				try {
					int i=counter.BoyerMoore(content, "searchResult__title");
					if(i<0)continue;
					content=content.substring(i);
					i=counter.BoyerMoore(content, "/lyric/");
					content=content.substring(i);
					int j=counter.BoyerMoore(content, "\"");
					String citeUrl="https://utaten.com"+content.substring(0,j);
					
					content=content.substring(j);
					j=counter.BoyerMoore(content, "</a>");
					String title=content.substring(12,j-8);
					content=content.substring(j);
					link= new Website("UtaTen: "+title, citeUrl, 300);
					links.add(link);
				}catch (IndexOutOfBoundsException e) {
					continue;
				}
			}
		}else if (url.contains("uta-net")&&url.contains("target=art")){
			for(int t=0;t<4;t++) {
				try {
					int i=counter.BoyerMoore(content, "<a class=\"d-block\"href=\"");
					content=content.substring(i);
					i=counter.BoyerMoore(content, "/artist/");
					content=content.substring(i);
					int j=counter.BoyerMoore(content, "\"");
					String citeUrl="https://www.uta-net.com"+content.substring(0,j);
					
					content=content.substring(j);
					j=counter.BoyerMoore(content, "</span>");
					String title=content.substring(24,j);
					content=content.substring(j);
					link= new Website("歌ネットー歌手:"+title, citeUrl, 300);
					links.add(link);
				} catch (IndexOutOfBoundsException e) {
					continue;
				}
			}
		}else if (url.contains("uta-net")&&url.contains("sort=6")){
			for(int t=0;t<4;t++) {
				try {
					int i=counter.BoyerMoore(content, "<td class=\"sp-w-100 pt-0 pt-lg-2\"><a href=\"");
					content=content.substring(i);
					i=counter.BoyerMoore(content, "/song/");
					content=content.substring(i);
					int j=counter.BoyerMoore(content, "\"");
					String citeUrl="https://www.uta-net.com"+content.substring(0,j);
					
					content=content.substring(j);
					i=counter.BoyerMoore(content, "songlist-title");
					content=content.substring(i);
					j=counter.BoyerMoore(content, "</span>");
					String title=content.substring(16,j);
					content=content.substring(j);
					link= new Website("歌ネットー歌詞:"+title, citeUrl, 300);
					links.add(link);
				} catch (IndexOutOfBoundsException e) {
					continue;
				}
			}
		}
				
	}

	public ArrayList<Website> getResult() {
		try {
			query();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return links;
	}
}
