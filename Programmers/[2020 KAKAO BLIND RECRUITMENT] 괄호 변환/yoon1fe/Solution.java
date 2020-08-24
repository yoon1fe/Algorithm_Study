package Programmers;

import java.util.Stack;

public class Solution {
	
	public boolean isAlright(String s) {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < s.length(); i++) {
			if(!stack.isEmpty() && stack.peek() == '(' && s.charAt(i) == ')') stack.pop();
			else stack.push(s.charAt(i));
		}
		return stack.isEmpty() == true ? true : false;
	}
	
	public String solution(String p) {		// 인풋으로 항상 균형잡인 괄호 문자열이 들어온다.
        String answer = "";
        if(isAlright(p)) return p;
        
        int lParen = 0;
        int rParen = 0;
        String u = "", v = "";
        for(int i = 0; i < p.length(); i++) {
        	if(p.charAt(i) == '(') lParen++;
        	else rParen++;
        	
        	if(lParen == rParen) {
        		u = p.substring(0, i + 1);
        		v = p.substring(i + 1);
        		break;
        	}
        }
        
        if(isAlright(u)) {
        	answer = u + solution(v);
        }
        
        else {
        	StringBuilder sb = new StringBuilder("(" + solution(v) + ")");
        	for(int i = 1; i < u.length() - 1; i++) sb.append(u.charAt(i) == '(' ? ')' : '(');
        	answer = sb.toString();
        }
        return answer;
    }

	public static void main(String[] args) {
		Solution sol = new Solution();

		String s = "()))((()";
		System.out.println(sol.solution(""));
	}

}
