import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class week23_이모티콘 {
	static void bfs(int s) {
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] v = new boolean[1001][1001];
		q.offer(new int[] {1, 0, 0});
		v[1][0] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			if(p[0] == s) {
				System.out.println(p[2]);
				return;
			}
			
			// 1. 화면 이모티콘 클립보드로 저장
			if(!v[p[0]][p[0]]) {
				q.offer(new int[] {p[0], p[0], p[2]+1});
				v[p[0]][p[0]] = true;
			}
			// 2. 클립보드 이모티콘을 화면으로 붙여넣기
			if(p[1] != 0 && p[0]+p[1] <= 1000) {
				if(!v[p[0]+p[1]][p[1]]) {
					q.offer(new int[] {p[0]+p[1], p[1], p[2]+1});
					v[p[0]+p[1]][p[1]] = true;
				}
			}
			// 3. 화면에 이모티콘 1개 삭제
			if(p[0] != 1 && !v[p[0]-1][p[1]]) {
				q.offer(new int[] {p[0]-1, p[1], p[2]+1});
				v[p[0]-1][p[1]] = true;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int S = sc.nextInt();
		bfs(S);
	}
}