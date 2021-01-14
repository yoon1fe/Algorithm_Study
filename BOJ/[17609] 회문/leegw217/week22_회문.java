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