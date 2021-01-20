# [2169] 로봇 조종하기 - JAVA

## 분류
> DP

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][] isVisited;
    static int[] dx = {0, 1, 0};
    static int[] dy = {1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        int[][] dp = new int[N+1][M+2];
        arr = new int[N+1][M+2];
        for(int i=1; i<=N; i++)
            dp[i][0] = dp[i][M+1] = Integer.MIN_VALUE;

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        dp[1][1] = arr[1][1];
        for(int i=2;i<=M; i++)
            dp[1][i] += arr[1][i] + dp[1][i-1];

        for(int i=2; i<=N; i++){
            int[] LU = new int[M+2];
            LU[M+1] = Integer.MIN_VALUE;
            int[] RU = new int[M+2];
            RU[0] = Integer.MIN_VALUE;

            // Left, Up에서 오는 경우
            for(int j=1; j<=M; j++)
                LU[j] = Math.max(dp[i-1][j], LU[j-1]) + arr[i][j];

            // Right, Up에서 오는 경우
            for(int j=M; j>=1; j--)
                RU[j] = Math.max(dp[i-1][j], RU[j+1]) + arr[i][j];

            // LU, RU 비교 : 왼쪽, 오른쪽, 위에서 오는 경우 모두 비교
            for(int j=1; j<=M; j++)
                dp[i][j] = Math.max(RU[j], LU[j]);
        }

        System.out.println(dp[N][M]);
    }
}
```

## 문제 풀이
[i][j]에 오는데 가장 높은 값을 찾는 문제인거 같습니다!

[i][j]에 오기 위해서는 MAX([i-1][j], [i][j-1], [i][j+1]) 중에 가장 큰 값을 가진 것을 선택합니다.
   -  그러므로 [i][j]를 계산할 때는 
      - [i-1][j]도 해당 위치로 오는데 최댓값을 가져야 합니다.
      - [i][j-1]도 해당 위치로 오는데 최댓값을 가져야 합니다.
      - [i][j+1]도 해당 위치로 오는데 최댓값을 가져야 합니다.
   - 즉, [i][j]에 최댓값을 계산하기 위해서는 위에서 오는 경우([i-1][j]), 오른쪽에서 오는 경우([i][j-1]), 왼쪽에서 오는 경우([i][j+1])가 각자의 최댓값을 가지고 있어야 하는 것입니다.

그런데 첫 번째 행은 (1,1)에서 출발하므로 (1, j)들은 (1, 1)에서 오는 경우 밖에 없습니다.
   - 그래서 dp[1][i]를 아래와 같이 세팅합니다.
        ```java
            dp[1][1] = arr[1][1];
            for(int i=2; i<=N; i++)
                dp[1][i] = arr[1][i] + dp[1][i-1];
        ```
   - 이제 2번째 행부터는 일반화해서 풀 수 있습니다.

2번째 행부터는 위에서 오는 경우, 오른쪽에서 오는 경우, 왼쪽에서 오는 경우를 모두 알 수 있습니다.
   - 위에서 오는 경우는 이미 각자 최댓값을 가지고 있기 때문에 비교를 하지 않아도 됩니다.
   - 그러면 [i][j]에서 오른쪽에서 온 경우와 왼쪽에서 온 경우를 구해야 합니다.
   - 오른쪽과 왼쪽을 모두 한 번에 계싼할 수 있을까요?
   - 쉽게 풀기 위해 **오른쪽에서 온 경우와 왼쪽에서 온 경우를 따로 계산하면 됩니다!**

### 왼쪽에서 오는 경우 계산하기
일단 위에서 오는 경우에 대한 값은 알고 있으니깐 왼쪽에서 오는 최댓값이랑 위에서 오는 최댓값을 비교하면

(i,j)의 왼쪽에서 오는 경우의 최댓값을 알 수 있을 것입니다!

즉, ``` LU[col] = max(dp[row-1][col], LU[col-1])  + arr[row][col] ``` 라는 식이 만들어집니다!
   - LU[col]는 왼쪽 또는 위쪽에서 오는 애들을 중에서 가장 큰 애랑 arr[row][col]을 더해서 저장합니다.
   - LU[col-1]이 왼쪽에서 오는 최댓값을 의미하고, dp[row-1][col]이 위에서 오는 최댓값이므로 이 둘을 비교해서 가장 큰 값과 arr[i][j]를 더하면
   - 왼쪽과 위쪽에서 (i, j)로 오는 경우 중 가장 큰 값을 알 수 있습니다!
   - col은 1씩 증가할 것이고, M까지 가면 LU에는 위에서 오는 경우와 왼쪽에서 오는 경우의 최댓값들로 세팅이 됩니다.

### 오른쪽에서 오는 경우 계산하기
왼쪽에서 오는 경우를 계산한 방법과 동일하게 위에서 오는 경우의 최댓값과 오른쪽에서 오는 최댓값을 비교하면 (i, j)의 오른쪽에서 오는 경우의 최댓값을 알 수 있습니다.!

``` RU[col] = max(dp[row-1][col], RU[row-1][col+1]) ``` 라는 식으로 풀 수 있습니다!
   - RU[col]은 오른쪽 또는 위쪽에서 오는 애들 중에서 갖아 큰애랑 arr[row][col]을 더해서 저장!
   - RU[col+1]은 오른쪽에서 오는 최댓값, dp[row-1][col]은 위에서 오는 최댓값이므로 이 둘을 비교해서 가장 큰 값과 arr[row][col]을 더하면
   - 오른쪽과 위쪽에서 (i, j)로 오는 경우 중 가장 큰 값을 알 수 있습니다!
   - col은 1씩 감소할 것이고, 1까지 가면서 RU에는 위에서 오는 경우와 오른쪽에서 오는 경의의 최댓값들로 세팅이 됩니다.!

그럼 이제 오른쪽+왼쪽에서 오는 경우, 왼쪽+위쪽에서 오는 경우를 모두 구했으니!
   - 두 값을 비교해서 가장 큰 값을 (i, j)를 세팅하면 끝입니다!

### 주의사항!
저는 0번째 열, M+1번째 열을 사용해서 index를 벗어나는 경우를 생각 안 하도록 했습니다.

그래서 1번째 열에 왼쪽에서 오는 경우에는 arr[1~N][0]을 모두 0으로 초기화했습니다.

그리고 M번째 열에 오른쪽에서 오는 경우에는 arr[1~N][M+1]을 모두 0으로 초기화했습니다!

하지만! 이렇게 하면 안됩니다..

아래 테케를 봅시다.

```
5
-100 -100 -100 -100 -100
-100 -100 -100 -100 -100
-100 -100 -100 -100 -100
-100 -100 -100 -100 -100
-100 -100 -100 -100 -100
```

답은 얼마일까요? 

900입니다!

저는 -100이 나왔는데요. 왜냐하면, 위에서 말했다시피 arr[i][0], arr[i][M+1]을 모두 0으로 초기화했기 때문에

0은 -100보다 크기 때문에 -100이 쌓여서 -900이 되는 경로를 계산할 수 없는 것입니다ㅠㅠ

그래서 양 사이드를 int형의 최솟값으로 세팅하고 다시 돌리니 맞았습니다!

음수가 있을 때는 최솟값도 생각해서 해야되는걸 오늘 또 다시 배웁니다!

## 후기
방금 들어온 방향으로 가지 못하게 하면 BFS도 돌릴 수 있지 않을까..?

아닌가..?