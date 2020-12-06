import java.util.Scanner;
public class week16_신기한소수 {
	static int N;
	static int[] number;
	static boolean[] visited;
	static StringBuilder sb;
	
	static void dfs(int cnt) {
		if(cnt == N) {
			System.out.println(sb.toString());
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(cnt == 0 && i == 0) continue;
			StringBuilder temp = new StringBuilder();
			temp.append(sb.toString());
			temp.append(i);
			if(isPrime(Integer.parseInt(temp.toString()))) {
				sb.append(i);
				visited[i] = true;
				dfs(cnt+1);
				visited[i] = false;
				sb.deleteCharAt(cnt);	
			}
		}
	}
	
	static boolean isPrime(int num) {
		if(num <= 1) return false;
		if(num == 2) return true;
		if(num % 2 == 0) return false;
		for(int i=3; i*i<=num; i++)
			if(num % i == 0) return false;
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		number = new int[10];
		visited = new boolean[10];
		sb = new StringBuilder();
		for(int i=0; i<10; i++) number[i] = i;
		dfs(0);
	}
}