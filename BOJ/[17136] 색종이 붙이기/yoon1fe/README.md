# [17136] 색종이 붙이기 - Java

###  :memo: 분류

> 완전 탐색
>
> 백트래킹



### :memo: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] map = new int [10][10];
	static int[] paper = {0, 5, 5, 5, 5, 5}; 
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		input();
		
		dfs(0, 0, 0);
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	public static void dfs(int y, int x, int cnt) {
		if (y == 9 && x > 9) {
			answer = Math.min(answer, cnt);
			return;
		}

		if (answer <= cnt) return;

		if (x == 10) {
            dfs(y + 1, 0, cnt);  return;
        }
		
		if (map[y][x] == 1) {
			for (int l = 5; l > 0; l--) {
				if (paper[l] == 0 || !check(y, x, l)) continue;

				action(y, x, l, 0);
				dfs(y, x + 1, cnt + 1);
				action(y, x, l, 1);
			}
		} else dfs(y, x + 1, cnt);
		
	}
	
	public static boolean isIn(int y, int x) {
		if(0 <= y && y < 10 && 0 <= x && x < 10) return true;
		else return false;
	}
	
	public static boolean check(int y, int x, int length) {
		for(int i = y; i < y + length; i++) {
			for(int j = x; j < x + length; j++) {
				if(!isIn(i, j) || map[i][j] == 0) return false;
			}
		}

		return true;
	}
	
	public static void action(int y, int x, int length, int action) {
		paper[length] = action == 0 ? paper[length] - 1: paper[length] + 1;
		for(int i = y; i < y + length; i++) {
			for(int j = x; j < x + length; j++) {
				map[i][j] = action;
			}
		}
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 10; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
}
```



### :memo: 풀이 방법

아 어렵다 백트래킹~~!!!

예전에는 얼토당토않게 풀었다 틀렸는데

많은 도움받고 겨우 풀어씀다..



큰 로직은 이렇습니다.

2차원 배열을 돌면서 1이면 변의 길이가 1~5까지 보면서 붙일 수 있는 경우 붙이고 난 뒤 dfs로 계속 들어갑니다. 나오면 붙인 부분을 다시 리커버리시켜주구요. 

 

check() 메소드는 변의 길이가 length인 색종이를 붙일 수 있는지 여부를 반환하고, action() 메소드는 action 파라미터에 따라 색종이를 붙이고 떼는 메소드입니다.

 

dfs() 메소드는 맨마지막까지 봐주고 나서 사용한 색종이의 개수를 보고 answer 를 갱신해주면 됩니다.

### :memo: 후기

하~~ 한시간반 넘게 걸렸네 ^^;;;

감사합니다..ㅜ