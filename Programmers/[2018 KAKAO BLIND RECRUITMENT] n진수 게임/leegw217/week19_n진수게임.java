import java.util.LinkedList;
public class week19_n진수게임 {
	char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F'};
	LinkedList<Character> makeNumber(int s, int num) {
		LinkedList<Character> list = new LinkedList<Character>();
		int q = num;
		int r = 0;
		while(true) {
			if(q < s) {
				if(q >= 10) list.addFirst(alpha[q%10]);
				else {
					char p = (char)(q + '0');
					list.addFirst(p);
				}
				break;
			}
			r = q % s;
			if(r >= 10) list.addFirst(alpha[r%10]);
			else {
				char p = (char)(r + '0');
				list.addFirst(p);
			}
			q = q / s;
		}
		return list;
	}
	
	public String solution(int n, int t, int m, int p) {
        String answer = "";
        int idx = 1;
        int pp = p;
        if(p == m) pp = 0;
        loop:
        for(int num=0; num<t*m; num++) {
        	LinkedList<Character> number = makeNumber(n, num);
        	for(int i=0; i<number.size(); i++) {
        		if(idx % m == pp) answer += number.get(i);
        		idx++;
        		if(answer.length() == t) break loop;
        	}
        }
        return answer;
    }
}