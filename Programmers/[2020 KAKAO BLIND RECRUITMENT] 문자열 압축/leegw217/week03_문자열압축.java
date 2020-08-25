
public class week03_문자열압축 {
	public int solution(String s) {
        int min = 99999;
        if(s.length() == 1) return 1; // 문자열길이 1개일때 처리
        for(int i=1; i<=s.length()/2; i++) { // 문자열 자르는 단위 수
        	String part = s.substring(0,i); // 기준 문자열
        	int cnt = 1;
        	StringBuilder answer = new StringBuilder();
        	int j;
        	for(j=i; j+i<=s.length(); j += i) { // 문자열 비교
        		String next = s.substring(j,j+i); // 비교하는 문자열
        		if(part.equals(next)) {
        			cnt++;
        		}
        		else {
        			if(cnt>1) {
        				answer.append(cnt);
        			}
        			answer.append(part);
        			part = next;
        			cnt = 1;
        			continue;
        		}
        	}
        	if(cnt>1) answer.append(cnt);
        	answer.append(s.subSequence(j-i, s.length()));
        	if(min > answer.length()) min = answer.length();
        }
        return min;
    }
}
