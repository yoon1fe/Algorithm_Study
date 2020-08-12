import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] students = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i =0 ; i< N; i++) students[i] = Integer.parseInt(st.nextToken());			
		st = new StringTokenizer(br.readLine(), " ");

		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long ans = N;
		
		for(int i = 0; i < N; i++) {
			students[i] -= B;
			if(students[i] <= 0) continue;
			if(students[i] % C == 0) ans+= students[i] / C;
			else ans += (students[i] / C) + 1;
		}
		System.out.println(ans);
	}
}