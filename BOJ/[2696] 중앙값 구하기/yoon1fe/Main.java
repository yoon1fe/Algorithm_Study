import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		input();

	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
			int M = Integer.parseInt(br.readLine());
			int cnt = 0;

			System.out.println((M+1)/2);	// 중앙값 개수
			
			for (int i = 0; i < M; i++) {
				if(i % 10 == 0) {
					st = new StringTokenizer(br.readLine(), " ");
				}
				
				int n = Integer.parseInt(st.nextToken());
				
				if(maxHeap.size() == minHeap.size()) {
					maxHeap.offer(n);
				} else {
					minHeap.offer(n);
				}
				
				// maxHeap에는 중간값 이하의 수만 존재하도록
				if(!minHeap.isEmpty()) {
					if(maxHeap.peek() > minHeap.peek()) {
						int t1 = maxHeap.poll();
						int t2 = minHeap.poll();
						
						maxHeap.offer(t2);
						minHeap.offer(t1);
					}
				}
				
				if(i % 2 == 0) {
					if(cnt == 9 || i == M - 1) {
						System.out.println(maxHeap.peek());
						cnt = 0;
					}else {
						System.out.print(maxHeap.peek() + " ");
					}
					cnt++;
				}
			}
		}
	}
}