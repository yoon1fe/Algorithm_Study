# [17070] 파이프 옮기기1 - Java

###  :octocat: 분류

> 구현
> 
> BFS

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

1. 파이프가 가로 형태일때는 가로, 대각선 이동만 가능
세로 형태일때는 세로, 대각선 이동만 가능
대각선 형태일때는 가로, 세로, 대각선 이동 가능
2. BFS로 형태에 따라서 이동 위치에 벽 여부를 체크하고 범위 체크해서 N-1,N-1으로
이동 가능한 경우를 모두 체크한다.
3. 가로로 이동할때 만약 x좌표가 N-1이 아니고 y좌표가 N-2가 되면 더이상 이동할
수 없으므로 이동시키지 않는다.
마찬가지로 세로로 이동할때도 더이상 이동할 수 없는 경우를 제외하고 이동시킨다.

### :octocat: 후기

이동 조건 때문에 엄청 지저분한 코드가 됐지만 그래도 BFS 문제 중 쉬운 난이도인것
같았다. 이동 후에 더이상 이동하지 못하는 경우를 신경 안쓰고 BFS를 돌렸더니
시간초과가 떠서 따로 조건을 추가해서 더이상 이동하지 못하는 경우로 이동안시켰더니
바로 통과!
