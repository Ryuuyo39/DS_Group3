import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleCaller 
{
	public String searchKeyword;
	public String url;
	public String content;
	public URL u;
	
	public GoogleCaller(String searchKeyword) throws MalformedURLException
	{
		this.searchKeyword = searchKeyword;
		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=20";
		u = new URL(url);
	}
	public GoogleCaller(String searchKeyword,URL u){
		this.searchKeyword = searchKeyword;
		this.u=u;
}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";

		URLConnection conn = u.openConnection();
		//set HTTP header
		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine()) != null)
		{
			retVal += line;
		}
		return retVal;
	}
	
	public HashMap<String, String> query() throws IOException
	{
		if(content == null)
		{
			content = fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		
		
		/* 
		 * some Jsoup source
		 * https://jsoup.org/apidocs/org/jsoup/nodes/package-summary.html
		 * https://www.1ju.org/jsoup/jsoup-quick-start
 		 */
		
		//using Jsoup analyze html string
		Document doc = Jsoup.parse(content);
		
		//select particular element(tag) which you want 
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		for(Element li : lis)
		{
			try 
			{
				String citeUrl = li.select("a").get(0).attr("href");
				citeUrl = java.net.URLDecoder.decode(citeUrl, "UTF-8");
				String[] url = citeUrl.split("&sa");
				citeUrl = url[0];
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) 
				{
					continue;
				}
				
				System.out.println("Title: " + title + " , url: " + citeUrl);
				
				//put title and pair into HashMap
				retVal.put(title, citeUrl);

			} catch (IndexOutOfBoundsException e) 
			{
//				e.printStackTrace();
			}
		}
		return retVal;
	}
	
	public void sort() {
		double x;
		for(int i=0; i<links.size(); i++) {
			for(int j=i+1; j<links.size(); j++) {
				if(links.get(i).weight < links.get(j).weight) {
					x = links.get(i).weight;
					links.get(i).weight = links.get(j).weight;
					links.get(j).weight = x;
				}
			}
		}
	}
}
