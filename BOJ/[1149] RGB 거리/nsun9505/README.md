# [1149] RGB 거리 - JAVA

## 분류
> DP

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N+1][3];
        int[] arr = new int[3];

        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            arr[2] = Integer.parseInt(st.nextToken());

            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[2];
        }

        System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
    }
}
```

## 문제 풀이
프로그래머스 땅따먹기?와 비슷한 문제같다.

빨간색을 칠했으면 다음번에 빨간색을 칠하지 않아야 한다!
   - 그러니까 N번 집에 빨간색 칠했으면 N+1집에는 빨간색을 칠하지 못한다.
   - 그런데 N+1에 빨간색을 칠하는 경우도 생각을 해야 한다! 여러 경로가 있기 때문이다.
   - 그래서 N+1의 빨간색을 칠할 때는 N에서 파랑색이나 초록색을 칠한 경우로 봐야 한다. N에서 파랑색과 초록색 중 작은 값을 N+1집에 빨강색을 칠하는 경우랑 더하면 된다!

그러면 식이 아래와 같다!
   ```java
    dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i];
    dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i];
    dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i];
   ```


## 후기
처음에는 문제가 이해가 안 됐다.

여러 사람이 생각한걸 찾아보니 N에 X색을 칠했을 때 N+1일 때 X 색을 못찰하는 조건이였다..

DP는 차근차근 그림을 그려보고 줄여나가는 맛인가?

식을 찾는 맛인가?

무슨 맛이지..