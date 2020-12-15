import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
	public static HashMap<String, Integer> dict;
    static int index = 27;
	public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        init();        
        while(msg.length() > 0) {
        	String key = getLongestString(msg);
        	list.add(dict.get(key));
        	msg = msg.substring(key.length());
        }
        
        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++)
        	answer[i] = list.get(i);
        
        return answer;
    }
	
	public static String getLongestString(String msg) {
		String ret = "";
		for(int i=1; i<=msg.length(); i++) {
			ret = msg.substring(0, i);
			if(dict.containsKey(ret))
				continue;
			dict.put(ret, index++);
			ret = msg.substring(0, i-1);
			break;
		}
		return ret;
	}
	
	public static void init() {
		dict = new HashMap<>();
        
		for(int i=0; i<26; i++)
			dict.put(String.valueOf(Character.toString((char) (i+'A'))), i+1);
	}
}