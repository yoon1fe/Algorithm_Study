import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class week16_소문난칠공주 {
	static char[][] student;
	static int[] arr;
	static int[] result;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int ans = 0;
	
	static void makeComb(int begin, int cnt, int yCnt) {
		if(cnt == result.length) {
			if(bfs()) ans++;
			return;
		}
		for(int i=begin; i<arr.length; i++) {
			int y = yCnt;
			if(student[arr[i]/5][arr[i]%5] == 'Y') {
				if(yCnt+1 > 3) continue;
				y += 1;
			}
			result[cnt] = arr[i];
			makeComb(i+1, cnt+1, y);
		}
	}
	
	static boolean bfs() {
		int cnt = 1;
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] v = new boolean[5][5];
		q.offer(new int[] {result[0]/5, result[0]%5});
		v[result[0]/5][result[0]%5] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				if(nx<0 || nx>=5 || ny<0 || ny>=5) continue;
				if(v[nx][ny]) continue;
				int idx = nx*5 + ny;
				for(int i=1; i<7; i++) {
					if(result[i] == idx) {
						q.offer(new int[] {nx,ny});
						v[nx][ny] = true;
						cnt++;
						break;
					}
				}
			}
		}
		if(cnt != 7) return false;
		else return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		student = new char[5][5];
		for(int i=0; i<5; i++) student[i] = sc.nextLine().toCharArray();
		arr = new int[25];
		result = new int[7];
		for(int i=0; i<25; i++) arr[i] = i;
		makeComb(0, 0, 0);
		System.out.println(ans);
	}
}