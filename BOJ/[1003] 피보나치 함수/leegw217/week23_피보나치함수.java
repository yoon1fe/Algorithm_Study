import java.util.Scanner;
public class week23_피보나치함수 {
	static int[][] dp = new int[41][2];
	
	static int[] fibonacci(int x) {
		if(dp[x][0] == 0 && dp[x][1] == 0) {
			dp[x][0] = fibonacci(x-1)[0] + fibonacci(x-2)[0];
			dp[x][1] = fibonacci(x-1)[1] + fibonacci(x-2)[1];
		}
		return dp[x];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		for(int i=0; i<T; i++) {
			int n = sc.nextInt();
			System.out.println(fibonacci(n)[0]+" "+fibonacci(n)[1]);
		}
	}
}