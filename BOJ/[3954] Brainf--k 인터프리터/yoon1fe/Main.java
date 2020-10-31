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