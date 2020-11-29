import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2023 {
	public static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		int[] arr = {2,3,5,7};
		for(int i : arr) {
			String result = String.valueOf(i);
			solution(1, result);	
		}
		
	}
	
	public static void solution(int cur, String result) {
		if(cur >= N) {
			System.out.println(result);
			return;
		}
		
		for(int i=1; i<10; i+=2) {
			String pnum = result + String.valueOf(i);
			int num = Integer.parseInt(pnum);
			if(isPrimeNumber(num))
				solution(cur+1, pnum);
		}
	}
	
	public static boolean isPrimeNumber(int prime) {
		if(prime <= 1)
			return false;
		
		if(prime%2 == 0)
			return prime == 2 ? true : false;
		
		for(int i = 3; i <=Math.sqrt(prime); i+=2)
			if(prime % i == 0)
				return false;
		return true;
	}
	
	
	public static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	}
}