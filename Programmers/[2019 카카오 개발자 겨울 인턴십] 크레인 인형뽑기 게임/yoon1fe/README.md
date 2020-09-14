## [2019 카카오 개발자 겨울 인턴십] 크레인 인형뽑기 게임 - Java

### :dolls: 분류

> 스택



###  :dolls: 코드

```java
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
```



### :dolls: 풀이 방법

**스택**을 이용하는 아주 간단한 문제입니다.

moves 배열에 들어오는 값을 따라 해당하는 인형의 번호를 찾아서 스택에 넣습니다. 이 때 만약 스택의 top에 있는 친구랑 막 들어오려는 친구랑 같다? 그럼 고냥 바로 탑에 있는 친구를 빼고 answer+=2 하면 되는 겁니다.



매우 EASY!



###  :dolls: 후기

카카오 공채 코테는 말아먹었지만 다시 처음부터 화이팅!!