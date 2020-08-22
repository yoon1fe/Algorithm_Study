public class Solution {
    public static int solution(String s) {
        int answer = s.length();

        for (int subLen = 1; subLen <= s.length() / 2; subLen++) {
            String newStr = "";
            String standard = s.substring(0, subLen);
            int cnt = 1;

            for (int i = subLen; i < s.length(); i += subLen) {
                if (i + subLen > s.length()) {
                    newStr += s.substring(i);
                    break;
                }
                String comp = s.substring(i, i + subLen);

                if (standard.equals(comp)) cnt += 1;
                else {
                    if (cnt > 1) newStr += Integer.toString(cnt) + standard;
                    else newStr += standard;

                    standard = comp;
                    cnt = 1;
                }
            }
            if (cnt > 1) newStr += Integer.toString(cnt) + standard;
            else newStr += standard;

            answer = answer < newStr.length() ? answer : newStr.length();
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.print(solution("aabbaccc"));
    }
}