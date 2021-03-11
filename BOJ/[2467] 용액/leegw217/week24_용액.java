import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class week24_용액 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] number = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) number[i] = Integer.parseInt(st.nextToken());
		int l = 0;
		int r = N-1;
		int answer = Integer.MAX_VALUE;
		int left = 0, right = 0;
		while(l < r) {
			int sum = number[l] + number[r];
			if(answer > Math.abs(sum)) {
				answer = Math.abs(sum);
				left = number[l];
				right = number[r];
			}
			if(sum < 0) l++;
			else r--;
		}
		
		System.out.println(left + " " + right);
	}
}