package Programmers;

import java.util.*;

public class Solution {
	public static String[] solution(String[] record) {
        StringTokenizer st;
        Map<String, String> uidList = new HashMap<>();
        List<String[]> chatList = new ArrayList<>();
        
        for(int i = 0; i < record.length; i++) {
        	String uid = null, nickname = null;
        	st = new StringTokenizer(record[i], " ");
        	switch(st.nextToken()) {
        	case "Enter":
        		uid = st.nextToken();
        		nickname = st.nextToken();
        		uidList.put(uid, nickname);
        		String[] enterMsg = {uid, "님이 들어왔습니다."};
        		chatList.add(enterMsg);
        		break;
        	case "Leave":
        		uid = st.nextToken();
        		String[] leaveMsg = {uid, "님이 나갔습니다."};
        		chatList.add(leaveMsg);
        		break;
        	case "Change":
        		uid = st.nextToken();
        		nickname = st.nextToken();
        		uidList.replace(uid, nickname);
        		break;
        	}
        }
        
        String[] answer = new String[chatList.size()];
        for(int i = 0; i < answer.length; i++) {
        	answer[i] = uidList.get(chatList.get(i)[0]) + chatList.get(i)[1];
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		String[] r = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		
		System.out.println(solution(r));
	}
}