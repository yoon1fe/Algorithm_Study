import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
	static class Tuple implements Comparable<Tuple>{
		int[] arr;
		Tuple(StringTokenizer st){
			arr = new int[st.countTokens()];
			for(int i=0; i<arr.length; i++)
				arr[i] = Integer.parseInt(st.nextToken());
		}
		
		@Override
		public int compareTo(Tuple tuple) {
			if(arr.length > tuple.arr.length)
				return 1;
			else if(arr.length == tuple.arr.length)
				return 0;
			return -1;
		}
		
	}
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(1, s.length()-1);
        Pattern pattern = Pattern.compile("[{](.*?)[}]");
        
        Matcher matcher = pattern.matcher(s);
        ArrayList<Tuple> list = new ArrayList<>();
        while(matcher.find()) {
        	StringTokenizer st = new StringTokenizer(matcher.group(1), ",");
        	list.add(new Tuple(st));
        	if(matcher.group() == null)
        		break;
        }
        
        Collections.sort(list);
        answer = new int[list.size()];
        int idx = 0;
        Set<Integer> exists = new HashSet<>();
        for(int i=0; i<list.size(); i++) {
        	Tuple tuple = list.get(i);
        	for(int num : tuple.arr) {
        		if(exists.contains(num))
        			continue;
        		exists.add(num);
        		answer[idx++] = num;
        	}
        }
        return answer;
    }
}