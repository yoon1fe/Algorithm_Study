import java.util.Stack;

public class week03_괄호변환 {
	public String[] makeUV(String p) {
		String[] arr = new String[2];
		arr[0] = "";
		arr[1] = "";
        int left = 0, right = 0;
        for(int i=0; i<p.length(); i++) {
        	if(p.charAt(i) == '(') left++;
        	else right++;

        	if(left == right) {
        		if(i == p.length()-1) {
        			arr[0] = p;
        			arr[1] = "";
        		}
        		else {
        			arr[0] = p.substring(0,i+1);
        			arr[1] = p.substring(i+1, p.length());
        			break;
        		}
        	}
        }
        return arr;
	}
	
	public String solution(String p) {
		String answer = new String();
        String[] arr = makeUV(p);
        while(true) {
        	boolean flag = true;
        	Stack<Character> st = new Stack<Character>();
        	for(int i=0; i<arr[0].length(); i++) {
        		if(arr[0].charAt(i) == '(') st.push('(');
        		else {
        			if(st.isEmpty()) {
        				flag = false;
        				break;
        			}
        			else st.pop();
        		}
        	}
        	if(flag) {
        		if(arr[1].length() == 0) {
        			answer += arr[0];
        			break;
        		}
        		answer += arr[0];
        		arr = makeUV(arr[1]);
        		continue;
        	}
        	else {
        		answer += "(";
        		answer += solution(arr[1]);
        		answer += ")";
        		for(int j=1; j<arr[0].length()-1; j++) 
        			answer += (arr[0].charAt(j) == '(' ? ")":"(");
        		break;
        	}
        }
        return answer;
    }
}