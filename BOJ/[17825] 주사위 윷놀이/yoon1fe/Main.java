package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	
	static List<Integer>[] board = new List[33];
	static int[] point = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 
			13, 16, 19, 25, 22, 24, 30, 35, 0, 28, 27, 26};
	
	static int[] moves = new int[10];
	static int[] pieces = new int[10];
	static int answer;
	
	public static void makeBoard() {
		for(int i = 0; i < 33; i++) board[i] = new ArrayList<>();
		
		for(int i = 0; i < 20; i++) board[i].add(i + 1);
		board[5].add(21); board[21].add(22); board[22].add(23); board[23].add(24);
		board[10].add(25); board[25].add(26); board[26].add(24);
		board[24].add(27); board[27].add(28); board[28].add(20); board[20].add(29);
		board[15].add(30); board[30].add(31); board[31].add(32); board[32].add(24);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 10; i++) moves[i] = Integer.parseInt(st.nextToken());
		
		makeBoard();

		perm(0);
		
		System.out.println(answer);
	}
	
	static void perm(int cnt) {
		if(cnt == 10) {
			answer = Math.max(answer, move());
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			pieces[cnt]  = i;
			perm(cnt + 1);
		}
	}

	public static int move() {
		int score = 0;
		int[] loc = new int[4];
		boolean[] v = new boolean[33];

		for (int i = 0; i < 10; i++) {
			int curPiece = pieces[i];
			int curPos = loc[curPiece];
			int nextPos;
			int move = moves[i];
			
			if(curPos == 29) continue;
			nextPos = board[curPos].size() > 1 ? board[curPos].get(1) : board[curPos].get(0);

			for (int j = 1; j < move; j++) {
				if(nextPos == 29) break;
				nextPos = board[nextPos].get(0);
			}

			if (nextPos != 29 && v[nextPos]) return 0;
			
			v[nextPos] = true;
			v[curPos] = false;
			loc[curPiece] = nextPos;
			score += point[nextPos];
		}

		return score;
	}
}