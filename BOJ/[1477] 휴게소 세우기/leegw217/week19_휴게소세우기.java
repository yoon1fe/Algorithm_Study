import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class week19_휴게소세우기 {
	static int N, M, L;
	static int[] restArea;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		restArea = new int[N+2];
		st = new StringTokenizer(br.readLine());
		restArea[0] = 0;
		for(int i=1; i<=N; i++) restArea[i] = Integer.parseInt(st.nextToken());
		restArea[N+1] = L;
		Arrays.sort(restArea);
		int left = 0;
		int right = 0;
		for(int i=1; i<=N+1; i++) right = Math.max(right, restArea[i]-restArea[i-1]+1);
		while(left <= right) {
			int mid = (left + right) / 2;
			int sum = 0;
			
			for(int i=1; i<N+2; i++) 
				if(restArea[i-1] < restArea[i])
					sum += (restArea[i] - restArea[i-1] - 1) / mid;
			
			if(sum > M) left = mid + 1;
			else right = mid - 1;
		}
		System.out.println(left);
	}
}