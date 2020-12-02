import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
public class week14_가운데를말해요 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		for(int i=0; i<N; i++) {
			int num = sc.nextInt();
			
			if(maxHeap.size() == minHeap.size()) maxHeap.offer(num);
			else minHeap.offer(num);
			
			if(!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				if(maxHeap.peek() > minHeap.peek()) {
					int temp = maxHeap.poll();
					maxHeap.offer(minHeap.poll());
					minHeap.offer(temp);
				}
			}
			System.out.println(maxHeap.peek());
		}
	}
}