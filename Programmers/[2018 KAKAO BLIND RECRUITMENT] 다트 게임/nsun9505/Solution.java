class Solution {
    public int solution(String dartResult) {
		int answer = 0;
		int[] arr = new int[3];
		String score = "";
		int idx = 0;
		for(int i=0; i<dartResult.length(); i++) {
			char ch = dartResult.charAt(i);
			if(ch >= '0' && ch <= '9')
				score += String.valueOf(ch);
			else if(ch == 'S' || ch == 'D' || ch == 'T') {
				arr[idx++] = calcScore(Integer.parseInt(score), ch);
				score = "";
			} else if(ch == '*' || ch == '#') {
				int curIdx = idx - 1;
				if(ch == '*') {
					arr[curIdx] *= 2;
					if(curIdx-1 >= 0)
						arr[curIdx-1] *= 2;
				} else 
					arr[curIdx] *= (-1);
			}
		}
		
		for(int i=0; i<3; i++)
			answer += arr[i];
		return answer;
	}
	
	public static int calcScore(int score, char area) {
		if(area == 'S')
			return score;
		else if(area == 'D')
			return score * score;
		return score * score * score;
	}
}