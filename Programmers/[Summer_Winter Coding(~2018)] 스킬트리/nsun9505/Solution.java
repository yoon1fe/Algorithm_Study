import java.util.HashSet;

class Solution {
    static HashSet<Character> isSkill = new HashSet<>();
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(int i=0; i<skill.length(); i++)
            isSkill.add(skill.charAt(i));

        for(String sk : skill_trees){
            String ret = "";
            for(int i = 0; i<sk.length(); i++){
                if(isSkill.contains(sk.charAt(i)))
                    ret += String.valueOf(sk.charAt(i));
            }
            
            if(ret.length() == 0)
                answer++;
            else if(ret.equals(skill.substring(0, ret.length())))
                answer++;
        }
        return answer;
    }
}