import java.util.ArrayList;

public class WebTree 
{
	private WebNode root;
		
	public WebTree(WebNode root)
	{		
		this.root=root;
	}
	public void setPostOrderScore(ArrayList<RelativeKeyword> rKeywords){
		setPostOrderScore(root,rKeywords);
	}
	private void setPostOrderScore(WebNode startNode,ArrayList<RelativeKeyword> keywords){}

	
}