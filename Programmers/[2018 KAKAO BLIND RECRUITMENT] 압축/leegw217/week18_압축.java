import java.util.ArrayList;
import java.util.List;
public class week18_압축 {
	public int[] solution(String msg) {
        List<Integer> ans = new ArrayList<Integer>();
        int mIdx = 0;
        char[] message = msg.toCharArray();
        List<String> dict = new ArrayList<String>();
        dict.add(",");
        for(int i=0; i<26; i++) {
        	char ch = (char)('A'+i);
        	dict.add(String.valueOf(ch));
        }
        String s = "";
        int idx = 0;
        while(mIdx < msg.length()) {
        	s += String.valueOf(message[mIdx++]);
        	if(dict.contains(s)) {
        		idx = dict.indexOf(s);
        		if(mIdx == msg.length()) ans.add(idx);
        	} else {
        		ans.add(idx);
        		dict.add(s);
        		s = "";
        		mIdx--;
        	}
        }
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++) answer[i] = ans.get(i);
        return answer;
    }
}