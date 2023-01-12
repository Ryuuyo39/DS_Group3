import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class WebHeap {
	public Website root;
	private PriorityQueue<Website> heap;

	private class KeywordComparator implements Comparator<Website>{
		@Override
		public int compare(Website o1, Website o2){
			if(o1 == null || o2 == null) throw new NullPointerException();
			if(o1.weight<o2.weight) {
				return 1;
			}else if(o1.weight>o2.weight) {
				return -1;
			}else {
				return 0;
			}			
		}
	}
	
	public WebHeap(Website rootPage){
		this.root = rootPage;
		this.heap = new PriorityQueue<Website>(50, new KeywordComparator());
	}
	public void add(Website k){
		heap.offer(k);
	}
	public void addMany(ArrayList<Website> ks){
		for(int i=0;i<ks.size();i++) {
			heap.offer(ks.get(i));
		}
		
	}
	
	public void peek(){
		if(heap.peek() == null){
			System.out.println("InvalidOperation");
			return;
		}
		
		Website k = heap.peek();		
		System.out.println(k.toString());
	}
	
	public void removeMin(){
		if(heap.peek() == null){
			System.out.println("InvalidOperation");
			return;
		}
		System.out.println(heap.poll().toString());
	}
	
	public ArrayList<Website> output(){
		Website k;
		ArrayList<Website> results=new ArrayList<>();
		if(heap.peek() == null){
			System.out.println("InvalidOperation");
			return null;
		}else {
			while(heap.peek() != null) {
				k=heap.poll();
				results.add(k);
			}
		}
		return results;
	}
}

