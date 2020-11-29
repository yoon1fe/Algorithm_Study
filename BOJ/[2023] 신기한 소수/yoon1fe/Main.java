import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer> num = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		input();
		
		dfs();
	}
	
	public static void dfs() {
		if(num.size() == N) {
			System.out.println(listToInt(num));
			return;
		}
		
		for(int i = 1; i <= 9; i++) {
			num.add(i);
			if(isPrime(listToInt(num)) == true) {
				dfs();
			}
			num.remove(num.size() - 1);
		}
	}
	
	public static int listToInt(List<Integer> num) {
		StringBuilder sb = new StringBuilder();
		for(int i : num) sb.append(i);
		
		return Integer.parseInt(sb.toString());
	}
	
	public static boolean isPrime(int num) {
		if(num < 2) return false;
		
		for(int i = 2; i * i <= num; i++) {
			if(num % i == 0) return false;
		}

		return true;
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
	}
}