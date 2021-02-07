## [2839] 설탕 배달 - Java

### :two: 분류

> DP



### :three: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    static int solve() {
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 10000);
        dp[3] = 1;
        if(N >= 5) dp[5] = 1;

        for(int i = 6; i <= N; i++) {
            dp[i] = Math.min(dp[i - 5], dp[i - 3]) + 1;
        }

        System.out.println(dp[N] < 10000 ? dp[N] : -1);
        return 0;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
    }

}
```



### :five: 풀이 방법

간단한 DP 문제입니다..

하지만 간단히는 못 풀었습니다 DP 할줄 모르니깐

 먼저 dp[N] 은 N킬로그램의 설탕을 배달할때 필요한 봉지의 최솟값입니다. 그리고 얘들을 일단 적당히 큰 수로 초기화해줍시다. N 의 최댓값이 5000이니깐 1700 조금 안되게 하면 될텐데 후한 인심으로 10000으로 초기화했습니다.

 

문제에서 3킬로그램, 5킬로그램짜리 봉지 두 개가 있기 때문에 dp[3] 과 dp[5]를 1로 초기화해줍시다. 이 때 dp의 크기를 쓸데없이 N+1로 해놨기 때문에 인풋으로 4가 들어왔을 때는 따로 체크해야 합니다. 귀찮으면 dp의 크기를 5001로 초기화하면 됩니다.

 

점화식은 이렇습니다. dp[N] = min(dp[N-3], dp[N-5]) + 1 입니다. 나눠떨어지고말고는 dp[N] 의 숫자로 체크합니다. 첨에 넉넉히 큰 수로 초기화해놨기 때문에 고것보다 작은 수일때 나눠떨어진단 의미가 됩니다.



### :seven: 후기

감사합니다!!

