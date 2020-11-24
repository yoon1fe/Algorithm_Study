# [1799] 비숍 - Java

###  :princess: 분류

> 백트래킹
>



### :princess: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] answer = new int[2];
	static int[] dy = {1, -1, -1, 1};
	static int[] dx = {1, -1, 1, -1};
	static int[][] board, colors;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		input();
		
		dfs(-1, 0, 1);
		dfs(-1, 0, 0);

		System.out.println(answer[0] + answer[1]);
	}
	
	public static void dfs(int check, int cnt, int color) {
		answer[color] = Math.max(answer[color], cnt);
	 
	    for (int i = check + 1; i < N * N; i++) {
	        int c = i / N;
	        int r = i % N;
	 
	        if (colors[c][r] != color) continue;
	        
	        if (board[c][r] == 1) {
	            if (check(c, r)) {
	                v[i] = true;
	                dfs(i, cnt + 1, color);
	            }
	        }
	    }
	    
	    if (check == -1) return;
	    v[check] = false;
	}

	public static boolean check(int c, int r) {
	    for (int i = 0; i < 4; i++) {
	    	int ny = dy[i] + c;
	        int nx = dx[i] + r;
	        int check = ny * N + nx;
	 
	        for (int j = 1; j < N; j++) {
	            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
	                if (v[check]) {
	                    return false;
	                }
	            }
	            ny += dy[i];
	            nx += dx[i];
	            check = ny * N + nx;
	        }
	    }
	    return true;
	}


	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		board = new int[N][N];
		colors = new int[N][N];
		v = new boolean[N*N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if((i + j) % 2 == 0) colors[i][j] = 1;	// (0, 0) 검은색
			}
		}
	}
}
```



### :princess: 풀이 방법

백트래킹 단골 문제 N-Queen 문제의 친구 느낌임니다..

맨첨에 N * N  2차원 배열을 만들어서 백트래킹을 돌렸는데 가지치기까지 해도 시간초과가 떴습니다 ㅜ

그래서 찾아보고 햇슴니다..

비숍은 흰색 판에 있는 애는 흰색만, 검은색 판에 있는 애는 검은색만 갈 수 있습니다. 요골 잘 생각해서 두가지 경우를 따로 봐주면 됩니당.





### :princess: 후기

감사합니다~!~!