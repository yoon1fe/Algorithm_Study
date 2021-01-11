import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		input();
	}
	
	public static int check(String str) {
		if(checkPalindrome(str) == true) return 0;
		else if(checkPseudoPalindrome(str) == true) return 1;
		else return 2;
	}
	
	public static boolean checkPalindrome(String str) {
		int s = 0, e = str.length() - 1;
		
		while(s <= e) {
			if(str.charAt(s++) != str.charAt(e--)) return false;
		}
		
		return true;
	}

	public static boolean checkPseudoPalindrome(String str) {
		int s = 0, e = str.length() - 1;

		while(s <= e) {
			if(str.charAt(s) != str.charAt(e)) {
				// 왼쪽 없애고 난 뒤의 문자열 | 오른쪽 없애고 난 뒤의 문자열 중 하나라도 회문이 있으면 유사 회문
				return checkPalindrome(str.substring(s+1, e+1)) | checkPalindrome(str.substring(s, e));
			}
			
			s++; e--;
		}

		return true;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			bw.write(String.valueOf(check(br.readLine())));
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}
