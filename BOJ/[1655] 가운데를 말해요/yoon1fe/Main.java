import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static List<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		input();
	}

	public static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		while(N-- > 0) {
			int num = Integer.parseInt(br.readLine());
			System.out.println(solve(num));
		}
	}
	
	public static int solve(int n) {
		// 이분탐색으로 정렬된 리스트 구하기
		int left = 0, right = list.size() - 1, mid = (left + right) / 2;
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			if(list.get(mid) > n) {
				right = mid - 1;
			}else {
				left = mid + 1;
				mid++;
			}
		}
		
		list.add(mid, n);
		
		return list.size() % 2 == 0 ? list.get((list.size() / 2) - 1) : list.get(list.size() / 2);
	}
}