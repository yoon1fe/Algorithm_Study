## [2018 KAKAO BLIND RECRUITMENT] n진수 게임 - Java

###    :de: ​분류

> 구현
>

​

###  :de: 코드

```java
import java.util.*;

class Solution {
    Map<Integer, Character> antilog = new HashMap<>();

	public String solution(int n, int t, int m, int p) {
		StringBuilder list = new StringBuilder();	// 말해야 하는 숫자 리스트
		StringBuilder answer = new StringBuilder();	// 튜브가 말해야 하는 숫자 리스트
		
		for(int i = 0; i < 16; i++) {
			if(10 <= i)	antilog.put(i, (char)(55+i));
			else antilog.put(i, (char)(48+i));
		}
		
		// 넉넉히 리스트 생성
		for(int i = 0; i < 30000; i++) {
			list.append(convert(i, n));
		}
		
		for(int i = 0; i < list.length(); i++) {
			if(answer.length() == t) break;
			if(i % m == (p-1)) answer.append(list.charAt(i));
		}
		
        return answer.toString();
    }
	
	public String convert(int num, int n) {
		StringBuilder sb = new StringBuilder();
		if(num == 0) return "0";
		
		while(num > 0) {
			sb.append(antilog.get(num % n));
			num /= n;
		}
		
		return sb.reverse().toString();
	}
}
```



### :de: ​풀이 방법

2018 카카오 문제 끝!!! 요때는 특별한 알고리즘을 요구하는 문제가 많지 않았네영..

내년 코테때는 꼭 올솔을...



문제의 로직은 다음과 같습니다

1. 최대 16진수로 변환하기 위해 10:A, 11:B, ... 15:F 로 매핑되는 HashMap을 만듭니다.
2. 진행되는 게임에서 나오는 답의 리스트를 넉넉히 만듭니다.
3. 튜브가 말해야하는 정답의 리스트를 구합니다.

이 문제의 핵심은 **n진수로 변환하기**라고 생각합니다. 요건 convert() 메소드를 만들어서 해결했습니다. 아주 간단합니다. 10~15까지의 수를 알맞은 알파벳으로 매핑해놨으니 간단히 구할 수 있습니당.

 

얘들을 다 StringBuilder에다 갖다 붙이니깐 문제에서 요구하는 한글자씩 말하는것도 따로 신경쓸 필요가 없겠지용.

 

마지막으로 정답은 각 인덱스를 m으로 나눈 나머지를 보고 찾을 수 있습니다.



###  :de: 후기 

내년 카카오는 꼭 통과합시다!!!

화이팅~!~!