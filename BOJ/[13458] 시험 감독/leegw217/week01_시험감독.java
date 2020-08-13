import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class week01_시험감독 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		List<Integer> arr = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr.add(Integer.parseInt(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
	
		long answer = 0;
		for(int i=0; i<N; i++) {
			answer += 1;
			int n = arr.get(i) - B;
			if(n>0) {
				if(n%C > 0) answer += n/C + 1;
				else answer += n/C;
			}
		}
		System.out.println(answer);
	}

}
