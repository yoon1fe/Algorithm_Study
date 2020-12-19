# [2019 카카오 개발자 겨울 인턴십] 튜플 - JAVA

## 분류
> 문자열
>
> 구현

## 코드
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
	static class Tuple implements Comparable<Tuple>{
		int[] arr;
		Tuple(StringTokenizer st){
			arr = new int[st.countTokens()];
			for(int i=0; i<arr.length; i++)
				arr[i] = Integer.parseInt(st.nextToken());
		}
		
		@Override
		public int compareTo(Tuple tuple) {
			if(arr.length > tuple.arr.length)
				return 1;
			else if(arr.length == tuple.arr.length)
				return 0;
			return -1;
		}
		
	}
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(1, s.length()-1);
        Pattern pattern = Pattern.compile("[{](.*?)[}]");
        
        Matcher matcher = pattern.matcher(s);
        ArrayList<Tuple> list = new ArrayList<>();
        while(matcher.find()) {
        	StringTokenizer st = new StringTokenizer(matcher.group(1), ",");
        	list.add(new Tuple(st));
        	if(matcher.group() == null)
        		break;
        }
        
        Collections.sort(list);
        answer = new int[list.size()];
        int idx = 0;
        Set<Integer> exists = new HashSet<>();
        for(int i=0; i<list.size(); i++) {
        	Tuple tuple = list.get(i);
        	for(int num : tuple.arr) {
        		if(exists.contains(num))
        			continue;
        		exists.add(num);
        		answer[idx++] = num;
        	}
        }
        return answer;
    }
}
```

## 문제 풀이
1. 먼저 가장 밖에 있는 {}를 제거합니다.
1. 정규표현식을 사용해서 튜플 {} 단위로 자르고, ","를 구분자로 해서 안에 있는 것을 추출합니다.
1. list에 Tuple 객체로 담습니다.
   - Tuple은 배열을 가지고 있으며, Tuple을 정렬할 때는 배열의 원소 수를 기준으로 오름차순으로 정렬합니다.
   - 정렬을 하게 되면 원소가 1개, 2개, 3개, 배열을 갖는 Tuple 순으로 정렬이 됩니다.
1. Set을 사용해서 배열 원소 중에서 새롭게 발견한 숫자를 answer와 exists에 추가합니다.
   - 처음에는 배열의 원소가 1개이므로 1개를 Set과 answer에 추가
   - 원소가 2개인 Tuple에서 새롭게 발견된 원소를 Set과 answer에 추가
   - 위와 같은 작업을 계속해서 하면 원하는 튜플을 answer에 저장할 수 있다.
1. answer를 리턴!

## 후기
참 뭘 많이 가져다 썼죠..?

다른 사람 풀이를 보니 람다식으로 해서 이쁘게 했던데

그래도 정규표현식을 써서 쉽게 파싱해서 풀 수 있었습니다!

정규표현식 쉽다면 쉽고 어렵다면 어렵다.