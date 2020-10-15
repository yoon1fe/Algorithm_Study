package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static interface Block{}
	
	static class OneBlock implements Block{
		Dir loc;
		OneBlock(int y, int x){
			this.loc = new Dir(y, x);
		}
	}
	
	static class HorBlock implements Block{
		Dir loc1, loc2;
		HorBlock(int y, int x){
			this.loc1 = new Dir(y, x); this.loc2 = new Dir(y, x + 1);
		}
	}
	
	static class VerBlock implements Block{
		Dir loc1, loc2;
		VerBlock(int y, int x){
			this.loc1 = new Dir(y, x); this.loc2 = new Dir(y + 1, x);
		}
	}
	
	static int[][] gBoard = new int[10][4];
	static int[][] bBoard = new int[10][4];
	static int score, max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			max = i;
			switch(t) {
			case 1: moveDown(gBoard, new OneBlock(y, x), i); moveDown(bBoard, new OneBlock(x, y), i); break;
			case 2: moveDown(gBoard, new HorBlock(y, x), i); moveDown(bBoard, new VerBlock(x, y), i); break;
			case 3: moveDown(gBoard, new VerBlock(y, x), i); moveDown(bBoard, new HorBlock(x, y), i); break;
			}
			
			checkScore(gBoard);
			checkScore(bBoard);
			
			checkSpecialArea(gBoard);
			checkSpecialArea(bBoard);
			
			while(true) {
				if(checkScore(gBoard) == false) break;
			}
			while(true) {
				if(checkScore(bBoard) == false) break;
			}
		}
		
		System.out.println(score);
		System.out.println(checkTile(gBoard) + checkTile(bBoard));
	}

	public static void moveDown(int[][] board, Block block, int no) {
		if(block instanceof OneBlock) {
			int y = 0, x = ((OneBlock) block).loc.x;
			for(y = ((OneBlock) block).loc.y; y <10; y++) {
				if(board[y][x] == 0) continue;
				break;
			}
			board[y - 1][x] = no;
		}else if(block instanceof HorBlock) {
			int y = 0, x1 = ((HorBlock) block).loc1.x, x2 = ((HorBlock) block).loc2.x;
			for(y = ((HorBlock) block).loc1.y; y <10; y++) {
				if(board[y][x1] == 0 && board[y][x2] == 0) continue;
				break;
			}
			board[y - 1][x1] = no; board[y - 1][x2] = no;
		}else if(block instanceof VerBlock){
			int y = 0, x = ((VerBlock) block).loc1.x;
			for(y = ((VerBlock) block).loc2.y; y <10; y++) {
				if(board[y][x] == 0) continue;
				break;
			}
			board[y - 2][x] = no; board[y - 1][x] = no;
		}
	}
	
	public static boolean checkScore(int[][] board) {
		List<Integer> removeIdx = new ArrayList<>();
		for(int i = 6; i < 10; i++) {
			boolean flag = true;
			for(int j = 0; j < 4; j++) {
				flag = flag & board[i][j] != 0;
			}
			if(flag) removeIdx.add(i);
		}
		score += removeIdx.size();
		
		if(removeIdx.size() >= 1) {
			for(int i = removeIdx.get(removeIdx.size() - 1); i >= removeIdx.size(); i--) {
				System.arraycopy(board[i - removeIdx.size()], 0, board[i], 0, 4);
			}
			for (int i = 4; i < 4 + removeIdx.size(); i++) {
				for (int j = 0; j < 4; j++) { 
					board[i][j] = 0;
				}
			}
			
			boolean[] v = new boolean[max + 1];
			
			for(int i = 9; i >= 4; i--) {
				for(int j = 0; j < 4; j++) {
					int curNo = board[i][j];
					if(board[i][j] == 0 || v[board[i][j]]) continue;
					v[board[i][j]] = true;
					if(board[i][j] == board[i - 1][j]) {

						for(int k = i + 1; k < 10; k++) {
							if(k == 9 && board[k][j] == 0) {
								board[i][j] = 0; board[i - 1][j] = 0; 
								board[k][j] = curNo; board[k - 1][j] = curNo; break;
							}
							if(board[k][j] == 0) continue;
							board[i][j] = 0; board[i - 1][j] = 0; 
							board[k - 1][j] = curNo; board[k - 2][j] = curNo; break;
						}
					
					}else if(j + 1 <= 3 && board[i][j] == board[i][j + 1]) {
						for(int k = i + 1; k < 10; k++) {
							if(k ==9 && board[k][j] == 0 && board[k][j + 1] == 0) {
								board[i][j] = 0; board[i][j + 1] = 0; 
								board[k][j] = curNo; board[k][j + 1] = curNo; break;
							}
							if(board[k][j] == 0 && board[k][j + 1] == 0) continue;
							board[i][j] = 0; board[i][j + 1] = 0; 
							board[k - 1][j] = curNo; board[k - 1][j + 1] = curNo; break;
						}
						
					}else {
						for(int k = i + 1; k < 10; k++) {
							if(k == 9 && board[k][j] == 0) {
								board[i][j] = 0; board[k][j] = curNo; break;
							}
							if(board[k][j] == 0 ) continue;
							board[i][j] = 0; board[k - 1][j] = curNo; break;
						}
					}
					
				}
			}
		}

		return removeIdx.size() > 0 ? true : false;
	}

	public static void checkSpecialArea(int[][] board) {
		int cnt = 0;
		for(int i = 4; i < 6; i++) {
			boolean flag = false;
			for(int j = 0; j < 4; j++) {
				flag = flag | board[i][j] != 0;
			}
			cnt = flag == true ? cnt + 1: cnt;
		}
	
		for(int c = 0; c < cnt; c++) {
			for(int i = 9; i >= 4; i--) {
				System.arraycopy(board[i - 1], 0, board[i], 0, 4);
			}
		}
	}

	public static int checkTile(int[][] board) {
		int tileCnt = 0;
		for(int i = 6; i < 10; i++) {
			for(int j = 0; j < 4; j++) {
				if(board[i][j] != 0) tileCnt++;
			}
		}
		return tileCnt;
	}

}