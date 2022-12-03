import java.util.ArrayList;
import javax.print.DocFlavor.URL;

public class HTMLHandler {
	private String htmlContent;
	
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
	
}