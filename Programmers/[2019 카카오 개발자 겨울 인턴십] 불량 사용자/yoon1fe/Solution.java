package Programmers;

import java.util.*;
import java.util.regex.*;

class Solution {
	Set<Set<Integer>> answerSet = new HashSet<>();
	boolean[] v;
	
	public int solution(String[] user_id, String[] banned_id) {
		v = new boolean[user_id.length];
		
		for(int i = 0; i < banned_id.length; i++) {
			banned_id[i] = banned_id[i].replace("*", ".");
		}
		
        dfs(user_id, banned_id, 0, new HashSet<>());
        
        return answerSet.size();
    }

	public void dfs(String[] user_id, String[] banned_id, int idx, Set<Integer> set) {
		if(idx == banned_id.length) {
			System.out.println(set.toString());
			answerSet.add(set);
			return;
		}
		
		for(int i = 0; i < user_id.length; i++) {
			if(Pattern.compile(banned_id[idx]).matcher(user_id[i]).matches() && !v[i]) {
				v[i] = true;
				set.add(i);
				
				dfs(user_id, banned_id, idx+1, new HashSet<>(set));
				
				set.remove(i);
				v[i] = false;
			}
		}
	}


	public static void main(String[] args) {
		Solution s = new Solution();
//		System.out.println(s.solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "abc1**"}));
		System.out.println(s.solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"}));
	}
}