# [2018 KAKAO BLIND RECRUITMENT] 압축 - JAVA

## 분류
> 구현

## 코드
```java
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
	public static HashMap<String, Integer> dict;
    static int index = 27;
	public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        init();        
        while(msg.length() > 0) {
        	String key = getLongestString(msg);
        	list.add(dict.get(key));
        	msg = msg.substring(key.length());
        }
        
        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++)
        	answer[i] = list.get(i);
        
        return answer;
    }
	
	public static String getLongestString(String msg) {
		String ret = "";
		for(int i=1; i<=msg.length(); i++) {
			ret = msg.substring(0, i);
			if(dict.containsKey(ret))
				continue;
			dict.put(ret, index++);
			ret = msg.substring(0, i-1);
			break;
		}
		return ret;
	}
	
	public static void init() {
		dict = new HashMap<>();
        
		for(int i=0; i<26; i++)
			dict.put(String.valueOf(Character.toString((char) (i+'A'))), i+1);
	}
}
```

## 문제 풀이
구현 로직은 간단합니다!

1. 현재 msg에서 사전에 없는 가장 긴 문자열 w를 구합니다.
1. w를 구했다면, w를 사전에 추가하고 w의 길이-1에 해당하는 문자열 s를 리턴합니다.
1. s는 사전에 있으므로 list에 s의 인덱스 값을 추가합니다.
1. 그리고 s만큼 msg에서 삭제합니다.
1. 아직 문자열이 더 남았다면 다시 1번부터 수행하고, 만약 msg의 문자열이 0이면 종료합니다.


## 후기
어렵게 생각하지 말고 문제에서 원하는대로만 해서 금방 풀 수 있었습니다!

매일매일 열심히 해봅시다!!!