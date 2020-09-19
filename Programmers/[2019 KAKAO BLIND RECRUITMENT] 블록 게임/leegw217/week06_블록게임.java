package week06;

import java.util.ArrayList;
import java.util.List;
public class week06_블록게임 {
	int answer = 0;
	int checkBlock(int x, int y, int[][] board) {
		int color = board[x][y];
		if(checkMap(x+1, y+2, board))
			if(board[x+1][y] == color)
				if(board[x+1][y+1] == color) 
					if(board[x+1][y+2] == color) return 1; // 오른쪽 긴ㄴ 모양
		if(checkMap(x+1, y-2, board))
			if(board[x+1][y] == color)
				if(board[x+1][y-1] == color) 
					if(board[x+1][y-2] == color) return 2; // 왼쪽 긴ㄴ 모양
		if(checkMap(x+1,y-1,board) && checkMap(x+1,y+1,board))
			if(board[x+1][y] == color)
				if(board[x+1][y-1] == color) 
					if(board[x+1][y+1] == color) return 3; // ㅗ 모양
		if(checkMap(x+2,y+1,board))
			if(board[x+1][y] == color)
				if(board[x+2][y] == color)
					if(board[x+2][y+1] == color) return 4; // 오른쪽 짧은ㄴ모양
		if(checkMap(x+2,y-1,board))
			if(board[x+1][y] == color)
				if(board[x+2][y] == color)
					if(board[x+2][y-1] == color) return 5; // 왼쪽 짧은ㄴ모양
		return -1;
	}
	
	boolean checkMap(int x, int y, int[][] board) {
		if(x<0||x>=board.length||y<0||y>=board.length) return false;
		return true;
	}
	
	boolean checkUp(int blockNum, int x, int y, int[][] board, List<Integer> cantDelete) {
		int xstart=0, ystart=0, yend=0, color=board[x][y];
		switch(blockNum) {
		case 1: ystart=y+1; yend=y+2; xstart=x; break;
		case 2: ystart=y-2; yend=y-1; xstart=x; break;
		case 3: ystart=y-1; yend=y+1; xstart=x; break;
		case 4: ystart=y+1; yend=y+1; xstart=x+1; break;
		case 5: ystart=y-1; yend=y-1; xstart=x+1; break;
		}
		for(int i=ystart; i<=yend; i++) // 지울 수 있는지 검사
			for(int j=xstart; j>=0; j--) 
				if(board[j][i]!=color&&board[j][i]!=0) return false;

		for(int i=0; i<board.length; i++) // 지우기
			for(int j=0; j<board.length; j++) 
				if(board[i][j] == color) 
					board[i][j] = 0;
		answer++;
		return true;
	}
	
	public int solution(int[][] board) {
        List<Integer> cantDelete = new ArrayList<Integer>();
        while(true) { // 검은돌 한줄 내려서 지울 수 있는 블록 지우기
        	int cnt = 0;
        	for(int i=0; i<board.length; i++)
            	for(int j=0; j<board.length; j++) 
            		if(board[i][j] != 0) {
            			if(cantDelete.contains(board[i][j])) continue;
            			int blockNum = checkBlock(i, j, board);
            			if(blockNum == -1) cantDelete.add(board[i][j]);
            			else if(checkUp(blockNum, i, j, board, cantDelete)) cnt++;
            		}
        	cantDelete.clear();
        	if(cnt == 0) break;
        }
        return answer;
    }
}