package Programmers;

import java.util.*;

public class Solution {
	static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int N;
	
	public static boolean isIn(int y, int x) {
		if(0 <= y && y < N && 0 <= x && x < N) return true;
		else return false;
	}
	
	public int solution(int[][] board) {
        int answer = 0;
        Map<Integer, Dir> blocks = new HashMap<>();		// 블럭 번호 : 블럭 시작 인덱스
        N = board.length;
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		if(board[j][i] == 0) continue;
        		if(blocks.containsKey(board[j][i])) continue;
        		
        		blocks.put(board[j][i], new Dir(j, i));
        	}
        }
        
        for(Integer k : blocks.keySet()) {
//        	System.out.println(k + " : " + blocks.get(k).y + ", " + blocks.get(k).x);
        	List<Integer> available = new ArrayList<>();		// 없앨 수 있는 블럭 번호
        	
        }

        while(true) {
        	List<Integer> available = new ArrayList<>();		// 없앨 수 있는 블럭 번호
        	for(Integer k : blocks.keySet()) {
        		if(checkRect(blocks.get(k), board)) {
        			available.add(k);
        		}
        	}
        	
        	for(int i : available) {
        		removeBlocks(blocks.get(i), board);
        		blocks.remove(i);
        		answer++;
        	}

        	if(available.size() == 0) break;
        }
        
        return answer;
    }
	
	public static boolean checkRect(Dir s, int[][] board) {
		int boardNo = board[s.y][s.x];
		
		if((isIn(s.y+1, s.x) && board[s.y+1][s.x] == boardNo) && 
			(isIn(s.y+1, s.x+1) &&board[s.y+1][s.x+1] == boardNo) && 
			(isIn(s.y+1, s.x+2) &&board[s.y+1][s.x+2] == boardNo)) {
			if(board[s.y][s.x+1] == 0 && board[s.y][s.x+2] == 0) {
				for(int i = 0; i < s.y; i++) {
					if(board[i][s.x+1] != 0) return false;
					if(board[i][s.x+2] != 0) return false;
				}
				return true;
			}
		}else if((isIn(s.y, s.x+1) && board[s.y][s.x+1] == boardNo) && 
				(isIn(s.y-1, s.x+1) && board[s.y-1][s.x+1] == boardNo) && 
				(isIn(s.y-2, s.x+1) && board[s.y-2][s.x+1] == boardNo)) {
			if(board[s.y-1][s.x]== 0 && board[s.y-2][s.x] == 0) {
				for(int i = 0; i < s.y-1; i++) {
					if(board[i][s.x] != 0) return false;
				}
				for(int i = 0; i < s.y-2; i++) {
					if(board[i][s.x] != 0) return false;
				}
				return true;
			}
		}else if((isIn(s.y+1, s.x) && board[s.y+1][s.x]== boardNo) && 
				(isIn(s.y+2, s.x) && board[s.y+2][s.x] == boardNo) && 
				(isIn(s.y+2, s.x+1) && board[s.y+2][s.x+1] == boardNo)) {
			if(board[s.y][s.x+1] == 0 && board[s.y+1][s.x+1] == 0) {
				for(int i = 0; i < s.y; i++) {
					if(board[i][s.x+1] != 0) return false;
				}
				for(int i = 0; i < s.y+1; i++) {
					if(board[i][s.x+1] != 0) return false;
				}
				return true;
			}
		}else if((isIn(s.y-1, s.x+1) &&board[s.y-1][s.x+1] == boardNo) && 
				(isIn(s.y, s.x+1) && board[s.y][s.x+1]== boardNo) && 
				(isIn(s.y, s.x+2) && board[s.y][s.x+2] == boardNo)) {
			if(board[s.y-1][s.x]== 0 && board[s.y-1][s.x+2] == 0) {
				for(int i = 0; i < s.y-1; i++) {
					if(board[i][s.x] != 0) return false;
					if(board[i][s.x+2] != 0) return false;
				}
				return true;
			}
		}else if((isIn(s.y, s.x+1) && board[s.y][s.x+1] == boardNo) && 
				(isIn(s.y, s.x+2) && board[s.y][s.x+2] == boardNo) && 
				(isIn(s.y-1, s.x+2) && board[s.y-1][s.x+2] == boardNo)) {
			if(board[s.y-1][s.x] == 0 && board[s.y-1][s.x+1] == 0) {
				for(int i = 0; i < s.y-1; i++) {
					if(board[i][s.x] != 0) return false;
					if(board[i][s.x+1] != 0) return false;
				}
				return true;
			}
		}
		
		return false;
	}

	public void removeBlocks(Dir start, int[][] board) {
		Queue<Dir> q = new LinkedList<>();
		int blockNo = board[start.y][start.x];
		q.offer(start);
		
		while(!q.isEmpty()) {
			Dir cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
				if(!isIn(next.y, next.x) || board[next.y][next.x] != blockNo ) continue;
				board[next.y][next.x] = 0;
				q.offer(next);
			}
		}
	}

	
	public static void main(String[] args) {
		Solution s = new Solution();
		int[][] b = {
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,4,0,0,0},
				{0,0,0,0,0,4,4,0,0,0},
				{0,0,0,0,3,0,4,0,0,0},
				{0,0,0,2,3,0,0,0,5,5},
				{1,2,2,2,3,3,0,0,0,5},
				{1,1,1,0,0,0,0,0,0,5}};
		
		System.out.println(s.solution(b));
	}
}