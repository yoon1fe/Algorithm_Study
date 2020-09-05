# [2020 카카오 인턴십] 키패드 누르기 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
public class week04_키패드누르기 {
	public String solution(int[] numbers, String hand) {
        int[][] phone = {{3,1},{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
        int[] lHand = {3, 0};
        int[] rHand = {3, 2};
		StringBuilder sb = new StringBuilder();
        for(int i=0; i<numbers.length; i++) {
        	if(numbers[i]==1 || numbers[i]==4 || numbers[i]==7) {
        		sb.append("L");
        		lHand = phone[numbers[i]];
        	} else if(numbers[i]==3||numbers[i]==6||numbers[i]==9) {
        		sb.append("R");
        		rHand = phone[numbers[i]];
        	} else {
        		int leftLen = Math.abs(lHand[0] - phone[numbers[i]][0]) + Math.abs(lHand[1] - phone[numbers[i]][1]);
        		int rightLen = Math.abs(rHand[0] - phone[numbers[i]][0]) + Math.abs(rHand[1] - phone[numbers[i]][1]);
        		if(leftLen < rightLen) {
        			sb.append("L");
            		lHand = phone[numbers[i]];
        		} else if(leftLen > rightLen) {
        			sb.append("R");
            		rHand = phone[numbers[i]];
        		} else {
        			if(hand.equals("left")) {
        				sb.append("L");
                		lHand = phone[numbers[i]];
        			} else {
        				sb.append("R");
                		rHand = phone[numbers[i]];
        			}
        		}
        	}
        }
        return sb.toString();
    }
}
```

### :octocat: 풀이 방법

1. 번호판 위치와 손가락 위치를 좌표로 저장해놓는다.
2. 번호를 입력받으면 해당 위치로 손가락을 옮긴다.
2. 1,4,7 나오면 왼쪽, 3,6,9 나오면 오른쪽
3. 2,5,8,0 나오면 손가락들과 해당 번호와의 거리를 비교해 짧은 쪽 손가락을 사용한다.
4. 거리가 같으면 오른손잡이면 오른쪽, 왼손잡이면 왼쪽 사용

### :octocat: 후기

예전에 인턴십 코테칠때도 쉽게 풀었던 문제였다. 이지이지
