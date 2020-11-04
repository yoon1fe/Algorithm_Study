import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] isNotPrime = new boolean[N + 1];
		List<Integer> primeList = new ArrayList<>();
		
		isNotPrime[1] = true;
		
		for(int i = 2; i <= Math.sqrt(N); i++) {
			if(isNotPrime[i]) continue;
			for(int j = i + i; j <= N; j += i) {
				isNotPrime[j] = true;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(!isNotPrime[i]) primeList.add(i);
		}
		
		System.out.println(solve(N, primeList));
	}	
	
	public static int solve(int n, List<Integer> list) {
		if(n == 1) return 0;
		if(n == 2) return 1;
		
		int left = 0, right = 1;
		int sum = list.get(0) + list.get(1), answer = list.contains(n) == true ? 1 : 0;
		
		while(left < right) {
			if(sum == n) {
				answer++;
				sum += list.get(++right);
			}else if(sum < n) {
				sum += list.get(++right);
			}else {
				sum -= list.get(left++);
			}
		}
		
		return answer;
	}	
}