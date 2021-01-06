# [2019 카카오 개발자 겨울 인턴십] 튜플 - Java

###  :octocat: 분류

> 문자열

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.Comparator;
public class week20_튜플 {
	public int[] solution(String s) {
        ArrayList<ArrayList<Integer>> numList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> num = new ArrayList<Integer>();
        String n = "";
        // 문자열 파싱
        for(int i=0; i<s.length()-1; i++) {
        	if(s.charAt(i) == '}') {
        		if(!n.equals("")) num.add(Integer.parseInt(n));
        		ArrayList<Integer> temp = new ArrayList<Integer>();
        		temp.addAll(num);
        		numList.add(temp);
        		num.clear();
        		n = "";
        	} else if(s.charAt(i) == ',') {
        		if(!n.equals("")) num.add(Integer.parseInt(n));
        		n = "";
        	} else if(s.charAt(i) != '{') {
        		n += s.charAt(i);
        	}
        }
        // 배열 길이 오름차순으로 정렬
        numList.sort(new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return o1.size() - o2.size();
			}
		});
        // 튜플 찾기
        ArrayList<Integer> tuple = new ArrayList<Integer>();
        for(int i=0; i<numList.size(); i++) {
        	for(int j=0; j<numList.get(i).size(); j++) {
        		if(!tuple.contains(numList.get(i).get(j))) {
        			tuple.add(numList.get(i).get(j));
        			break;
        		}
        	}
        }
        int[] answer = new int[tuple.size()];
        for(int i=0; i<tuple.size(); i++) answer[i] = tuple.get(i);
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. 문자열을 파싱해서 리스트 단위(num)로 만들어서 리스트(numList)에 담기
2. numList를 내부 리스트 길이 별로 오름차순 정렬
3. 숫자 하나 들어간 배열부터 검사하면서 중복아닌 숫자를 정답 배열에 담기
4. 정답배열 출력

### :octocat: 후기

예전에 카카오 준비하면서 풀어봤던 문제.. 안보고 새로 풀어봤는데 시간초과 안걸려서 통과~
