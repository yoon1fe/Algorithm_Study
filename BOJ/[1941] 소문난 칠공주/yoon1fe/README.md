# [1941] 소문난 칠공주 - Java

###  :princess: 분류

> 백트래킹
>
> BFS



### :princess: 코드

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
	
	static char[][] seat = new char[5][5];
	static Map<Integer, Dir> map = new HashMap<>();					// (번호 : 좌표) 값이 저장된 map
	static List<Integer> list = new ArrayList<>();
	static int[] dy = {1, -1, 0 ,0};
	static int[] dx = {0, 0, 1, -1};
	static int answer, S, Y, total;
	
	public static void main(String[] args) throws Exception {
		input();
		// 25개 중에서 7개 뽑기 - 조합
		comb(0);
		System.out.println(total);
	}
	
	public static void comb(int idx) {
		// 다솜파가 적어도 네 명 이상 있어야 하므로 가지치기
		if(Y == 4) return;
		
		if(list.size() == 7) {
			// 선택된 자리가 인접해있는지 체크
			total++;
//			answer = (bfs() == true ? answer + 1 : answer);
			return;
		}
		
		for(int i = idx; i < 25; i++) {
			Dir next = map.get(i);
			
			// 다솜파와 도연파 수 구하기
			S = seat[next.y][next.x] == 'S' ? S + 1 : S;
			Y = seat[next.y][next.x] == 'Y' ? Y + 1 : Y;
			list.add(i);
			
			comb(i + 1);
			
			// 백트래킹
			list.remove((Integer)i);
			S = seat[next.y][next.x] == 'S' ? S - 1 : S;
			Y = seat[next.y][next.x] == 'Y' ? Y - 1 : Y;
		}
	}
	
	public static boolean bfs() {
		Queue<Dir> q = new LinkedList<>();
		boolean[][] v = new boolean[5][5];
		boolean[][] m = new boolean[5][5];
		int cnt = 1;
		
		// map 에 표시
		for(int i : list) {
			Dir c = map.get(i);
			m[c.y][c.x] = true; 
		}
		
		q.offer(map.get(list.get(0)));
		v[map.get(list.get(0)).y][map.get(list.get(0)).x] = true;
		
		// BFS로 인접한 애들 확인
		while(!q.isEmpty()) {
			Dir c = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(c.y + dy[i], c.x + dx[i]);
				if(!isIn(next) || v[next.y][next.x] || m[next.y][next.x] == false) continue;
				
				v[next.y][next.x] = true; 
				cnt++;
				q.offer(next);
			}
		}
		// 7개인 경우가 모두 인접한 경우
		return cnt == 7 ? true : false;
	}
	
	public static boolean isIn(Dir c) {
		if(0 <= c.y && c.y < 5 && 0 <= c.x && c.x < 5) return true;
		else return false;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 5; i++) {
			seat[i] = br.readLine().toCharArray();
		}
		
		int idx = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				map.put(idx++, new Dir(i, j));
			}
		}
	}
}
```



### :princess: 풀이 방법

다솜파인 소문난 칠공주가 되는 경우의 수를 구하는 문제입니다.

친하게 좀 지내지ㅣ;;

 

맨 처음 생각한건.. DFS로 자리를 하나씩 선택하면서 진행하는 방법이었슴니다.. 

하지만 요 방법으로는 십자가를 맹들수가 없읍니다... 고로 잘못된 접근이지요

그래서 25 개 중에서 7개를 뽑는 조합을 만들어서 얘들이 모두 인접한지를 체크함으로써 문제를 해결했습니다.

 

근데 25C7을 하면 480700 번입니당. 겁나게 많져. 요 문제는 가지치기를 할 수 있답니다. 다솜이네가 적어도 네 명 이상 되어야 하기 때문에 이 말인즉슨 소연이네가 4명이 되는 순간 그 뒤로는 볼 필요도 없는것이지요~! 그렇게 하면 대충 5000개쯤으로 쭙니당.

 

7개를 다 고르면 얘들이 모두 인접해있는지를 확인해야 합니다. 여러 방법이 있겠지만 제일 단순하게 2차원 배열을 만들어서 고기서 BFS를 돌려서 인접한 친구들의 수를 셌습니당 허허



### :princess: 후기

**발상의 전환**이 필요할 때!!!

감사합니다!!