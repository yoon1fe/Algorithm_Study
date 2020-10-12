# [17142] 연구소3 - Java

###  :octocat: 분류

> 구현
> BFS

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
public class week09_연구소3 {
	static int N, M;
	static int[][] map;
	static List<int[]> virus;
	static int[] arr;
	static int[] result;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int answer = Integer.MAX_VALUE;
	static boolean flag = false;
	
	static void makeComb(int begin, int cnt) {
		if(cnt == result.length) {
			int[][] tempMap = new int[N][N];
			for(int i=0; i<N; i++) System.arraycopy(map[i], 0, tempMap[i], 0, N);
			Queue<int[]> q = new LinkedList<int[]>();
			for(int i=0; i<M; i++) {
				int x = virus.get(result[i])[0];
				int y = virus.get(result[i])[1];
				q.add(new int[] {x,y,1});
			}
			while(!q.isEmpty()) {
				int[] p = q.poll();
				
				for(int d=0; d<4; d++) {
					int nx = p[0] + dx[d];
					int ny = p[1] + dy[d];
					if(nx<0||nx>=N||ny<0||ny>=N) continue;
					if(tempMap[nx][ny]==1) continue;
					if(tempMap[nx][ny]>2) continue;
					q.add(new int[] {nx,ny,p[2]+1});
					tempMap[nx][ny] = p[2]+1;
				}
				
				if(checkMap(tempMap)) {
					flag = true;
					if(answer > p[2]) answer = p[2];
					break;
				}
			}
			return;
		}
		
		for(int i=begin; i<arr.length; i++) {
			result[cnt] = arr[i];
			makeComb(i+1, cnt+1);
		}
	}
	
	static boolean checkMap(int[][] m) {
		for(int i=0; i<N; i++) 
			for(int j=0; j<N; j++) 
				if(m[i][j] == 0) return false;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<int[]>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virus.add(new int[] {i,j});
			}
		}
		if(checkMap(map)) {
			System.out.println(0);
			return;
		}
		arr = new int[virus.size()];
		result = new int[M];
		for(int i=0; i<virus.size(); i++) arr[i] = i;
		makeComb(0, 0);
		if(flag)
			System.out.println(answer);
		else
			System.out.println(-1);
	}
}
```

### :octocat: 풀이 방법

1. M개 바이러스 선택하는 조합 만들기
2. 선택된 바이러스를 BFS를 이용해 꽉찰때까지 퍼트린다.
3. 꽉찰때 퍼트린 시간이 최소가 되는 조합을 찾는다.
4. 만약 꽉차지 못하면 -1 출력 

### :octocat: 후기

연구소 1 문제에서 활성/비활성만 추가된거같은데 생각보다 별 차이가 없어서 금방 풀 수 있었다!
