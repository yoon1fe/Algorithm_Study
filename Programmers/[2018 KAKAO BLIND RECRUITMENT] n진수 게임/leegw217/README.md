# [2018 KAKAO BLIND RECRUITMENT] n진수 게임 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.LinkedList;
public class week19_n진수게임 {
	char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F'};
	LinkedList<Character> makeNumber(int s, int num) {
		LinkedList<Character> list = new LinkedList<Character>();
		int q = num;
		int r = 0;
		while(true) {
			if(q < s) {
				if(q >= 10) list.addFirst(alpha[q%10]);
				else {
					char p = (char)(q + '0');
					list.addFirst(p);
				}
				break;
			}
			r = q % s;
			if(r >= 10) list.addFirst(alpha[r%10]);
			else {
				char p = (char)(r + '0');
				list.addFirst(p);
			}
			q = q / s;
		}
		return list;
	}
	
	public String solution(int n, int t, int m, int p) {
        String answer = "";
        int idx = 1;
        int pp = p;
        if(p == m) pp = 0;
        loop:
        for(int num=0; num<t*m; num++) {
        	LinkedList<Character> number = makeNumber(n, num);
        	for(int i=0; i<number.size(); i++) {
        		if(idx % m == pp) answer += number.get(i);
        		idx++;
        		if(answer.length() == t) break loop;
        	}
        }
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 숫자를 0부터 t*m 까지 높히면서 n진법에 맞게 변환한다.
2. 변환한 수를 한글자씩 인원들이 말해야하니 자기 차례가 될때만 answer에 넣는다.

### :octocat: 후기

1번문제답게 쉬운 문제였다!
