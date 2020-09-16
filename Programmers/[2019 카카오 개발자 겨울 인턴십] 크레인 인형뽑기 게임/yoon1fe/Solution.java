import java.util.*;

class Solution {
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> s = new Stack<>();
        
        for(int m : moves) {
        	for(int i = 0; i < board.length; i++) {
        		if(board[i][m-1] == 0) continue;
        		if(!s.isEmpty() && s.peek() == board[i][m-1]) {
        			s.pop();
        			answer += 2;
        		}else s.push(board[i][m-1]);
        		
        		board[i][m-1] = 0;
        		break;
        	}
        }
        return answer;
    }
}