# [2020 카카오 인턴십] 수식 최대화 - Java

###  :octocat: 분류

> 문자열처리

### :octocat: 코드

```java
import java.util.ArrayList;
import java.util.StringTokenizer;
public class week04_수식최대화 {
	static String[] arr = {"+","-","*"};
	static String[] result = new String[3];
	static boolean[] visited = new boolean[3];
	static ArrayList<Long> num;
	static ArrayList<String> oper;
	static long max = Long.MIN_VALUE;
	
	static void makePerm(int cnt) {
		if(cnt == result.length) {
			ArrayList<Long> tn = (ArrayList<Long>) num.clone();
			ArrayList<String> to = (ArrayList<String>) oper.clone();
			for(int i=0; i<3; i++)
				tn = calc(tn, to, result[i]);
			if(max < Math.abs(tn.get(0))) max = Math.abs(tn.get(0));
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!visited[i]) {
				result[cnt] = arr[i];
				visited[i] = true;
				makePerm(cnt+1);
				visited[i] = false;
			}
		}
	}
	
	static ArrayList<Long> calc(ArrayList<Long> tn, ArrayList<String> to, String op) {
		long one;
		long two;
		for(int i=0; i<to.size(); i++) {
			if(to.get(i).equals(op)) {
				to.remove(i);
				one = tn.remove(i);
				two = tn.remove(i);
				switch(op) {
				case "+": tn.add(i,one+two); break;
				case "-": tn.add(i,one-two); break;
				case "*": tn.add(i,one*two); break;
				}
				i=-1;
			}
		}
		return tn;
	}
	
	public long solution(String expression) {
        num = new ArrayList<Long>();
        oper = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(expression,"+|-|*");
        for(int i=0; st.hasMoreElements(); i++)
        	num.add(Long.parseLong(st.nextToken()));
        st = new StringTokenizer(expression,"0|1|2|3|4|5|6|7|8|9");
        for(int i=0; st.hasMoreElements(); i++)
        	oper.add(st.nextToken());
        makePerm(0);
        return max;
    }
}
```

### :octocat: 풀이 방법

1. 입력받은 문자열을 파싱해서 숫자끼리 연산자끼리 List에 담는다.
2. 연산자 3가지 순열로 만들어서 모든 경우 계산해본다. (6가지)
3. 계산할때 연산자 우선순위에 따라 3단계로 계산한다. (ex 먼저 +계산 다하고 -계산 후 *계산)
4. 모든 우선순위 경우 중 가장 큰 값을 출력한다.

### :octocat: 후기

생각보다 어렵지 않은 문제였다. 인턴십때 풀었던 문제여서 빨리 풀 수 있었다!
