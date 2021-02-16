# [2021 KAKAO BLIND RECURUITMENT] 매출 하락 최소화 - JAVA

## 분류
> DP
>
> 트리 DP

## 코드
```java
import java.util.ArrayList;

public class Solution {
    static ArrayList<Element>[] graph;
    static boolean[] isVisited;
    static int[][] dp;
    static int N;
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        N = sales.length;
        graph = new ArrayList[N+1];
        dp = new int[N+1][2];
        isVisited = new boolean[N+1];
        Element[] arr = new Element[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            dp[i][1] = sales[i-1];
            arr[i] = new Element(i, sales[i-1]);
        }

        for(int i=0; i<links.length; i++)
            graph[links[i][0]].add(arr[links[i][1]]);

        DFS(1);

        answer = Math.min(dp[1][0], dp[1][1]);

        return answer;
    }

    public static void DFS(int cur){
        isVisited[cur] = true;

        if(graph[cur].size() == 0)
            return;

        int sum = 0;
        int cnt = 0;
        for(Element next : graph[cur]){
            if(isVisited[next.vertex])
                continue;

            DFS(next.vertex);
            if(dp[next.vertex][0] > dp[next.vertex][1]){
                sum += dp[next.vertex][1];
                cnt++;
            } else {
                sum += dp[next.vertex][0];
            }
        }

        dp[cur][1] += sum;
        if(cnt > 0)
            dp[cur][0] = sum;
        else {
            int min = Integer.MAX_VALUE;
            for (Element next : graph[cur])
              min = Math.min(min, dp[next.vertex][1] - dp[next.vertex][0]);
            dp[cur][0] = sum + min;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(	new int[]{5, 6, 5, 3, 4}, new int[][]{{2, 3}, {1, 4}, {2, 5}, {1, 2}}));
    }

    static class Element{
        int vertex;
        int val;

        public Element(int vertex, int val) {
            this.vertex = vertex;
            this.val = val;
        }
    }
}
```

## 문제 풀이
와우 어려워유 테이블 정의까지는 했는데 식을 도출하지 못해서 카카오 해설을 봤습니다.

값을 구하기 위해서 DFS로 내려오면서 마지막에 1번 노드에서 계산을 마치면 됩니다.

테이블 정의는 다음과 같습니다.
   - dp[i][0] : i번째 노드가 루트인 서브트리에서, i번 노드가 워크숍에 불참석
   - dp[i][1] : i번째 노드가 루트인 서브트리에서, i번 노드가 워크숍에 참석

dp[i][1]은 i번째 노드가 워크숍에 참석한다는 의미이므로
   - i의 자식들은 워크숍에 참석을 하든 말든, 
   - 즉, 자식들에게 또 다른 자식이 있든 말든, i는 워크숍에 참석하므로 자식 노드가 또 다른 자식 노드를 갖지 않는다면 0이 주어질 것이고 해당 자식 노드가 또 다른 자식 노드를 갖는다면 해당 팀에서 최솟값이 dp[k][0] 또는 dp[k][1]에 세팅될 것입니다.
   - 그러므로 각각의 자식 노드의 워크숍 참석 또는 불참석에 대한 최솟값을 모두 더한 값과 i번째가 워크숍에 참석하는 비용인 sales[i](=dp[i][1])을 더해서 dp[i][1]에 저장하면 됩니다.

i번째 노드의 모든 자식 노드에 대해서 합을 구합니다.
   - sum += Math.min(dp[k][0], dp[k][1])
   - k는 자식 노드를 의미합니다.

dp[i][0]은 i번째가 속하는 팀(i를 루트로 하는 서브트리)에서 i가 참석하지 않는 경우입니다.
   - i번째 자식들 중에서 최소가 되는 값을 dp[i][0]에 세팅해야 합니다.
   - 그래서 sum 값을 구할 때 dp[k][0] > dp[k][1]을 만족하는 k가 한 개라도 있다면 dp[i][0]에는 sum 값을 넣어줍니다.
       - 즉, i의 자식 노드들 중에서 자식을 가지는 노드들이 존재한다는 의미이고, k번째 노드는 i번째가 속한 팀에서 워크숍에 참석한다는 의미입니다.
       - 그러므로 dp[i][0]의 값은 sum이 됩니다.
       - 이 경우는 자식 노드들이 리프 노드 또는 서브 트리의 루트인 경우라고 생각됩니다.
   - 또는, dp[k][0] > dp[k][1]을 만족하는 k가 하나도 없는 경우는 i의 자식 노드들 중에서 워크숍에 참석하는 도느가 없다는 의미입니다.
      - 그래서 하나의 노드를 골라서 팀에서 하나라도 참여하도록 해야 합니다.
      - 참석 시킬 노드를 찾기 위해서 매출 손해가 가장 적게 발생하는 k를 찾기 위해 minGap = dp[k][1] - dp[k][0]이 최소인 k를 참석시키도록 합니다.
      - dp[i][0]에는 sum + minGap 값을 넣어주면 됩니다.
      - 이 경우는 자식 노드들이 모두 리프 노드인 경우라고 생각됩니다.

DFS로 탐색을 하기 때문에 답으로는 dp[1][0]과 dp[1][1] 중에서 작은 값을 출력하면 됩니다.

## 후기
트리 DP 참 어렵습니다..

