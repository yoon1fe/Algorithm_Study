# [16637] 괄호 추가하기 - Java

###  :heavy_plus_sign: 분류

> 시뮬레이션
>
> 조합

​

### :heavy_plus_sign: 코드

```java
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
```



### :heavy_plus_sign: 풀이 방법

이제 삼성 상시 역량테스트 기출입니다.

괜히 어렵게 접근했다가 시간이 쫌 걸렸네용 ㅜ

그래도 한시간 반이 채 안걸리니 장족의 발전입니다.



코드가 잠깐 산으로 갔다와서 좀 중구난방입니다...;;

먼저 조합으로 괄호를 칠 연산자를 구합니다. 이때 중첩된 괄호는 치면 안되니깐 선택한 연산자 바로 다음에 오는 연산자는 선택하면 안됩니다. 

 

그렇게 연산자를 구해주고나서 숫자들의 List와 연산자들의 List를 만들어줍니다. 선택된 연산자에서는 그 값을 먼저 계산한 결과값을 구해줘야겠죵

 

수식 계산은 calc() 메소드에서 합니다. 요때 String을 받는 메소드와 연산자와 피연산자의 List를 받는 메소드 두개를 만들었습니다. 코드를 짜다보니 음수가 나왔을 때가 쪼금 골치 아파서 결국 수식 계산은 연산자와 피연산자의 List로 계산하도록 짰씁니다.

 

모든 가능한 경우에서 결과값이 가장 큰걸 리턴하면 끝!! 

괄호를 안 치는 경우도 포함해야 하니 맨 처음 식의 결과값을 answer 변수에 넣어놓고 시작합시다!

### :heavy_plus_sign: 후기

감사합니다 ~!