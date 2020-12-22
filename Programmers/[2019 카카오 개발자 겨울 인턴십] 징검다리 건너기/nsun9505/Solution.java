public class Solution {	
	 public int solution(int[] stones, int k) {
		 int answer = 0;
		 int start = 1;
		 int end = 200000000;
		 while(start <= end) {
			 int mid = (start + end) / 2;
			 if(check(stones, k, mid)) {
				 start = mid + 1;
				 if(answer < mid)
					 answer = mid;
			 } else {
				 end = mid - 1;
			 }
		 }
		 return answer;
	 }
	 
	 public static boolean check(int[] stones, int k, int mid) {
		 int cnt = 0;
		 for(int i=0; i<stones.length; i++) {
			 if(stones[i] < mid)
				 cnt++;
			 else
				 cnt = 0;
			 if(cnt >= k)
				 return false;
		 }
		 return true;
	 }
}
