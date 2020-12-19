import java.io.*;
import java.util.*;


public class Main {
	static int N, K, L;
	
	public static void main(String[] args) throws Exception {
		input();
		
		System.out.println(solve());
	}
	
	public static int solve() {
		int answer = 0;
		List<Integer> curRound = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			curRound.add(i);
		}
		
		while(true) {
			List<Integer> nextRound = new ArrayList<>();
			if(curRound.size() == 1) break;
			
			for(int i = 0; i < curRound.size(); i+=2) {
				// 홀수일 때 마지막 참가자
				if(i == curRound.size() - 1) {
					nextRound.add(curRound.get(i));
					break;
				}
				int left = curRound.get(i);
				int right = curRound.get(i+1);

				// 둘이 만나면 종료
				if((left == K && right == L )|| (left == L && right == K)) return answer+1;
				// 다음 라운드 진출자 결정
				else if(left == K || left == L) nextRound.add(left);
				else if(right == K || right == L) nextRound.add(right);
				else nextRound.add(left);
			}
			answer++;
			
			curRound = nextRound;
		}
		
		return -1;
	}
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
	}
}