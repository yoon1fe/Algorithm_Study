# [2018 KAKAO BLIND RECRUITMENT] 뉴스 클러스터링 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
public class week17_뉴스클러스터링 {
	public int solution(String str1, String str2) {
        int answer = 0;
        String s1 = str1.toLowerCase();
        String s2 = str2.toLowerCase();
        HashMap<String, Integer> A = new HashMap<String, Integer>();
        HashMap<String, Integer> B = new HashMap<String, Integer>();
        for(int i=0; i<s1.length()-1; i++) {
        	String s = s1.substring(i, i+2);
        	s = s.replaceAll("[^a-z]", "");
        	if(s.length() == 2) {
        		if(A.containsKey(s)) A.put(s, A.get(s)+1);
        		else A.put(s, 1);
        	}
        }
        for(int i=0; i<s2.length()-1; i++) {
        	String s = s2.substring(i, i+2);
        	s = s.replaceAll("[^a-z]", "");
        	if(s.length() == 2) {
        		if(B.containsKey(s)) B.put(s, B.get(s)+1);
        		else B.put(s, 1);
        	}
        }
        Set<String> aSet = new HashSet<String>();
        Set<String> bSet = new HashSet<String>();
        aSet = A.keySet();
        bSet = B.keySet();
        if(aSet.size()==0 && bSet.size()==0) return 65536;
        Set<String> intersec = new HashSet<String>();
        Set<String> union = new HashSet<String>();
        intersec.addAll(aSet);
        union.addAll(aSet);
        intersec.retainAll(bSet);
        union.addAll(bSet);
        int interCnt = intersec.size();
        int unionCnt = union.size();
        for(String ss : intersec) {
        	int min = Math.min(A.get(ss), B.get(ss));
        	interCnt += min-1;
        }
        for(String ss : union) {
        	int max = Math.max(A.containsKey(ss)?A.get(ss):0, B.containsKey(ss)?B.get(ss):0);
        	unionCnt += max-1;
        }
        answer = (int)((float)interCnt/(float)unionCnt*65536);
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 입력받은 문자열을 다 소문자로 바꾼다.
2. 문자열을 두글자씩 끊어서 hashmap에 넣는다. 이때 숫자, 특수문자, 공백이 들어간건 제외한다.
3. map의 key들로 이루어진 set을 만들어 교집합과 합집합을 구하고 각각 size를 구한다.
4. 교집합 내에 속해있는 문자열들을 map에서 검사해 중복이면 개수가 가장 적은 값-1을 교집합 size에 더한다.
5. 합집합 내에 속해있는 문자열들을 map에서 검사해 중복이면 개수가 가장 많은 값-1을 합집합 size에 더한다.
6. 최종 size를 이용해 자카드 유사도를 계산하고 만약 map들이 공집합이면 자카드 유사도는 1로 처리한다.

### :octocat: 후기

역시 카카오는 문자열을 이용하는 문제가 많다! 그래도 어렵지 않은 문제여서 금방풀었다 히히
