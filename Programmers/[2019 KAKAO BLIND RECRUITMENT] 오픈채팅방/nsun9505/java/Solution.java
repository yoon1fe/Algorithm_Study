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