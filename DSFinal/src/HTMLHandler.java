import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//	public ArrayList<String> DeriveRelativeKeywords(){}
	
	public String GetBody() throws IOException {
		
		String patternString = "(.*<body.*?>)(.*)(</body>.*)";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        String htmlStr = GetHTML();
        Matcher matcher = pattern.matcher(htmlStr);
        String content = matcher.matches() ? matcher.group(2) : "";
        
        return content;
	}	
	
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
	
	public String GetPageName() throws IOException {
		name = "";
		String searchTitle = "(<title>|<TITLE>)(.*?)(</title>|</TITLE>)";
	    Pattern pattern = Pattern.compile(searchTitle);
	    Matcher matcher = pattern.matcher(GetHTML());
	    if(matcher.find()) {
	    	name = matcher.group(2);
	    }
	    return name;
	}
	
	public ArrayList<URL> GetSubLinks() throws IOException {
		ArrayList<URL> links = new ArrayList<URL>();
		url = "";
		 
		Pattern pattern = Pattern.compile("<a href=(.*?)>");
        Matcher matcher = pattern.matcher(GetHTML());

        while (matcher.find()) {
        	Pattern pattern1 = Pattern.compile("\"(.*?)\"");
            Matcher matcher1 = pattern1.matcher(matcher.group(1));
            if (matcher1.find()) {
                url = matcher1.group(1);
            }
            if (url.indexOf("http") != -1) {
                if (url != null) {
                	links.add(new URL(url));
                }
            }
        }
        return links;
}
	public void setScore(ArrayList<Keyword> keywords) throws IOException{
		score = 0;
//		1. calculate score
		for(Keyword keyword:keywords) {
			score+=keyword.weight*counter.countKeyword(keyword.name);
		}
	}	
	
}
