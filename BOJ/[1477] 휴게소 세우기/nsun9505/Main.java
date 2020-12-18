import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, L;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	public static void solution() {
		int start = 1;
		int end = L+1;
		while(start < end) {
			int mid = (start + end) / 2;
			int cnt = 0;
			for(int i=1; i<=N; i++) {
				if(arr[i] - arr[i-1] <= mid)
					continue;
				cnt += (arr[i]-arr[i-1]-1)/mid;
			}
			if(cnt <= M)
				end = mid;
			else
				start = mid + 1;
		}
		System.out.println(start);
	}
	
	public static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[N+2];
		arr[0] = 0;
		arr[N+1] = L;
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
	}
}