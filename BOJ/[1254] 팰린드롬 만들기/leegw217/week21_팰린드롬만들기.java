import java.util.Scanner;
public class week21_팰린드롬만들기 {
	static boolean check(String s) {
		int start = 0;
		int end = s.length()-1;
		while(start < end) {
			if(s.charAt(start) != s.charAt(end)) return false;
			start++;
			end--;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		if(check(s)) System.out.println(s.length());
		else {
			for(int i=0; i<s.length(); i++) {
				String t = s.substring(0, i);
				StringBuffer sb = new StringBuffer(t);
				t = s + sb.reverse().toString();
				if(check(t)) {
					System.out.println(t.length());
					break;
				}
			}
		}
	}
}