import java.util.Stack;

public class Solution {
	public int solution(String s) {
		int answer = 1000;
		if(s.length() == 1) return 1;
		
		for (int i = 1; i <= s.length() / 2; i++) {
			StringBuilder compression = new StringBuilder();
			Stack<String> stack = new Stack<>();
			
			for(int j =0; j < s.length(); j+=i) {
				String sub;
				
				if(j + i > s.length()) 				// 마지막 부분 문자열
					sub = s.substring(j);
				else 
					sub = s.substring(j, j + i);
				
				if(stack.isEmpty()) {
					stack.push(sub);
					continue;
				}
				
				if(stack.peek().equals(sub)) {
					stack.push(sub);
				}else {
					int compLen = stack.size();
					compression.append(compLen > 1 ? compLen+stack.peek() : stack.peek());
					stack.clear();
					stack.push(sub);
				}
			}
			int compLen = stack.size();
			compression.append(compLen > 1 ? compLen+stack.pop() : stack.pop());
		
			answer = Math.min(answer, compression.length());
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();

		String s = "abcabcabcabcdededededede";
		System.out.println(sol.solution(s));
	}

}
