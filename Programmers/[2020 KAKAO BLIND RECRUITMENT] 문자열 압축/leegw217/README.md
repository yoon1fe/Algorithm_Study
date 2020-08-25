# [2020 KAKAO BLIND RECRUITMENT] 문자열 압축 - Java

###  :octocat: 분류

> 문자열 처리

### :octocat: 코드

```java
public class week03_문자열압축 {
	public int solution(String s) {
        int min = 99999;
        if(s.length() == 1) return 1; // 문자열길이 1개일때 처리
        for(int i=1; i<=s.length()/2; i++) { // 문자열 자르는 단위 수
        	String part = s.substring(0,i); // 기준 문자열
        	int cnt = 1;
        	StringBuilder answer = new StringBuilder();
        	int j;
        	for(j=i; j+i<=s.length(); j += i) { // 문자열 비교
        		String next = s.substring(j,j+i); // 비교하는 문자열
        		if(part.equals(next)) {
        			cnt++;
        		}
        		else {
        			if(cnt>1) {
        				answer.append(cnt);
        			}
        			answer.append(part);
        			part = next;
        			cnt = 1;
        			continue;
        		}
        	}
        	if(cnt>1) answer.append(cnt);
        	answer.append(s.subSequence(j-i, s.length()));
        	if(min > answer.length()) min = answer.length();
        }
        return min;
    }
}
```

### :octocat: 풀이 방법

1. 문자열을 자르는 개수를 1개에서 문자열 길이 절반까지 늘리면서 최소길이 체크
2. 자른 문자열끼리 비교하면서 연달아 같은 문자열이 오는 횟수를 카운트하여 1이상일경우 숫자도 길이에 포함
3. 자르고 남은 문자열을 이어 붙인것도 길이에 포함해야함 (ex 자르는 개수가 4개일때 뒤에 남은 문자열의 수가 4 미만일경우)

### :octocat: 후기

예전에 파이썬으로 풀었던 문제여서 자바로 바꿔 새로 풀어봤는데 속도차이가 어마어마!
