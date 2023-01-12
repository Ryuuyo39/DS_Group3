import java.net.MalformedURLException;
import java.util.ArrayList;

public class Website {
	public String name;
	public String url;
	public int weight;
	
	public Website(String name, String url, int weight){
		this.name = name;
		this.url = url;
		this.weight = weight;
	}
	
	 public ArrayList<Website> getSubLinks(){
		 ArrayList<Website> subLinks=new ArrayList<>();
		 try {
			HTMLHandler caller=new HTMLHandler(url);
			subLinks=caller.getResult();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		 
		 return subLinks;
	 }
	
    public String getName()
    {
    	return name;
    }
    public String getUrl()
    {
    	return url;
    }
    public double getWeight()
    {
    	return weight;
    }
}
