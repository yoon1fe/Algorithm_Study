import java.util.ArrayList;
import java.util.Comparator;
public class week20_튜플 {
	public int[] solution(String s) {
        ArrayList<ArrayList<Integer>> numList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> num = new ArrayList<Integer>();
        String n = "";
        // 문자열 파싱
        for(int i=0; i<s.length()-1; i++) {
        	if(s.charAt(i) == '}') {
        		if(!n.equals("")) num.add(Integer.parseInt(n));
        		ArrayList<Integer> temp = new ArrayList<Integer>();
        		temp.addAll(num);
        		numList.add(temp);
        		num.clear();
        		n = "";
        	} else if(s.charAt(i) == ',') {
        		if(!n.equals("")) num.add(Integer.parseInt(n));
        		n = "";
        	} else if(s.charAt(i) != '{') {
        		n += s.charAt(i);
        	}
        }
        // 배열 길이 오름차순으로 정렬
        numList.sort(new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return o1.size() - o2.size();
			}
		});
        // 튜플 찾기
        ArrayList<Integer> tuple = new ArrayList<Integer>();
        for(int i=0; i<numList.size(); i++) {
        	for(int j=0; j<numList.get(i).size(); j++) {
        		if(!tuple.contains(numList.get(i).get(j))) {
        			tuple.add(numList.get(i).get(j));
        			break;
        		}
        	}
        }
        int[] answer = new int[tuple.size()];
        for(int i=0; i<tuple.size(); i++) answer[i] = tuple.get(i);
        return answer;
    }
}