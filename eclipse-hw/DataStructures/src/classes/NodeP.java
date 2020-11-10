package classes;

public class NodeP<E> {
	
	private E data;
	private  int  priority;
	private  NodeP <E> next;
	
	public  NodeP(E data , int  priority , NodeP <E> next) {
		this.data = data;
		this.priority = priority;
		this.next = next;
	}
	
	public  NodeP(E data , int  priority) {
		this(data , priority , null);
	}	
	
	public int top_priority() {
		NodeP<E> current = this;
		int max = current.priority;
		
		while(current != null) {
			max = Math.max(max, current.top_priority());
			current = current.next;
		}
		
		return max;
	}
	
	public void bump_if(int lower_bound) {
		NodeP<E> current = this;
		
		while(current != null) {
			if (current.priority > lower_bound) {
				current.priority++;
			}
			current = current.next;
		}
	}
	
	


}
