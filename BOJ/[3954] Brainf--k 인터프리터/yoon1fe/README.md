## [3954] Brainf**k 인터프리터 - Java

### :fu: 분류

> 시뮬레이션



### :fu: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int m, c, i;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			m = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken()); i = Integer.parseInt(st.nextToken());
			
			int[] memory = new int[m];
			char[] code = new char[c];
			char[] input = new char[i];
			
			code = br.readLine().toCharArray();
			input = br.readLine().toCharArray();
			
			bw.append(execute(memory, code, input) + "\n");
		}
		
		bw.flush();
		bw.close();
	}	
	
	public static String execute(int[] memory, char[] code, char[] input) {
		String result = "Terminates";
		int[] bracket = new int[code.length];
		int ptr = 0, cnt = 0, inputIdx = 0, maxRightBracket = 0;
		
		// 괄호 짝짓기
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < code.length; i++) {
			if(code[i] == '[') stack.push(i);
			else if(code[i] == ']') {
				bracket[stack.peek()] = i;
				bracket[i] = stack.pop();
			}
		}

		for(int i = 0; i < code.length; i++) {
			
			if(++cnt == 50000000) {
				result = "Loops " + bracket[maxRightBracket] + " " + maxRightBracket;
				break;
			}
			
			switch(code[i]) {
			case '-': memory[ptr] = (memory[ptr] - 1) % (1 << 8); break;
			case '+': memory[ptr] = (memory[ptr] + 1) % (1 << 8); break;
			case '<': ptr = (ptr - 1 + memory.length) % memory.length; break;
			case '>': ptr = (ptr + 1) % memory.length; break;
			case '[': 
				if(memory[ptr] == 0) i = bracket[i] - 1;
				break;
			case ']': 
				if(memory[ptr] != 0) {
					maxRightBracket = Math.max(maxRightBracket, i);
					i = bracket[i] - 1; 
				}
				break;
			case '.': break; // 출력
			case ',': memory[ptr] = inputIdx == input.length ? 255 : input[inputIdx++]; break;
			}
			
		}
		return result;
	}
	
}
```



### :fu: 풀이 방법

닉값하는 문젭니다.

메모리 초과에서 한참 걸렸네여 ㅜ



문제의 큰 흐름은 별로 까다롭지 않습니다.. 각 명령어에 맞는 로직을 짜고 인풋으로 받은 코드 내부에서 돌아다니면 됩니다.

 

제 코드는 자꾸 28프로에서 메모리 초과가 떴습니다ㅠㅠ

이유가 뭥고하니 Map 때문이었슴니다.... 괄호 짝을 Map에다가 넣어서 관리했는데 이게 문제였습니다. ... ㅠㅠ 그래서 그냥 배열에 넣는걸로 바꿨습니다.......

 

여기서 주의할 점은 중첩된 루프입니다. 

만약 코드가 +[+[-]+] 라면 정답은 1 7 이 나와야 합니다. 왜냐면 3~5 반복문은 빠져나올 수 있기 때문이져. 그렇기 때문에 닫는 괄호중에서 가장 바깥쪽에 있는 애의 정보를 갖고 있어야합니다. 그럼 5000만번이 돌았을 때의 maxRightBracket의 인덱스 값이 바로 무한 루프의 닫는 괄호가 되겠죠.

### :fu: 후기

하 시간 너무 잡아먹었다 ㅜㅜ

그래도 재밌는 문제였슴니다!!!

감사합니다!!!