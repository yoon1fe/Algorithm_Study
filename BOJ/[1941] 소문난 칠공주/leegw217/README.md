# [1941] 소문난 칠공주 - Java

###  :octocat: 분류

> 조합
>
> 백트래킹

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

1. 조합으로 0~24 중 7개 숫자를 뽑는다.
2. 이때 백트래킹으로 Y가 3개 이하인 경우만 뽑는다.
3. BFS로 나온 7개 숫자가 다 연결되어 있는지 검사한다.
4. 이때 숫자를 좌표로 바꿔서 검사하는데 x좌표는 숫자/5, y좌표는 숫자%5이다.
5. 7개가 다 연결되어 있으면 ans ++ 

### :octocat: 후기

처음에 DFS로 백트래킹하면서 7개 찾을려고했는데 십자가 모양같은 경우를 찾기 힘들어서
조건에 맞는 7개 좌표를 뽑고 연결되어있는지 확인하는 방법을 썼다!
