import java.util.ArrayList;

public class 뉴스클러스터링 {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        if(str1.equals(str2))
            return 65536;

        ArrayList<String> str1MultiSet = getMultiSet(str1);
        ArrayList<String> str2MultiSet = getMultiSet(str2);

        int intersecSize = getIntersectionSize(str1MultiSet, str2MultiSet);
        int unionSize = getUnionSize(str1MultiSet, str2MultiSet);
        double ret = intersecSize / (unionSize*1.0);

        return (int)(ret * 65536);
    }

    public static void print(ArrayList<String> strlist){
        for(String str : strlist)
            System.out.print(str + " ");
        System.out.println();
    }

    public static int getIntersectionSize(ArrayList<String> str1, ArrayList<String> str2) {
        int cnt = 0;
        boolean isUsed[] = new boolean[str2.size()];
        for(int i=0; i<str1.size(); i++) {
            for (int j = 0; j < str2.size(); j++) {
                if (str1.get(i).equals(str2.get(j)) && !isUsed[j]) {
                    cnt++;
                    isUsed[j] = true;
                    break;
                }
            }
        }
        return cnt;
    }

    public static int getUnionSize(ArrayList<String> str1, ArrayList<String> str2) {
        int cnt = 0;
        boolean isUsed[] = new boolean[str2.size()];
        for(String s1 : str1){
            boolean flag = true;
            for(int j=0; j<str2.size(); j++){
                if(s1.equals(str2.get(j)) && !isUsed[j]){
                    isUsed[j] = true;
                    flag = false;
                    break;
                }
            }
            if(flag)
                cnt++;
        }

        return cnt + str2.size();
    }

    public static ArrayList<String> getMultiSet(String str){
        ArrayList<String> result = new ArrayList<>();
        for(int i=2; i<=str.length(); i++) {
            String subStr = str.substring(i-2, i).toLowerCase();
            if(subStr.matches("[a-z]*$"))
                result.add(subStr);
        }
        return result;
    }

    public static void main(String[] args){
        뉴스클러스터링 s = new 뉴스클러스터링();
        System.out.println(s.solution("E=M*C^2", "e=m*c^2"));
    }
}