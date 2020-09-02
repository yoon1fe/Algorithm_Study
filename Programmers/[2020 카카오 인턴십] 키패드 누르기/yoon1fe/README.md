## [2020 카카오 인턴십] 키패드 누르기 - Java

###    :vibration_mode: ​분류

> 단순 구현



###  :vibration_mode: 코드

```java
import java.util.*;

class Solution {
    static class Dir{
		int y, x;
		Dir(int y, int x){
			this.y = y; this.x = x;
		}
	}
	
	public int getDist(Dir hand, Dir key) {
		return Math.abs(hand.y - key.y) + Math.abs(hand.x - key.x);
	}
	
	public String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        Dir left = new Dir(3, 0);
        Dir right = new Dir(3, 2);
        Map<Integer, Dir> keyPad = new HashMap<>();
        int n = 1;
        for(int i = 0; i < 3; i++) {
        	for(int j = 0; j < 3; j++) {
        		keyPad.put(n++, new Dir(i, j));
        	}
        }
        keyPad.put(0, new Dir(3, 1));
     
        for(int i = 0; i < numbers.length; i++) {
        	if(numbers[i] % 3 == 1) {			// 왼손
        		left = keyPad.get(numbers[i]);
        		answer.append("L");
        	}else if(numbers[i] != 0 && numbers[i] % 3 == 0) {		// 오른손
        		right = keyPad.get(numbers[i]);
        		answer.append("R");
        	}else {
        		Dir key = keyPad.get(numbers[i]);
        		if(getDist(left, key) > getDist(right, key)) {
        			right = key;
        			answer.append("R");
        		}
        		else if(getDist(left, key) < getDist(right, key)) {
        			left = key;
        			answer.append("L");
        		}
        		else {
        			if(hand.equals("left")) {
        				left = key;
        				answer.append("L");
        			}
        			else {
        				right = key;
        				answer.append("R");
        			}
        		}
        	}
        }
        return answer.toString();
    }
}
```



### :vibration_mode: 풀이 방법

 

올해 여름 인턴 코딩 테스트 1번 문항입니다.

문제에서 요구하는 그대로 코드로 표현하면 됩니다.



빠르게 푼다고 분기가 좀 많습니다...

 

우선 저는 키패드에서 번호와 번호의 위치 좌표를 해시셋으로 구현했습니다.

이렇게 해서 번호를 누르고 나서 바로 손가락의 위치를 수월하고 갱신하고, 중간에 있는 번호를 눌렀을 때의 이동 거리도 손쉽게 구했습니다.

###  :vibration_mode: 후기

인풋이 엄청 크지도 않고 로직이 복잡하지도 않아서 아주 손쉽게 풀 수 있는 문제입니다.

공채 문제풀다가 이거 푸니까 선녀가 따로 없습니다..



화이팅!!!!