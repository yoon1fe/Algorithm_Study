import java.util.ArrayList;
import java.util.HashSet;

public class Solution {
    static HashSet<String> keyCountSet = new HashSet<>();
    public int solution(String[][] relation) {
        int answer = 0;

        int N = relation[0].length;
        ArrayList<Key> keys = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=1; i<(1<<N); i++){
            tmp.clear();
            for(int j=0; j<N; j++){
                if((i & (1 << j)) > 0)
                    tmp.add(j);
            }

            Integer[] tmpArr = tmp.toArray(new Integer[tmp.size()]);
            keys.add(new Key(i, tmpArr));
        }

        for(int i=0; i<keys.size(); i++){
            Key key = keys.get(i);
            if(!key.isUnique)
                continue;

            boolean ret = check(key, relation);
            if(!ret)
                key.isUnique = false;
            else{
                for(int j=i+1; j<keys.size(); j++){
                    if((keys.get(j).num & keys.get(i).num) == keys.get(i).num)
                        keys.get(j).isUnique = false;
                }
            }
        }

        answer = 0;
        for(Key key : keys){
            if(key.isUnique)
                answer++;
        }

        return answer;
    }

    public boolean check(Key key, String[][] relation) {
        keyCountSet.clear();
        for(int i=0; i<relation.length; i++){
            String candidateKey = "";
            for(int idx : key.arr)
                candidateKey += " " + relation[i][idx];
            if(keyCountSet.contains(candidateKey))
                return false;
            keyCountSet.add(candidateKey);
        }
        return true;
    }

    static class Key{
        int num;
        Integer[] arr;
        boolean isUnique;

        public Key(int num, Integer[] arr) {
            this.num = num;
            this.arr = arr;
            this.isUnique = true;
        }
    }
}