## [2018 KAKAO BLIND RECRUITMENT] 압축 - Java

###    :computer: ​분류

> Map 활용
>

​

###  :computer: 코드

```java
import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> dict = new HashMap<>();
        List<Integer> list = new ArrayList<>();
                
        // 사전 초기화
        for(char c = 'A'; c <= 'Z'; c++) {
        	dict.put(c+"", c-'A' + 1);
        }
        
        for(int i = 0; i < msg.length(); i++) {
        	String subStr = msg.substring(i,i+1);
        	String newSubStr = subStr;
        	int j = 0;
        	
        	for(j = i+2; j <= msg.length(); j++) {
        		newSubStr = msg.substring(i, j);
                // 부분 문자열이 사전에 포함되어있는지 체크
        		if(dict.containsKey(newSubStr) == true) {
        			subStr = newSubStr;
        			continue;
        		}else break;
        	}
            // 사전에 없는 문자열을 구했을 때 이전의 문자열로 색인 번호 더하고
        	list.add(dict.get(subStr));
            // 새로 사전에 추가
        	dict.put(newSubStr, dict.size() + 1);
        	i = j-2;
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
        	answer[i] = list.get(i);
        }
        
        return answer;
    }
}
```



### :computer: ​풀이 방법

주어진 문자열을 LZW 알고리즘으로 압축시키는 문제입니다.

음.... 하라는대로 하면 됩니당 ㅎㅎ.



 사용한 변수들은 다음과 같습니다.

```java
// 사전 (문자열-색인 번호)
Map<String, Integer> dict = new HashMap<>();
// 압축된 색인 번호 리스트
List<Integer> list = new ArrayList<>();
// 검색할 첫 부분 문자열 (길이 1 짜리)
String subStr;
// 검색할 부분 문자열 (길이 2~)
String newSubStr;
```

 

맨먼저 사전을 초기화하고 인풋으로 들어온 문자열의 처음부터 체크합니다. 길이를 하나씩 늘려가면서 부분 문자열이 사전에 있는지 체크해야 하고, 사전에 없을 때 그 전의 문자열과 지금 문자열에 대한 정보가 모두 필요하기 때문에 subStr, newSubStr 두개를 두었습니다. subStr은 색인 번호를 구할 때 쓰이고, newSubStr은 사전에 새롭게 넣어주어야 겠지용. 말로 풀어서 설명할라니깐 어렵네여. 코드를 보면 좀 더 이해가 잘 되리라 믿습니다...ㅎㅋ

 

요렇게 list를 잘 구하고나서 얘를 int[] 로 바꿔서 리턴해주면 끝!!

 



###  :computer: 후기 

글 잘쓰는 법도 공부해야겠습니다.

감사합니다~!~!!