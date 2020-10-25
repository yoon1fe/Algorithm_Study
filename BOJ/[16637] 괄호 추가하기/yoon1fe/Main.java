package BOJ;

import java.io.*;
import java.util.*;

public class Main {
	static int N, answer;
	static String expr;
	static List<Integer> selectedIdx = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException {
		input();
	
		
		for(int depth = 1; depth <= N / 4 + 1; depth++ ) {
			comb(depth, 0, 1);
		}
		
		System.out.println(answer);
		
	}
	
	public static void comb(int depth, int cnt, int idx) {
		if(cnt == depth) {
			answer = Math.max(answer, solve());
			
			return;
		}
		
		for(int i = idx; i < N-1; i += 2) {
			if(selectedIdx.contains(i - 2)) continue;
			
			selectedIdx.add(i);
			comb(depth, cnt + 1, i + 2);
			selectedIdx.remove((Integer)i);
		}
	}
	
	public static int solve() {
		StringBuilder sb = new StringBuilder(expr);
		List<Integer> nums = new ArrayList<>();
		List<Character> ops = new ArrayList<>();
		
		for(int i = 0; i < expr.length(); i++) {
			if(selectedIdx.contains(i)) {
				int result = calc(sb.substring(i-1, i+2).toString());
				nums.remove(nums.size() - 1);
				nums.add(result); 
				i++;
			}else {
				if(Character.isDigit(expr.charAt(i))) nums.add(expr.charAt(i) - '0');
				else ops.add(expr.charAt(i));
			}
		}
		
		return calc(nums, ops);
	}
	
	public static int calc(String expr) {
		StringTokenizer st = new StringTokenizer(expr, "+|-|*");
		List<Integer> nums = new ArrayList<>();
		List<Character> ops = new ArrayList<>();
		
		while(st.hasMoreTokens()) {
			nums.add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < expr.length(); i++) {
			if(!Character.isDigit(expr.charAt(i))) ops.add(expr.charAt(i));
		}
		
		return calc(nums, ops);
	}		
	
	public static int calc(List<Integer> nums, List<Character> ops) {
		
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < nums.size(); i++) {
			stack.push(nums.get(i));
			if(stack.size() != 2) continue;
			
			char op = ops.get(i - 1);
			
			int op1 = stack.pop(), op2 = stack.pop();
			switch(op) {
			case '+': stack.push(op1 + op2); break;
			case '-': stack.push(op2 - op1); break;
			case '*': stack.push(op1 * op2); break;
			}
		}
		
		return stack.peek();
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		expr = br.readLine();
		answer = calc(expr);
	}
}