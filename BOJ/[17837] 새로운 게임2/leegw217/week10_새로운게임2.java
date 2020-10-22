package week10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class week10_새로운게임2 {
	static class chess {
		int index;
		int x, y;
		int dir;
		chess(int index, int x, int y, int dir) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] board = new int[N+1][N+1];
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				board[i][j] = sc.nextInt();
		List<chess>[][] chessBoard = new ArrayList[N+1][N+1];
		for(int i=0; i<=N; i++)
			for(int j=0; j<=N; j++)
				chessBoard[i][j] = new ArrayList<chess>();
		List<chess> Chess = new ArrayList<chess>();
		for(int i=0; i<K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int dir = sc.nextInt();
			if(dir == 2) dir = 3;
			else if(dir == 3) dir = 0;
			else if(dir == 4) dir = 2;
			Chess.add(new chess(i,x,y,dir));
			chessBoard[x][y].add(new chess(i,x,y,dir));
		}
		for(int t=1; t<=1000; t++) {
			for(int k=0; k<Chess.size(); k++) {
				int index = Chess.get(k).index;
				int x = Chess.get(k).x;
				int y = Chess.get(k).y;
				int dir = Chess.get(k).dir;
				int nx = x+dx[dir];
				int ny = y+dy[dir];
				if(nx<1||nx>N||ny<1||ny>N||board[nx][ny] == 2) {
					dir = (dir+2)%4;
					Chess.get(k).dir = dir;
					nx = x+dx[dir];
					ny = y+dy[dir];
				}
				if(nx<1||nx>N||ny<1||ny>N||board[nx][ny] == 2) continue;
				int start = 0;
				List<chess> temp = new ArrayList<chess>();
				for(int i=0; i<chessBoard[x][y].size(); i++) 
					if(chessBoard[x][y].get(i).index == index) {
						start = i;
						break;
					}
				temp = chessBoard[x][y].subList(start, chessBoard[x][y].size());
				for(int i=0; i<temp.size(); i++) {
					Chess.get(temp.get(i).index).x = nx;
					Chess.get(temp.get(i).index).y = ny;
				}
				if(board[nx][ny] == 1) Collections.reverse(temp);
				chessBoard[nx][ny].addAll(temp);
				chessBoard[x][y].removeAll(temp);
				if(chessBoard[nx][ny].size() >= 4) {
					System.out.println(t);
					System.exit(0);
				}
			}
		}
		System.out.println(-1);
	}
}