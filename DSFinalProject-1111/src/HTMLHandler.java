import java.io.IOException;
import java.util.ArrayList;
import javax.print.DocFlavor.URL;

public class HTMLHandler {
	private String htmlContent;
	public String url;
	public String name;
	public KeywordCounter counter;
	public double score;
	
	public HTMLHandler(String html){
		
		htmlContent=html;
	}
	public ArrayList<String> DeriveRelativeKeywords(){}
	public String GetBody(){}	
	public String GetHTML(){
		return htmlContent;
	}
	public String GetPageName(){}
	public ArrayList<URL> GetSubLinks(){}
	public void setScore(ArrayList<Keyword> keywords) throws IOException{
		score = 0;
//		1. calculate score

	}	
	
}
