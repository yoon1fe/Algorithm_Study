# [2019 KAKAO BLIND RECRUITMENT] 오픈채티방 - JAVA

## 분류
> 해시
>
> 구현

## 코드
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    public String[] solution(String[] record) {
        StringBuilder sb = new StringBuilder();
        String[] messages = {"님이 들어왔습니다.", "님이 나갔습니다."};
        int N = record.length;
        ArrayList<Message> answer = new ArrayList<>();
        HashMap<String, String> idToNickName = new HashMap<>();
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(record[i]);
            String cmd = st.nextToken();
            String id = st.nextToken();

            if(cmd.equals("Enter")){
                answer.add(new Message(id,0));
                idToNickName.put(id, st.nextToken());
            }
            else if(cmd.equals("Leave"))
                answer.add(new Message(id, 1));
            else if(cmd.equals("Change"))
                idToNickName.put(id, st.nextToken());
        }

        String[] ret = new String[answer.size()];
        int idx = 0;
        for(Message msg : answer)
            ret[idx++] = idToNickName.get(msg.id) + messages[msg.messageIdx];

        return ret;
    }

    static class Message{
        String id;
        int messageIdx;

        public Message(String id, int messageIdx) {
            this.id = id;
            this.messageIdx = messageIdx;
        }
    }
}
```

## 문제 풀이
id를 해시맵의 키로 주고 닉네임을 Value로 줘서 쉽게 풀 수 있었습니다.

"님이 들어왔습니다.", "님이 나갔습니다."와 같은 메시지는 따로 배열에 저장합니다.

id가 바뀌면, HashMap에서 id에 해당하는 닉네임 값을 변경해줍니다.

정답으로 출력할 메시지는 {id, message index}와 같이 리스트에 순서대로 저장합니다.

리턴하기 전에 HsahMap에서 id로 닉네임을 찾고 여기에 message index에 해당하는 문자를 합쳐서 배열에 저장하고, 리턴하면 됩니다.

## 후기
재미있어요~
