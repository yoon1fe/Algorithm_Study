# [2020 카카오 인턴십] 보석 쇼핑 - JAVA

## 분류
> 투 포인터

## 코드
```java
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
```

## 문제 풀이
투 포인터를 사용해서 풀었습니다!

투 포인터를 사용한 이유는 문제에서 `진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매`라는 문장이 있습니다.

가장 짧은 구간을 연속된 구간으로 이해를 했습니다.
   - 그래서 투 포인터를 사용할 수 있겠구나라고 생각했습니다.

그리고 적어도 1개 이상 포함하는 것에 대한 의미는 조건을 충족한 상태! 

즉, start ~ end 구간에는 보석을 적어도 1개 이상을 포함하고 있으므로 start를 증가시켜도 보석을 적어도 1개 이상 포함하고 있는지 검사하면 됩니다.

투포인터에서 end가 증가하는 경우는 아직 각 보석을 적어도 1개 이상씩 가지고 있지 않다면 end를 증가시켜서 진열된 보석을 포함시켜 봅니다.

그러다가 모든 보석을 1개 이상씩 가지고 있다면!

start를 감소시켜서 그 범위가 아직도 모든 보석을 1개 이상씩 가지고 있는지 체크하면 됩니다.

즉, start ~ end 범위 안에서 모든 보석이 1개 이상 나온다면, start를 증가시켜서 줄어든 범위에서도(가장 짧은 범위를 알아내기 위해서) 보석이 1개 이상 나오는지! 체크하면 됩니다.

모든 보석이 1개 이상 포함되지 않는다면 end를 증가시켜서 start ~ end 범위에 보석을 하나 추가시키는 것입니다.

그리고 답을 구하기 위해서 모든 보석이 포함된 구간을 만날 떄마다 구간의 길이를 구해서 가장 짧은 길이의 start와 end를 답으로 하면 됩니다.

## 후기
오랜만에 투포인터 문제!