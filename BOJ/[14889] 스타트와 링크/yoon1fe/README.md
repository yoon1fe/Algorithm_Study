# [14889] 스타트와 링크 - Java

###  :soccer: 분류

> 조합
>
> 완전 탐색



### :soccer: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static boolean[] selected;
    static int[][] S;
    static int N;
    static int answer = Integer.MAX_VALUE;

    public static int getAbility() {
        List<Integer> start = new ArrayList<>();
        List<Integer> link = new ArrayList<>();
        int startSum = 0, linkSum = 0;
        for(int i = 0; i < N; i++) {
            if(selected[i]) start.add(i);
            else link.add(i);
        }
        for(int i = 0; i < start.size(); i++) {
            for(int j = i+1; j < start.size(); j++) {
                startSum += S[start.get(i)][start.get(j)] + S[start.get(j)][start.get(i)];
                linkSum += S[link.get(i)][link.get(j)] + S[link.get(j)][link.get(i)];
            }
        }
        return Math.abs(startSum-linkSum);
    }

    public static void comb(int cnt, int idx) {
        if(cnt == N / 2) {
            answer = Math.min(answer, getAbility());
            return;
        }

        for(int i = idx; i < N; i++) {
            selected[i] = true;
            comb(cnt+1, i+1);
            selected[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        selected = new boolean[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);
        System.out.println(answer);
    }
}
```

### 

### :soccer: 풀이 방법

삼성 SW 역량 테스트 기출문제입니다. 아마 개중에 가장 쉬운 문제가 아닐까 싶습니다.

조합을 만들어서 처리하면 됩니다.



N명의 사람을 두개의 팀으로 나야 하기 때문에 nCn/2 를 만들면 됩니다.

 조합을 다 만들면 만들어진 사람들로 능력치를 계산해야 합니다. 두명씩 짝지어서 계산하면 되므로 심플하게 2중 for문을 썼습니다.

getAbility()에서 각 팀의 능력치의 차를 리턴받아와서 answer와 비교해서 작으면 answer를 그 값으로 갱신해주면 끝입니다.



### :soccer: 후기



다시 화이팅!!