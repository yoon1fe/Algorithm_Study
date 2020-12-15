# [2018 KAKAO BLIND RECRUITMENT] 압축 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.List;
public class week18_압축 {
	public int[] solution(String msg) {
        List<Integer> ans = new ArrayList<Integer>();
        int mIdx = 0;
        char[] message = msg.toCharArray();
        List<String> dict = new ArrayList<String>();
        dict.add(",");
        for(int i=0; i<26; i++) {
        	char ch = (char)('A'+i);
        	dict.add(String.valueOf(ch));
        }
        String s = "";
        int idx = 0;
        while(mIdx < msg.length()) {
        	s += String.valueOf(message[mIdx++]);
        	if(dict.contains(s)) {
        		idx = dict.indexOf(s);
        		if(mIdx == msg.length()) ans.add(idx);
        	} else {
        		ans.add(idx);
        		dict.add(s);
        		s = "";
        		mIdx--;
        	}
        }
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++) answer[i] = ans.get(i);
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. A-Z까지 dict 리스트에 넣는다.
2. 인덱스를 하나씩 늘려가면서 입력된 문자열을 검사한다.
3. dict에 문자가 있으면 해당 인덱스를 저장하고 다음 문자와 합친다.
4. dict에 합친 문자열이 있으면 다음 문자를 합치고 없으면 dict에 추가하고 저장한 인덱스를
ans 리스트에 저장한다.
5. 마지막 문자까지 검사하고 ans에 저장된 인덱스들을 정답배열에 넣는다.

### :octocat: 후기

생각보다 빨리 푼문제! 시간초과 걱정됐는데 그정돈 아닌가봄!
