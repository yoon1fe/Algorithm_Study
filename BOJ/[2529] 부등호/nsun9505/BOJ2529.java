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