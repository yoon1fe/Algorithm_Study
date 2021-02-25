import java.util.ArrayList;

public class Solution {
    static ArrayList<String> list = new ArrayList<>();
    public int solution(String string){
        int ans = string.length();
        for(int len=1; len<string.length(); len++)
            ans = Math.min(ans, compress(string, len));

        return ans;
    }

    public int compress(String str, int len) {
        list.clear();
        for (int i = 0; i < str.length(); i += len) {
            String ret = "";
            if (i + len > str.length())
                ret = str.substring(i);
            else
                ret = str.substring(i, i + len);
            list.add(ret);
        }

        String ret = "";
        for (int i = 0; i < list.size(); ) {
            String comp = list.get(i);
            int cnt = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).equals(comp))
                    cnt++;
                else
                    break;
            }
            i += cnt;
            if (cnt > 1)
                ret += String.valueOf(cnt) + comp;
            else
                ret += comp;
        }
        return ret.length();
    }
}