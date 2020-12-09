import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
public class week17_뉴스클러스터링 {
	public int solution(String str1, String str2) {
        int answer = 0;
        String s1 = str1.toLowerCase();
        String s2 = str2.toLowerCase();
        HashMap<String, Integer> A = new HashMap<String, Integer>();
        HashMap<String, Integer> B = new HashMap<String, Integer>();
        for(int i=0; i<s1.length()-1; i++) {
        	String s = s1.substring(i, i+2);
        	s = s.replaceAll("[^a-z]", "");
        	if(s.length() == 2) {
        		if(A.containsKey(s)) A.put(s, A.get(s)+1);
        		else A.put(s, 1);
        	}
        }
        for(int i=0; i<s2.length()-1; i++) {
        	String s = s2.substring(i, i+2);
        	s = s.replaceAll("[^a-z]", "");
        	if(s.length() == 2) {
        		if(B.containsKey(s)) B.put(s, B.get(s)+1);
        		else B.put(s, 1);
        	}
        }
        Set<String> aSet = new HashSet<String>();
        Set<String> bSet = new HashSet<String>();
        aSet = A.keySet();
        bSet = B.keySet();
        if(aSet.size()==0 && bSet.size()==0) return 65536;
        Set<String> intersec = new HashSet<String>();
        Set<String> union = new HashSet<String>();
        intersec.addAll(aSet);
        union.addAll(aSet);
        intersec.retainAll(bSet);
        union.addAll(bSet);
        int interCnt = intersec.size();
        int unionCnt = union.size();
        for(String ss : intersec) {
        	int min = Math.min(A.get(ss), B.get(ss));
        	interCnt += min-1;
        }
        for(String ss : union) {
        	int max = Math.max(A.containsKey(ss)?A.get(ss):0, B.containsKey(ss)?B.get(ss):0);
        	unionCnt += max-1;
        }
        answer = (int)((float)interCnt/(float)unionCnt*65536);
        return answer;
    }
}