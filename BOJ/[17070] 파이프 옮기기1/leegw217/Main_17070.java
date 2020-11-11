import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Main_17070 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int answer = 0;
		int N = sc.nextInt();
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				map[i][j] = sc.nextInt();
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {0, 0, 0});
		while(!q.isEmpty()) {
			int[] p = q.poll();
			// N,N에 도착했는지 확인
			if((p[0]==N-1 && p[1]==N-2 && p[2]==0) ||
					(p[0]==N-2 && p[1]==N-1 && p[2]==1) ||
					(p[0]==N-2 && p[1]==N-2 && p[2]==2)) {answer++; continue;}
			// 이동
			if(p[2] == 0) { // 가로
				// 가로 이동
				if(p[1]+2 < N && map[p[0]][p[1]+2] != 1) {
					if(!(p[0]!=N-1 && p[1]+2==N-1))
						q.offer(new int[] {p[0], p[1]+1, 0});
				}
				// 대각선 이동
				if(p[1]+2 < N && p[0]+1 < N) 
					if(map[p[0]][p[1]+2]!=1 && map[p[0]+1][p[1]+1]!=1 && map[p[0]+1][p[1]+2]!=1) 
						q.offer(new int[] {p[0], p[1]+1, 2});
			} else if(p[2] == 1) { // 세로
				// 세로 이동
				if(p[0]+2 < N && map[p[0]+2][p[1]] != 1) {
					if(!(p[1]!=N-1 && p[0]+2==N-1)) 
						q.offer(new int[] {p[0]+1, p[1], 1});
				}
				// 대각선 이동
				if(p[0]+2 < N && p[1]+1 < N) 
					if(map[p[0]+2][p[1]]!=1 && map[p[0]+1][p[1]+1]!=1 && map[p[0]+2][p[1]+1]!=1)
						q.offer(new int[] {p[0]+1, p[1], 2});
			} else if(p[2] == 2) { // 대각선
				// 가로 이동
				if(p[0]+1<N && p[1]+2<N && map[p[0]+1][p[1]+2]!=1) {
					if(!(p[0]+1!=N-1 && p[1]+2==N-1))
						q.offer(new int[] {p[0]+1, p[1]+1, 0});
				}
				// 세로 이동
				if(p[0]+2<N && p[1]+1<N && map[p[0]+2][p[1]+1]!=1) {
					if(!(p[1]+1!=N-1 && p[0]+2==N-1))
						q.offer(new int[] {p[0]+1, p[1]+1, 1});
				}
				// 대각선 이동
				if(p[0]+2<N && p[1]+2<N) 
					if(map[p[0]+1][p[1]+2]!=1 && map[p[0]+2][p[1]+1]!=1 && map[p[0]+2][p[1]+2]!=1)
						q.offer(new int[] {p[0]+1, p[1]+1, 2});
			}
		}
		System.out.println(answer);
	}
}