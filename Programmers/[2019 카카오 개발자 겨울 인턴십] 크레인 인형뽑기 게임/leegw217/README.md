# [2019 카카오 개발자 겨울 인턴십] 크레인 인형뽑기 게임 - Java

###  :octocat: 분류

> 스택

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

1. moves 배열 순서대로 인형을 뽑는다.
2. 뽑은 인형을 스택에 넣기전 가장 위에 인형이랑 같으면 스택 pop 해주고 answer에 2 추가

### :octocat: 후기

역시 1번문제! 호다닥 풀었습니다!
