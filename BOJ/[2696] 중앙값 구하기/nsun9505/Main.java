import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int T;
	public static void main(String[] args) throws NumberFormatException, IOException {
		solution();
	}
	
	public static void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			PriorityQueue<Integer> minPq = new PriorityQueue<>();
			PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
			
			int M = Integer.parseInt(br.readLine());
			if(M <= 10) M = 1;
			else M = (M / 10) + 1;
			
			int cnt = 1;
			ArrayList<Integer> list = new ArrayList<>();
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int len = st.countTokens();
				
				for(int j=0; j<len; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(maxPq.size() == minPq.size())
						maxPq.offer(num);
					else
						minPq.offer(num);
					
					if(!minPq.isEmpty() && !maxPq.isEmpty() && minPq.peek() < maxPq.peek()) {
						int min = maxPq.poll();
						int max = minPq.poll();
						
						maxPq.offer(max);
						minPq.offer(min);
					}
					
					if(cnt++ % 2 == 1)
						list.add(maxPq.peek());
				}
			}
			System.out.println(list.size());
			for(int i=1; i<=list.size(); i++) {
				System.out.print(list.get(i-1) + " ");
				if(i % 10 == 0) System.out.println();
			}
			if(list.size() < 10)
				System.out.println();
		}
	}
}