package Programmers;

class Solution {
	static class Result{
		int score, total;
		String bonus;
	}
	
	public int solution(String dartResult) {
		int answer = 0;
		Result[] results = new Result[3];
		for(int i = 0; i < 3; i++) results[i] = new Result();
		
		getTurn(dartResult, results);

		for(int i = 0; i < results.length; i++) {
			Result result = results[i];
			
			for(int j = 0; j < result.bonus.length(); j++) {
				switch(result.bonus.charAt(j)){
				case 'S': result.total = result.score; break;
				case 'D': result.total = (int)Math.pow(result.score, 2); break;
				case 'T': result.total = (int)Math.pow(result.score, 3); break;
				case '*':
					result.total *= 2;
					if(0 < i) results[i-1].total *= 2;
					break;
				case '#': result.total *= -1; break;
				}
			}
		}
        
		for(Result r : results) answer += r.total;
		
        return answer;
    }

	private void getTurn(String dartResult, Result[] results) {
		int idx = -1;
        StringBuilder temp = new StringBuilder();
        
        for(int i = 0; i < dartResult.length(); i++) {
        	int score = getScore(dartResult, i);
        	if(score != -1) {
        		if(temp.length() != 0) {
        			results[idx].bonus = temp.toString();
        			temp.delete(0, temp.length());
        		}
        		
        		results[++idx].score = score;
        		i = score == 10 ? i+1 : i;
        		continue;
        	}
        	temp.append(dartResult.charAt(i));
        }
        results[idx].bonus = temp.toString();
	}
	
	public int getScore(String dartResult, int idx) {
		if('0' > dartResult.charAt(idx) || dartResult.charAt(idx) > '9') return -1;
		// 10 체크
		if(dartResult.charAt(idx) == '1' && dartResult.charAt(idx + 1) == '0') return 10;
		else return dartResult.charAt(idx) - '0';
	}

	public static void main(String[] args) {
		Solution s = new Solution();

		
		System.out.println(s.solution("10S2D*3T*"));
//		System.out.println(s.solution("1S2D*3T"));
//		System.out.println(s.solution("1D2S#10S"));
//		System.out.println(s.solution("1D2S0T"));
//		System.out.println(s.solution("1S*2T*3S"));
//		System.out.println(s.solution("1D#2S*3S"));
//		System.out.println(s.solution("1T2D3D#"));
//		System.out.println(s.solution("1D2S3T*"));
	}
}