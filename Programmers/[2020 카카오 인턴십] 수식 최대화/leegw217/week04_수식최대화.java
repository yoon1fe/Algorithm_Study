package week04;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class week04_수식최대화 {
	static String[] arr = {"+","-","*"};
	static String[] result = new String[3];
	static boolean[] visited = new boolean[3];
	static ArrayList<Long> num;
	static ArrayList<String> oper;
	static long max = Long.MIN_VALUE;
	
	static void makePerm(int cnt) {
		if(cnt == result.length) {
			ArrayList<Long> tn = (ArrayList<Long>) num.clone();
			ArrayList<String> to = (ArrayList<String>) oper.clone();
			for(int i=0; i<3; i++)
				tn = calc(tn, to, result[i]);
			if(max < Math.abs(tn.get(0))) max = Math.abs(tn.get(0));
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!visited[i]) {
				result[cnt] = arr[i];
				visited[i] = true;
				makePerm(cnt+1);
				visited[i] = false;
			}
		}
	}
	
	static ArrayList<Long> calc(ArrayList<Long> tn, ArrayList<String> to, String op) {
		long one;
		long two;
		for(int i=0; i<to.size(); i++) {
			if(to.get(i).equals(op)) {
				to.remove(i);
				one = tn.remove(i);
				two = tn.remove(i);
				switch(op) {
				case "+": tn.add(i,one+two); break;
				case "-": tn.add(i,one-two); break;
				case "*": tn.add(i,one*two); break;
				}
				i=-1;
			}
		}
		return tn;
	}
	
	public long solution(String expression) {
        num = new ArrayList<Long>();
        oper = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(expression,"+|-|*");
        for(int i=0; st.hasMoreElements(); i++)
        	num.add(Long.parseLong(st.nextToken()));
        st = new StringTokenizer(expression,"0|1|2|3|4|5|6|7|8|9");
        for(int i=0; st.hasMoreElements(); i++)
        	oper.add(st.nextToken());
        makePerm(0);
        return max;
    }
}