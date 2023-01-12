import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

public class WordCounter {
	
    private String content;
    private URL url;
    
    public WordCounter(URL url){
    	this.url=url;
    }
    public WordCounter(String cont){
    	content=cont;
    }
    private String fetchContent() throws IOException{
		
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}
	
		return retVal;
    }
    
    public int BoyerMoore(String T, String P){
    	int m=P.length() ;
        int i = m-1;
        int j = m-1;
        
        // Bonus: Implement Boyer-Moore Algorithm     
        while(i<T.length()-1) {
        	if(T.charAt(i)==P.charAt(j)) {
        		if(j==0) {
        			return i;
        		}
        		else {
        			i-=1;
        			j-=1;
        		}
        	}
        	else {
        		int l=last(T.charAt(i), P);
        		i=i+m-min(j,1+l);
        		j=m-1;
        	}
        }
        return -1;
    }

    public int last(char c, String P){
    	// Bonus: Implement last occurence function
    	int i=P.lastIndexOf(c);
        return i;
    }

    public int min(int a, int b){
        if (a < b)
            return a;
        else if (b < a)
            return b;
        else 
            return a;
    }
    
    public int countKeyword(String keyword) throws IOException{
    	if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
		
		int retVal = 0; 
		// 1. calculates appearances of keyword (Bonus: Implement Boyer-Moore Algorithm)
		int j=0;
//		j=content.indexOf(keyword);
		j=BoyerMoore(content, keyword);
		while(j!=-1) {
			
			content=content.substring(j+keyword.length());
			retVal++;
//			j=content.indexOf(keyword);
			j=BoyerMoore(content, keyword);
		}

		return retVal;
    }
}
