# [16234] 인구 이동 - Java

###  :family_man_girl_boy: 분류

> 시뮬레이션
>
> BFS

​

### :family_man_girl_boy: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;

		public Dir(int y, int x) {
			this.y = y; this.x = x;
		}
	}
	
	static int[][] map;
	static int N, L, R;
	static boolean[][] v;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
		else return false;
	}
	
	public static boolean bfs(Dir s) {
		List<Dir> union = new ArrayList<>();
		Queue<Dir> q = new LinkedList<>();
		int newPopulation = 0;
		
		q.offer(s);
		newPopulation = map[s.y][s.x];
		v[s.y][s.x] = true;
		union.add(s);
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next)) continue;
				int diff = Math.abs(map[cur.y][cur.x] - map[next.y][next.x]);
				if(v[next.y][next.x] || L > diff || R < diff) continue;
				
				v[next.y][next.x] = true;
				q.offer(next);
				union.add(next);
				newPopulation += map[next.y][next.x];
			}
		}

		newPopulation /= union.size();
		
		for(Dir d : union) map[d.y][d.x] = newPopulation; 
		
		return union.size() > 1 ? true : false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int answer = 0;
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			boolean flag = false;
			v = new boolean[N][N];

			for(int i = 0 ; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(v[i][j]) continue;
					flag = bfs(new Dir(i, j)) | flag;
				}
			}
			if(!flag) break;
			answer++;
		}
		
		System.out.println(answer);
	}
}
```



### :family_man_girl_boy: 풀이 방법

인접한 나라를 고려한다? 그렇다면 BFS져!

한 좌표에서 인접한 나라들을 봐줄 것이기 때문에 BFS를 돌려줍니다. 이 때 인접한 두 나라의 인구의 차를 갖고 조건에 맞는 경우에만 Queue에 넣어줍니다. 나중에 조건에 맞는 연합을 새로운 인구수로 갱신해줘야 하기 때문에 연합의 좌표값을 갖는 List도 하나 뒀습니다.

 

프로그램의 종료 조건은 더이상 인구 이동이 없을 때까지 입니다. 그렇기 때문에 bfs 메소드에서 인구 이동이 있었는지 여부를 boolean 형태로 리턴했습니다. union의 크기가 2 이상이라면 조건에 맞는 인접한 나라가 있다는 의미니깐 인구 이동이 있었겠져? 한 턴마다 flag 변수를 두고 한 번이라도 true였다면 인구 이동이 있었다는 거니깐 |(or) 연산자를 사용해줬습니다.

 

그리고 한 턴에 여러 개의 연합이 있을 수 있겠죠. 그렇기 때문에 턴마다 v[][] 배열을 새로 초기화해줬습니다.



### :family_man_girl_boy: 후기

아직 많이 부족하지만 실력이 늘었음을 느낍니다,,, 10분컷!!

꾸준히!!! 화이팅!!!