# [9576] 책 나눠주기 - JAVA

## 분류
> 그리디

## 코드
```java
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] memo;
    static Range[] ranges;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N + 1];
            memo = new int[M];
            ranges = new Range[M];
            Arrays.fill(arr, 1);

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                ranges[i] = new Range(from, to);
            }

            Arrays.sort(ranges);
            int ans = 0;
            for(int i=0; i<M; i++){
                for(int bIdx = 1; bIdx <= N; bIdx++){
                    if(arr[bIdx] == 1 && ranges[i].from <= bIdx && bIdx <= ranges[i].to){
                        ans++;
                        arr[bIdx] = 0;
                        break;
                    }
                }
            }
            sb.append(ans + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Range implements Comparable<Range> {
        int from;
        int to;

        public Range(int f, int t){
            this.from = f;
            this.to = t;
        }

        @Override
        public int compareTo(Range o1){
            if(o1.to == this.to)
                return Integer.compare(this.from, o1.from);
            return Integer.compare(this.to, o1.to);
        }
    }
}
```

## 문제 풀이
처음에는 범위로 내림차순 정렬을 했는데 바로 틀립니다!
반례로는 다음과 같습니다.
    ```
        1
        7 2
        1 7
        1 1
    ```
       - 1 1을 먼저 보고 1~7을 봐야하는데 범위기준으로 내림차순으로 정렬을 하면 1권밖에 못나눠주게 됩니다.

그래서 직접 그림을 그려보면 어떤식으로 정렬을 하면 될지가 보입니다!

범위를 그려보면 어떻게 정렬하면 최대한 많이 나눠줄 수 있는지가 보입니다!

예를 들어
   ```
    1 2 3 4 5 6 7 8 9
    1     4
      2   4
        3 4
      2       6
          4   6
   ```
      - 위와 같이 정렬하려면 먼저 (from, to)에서 to를 오름차순으로 정렬하고, from에 대해서 오름차순 정렬하면 저 모습이 나옵니다.
      - to로 오름차순 정렬을 하면 i번째는 i+1번째를 포함 또는 동일하거나 i+1번째와 겹치게 될 것입니다.
      - i번째의 to가 i+1번째 to보다 작다면, i+1번쨰의 to는 무조건 나눠줄 수 있습니다!
      - 그러면 이제 같은 경우에 문제가 됩니다.
      - 같은 경우에는 from을 기준으로 오름차순 정렬을 합니다.
      - 그러면 i번째는 i+1번째를 포함할 것입니다. 즉, i번째의 from은 i+1번쨰보다 작기 때문에 i번째 from은 무조건 나눠줄 수 있죠!
      - i번째 from번 책을 나눠줬다면 i+1번째의 from도 당연히 나눠줄 수 있을 것입니다.
         - 왜냐하면 오름차순이기 때문에 i번째의 from을 나눠주게 되면, i+1번째의 from은 i번째의 from보다 크니깐 중복되지 않고 나눠줄 수 있죠!
         - 물론 완전 똑같은 범위가 M개인데 책이 M보다 작다면 딱 N만큼만 나눠줄 것입니다.

그래서! to에 대해서 오름차순 정렬을 하고, to가 같다면 from을 기준으로 오름차순하면 나눠주는 책의 수를 최대로 할 수 있습니다!

## 후기
이분 매칭을 배워야 했지만, 정렬로도 충분히 풀 수 있었던 문제 같습니다.