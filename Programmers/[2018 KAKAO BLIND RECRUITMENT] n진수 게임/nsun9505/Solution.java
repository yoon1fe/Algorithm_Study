class Solution {
   public static String[] remainder = {
		      "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
		        "A", "B", "C", "D", "E", "F"
		    };
	
	public String solution(int n, int t, int m, int p) {
        String answer = "";
        StringBuffer sb = new StringBuffer();
        int cnt = 0;
        int idx = p - 1;
        int num = 0;
        while(cnt < t) {
        	sb.append(numToNjinsoo(num++, n));
        	while(idx < sb.length() && cnt < t) {
        		answer += String.valueOf(sb.charAt(idx));
        		idx += m;
        		cnt++;
        	}
        }
        return answer;
    }
	
	public static String numToNjinsoo(int num, int n) {
		if(num == 0)
			return "0";
		
		StringBuffer sb = new StringBuffer();
		while(num > 0) {
			sb.append(remainder[num%n]);
			num /= n;
		}
		
		sb = sb.reverse();
		return sb.toString();
	}
}