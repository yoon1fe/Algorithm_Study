import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class week19_중앙값구하기 {
	static List<Integer> answer;
	static PriorityQueue<Integer> maxHeap;
	static PriorityQueue<Integer> minHeap;
	
	static void input(int n) {
		if(maxHeap.size() == minHeap.size()) {
			maxHeap.offer(n);
			if((!maxHeap.isEmpty() && !minHeap.isEmpty()) && 
					maxHeap.peek() < minHeap.peek()) {
				int temp = minHeap.poll();
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(temp);
			}
			answer.add(maxHeap.peek());
		} else {
			minHeap.offer(n);
			if((!maxHeap.isEmpty() && !minHeap.isEmpty()) &&
					maxHeap.peek() < minHeap.peek()) {
				int temp = minHeap.poll();
				minHeap.offer(maxHeap.poll());
				maxHeap.offer(temp);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			answer = new ArrayList<Integer>();
			maxHeap = new PriorityQueue<Integer>(
					new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o1 - o2;
						}
			});
			minHeap = new PriorityQueue<Integer>(
					new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o2 - o1;
						}
			});
			int q = N / 10;
			int r = N % 10;
			for(int i=0; i<q; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<10; j++) {
					int n = Integer.parseInt(st.nextToken());
					input(n);
				}
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<r; i++) {
				int n = Integer.parseInt(st.nextToken());
				input(n);
			}
			System.out.println(N/2 + 1);
			for(int i=0; i<answer.size(); i++) {
				System.out.print(answer.get(i)+" ");
				if((i+1) % 10 == 0) System.out.println();
			}
			System.out.println();
		}
	}
}