import java.io.*;
import java.util.*;

public class Main {
//	static List<Character> inequality = new ArrayList<>();
	static int N;
	static List<Integer>[] forward = new List[10], reverse = new List[10];
	static int[] inDegree = new int[10], reverseInDegree = new int[10];
	static int[] min, max;
	public static void main(String[] args) throws Exception {
		input();
		
		topol(0, forward, inDegree, min);
		topol(1, reverse, reverseInDegree, max);
		
		printArray(max);
		System.out.println();
		printArray(min);
	}
	
	public static void printArray(int[] answer) {
		for(int i : answer) System.out.print(i);
	}
	
	public static void topol(int c, List<Integer>[] graph, int[] inDegree, int[] answer) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		boolean[] check = new boolean[10];
		
		for(int i = 0; i <= N; i++) {
			if(inDegree[i] == 0) pq.offer(i);
		}
		
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			
			if(c == 0) {
				for(int i = 0; i <= 9; i++) {
					if(check[i] == false) {
						answer[cur] = i;
						check[i] = true;
						break;
					}
				}
			}else {
				for(int i = 9; i >= 0; i--) {
					if(check[i] == false) {
						answer[cur] = i;
						check[i] = true;
						break;
					}
				}
			}
			for(int i = 0; i < graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(--inDegree[next] == 0) pq.offer(next);
			}
		}
	}
	

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		min = new int[N + 1];
		max = new int[N + 1];
		for(int i = 0; i < 10; i++) {
			forward[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
//			inequality.add(st.nextToken().charAt(0));
			char temp = st.nextToken().charAt(0);
			if(temp == '<') {
				forward[i].add(i + 1);
				inDegree[i + 1]++;
				reverse[i + 1].add(i);
				reverseInDegree[i]++;
			}else {
				forward[i + 1].add(i);
				inDegree[i]++;
				reverse[i].add(i + 1);
				reverseInDegree[i + 1]++;
			}
		}
	}
}