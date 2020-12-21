import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println(solve(input()));
	}
	
	public static int solve(String input) {
		// 입력받은 문자열이 이미 팰린드롬일때
		if(input.equals(new StringBuilder(input).reverse().toString())) {
			return input.length();
		}
		
		for(int i = 1; i < input.length(); i++) {
			StringBuilder sb = new StringBuilder(input);

			// 앞에서부터 길이 1씩 늘려가면서 뒤에 붙여보자
			sb.append(new StringBuilder(input.substring(0, i)).reverse());
			
			// 팰린드롬인지 체크
			if(sb.toString().equals(sb.reverse().toString())) {
				return sb.length();
			}
		}

		return -1;
	}
		
	public static String input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
}