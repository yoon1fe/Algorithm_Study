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