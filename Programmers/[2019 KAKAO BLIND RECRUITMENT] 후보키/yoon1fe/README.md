## [2019 KAKAO BLIND RECRUITMENT] 후보키 - Java

###    :bookmark_tabs: 분류

> 조합 만들기



###  :bookmark_tabs: 코드

```java
import java.util.*;

class Solution {
    static List<Integer> combination = new ArrayList<>();
    static List<List<Integer>> candidateCols = new ArrayList<>();
    static List<Integer> cols = new ArrayList<>();

    public static int solution(String[][] relation) {
        for(int i = 0; i < relation[0].length; i++) {
            cols.add(i);
        }

        for(int i = 1; i <= cols.size(); i++) {
            makeComb(0, 0, i, relation);
        }

        return candidateCols.size();
    }

    public static void makeComb(int cnt, int idx, int depth, String[][] relation) {
        if(cnt == depth) {
            // 조합 생성 완료

            // 최소성 체크
            for(int i = 0; i < candidateCols.size(); i++) {
                int dupCnt = 0;
                List<Integer> temp = candidateCols.get(i);

                for(int j = 0; j < temp.size(); j++) {
                    if(combination.contains(temp.get(j))) dupCnt++;
                }
                if(dupCnt == temp.size()) return;
            }

            // 유일성 체크
            if(checkCandidateKey(relation)) {
//                System.out.println(combination.toString());
                List<Integer> temp = new ArrayList(combination);
                candidateCols.add(temp);
            }
        }

        for(int i = idx; i < cols.size(); i++) {
            combination.add(cols.get(i));
            makeComb(cnt+1, i+1, depth, relation);
            combination.remove((Integer)cols.get(i));
        }
    }

    public static boolean checkCandidateKey(String[][] relation) {
        Set<String> hashSet = new HashSet<>();

        for(int i = 0; i < relation.length; i++) {
            StringBuilder str = new StringBuilder();
            for(int j = 0; j < combination.size(); j++) {
                str.append(relation[i][combination.get(j)]);
            }
            hashSet.add(str.toString());
        }

        return hashSet.size() == relation.length ? true : false;
    }
}
```



### :bookmark_tabs: 풀이 방법

릴레이션(테이블)이 하나 인풋으로 주어지고, 칼럼들 중에서 후보키가 되는 칼럼들의 개수를 구하는 문제입니다.

아이디어는 꽤 빨리 생각났는데 최소성 체크하는데 너무 어렵게 생각해서 삥삥 돌아갔다 와서 풀었습니당



먼저 1개부터 칼럼의 수 개만큼의 조합을 만들어줍니다.

조합을 다 만들면 먼저 **최소성**을 체크해줍니다. 

조합으로 만든 칼럼 인덱스들이 후보키를 저장해놓은 candidateCols에 다 포함된 인덱스들이 있는지를 체크해주면 됩니다.

처음에 짤 때는 유일성을 만족하는 칼럼들을 구해놓고 여기서 어떻게 중복되는 애들을 제거하지 고민하다가 방향을 틀었습니다.

무식하게 N^2 돌리면 시간초과뜰까봐 그랬는데..힝

아무튼 최소성을 만족하는 경우 **유일성**을 체크합니다.

요것은 뽑힌 칼럼들의 레코드를 더해서 Set에 때려박아 개수로 봐주면 됩니다.

  





###  :bookmark_tabs: 후기

로직이 완벽한데 자꾸 두세개가 통과가 안돼서 한참 헤맸는데

만드는 조합의 크기를 칼럼의 개수까지 해야되는데 칼럼 개수 - 1만큼 반복문을 돌려서 그랬던 것이어씀다.......

 

몇개만 안되면 나 자신을 믿고 첨부터 차근차근 살펴보는 습관을 들입쉬다.