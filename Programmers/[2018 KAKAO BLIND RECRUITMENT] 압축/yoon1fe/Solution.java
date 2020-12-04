package Programmers;

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
        		if(dict.containsKey(newSubStr) == true) {
        			subStr = newSubStr;
        			continue;
        		}else break;
        	}
        	
        	list.add(dict.get(subStr));
        	dict.put(newSubStr, dict.size() + 1);
        	i = j-2;
        }
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < answer.length; i++) {
        	answer[i] = list.get(i);
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();

//		System.out.println(s.solution("KAKAO"));
//		System.out.println(s.solution("TOBEORNOTTOBEORTOBEORNOT"));
		System.out.println(s.solution("ABABABABABABABAB"));
	}
}