# [2019 KAKAO BLIND RECRUITMENT] 후보키 - JAVA

## 분류
> 해시
>
> 부분집합

## 코드
```java
import java.util.ArrayList;
import java.util.HashSet;

public class Solution {
    static HashSet<String> keyCountSet = new HashSet<>();
    public int solution(String[][] relation) {
        int answer = 0;

        int N = relation[0].length;
        ArrayList<Key> keys = new ArrayList<>();
        ArrayList<Integer> tmp = new ArrayList<>();
        // 부분 집합 구하기 -> 모든 컬럼 조합 구하기
        for(int i=1; i<(1<<N); i++){
            tmp.clear();
            for(int j=0; j<N; j++){
                if((i & (1 << j)) > 0)
                    tmp.add(j);
            }

            Integer[] tmpArr = tmp.toArray(new Integer[tmp.size()]);
            keys.add(new Key(i, tmpArr));
        }

        // 키를 만들 수 있는지 보기
        for(int i=0; i<keys.size(); i++){
            Key key = keys.get(i);
            if(!key.isUnique)
                continue;

            // i번째 컬럼 조합이 키가 될 수 있는지 체크
            boolean ret = check(key, relation);
            // 키가 될 수 없으면 해당 조합은 key가 될 수 없음을 표시
            if(!ret)
                key.isUnique = false;

            // i번째 컬럼 조합이 키가 될 수 있으면, i번째 컬럼을 포함하는 조합들을 후보키에서 탈락시킨다.
            // 왜냐하면 i번째 컬럼이 키가 될 수 있는데 다른 컬럼이 붙어도 식별이 가능하지만, 최소성을 만족시키지 못해 탈락
            else{
                for(int j=i+1; j<keys.size(); j++){
                    if((keys.get(j).num & keys.get(i).num) == keys.get(i).num)
                        keys.get(j).isUnique = false;
                }
            }
        }

        // 후보키 카운트
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
            // 컬럼의 값을 연결해서 하나의 키로 만든다.
            for(int idx : key.arr)
                candidateKey += " " + relation[i][idx];

            // 이미 존재하는 컬럼 조합의 값이 있다면 후보키 탈락
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
```

## 문제 풀이
부분집합을 사용해서 문제를 풀었습니다.

후보키를 알아내기 위해서는 컬럼의 부분집합을 통해 컬럼 조합을 알아낼 수 있습니다.
   - 부분집합을 알아내기 위해서 비트마스킹을 사용했습니다.
   - 부분집합은 작은 수(0001, 0010, 0011, ...)에서 점차 증가하는 방식으로 저장되므로
   - 작은 수에서 유일성을 만족한다면, 이후에 유일성을 만족한 수를 포함하는 수는 최소성을 만족하지 못하게 됩니다.
   - 예를 들어 0011(컬럼 0번과 컬럼 1번 조합)이 유일성을 만족한다고 할 때 1011, 1111은 0011이 포함되므로 최소성을 만족하지 못합니다.

알아낸 각 조합이 키의 조건 중 하나인 유일성을 만족하는지 테스트합니다.

유일성을 만족한다면, 해당 조합을 포함하는 다른 컬럼 조합들은 최소성을 만족하지 못하므로 

유일성을 만족한 컬럼 조합을 포함하는 다른 컬럼 조합은 유일성을 만족하겠지만, 최소성을 만족하지 못하므로 후보키에서 탈락시키면 됩니다.
   - 포함하는지를 확인하기 위해 부분집합을 구할 때 알아낸 비트로 & 연산을 해서 포함하는지 아닌지를 검사하면 됩니다.

## 후기
할만해유!