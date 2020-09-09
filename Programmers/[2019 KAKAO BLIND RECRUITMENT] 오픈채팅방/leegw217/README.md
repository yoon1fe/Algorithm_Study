# [2019 KAKAO BLIND RECRUITMENT] 오픈채팅방 - Java

###  :octocat: 분류

> 문자열

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
public class week05_오픈채팅방 {
	public String[] solution(String[] record) {
        HashMap<String, String> user = new HashMap<String, String>();
        ArrayList<String[]> chat = new ArrayList<String[]>();
        for(int i=0; i<record.length; i++) {
        	StringTokenizer st = new StringTokenizer(record[i]);
        	switch(st.nextToken()){
        	case "Enter":
        		String id = st.nextToken();
        		String name = st.nextToken();
        		user.put(id, name);
        		chat.add(new String[] {id,"Enter"});
        		break;
        	case "Leave":
        		chat.add(new String[] {st.nextToken(),"Leave"});
        		break;
        	case "Change":
        		id = st.nextToken();
        		name = st.nextToken();
        		user.put(id, name);
        		break;
        	}
        }
        String[] answer = new String[chat.size()];
        for(int i=0; i<chat.size(); i++) {
        	answer[i] = user.get(chat.get(i)[0]);
        	if(chat.get(i)[1].equals("Enter")) {
        		answer[i] += "님이 들어왔습니다.";
        	} else {
        		answer[i] += "님이 나갔습니다.";
        	}
        }
        return answer;
    }
}
```

### :octocat: 풀이 방법

1. record에서 들어오는 문자열을 파싱해서 hashmap에 key는 id, value는 name으로 저장
2. Enter, Leave, Change별로 채팅기록에 저장하고 마지막에 출력하면 끝

### :octocat: 후기

map 쓸줄알면 아주빨리 풀리는 문제였다!
