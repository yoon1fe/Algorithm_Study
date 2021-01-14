# [17609] 회문 - Java

###  :octocat: 분류

> 문자열

### :octocat: 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class week22_회문 {
	static int[] check(String s) {
		int left = 0;
		int right = s.length()-1;
		
		while(left < right) {
			if(s.charAt(left) != s.charAt(right)) return new int[] {left, right};
			left++;
			right--;
		}
		return new int[] {-1, -1};
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			String s = br.readLine();
			int[] answer = check(s);
			if(answer[0] == -1) System.out.println(0);
			else {
				StringBuffer left = new StringBuffer(s);
				StringBuffer right = new StringBuffer(s);
				left.deleteCharAt(answer[0]);
				right.deleteCharAt(answer[1]);
				if(check(left.toString())[0] == -1 || check(right.toString())[0] == -1) System.out.println(1);
				else System.out.println(2);
			}
		}
	}
}
```

### :octocat: 풀이 방법

1. 문자열 앞뒤에서부터 시작해서 회문인지 검사
2. 회문이 맞으면 0 출력 아니면 다른 부분 return
3. 다른 부분 중 왼쪽을 지우고 다시 회문 검사
4. 오른쪽 지우고 회문검사
5. 둘중 하나라도 회문이 되면 유사회문이라 1 출력
6. 둘다 안되면 2 출력

### :octocat: 후기

처음에 간단하게 회문검사하고 아니면 하나씩 지우면서 회문검사 쭉 했는데
시간초과가 떴다 덜덜.. 오지게 해보다가 결국 웡게꺼 방법 읽어보고 짰당ㅜ

