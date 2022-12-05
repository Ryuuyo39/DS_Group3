import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.print.DocFlavor.URL;

public class HTMLHandler {
	public String htmlContent;
	public String url;
	public String name;
	public KeywordCounter counter;
	public double score;
	
	public HTMLHandler(String url) throws IOException{
		this.url=url;
		counter=new KeywordCounter(GetHTML());
	}
	public ArrayList<String> DeriveRelativeKeywords(){}
	public String GetBody(){}	
	public String GetHTML()throws IOException{
		URL url = new URL(this.url);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		String line = null;
		
		while ((line = br.readLine()) != null){
			htmlContent += line + "\n";
		}
	  
		return htmlContent;
	}
	public String GetPageName(){
		}
	public ArrayList<URL> GetSubLinks(){}
	public void setScore(ArrayList<Keyword> keywords) throws IOException{
		score = 0;
//		1. calculate score
		for(Keyword keyword:keywords) {
			score+=keyword.weight*counter.countKeyword(keyword.name);
		}
	}	
	
}
