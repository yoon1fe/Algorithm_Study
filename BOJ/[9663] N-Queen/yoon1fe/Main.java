import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static int[] board;

	public static void main(String[] args) throws IOException {
		input();
		
		dfs(1);
		System.out.println(answer);
	}
	
	public static void dfs(int row) {
		if(row == N + 1) {
			answer++;
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			board[row] = i;
			if(check(row) == true) dfs(row + 1);
		}
	}
	
	public static boolean check(int x) {
		for(int i = 1; i < x; i++) {
			if(board[x] == board[i] || Math.abs(board[x] - board[i]) == x - i) return false;
		}
		
		return true;
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N + 1];
	}
}
