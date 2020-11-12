import java.io.*;
import java.util.*;

public class Main {
	static int N, k;
	
	public static void main(String[] args) throws Exception {
		input();

		System.out.println(solve());
	}
	
	public static int solve() {
		int answer = 0;
		int left = 1, right = k;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			long sum = 0;
			for(int i = 1; i <= N; i++) sum += Math.min(N, mid / i);
			
			if(sum >= k) {
				right = mid - 1;
				answer = mid;
			}else 
				left = mid + 1;
		}
		
		return answer;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); k = Integer.parseInt(br.readLine());
	}
}