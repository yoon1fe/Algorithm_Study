# [17141] 연구소 2 - Java

###  :hospital: 분류

> 시뮬레이션
>
> BFS
>
> 조합



### :hospital: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static List<Dir> virusLoc = new ArrayList<>();
	static boolean[] selectedVirus;
	static int emptySpace;
	
	public static void main(String[] args) throws Exception {
		input();
		
		// 바이러스 놓을 수 있는 곳 중에서 M 개 선택 - 조합
		comb(0, 0);
		
		System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
	}

	public static void comb(int cnt, int idx) {
		if(cnt == M) {
			answer = Math.min(answer, spreadVirus());
			return;
		}
		
		for(int i = idx; i < virusLoc.size(); i++) {
			selectedVirus[i] = true;
			comb(cnt + 1, i + 1);
			selectedVirus[i] = false;
		}
	}

	public static int spreadVirus() {
		Queue<Dir> q = new LinkedList<>();
		int[][] v = new int[N][N];
		int maxDay = 0, remains = emptySpace;
		
		for(int i = 0; i < virusLoc.size(); i++) {
			if(selectedVirus[i]) {
				q.offer(virusLoc.get(i));
				v[virusLoc.get(i).y][virusLoc.get(i).x] = 1;
			}else {
				v[virusLoc.get(i).y][virusLoc.get(i).x] = 0;
				remains++;
			}
		}
		
		while(!q.isEmpty()) {
			if(remains == 0) break;
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next) || map[next.y][next.x] == 1 || v[next.y][next.x] > 0) continue;
				remains--;
				
				v[next.y][next.x] = v[cur.y][cur.x] + 1;
				q.offer(next);
				maxDay = Math.max(maxDay, v[next.y][next.x]);
			}
		}
		
		return remains == 0 ? (maxDay == 0 ? 0 : maxDay - 1) : Integer.MAX_VALUE;
	}
	
	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
		else return false;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virusLoc.add(new Dir(i, j));
				else if(map[i][j] == 0) emptySpace++;
			}
		}
		
		selectedVirus = new boolean[virusLoc.size()];
	}
}

```



### :hospital: 풀이 방법

예전에 풀었던 연구소 시리즈 문제입니다.

왜 3을 먼저 풀었지 ;;; 3보다 조건이 덜 까다로운 문제임니다 ^^;;



연구소 3 문제 코드랑 아주 비슷합니다 허허..

여기서는 **선택되지 않은 바이러스는 그냥 빈 칸**으로 생각해주고 풀면 끝이랍니다ㅎㅎ

나머지 자세한 내용은 [여기루](https://yoon1fe.tistory.com/130)! 히히

 

### :hospital: 후기

아우 하나 날로 먹었습니다

감사합니다!!