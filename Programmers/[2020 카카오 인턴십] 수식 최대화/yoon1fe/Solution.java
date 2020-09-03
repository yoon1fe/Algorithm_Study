package Programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


public class Solution {
	static int[] priority = new int[3];	//[0]: + [1]: - [2]: * 의 우선순위	값은 1~3
	static boolean[] isSelected = new boolean[4];
	static List<Integer> numbers;
	static List<Character> operators;
	static long answer = 0;
	
	static void perm(int cnt) {
		if(cnt == 3) {
			makePostfix();
			return;
		}
		
		for(int i = 1; i <= 3; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			priority[cnt] = i;
			perm(cnt+1);
			isSelected[i] = false;
		}
	}

	static int convert(char op) {
		switch(op) {
		case '+': return 0;
		case '-': return 1;
		case '*': return 2;
		}
		return -1;
	}
	
	static void makePostfix() {
		List<String> prefix = new ArrayList<>();
		Stack<Character> s = new Stack<>();
		
		for(int i = 0; i < numbers.size(); i++) {
			prefix.add(numbers.get(i).toString());
			if(i == numbers.size() - 1) break;
			char curOp = operators.get(i);
			if(s.isEmpty()) s.push(curOp);
			else {
				if(priority[convert(s.peek())] < priority[convert(curOp)]) {
					s.push(curOp);
				}else {
					inner:
					do {
						prefix.add(s.pop().toString());
						if(s.isEmpty()) break inner;
					} while(priority[convert(s.peek())] >= priority[convert(curOp)]);
					s.push(curOp);
				}
			}
		}
		
		while(!s.isEmpty()) {
			prefix.add(s.pop().toString());
		}
		
		calculate(prefix);
	}

	private static void calculate(List<String> prefix) {
		Stack<Long> s = new Stack<>();
		for(int i = 0; i < prefix.size(); i++) {
			String cur = prefix.get(i);
			if(cur.equals("+") || cur.equals("-") || cur.equals("*")) {
				long op2 = s.pop();
				long op1 = s.pop();
				switch(cur) {
				case "+": s.add(op1 + op2); break;
				case "-": s.add(op1 - op2); break;
				case "*": s.add(op1 * op2); break;
				}
			}else {
				s.add(Long.parseLong(cur));
			}
		}
		
		answer = Math.max(answer, Math.abs(s.pop()));
	}

	public static long solution(String expression) {
		StringTokenizer st = new StringTokenizer(expression, "+|-|*");
		numbers = new ArrayList<Integer>();
		operators = new ArrayList<Character>();
		for(int i = 0; i < expression.length(); i++) {
			if(expression.charAt(i) - '0' < 0 || expression.charAt(i) - '0' > 9) operators.add(expression.charAt(i));
		}
		
		while(st.hasMoreTokens()) {
			numbers.add(Integer.parseInt(st.nextToken()));
		}
		perm(0);
		
		return answer;
	}

	public static void main(String[] args) {		
		System.out.println(solution("3*12+5+10-4"));			//답 : 77
	}
}