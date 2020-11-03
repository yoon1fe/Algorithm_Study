import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static List<Integer> sequence = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		input();

		System.out.println(sequence.size() - 1);
	}	
	
	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sequence.add(0);
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(sequence.get(sequence.size() - 1) < num) sequence.add(num);
			// 넣을 인덱스 구해서 넣기
			else binarySearch(num, 0, sequence.size() - 1);
		}
	}
	
	public static void binarySearch(int num, int left, int right) {
		while(left < right) {
			int mid = (left + right) / 2;
			
			if(sequence.get(mid) >= num) right = mid;
			else left = mid + 1;
		}
		sequence.set(right, num);
	}
	
}