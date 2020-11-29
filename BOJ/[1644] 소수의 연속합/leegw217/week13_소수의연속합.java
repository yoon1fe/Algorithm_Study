import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class week13_소수의연속합 {
	static List<Integer> prime = new ArrayList<Integer>();
	static int answer = 0;
	
	static void makePrime(int N) {
		boolean[] number = new boolean[N+1];
		number[0] = number[1] = true;
		
		for(int i=2; i*i<=N; i++) 
			if(!number[i]) 
				for(int j=i*i; j<=N; j+=i) number[j] = true;
		
		for(int i=2; i<=N; i++)
			if(!number[i]) prime.add(i);
	}
	
	static void findContinuity(int N) {
		int startIdx = 0;
		int endIdx = 0;
		int sum = prime.get(0);
		while(true) {
			if(startIdx == endIdx && endIdx == prime.size()-1) break;
			if(sum <= N) {
				endIdx++;
				if(sum == N) answer++;
				sum += prime.get(endIdx);
			} else {
				sum -= prime.get(startIdx);
				startIdx++;
			}
		}
		if(prime.get(prime.size()-1) == N) answer++;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if(N == 1) {
			System.out.println(0);
		} else {
			makePrime(N);
			findContinuity(N);
			System.out.println(answer);
		}
	}
}