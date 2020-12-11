## [2019 카카오 개발자 겨울 인턴십] 튜플 - Java

### :three: 분류

> 구현
>
> 문자열 처리



:three: 코드

```java
import java.util.*;

class Solution {
    public int[] solution(String s) {
        List<List<Integer>> sets = new ArrayList<>();
        String[] arr = s.replaceAll("[{}]", " ").trim().split(" , ");
        int[] answer = new int[arr.length];
        
        Arrays.sort(arr, (o1, o2)->{ return o1.length() - o2.length(); });
        
        for(String str : arr) {
        	List<Integer> temp = new ArrayList<>();
        	for(String n : str.split(",")) {
        		temp.add(Integer.parseInt(n));
        	}
        	sets.add(temp);
        }
        

        for(int i = 0; i < sets.size(); i++) {
        	int num = sets.get(i).get(0);
        	answer[i] = num;
        	
        	for(int j = i + 1; j < sets.size(); j++) {
        		sets.get(j).remove((Integer)num);
        	}
        }

        return answer;
    }
}
```



### :three: 풀이 방법

부분집합이 주어지고 얘들을 갖고 튜플을 만드는 문제입니다. 최대 개수를 잘못 보고 엉뚱하게 풀뻔 했네욤 ^^;;



사실 첨 짠 코드는 이렇게 깔끔하지 않습니다... 입력으로 주어진 문자열 s를 길이만큼 돌면서 무식하게 처리해줬는데 코드가 너무 지저분해서 다른 풀이를 보고 도움을 받았습니다. 대괄호를 빈칸으로 바꾸고 양 끝의 빈칸을 자르고 " , "으로 자르면 너무나 깔끔합니다.. 캬...



이렇게 자르고 길이에 대한 오름차순으로 정렬합니다.



그리고 정답을 구하는 로직은 간단합니다. 앞에 있었던 애들을 뒤에 있는 리스트에서 빼주면서 O(N^2) 을 돌면 끝입니다.



###  :three: 후기

숫자의 최댓값이 10만이었는데 이걸 개수로 잘못보고 O(N^2) 말고 다른 방법을 한참 고민했네유 ... 8_8

문제를 잘 읽읍시다!!

정규식 공부합시다!!

감사합니다!!!

