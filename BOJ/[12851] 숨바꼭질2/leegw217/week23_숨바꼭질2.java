import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class week23_숨바꼭질2 {
	static int N, K;
	static int time = Integer.MAX_VALUE;
	static int cnt = 0;
	
	static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[] v = new boolean[100001];
		q.offer(new int[] {N, 0});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			if(time < p[1]) continue;
			v[p[0]] = true;
			
			if(p[0] == K) {
				time = Math.min(time, p[1]);
				cnt++;
				continue;
			}
			if(p[0]-1 >= 0 && !v[p[0]-1]) q.offer(new int[] {p[0]-1, p[1]+1});
			if(p[0]+1 <= 100000 && !v[p[0]+1]) q.offer(new int[] {p[0]+1, p[1]+1});
			if(p[0]*2 <= 100000 && !v[p[0]*2]) q.offer(new int[] {p[0]*2, p[1]+1});
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		bfs();
		System.out.println(time);
		System.out.println(cnt);
	}
}