# [Summber\Winter Coding(~2018)] 스킬트리 - JAVA

## 분류
> 구현
>
> 정규표현식

## 코드
```java
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
```

## 문제 풀이
먼저 스킬을 Set에 등록합니다.

스킬트리 배열에서 스킬트리를 가져와서 Set에 포함되어 있는지 검사합니다.
   - 만약에 Set에 있다면 해당 문자를 ret에 붙입니다.

스킬트리로부터 뽑은 스킬의 길이가 0인 경우 해당 스킬트리를 배울 수 있습니다.
   - 조건에서 보면 `위 순서에 없는 다른 스킬(힐링 등)은 순서에 상관없이 배울 수 있습니다. `라는 조건이 있습니다.
   - 다른 스킬은 순서에 상관없이 배울 수 있다 = 주어진 스킬을 사용하지 않았을 경우 어떤 순서라도 상관없다.
   - 그러므로 ret의 길이가 0인 경우 -> 스킬에 해당하는 문자가 없는 경우 answer를 1증가시키고 다음 스킬트리를 보면 됩니다.

만약 스킬트리로부터 뽑은 스킬의 길이가 1이상인 경우 해당 스킬트리를 배울 수 있는지 체크합니다.
   - 주어진 스킬에서 스킬트리로부터 뽑은 스킬의 길이만큼 잘라서 비교합니다.
   - 내용이 같다면 배울 수 있으므로 answer를 증가시킵니다.

## 후기
뚜아.. 난이도 조절 실패ㅠ

다른 사람 풀이보면 정규 표현식으로 푼 사람있는데

처음에 이해가 안 됐는데 검색하고 찾아보니 대박이구만유