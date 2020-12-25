import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[2];
		arr[0] = Integer.parseInt(st.nextToken());
		arr[1] = Integer.parseInt(st.nextToken());
		int ans = 1;
		
		while(true) {
			arr[0] = calcNext(arr[0]);
			arr[1] = calcNext(arr[1]);
			if(arr[0] == arr[1])
				break;
			ans++;
		}
		System.out.println(ans);
	}
	
	public static int calcNext(int num) {
		if(num % 2 == 0)
			return num / 2;
		return num / 2 + 1;
	}
}
