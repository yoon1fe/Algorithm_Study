import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static Set<Character> chars = new HashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String string = br.readLine();
		for(int i=0; i<string.length(); i++)
			chars.add(string.charAt(i));
		
		int ans = string.length();
		for(int i=0; i<=string.length(); i++) {
			if(isPalindrome(string + getReverse(string.substring(0,i)))) {
				System.out.println(ans + i);
				break;
			}
		}
	}
	
	public static boolean isPalindrome(String str) {
		for(int i=0, j=str.length()-1; i<str.length(); i++, j--) {
			if(str.charAt(i) != str.charAt(j))
				return false;
		}
		return true;
	}
	
	public static String getReverse(String str) {
		String ret = "";
		for(int i = str.length()-1; i>=0; i--)
			ret += String.valueOf(str.charAt(i));
		return ret;
	}
}