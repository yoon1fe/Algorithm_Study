package week05;

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