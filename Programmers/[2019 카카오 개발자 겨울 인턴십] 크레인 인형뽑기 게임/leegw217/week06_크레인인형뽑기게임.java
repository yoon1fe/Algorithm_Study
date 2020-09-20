import java.util.Stack;
public class week06_크레인인형뽑기게임 {
	int pick(int col, int[][] board) {
		for(int i=0; i<board.length; i++)
			if(board[i][col] != 0) {
				int doll = board[i][col];
				board[i][col] = 0;
				return doll;
			}
		return -1;
	}
	
	public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> basket = new Stack<Integer>();
        for(int i=0; i<moves.length; i++) {
        	int doll = pick(moves[i]-1, board);
        	if(doll == -1) continue;
        	if(!basket.isEmpty()) {
        		if(basket.peek() == doll) {
        			basket.pop();
        			answer += 2;
        		} else basket.add(doll);
        	} else basket.add(doll);
        }
        return answer;
    }
}