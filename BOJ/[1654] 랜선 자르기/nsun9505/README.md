# [1654] 랜선 자르기 - JAVA

## 분류
> 이분탐색

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long left = 1;
        long right = 0;
        int[] lines = new int[K];
        for (int i = 0; i < K; i++){
            lines[i] = Integer.parseInt(br.readLine());
            right = Math.max(lines[i], right);
        }

        long ans = 0;
        while(left <= right){
            long mid = (left + right) / 2;

            int cnt = 0;
            for(int i=0; i<K; i++)
                cnt += lines[i] / mid;
            if(cnt < N)
                right = mid - 1;
            else{
                left = mid + 1;
                ans = Math.max(ans, mid);
            }
        }
        System.out.println(ans);
    }
}
```

## 문제 풀이
주어진 랜선 중에 가장 길이 긴 랜선 길이를 right로 잡아줍니다.

그리고 가장 작은 랜선의 길이는 1이기 때문에 left를 1로 잡아줍니다.
   - 0으로 잡으면 0으로 나누게 되는 경우가 발생하므로 조심해야 합니다.

mid = (left + right) / 2를 구합니다.

mid만큼 랜선들을 잘랐을 때 얼마나 나오는지 체크합니다.

만약 N보다 적다면 right를 mid - 1로 줄여서 자르는 랜선의 크기를 줄입니다.

N보다 같거나 크다면 left를 mid + 1로 줄여서 자르는 랜선의 크기를 늘립니다.
   - 현재 답과 mid를 비교해서 큰 것을 답으로 갱신합니다.

자를 랜선의 길이를 구해서 랜선들을 잘랐을 때 N을 넘느냐 넘지 않느냐에 따라 left와 right를 조정하면 됩니다.

## 후기
두 번째 풀어보는건데 또 틀이네ㅠ

코드를 조금씩 고쳐보니깐 다시 맞았습니다ㅠ

올해도 파이팅!