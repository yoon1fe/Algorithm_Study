import java.io.*;
import java.util.*;

public class Main {
	static class Rest {
		// 휴게소없는 구간의 길이, 그 안에 있는 휴게소 수 (cnt - 1)개
		int distance, cnt;
		
		Rest(int d, int c) {
			this.distance = d; this.cnt = c;
		}
	}
	
	static int N, M, L;
	static List<Integer> pos = new ArrayList<>();
	static PriorityQueue<Rest> pq = new PriorityQueue<>(new Comparator<Rest>(){
		public int compare(Rest o1, Rest o2) {
			int a = o1.distance / o1.cnt;
			int b = o2.distance / o2.cnt;
			
			if(o1.distance % o1.cnt != 0) a++;
			if(o2.distance % o2.cnt != 0) b++;
			
			return Integer.compare(b, a);
		}
	});
	
	public static void main(String[] args) throws Exception {
		input();
		
		System.out.println(solve());
	}
	
	public static int solve() {
		while(M-- > 0) {
			Rest cur = pq.poll();
			
			pq.offer(new Rest(cur.distance, cur.cnt+1));
		}
		
		Rest top = pq.poll();
		// 소수점 아래 있으면 +1 해서 출력
		return top.distance % top.cnt == 0 ? top.distance / top.cnt : (top.distance / top.cnt) + 1;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			pos.add(Integer.parseInt(st.nextToken()));
		}
		pos.add(0);
		pos.add(L);

		Collections.sort(pos);
		
		for(int i = 0; i < pos.size() - 1; i++) {
			pq.offer(new Rest(pos.get(i+1) - pos.get(i), 1));
		}
	}
}