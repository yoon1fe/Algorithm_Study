public class week24_124나라의숫자 {
	public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        int q = n;
        int r = 0;
        while(true) {
        	r = q % 3;
        	q = q / 3;
        	if(r == 0) {
        		q = q - 1;
        		r = 4;
        	}
        	answer.append(r);
        	if(q == 0) break;
        }
        return answer.reverse().toString();
    }
}