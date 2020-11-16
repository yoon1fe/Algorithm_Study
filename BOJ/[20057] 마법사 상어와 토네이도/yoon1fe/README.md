# [20057] 마법사 상어와 토네이도 - Java

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
	
	static int N;
	static int[][] map;
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {-1, 0, 1, 0};
	static int[][][] spread = {
			{ { 0, 0, 2, 0, 0 }, { 0, 10, 7, 1, 0 }, { 5, 55, 0, 0, 0 }, { 0, 10, 7, 1, 0 }, { 0, 0, 2, 0, 0 } },
			{ { 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0 }, { 2, 7, 0, 7, 2 }, { 0, 10, 55, 10, 0 }, { 0, 0, 5, 0, 0 } },
			{ { 0, 0, 2, 0, 0 }, { 0, 1, 7, 10, 0 }, { 0, 0, 0, 55, 5 }, { 0, 1, 7, 10, 0 }, { 0, 0, 2, 0, 0 } },
			{ { 0, 0, 5, 0, 0 }, { 0, 10, 55, 10, 0 }, { 2, 7, 0, 7, 2 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 0 } } };

	public static void main(String[] args) throws Exception {
		input();
		
		System.out.println(solve(0));
	}
	
	public static int solve(int d) {
		int answer = 0, cnt = 0, n = 1, twoCnt = 0;
		
		Dir tornado = new Dir(N / 2, N / 2);
		
		while(tornado.y != 0 || tornado.x != 0) {
			cnt++;
			tornado.y += dy[d]; tornado.x += dx[d];
			
			answer += move(tornado, d); 
		
			if(cnt == n) {
				twoCnt++;
				if(twoCnt == 2) {
					n++;
					twoCnt = 0;
				}
				cnt = 0;
				d = (d + 1) % 4;
			}
		}
		
		return answer;
	}
	
	public static int move(Dir c, int d) {
		int out = 0;
		int sand = map[c.y][c.x];
		Dir next = new Dir(c.y, c.x);
		int nextSand = 0;
		
		int sum = 0;
		for(int i = -2; i < 3; i++) {
			for(int j = -2; j < 3; j++) {
				next = new Dir(c.y + i, c.x + j);
				nextSand = (int)((sand * spread[d][i + 2][j + 2]) / 100);
				sum = spread[d][i+2][j+2] != 55 ? sum + nextSand : sum;
				if(spread[d][i + 2][j + 2] == 55) continue;
				
				out += update(next, nextSand);
			}
		}
		
		next.y = c.y + dy[d]; next.x = c.x + dx[d];
		nextSand = sand - sum;
		out += update(next, nextSand);
		
		map[c.y][c.x] = 0;
		return out;
	}

	private static int update(Dir next, int nextSand) {
		if(!isIn(next)) return nextSand;
		else {
			map[next.y][next.x] += nextSand;
			return 0;
		}
	}

	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
		else return false;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
```



### :shark: 풀이 방법

삼성 하반기 오후 문제 중 하나입니다.

와 문제 이해를 잘못해서 한시간 반을 넘겨부렀슴다 ^^;;;;



먼저 모래가 퍼지는 퍼센티지를 5x5 2차원 배열로 만들어서 쉽게 구해씀니다. 요것은 다른 블로그를 참조 ^^

이 문제는 토네이도의 움직임을 구현하는게 일단 관건이라고 생각합니다. 여러 방법이 있겠지만 길이 n=1 부터 두 번씩 이동하면 n++ 되는 규칙을 찾아서 그렇게 풀어씀니다.

 

그리고 이동한 후 퍼지는 애들을 체크하는 메소드는 move() 입니다. 요기서 방향(d)에 따라 5x5 배열에서 체크하면서 갱신해주면 됩니다. update()에서 만약 새로운 좌표가 밖으로 나간다면 out에 더해주고, 그렇지 않으면 map을 갱신해주면 됩니다.

 

그리고 여기서 시간을 많이 뺏겼씀니다. ㅜㅜㅜ 알파 위치에는 그냥 단순히 55%가 드가는줄 알고 왜 안되나 해씀다.... 고것이 아니라 다른데 다 퍼지고 남은 만큼 갱신해줘야 하는 것이어씀다.... ㅠㅠ



### :shark: 후기

오후 시험 쳤으면 떨어질뻔!!! 킼킼

감사합니다~~~!!