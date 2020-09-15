import java.util.Arrays;
import java.util.Comparator;
public class week06_무지의먹방라이브 {
	class Node{
		int index;
		int value;
		Node(int index, int value){
			this.index = index;
			this.value = value;
		}
	}
	public int solution(int[] food_times, long k) {
        Node[] nodes = new Node[food_times.length];
        for(int i=0; i<food_times.length; i++)
        	nodes[i] = new Node(i+1, food_times[i]);
        
        Arrays.sort(nodes, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.value - o2.value;
			}
		});
        
        long pre = 0;
        for(int i=0; i<food_times.length; i++) {
        	long minus  = nodes[i].value - pre;
        	if(minus != 0) {
        		minus *= (food_times.length-i);
        		if(minus <= k) {
        			k -= minus;
        			pre = nodes[i].value;
        		} else {
        			k %= food_times.length-i;
        			Node[] temp = Arrays.copyOfRange(nodes, i, food_times.length);
        			Arrays.sort(temp, new Comparator<Node>() {
        				@Override
        				public int compare(Node o1, Node o2) {
        					return o1.index - o2.index;
        				}
        			});
        			return temp[(int)k].index;
        		}
        	}
        }
        return -1;
    }
}