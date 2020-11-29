# [2529] 부등호 - JAVA

## 분류
> 백트랙킹
> 
> 완저탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2529 {
	public static int N;
	public static String input = "";
	public static String max = "0";
	public static String min = "98765432100";
	public static boolean isUsed[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		for(int i=0; i<10; i++) {
			isUsed[i] = true;	
			String result = String.valueOf(i);
			solution(0, i, result);
			isUsed[i] = false;
		}
		System.out.println(max + "\n" + min);
	}
	
	public static void solution(int cur, int prev, String result) {
		if(cur >= input.length()) {
			if(Long.parseLong(max) < Long.parseLong(result))
				max = result;
			if(Long.parseLong(result) < Long.parseLong(min))
				min = result;
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(isUsed[i])
				continue;
			
			char ch = input.charAt(cur);
			if(!comp(prev, i, ch))
				continue;

			isUsed[i] = true;
			solution(cur+1, i, result + String.valueOf(i));
			isUsed[i] = false;
		}
	}
	
	public static boolean comp(int a, int b, char ch) {
		if(ch == '<')
			return a < b;
		return a > b;
	}
	
	public static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		isUsed = new boolean[10];
		for(int i=0; i<N; i++)
			input += st.nextToken();
	}
}
```

## 문제 풀이
입력 받은 문자열에서 가장 처음에 오는 숫자는 메인에서 0부터 9까지 순서대로 넣어본다.
```
    [ a ] < [ b ] < [ c ] ...
```
   - 위에서는 a번째에는 0~9까지 숫자가 순서대로 들어가서 b, c에 알맞게 들어갈 수 있는 숫자를 찾는다.

b번째 부터 이전의 숫자와 비교하게 된다.
- prev(a), 현재 부등호, b에 올 숫자들(0~9)를 검사한다.
- prev는 이전에 넣어진 숫자를 의미하고, b에 들어올 숫자들은 현재 사용되지 않았고, 현재 부등호를 이용해서 a와 b를 비교했을 때 참이라면 b에 해당 숫자를 넣고 c로 이동하게 된다.

c에도 b에서 한 방법처럼 하고 다음으로 넘어가면 끝이난다.
- 끝에서는 현재까지 넣은 값(?)을 순서대로 저장한 문자열을 long 형으로 바꿔서 max, min과 비교한다.
- max보다 크면 max 값을 갱신하고, min보다 작으면 min 값을 갱신한다.
- (주의) 가장 큰 값의 경우 9876543210이므로 int 형보다 크다, Long형으로 바꿔야 함!

## 후기
재미있게 잘 풀었습니다~