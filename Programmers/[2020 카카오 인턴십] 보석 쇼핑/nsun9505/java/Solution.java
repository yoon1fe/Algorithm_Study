import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
    static HashMap<String, Integer> gemMap = new HashMap<>();
    static int[] convertGems;
    public int[] solution(String[] gems) {
        int idx = 0;
        for(int i=0; i<gems.length; i++) {
            if(gemMap.containsKey(gems[i]))
                continue;
            gemMap.put(gems[i], idx++);
        }

        convertGems = new int[gems.length];
        for(int i=0; i<gems.length; i++)
            convertGems[i] = gemMap.get(gems[i]);

        return findRange(convertGems);
    }

    public int[] findRange(int[] gems){
        int start = 0;
        int end = 0;
        int retLen = Integer.MAX_VALUE;
        int retStart = 0;
        int retEnd = 0;
        int[] cnts = new int[gemMap.size()];
        while(true){
            if(check(cnts)){
                cnts[gems[start]]--;
                int len = end - start + 1;
                if(len < retLen){
                    retLen = len;
                    retStart = start+1;
                    retEnd = end;
                }
                start++;
            } else {
                if(end >= gems.length)
                    break;
                cnts[gems[end]]++;
                end++;
            }
        }

        return new int[]{retStart, retEnd};
    }

    public boolean check(int[] cnts){
        for(int cnt : cnts) {
            if (cnt == 0)
                return false;
        }
        return true;
    }
}