public class week18_다트게임 {
    public int solution(String dartResult) {
        int answer = 0;
        char[] arr = dartResult.toCharArray();
        int[] score = new int[3];
        int idx = -1;
        for(int i=0; i<arr.length; i++) {
        	if(arr[i]-'0' >=0 && arr[i]-'0' <= 9) {
        		idx++;
        		if(arr[i]-'0' == 1) {
        			if(arr[i+1]-'0' == 0) {
        				score[idx] = 10;
        				i++;
        				continue;
        			}
        		}
        		score[idx] = arr[i]-'0';
        	} else if(arr[i] == 'D') score[idx] = (int)Math.pow(score[idx], 2);
        	else if(arr[i] == 'T') score[idx] = (int)Math.pow(score[idx], 3);
        	else if(arr[i] == '#') score[idx] *= -1;
        	else if(arr[i] == '*') {
        		score[idx] *= 2;
        		if(idx != 0) score[idx-1] *= 2;
        	}
        }
        for(int i=0; i<3; i++) answer += score[i];
        return answer;
    }
}