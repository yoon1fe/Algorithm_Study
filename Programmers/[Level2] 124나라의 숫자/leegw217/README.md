# [Level2] 124나라의 숫자 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
public class week24_124나라의숫자 {
	public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        int q = n;
        int r = 0;
        while(true) {
        	r = q % 3;
        	q = q / 3;
        	if(r == 0) {
        		q = q - 1;
        		r = 4;
        	}
        	answer.append(r);
        	if(q == 0) break;
        }
        return answer.reverse().toString();
    }
}
```

### :octocat: 풀이 방법

1. 10진수 수를 3진수로 바꾼다.
2. 나눈 나머지가 0이면 0 대신 4를 넣고 몫에서 1을 뺀다.

### :octocat: 후기

그냥 3진수만드는 문제였음.
