# [20058] 마법사 상어와 파이어스톰 - Java

###  :shark: 분류

> 시뮬레이션
>



### :shark: 코드

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
	
	static int N, Q;
	static int[][] map;
	static int[] order;
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int remained, maxVal;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		input();
		
		solve();
	}
	
	public static void solve() {
		v = new boolean[1 << N][1 << N];
		
		// 파이어 스톰 Q 번 시행
		for(int i = 0; i < order.length; i++) {
			rotate(order[i]);
		}
		
		// 남아있는 얼음의 합
		for(int i = 0; i < (1 << N); i++) {
			for(int j = 0; j < (1 << N); j++) {
				remained += map[i][j];
			}
		}
		
		// 가장 큰 덩어리가 차지하는 칸의 개수
		for(int i = 0; i < (1 << N); i++) {
			for(int j = 0; j < (1 << N); j++) {
				if(map[i][j] == 0 || v[i][j] == true) continue;
				maxVal = Math.max(maxVal, bfs(new Dir(i, j)));
			}
		}
		
		System.out.println(remained + "\n" + maxVal);
	}
	
	public static int bfs(Dir s) {
		int sum = 1;
		Queue<Dir> q = new LinkedList<>();
		v[s.y][s.x] = true;
		
		q.offer(s);
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next) || v[next.y][next.x] == true || map[next.y][next.x] == 0) continue;
				
				v[next.y][next.x] = true;
				q.offer(next);
				sum++;
			}
		}
		
		return sum;
	}

	public static void rotate(int L) {
		// L에 맞게 인덱스 구하기
		for(int i = 0; i < (1 << N); i += (1 << L)) {
			for(int j = 0; j < (1 << N); j += (1 << L)) {
				// 회전할 애들
				List<List<Integer>> list = new ArrayList<>();
				for(int y = i; y < i + (1 << L); y++) {
					List<Integer> temp = new ArrayList<>();
					for(int x = j; x < j + (1 << L); x++) {
						temp.add(map[y][x]);
					}
					list.add(temp);
				}
				
				// 회전
				for(int x = j + (1 << L) - 1, lIdx = 0; x >= j; x--, lIdx++) {
					for(int y = i, idx = 0; y < i + (1 << L); y++, idx++) {
						map[y][x] = list.get(lIdx).get(idx);
					}
				}
			}
		}
		
		// 인접한 칸의 얼음의 수가 2개 이하인 곳 찾아서 얼음양 줄이기
		int[][] tempMap = new int[1 << N][1 << N];
		
		for(int i = 0; i < (1 << N); i++) {
			for(int j = 0; j < (1 << N); j++) {
				if(map[i][j] == 0) continue;
				int adjCnt = 0;
				
				for(int k = 0; k < 4; k++) {
					Dir next = new Dir(i + dy[k], j + dx[k]);
					adjCnt += check(next);
				}
				
				tempMap[i][j] = adjCnt <= 2 ? map[i][j] - 1 : map[i][j];
			}
		}
		
		map = tempMap;
	}

	public static boolean isIn(Dir cur) {
		if(0 <= cur.y && cur.y < (1 << N) && 0 <= cur.x && cur.x < (1 << N)) return true;
		else return false;
	}
	
	public static int check(Dir cur) {
		if(isIn(cur) && map[cur.y][cur.x] != 0) return 1;
		else return 0;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); Q = Integer.parseInt(st.nextToken());
		
		map = new int[1 << N][1 << N];
		order = new int[Q];
		
		for(int i = 0; i < (1 << N); i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < (1 << N); j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < Q; i++) order[i] = Integer.parseInt(st.nextToken());
	}
}
```



### :shark: 풀이 방법

삼성 2020 하반기 SW 역량 테스트 오후 2번 문제입니다. 사실 2번인진 모르게씀니다.

뭐 이것저것 열심히 하라고 합니다. 구하라는거 잘 구하면 간단히 풀 수 있습니다.

이 문제는 L의 크기에 따라서 알맞게 격자를 나누는게 관건이라고 생각합니다. 돌리는건 뭐 부수적인것이죠.

첨에 어떻게 작은 네모들로 나누나 걱정했는데 인풋이 제곱으로 들어오길래 비트마스킹을 썼다가 해결책이 되게 빨리 났슴니다. i, j를 2중으로 반복하는데 각각을 2^L 만큼 더해주면서 늘리면 각각 작은 네모의 시작 인덱스가 되는 것을 말이져.. 사실 누구나 생각했을 껍니다 ㅎ.

 

쨌든 큰 반복문을 이렇게 돌려서 작은 네모들의 시작 인덱스를 찾았습니다. 그리고 그 안에서 2^L 만큼 반복해서 90도 회전하면 됩니다. 저는 그냥 리스트에 넣어서 간단하게 구현했습니다.

 

회전을 이쁘게 잘 하면 그 다음은 인접한 얼음의 개수를 구하고 2개 이하인 위치의 얼음 개수를 하나 빼주어야 합니다. N * N 돌려서 체크하면 됩니당.!

 

요것을 Q번 만큼 수행하면 이제 복잡한 로직은 끝입니다.

문제에서 구하라는 것이 남아 있는 얼음의 합과 가장 큰 덩어리의 얼음의 개수입니다. 전자도 N * N 돌려서 합하면 되고, 후자도 N * N 돌려서 방문하지 않은 곳에서 BFS를 돌려서 구하면 됩니다. BFS는 눈감고 짤 수 있으니깐 설명은 패스



### :shark: 후기

아~~ 한시간도 안걸려서 풀어버렸네~~

오후 걸렸어도 통과했겠구만~`~~ 허 참내~~~^^^^^

감사합니다 ^^;