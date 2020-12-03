# [20058] 마법사 상어와 파이어스톰 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class week15_마법사상어와파이어스톰 {
	static int N, Q;
	static int[][] map;
	static int[] L;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] v;
	
	static void rotateMap(int l) {
		int n = (int)Math.pow(2, N);
		int len = (int)Math.pow(2, l);
		// 격자 시작점 좌표 찾기
		for(int i=0; i<n; i+=len) 
			for(int j=0; j<n; j+=len) 
				rotate(i, j, len); // 격자 시작점과 한변 길이 넘기기
	}
	
	static void rotate(int x, int y, int l) {
		int[][] temp = new int[l][l];
		// temp 배열에 90도 회전해서 저장
		for(int i=0; i<l; i++)
			for(int j=0; j<l; j++)
				temp[j][l-1-i] = map[x+i][y+j];
		// map 배열에 옮기기
		for(int i=0; i<l; i++)
			for(int j=0; j<l; j++)
				map[x+i][y+j] = temp[i][j];
	}
	
	static void meltIce() {
		int n = (int)Math.pow(2, N);
		int[][] temp = new int[n][n];
		// temp 배열에 map 복사
		for(int i=0; i<n; i++) System.arraycopy(map[i], 0, temp[i], 0, map[i].length);
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 0) continue;
				int ice = 0;
				// 사방탐색으로 인접한 부분에 얼음이 있는지 검사
				for(int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
					if(map[nx][ny] > 0) ice++;
				}
				// 얼음 개수가 3개 미만이면 얼음--
				if(ice < 3) temp[i][j]--;
			}
		}
		// map에 temp 복사
		for(int i=0; i<n; i++) System.arraycopy(temp[i], 0, map[i], 0, temp[i].length);
	}
	
	static void result() {
		int totalIce = 0;
		int bigIce = 0;
		int n = (int)Math.pow(2, N);
		v = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 0) continue;
				// 총 얼음 수 구하기
				totalIce += map[i][j];
				// 가장 큰 얼음 덩어리 크기 구하기
				if(!v[i][j]) bigIce = Math.max(bigIce, BFS(i, j, n));
			}
		}
		System.out.println(totalIce);
		System.out.println(bigIce);
	}
	
	static int BFS(int x, int y, int n) {
		int ice = 1;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x, y});
		v[x][y] = true;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			for(int d=0; d<4; d++) {
				int nx = p[0] + dx[d];
				int ny = p[1] + dy[d];
				if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
				if(v[nx][ny] || map[nx][ny] == 0) continue;
				q.offer(new int[] {nx, ny});
				v[nx][ny] = true;
				ice++;
			}
		}		
		return ice;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		int n = (int)Math.pow(2, N);
		map = new int[n][n];
		L = new int[Q];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++) L[i] = Integer.parseInt(st.nextToken());
		for(int i=0; i<Q; i++) {
			if(L[i] != 0) rotateMap(L[i]);
			meltIce();
		}
		result();
	}
}
```

### :octocat: 풀이 방법

1. L에 해당하는 격자들을 90도 회전시킨다.
2. 인접한 구역 중 얼음이 있는 구역이 3곳 이상이 아니면 해당 칸 얼음 1뺀다.
3. Q만큼 반복하고 총 얼음 수를 구한뒤 가장 큰 얼음덩어리를 BFS로 구한다.

### :octocat: 후기

하 그렇게까지 어려운 문제 아니였는데 배열 돌리는거 실수해서 너무 오래걸렸다..
분명 디버깅할때는 잘 돌아가는거같았는데...
