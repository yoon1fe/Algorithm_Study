# [2018 KAKAO BLIND RECRUITMENT] n진수 게임 - JAVA

## 분류
> 구현

## 코드
```java
class Solution {
   public static String[] remainder = {
		      "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
		        "A", "B", "C", "D", "E", "F"
		    };
	
	public String solution(int n, int t, int m, int p) {
        String answer = "";
        StringBuffer sb = new StringBuffer();
        int cnt = 0;
        int idx = p - 1;
        int num = 0;
        while(cnt < t) {
        	sb.append(numToNjinsoo(num++, n));
        	while(idx < sb.length() && cnt < t) {
        		answer += String.valueOf(sb.charAt(idx));
        		idx += m;
        		cnt++;
        	}
        }
        return answer;
    }
	
	public static String numToNjinsoo(int num, int n) {
		if(num == 0)
			return "0";
		
		StringBuffer sb = new StringBuffer();
		while(num > 0) {
			sb.append(remainder[num%n]);
			num /= n;
		}
		
		sb = sb.reverse();
		return sb.toString();
	}
}
```

## 문제 풀이
1. 10진수 num을 n진수로 바꾸는 함수 numToNjinsoo를 사용해서 n진수로 바꾼 결과를 String으로 리턴합니다.
   - 리턴한 값은 StrigBuffer에 저장합니다.
   - numToNjinsoo에서는 remainder[]를 사용해서 num의 나머지값을 stringbuffer에 쌓고, reverse해서 리턴!
1. 저장된 strigbuffer에서 튜브가 말해야하는 i번째가 stringbuffer의 길이보다 작다면 answer에 해당 i번째를 추가
   - i번째를 추가했다면, i를 사람 수만큼 증가시켜서 다음에 말해야하는 i번째로 인덱스 증가!
   - 그리고 튜브가 말해야하는 t개에 도달하거나 아직 stringbuffer에 갱신된 i번째가 아직 세팅되지 않았다면 1증가된 num을 n진수로 바꿔서 cnt가 t가 될때까지 루프를 돕니다.
1. 저장된 answer를 리턴하면 끝!

## 후기
카카오는 구현문제가 많은 것 같습니다!

구현문제를 많이 풀어봅시다!

삼성과는 또 다른 느낌인듯!