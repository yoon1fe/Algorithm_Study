# [13458] 시험감독 - JAVA

## 분류
> 수학

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        long[] isUsed = new long[1000001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long ans = 0;

        for(int i=0; i<N; i++){
            if(isUsed[arr[i]] > 0) {
                ans += isUsed[arr[i]];
                continue;
            }

            int num = arr[i];
            int cnt = 0;

            num -= B;
            cnt++;

            if(num <= 0){
                isUsed[arr[i]] = cnt;
                ans += cnt;
                continue;
            }

            cnt += (num / C);
            num %= C;
            if(num > 0)
                cnt++;

            ans += cnt;
            isUsed[arr[i]] = cnt;
        }
        System.out.println(ans);
    }
}
```

## 문제 풀이
각 시험장에 총감독관은 하나씩 들어가는 걸로 먼저 계산합니다.

충분히 커버 가능하면 부감독관의 수는 카운트하지 않습니다.

만약 총감독관으로도 커버하지 못한다면 부감독관의 수를 구합니다.

주의할 점은 ans를 long형으로 선언해야 합니다.

예를 들어, 시험관의 수가 1,000,000개이고, 총감독관과 부감독관이 맡을 수 있는 사람 수가 각각 1일 때

주어진 시험생의 수가 각 시험관마다 1,000,000이라면 10^12이므로 int의 범위를 넘어서기 떄문입니다.

## 후기
쉬워보인다고 너무.. 대충 풀지 말자..ㅠ

지금부터 문제 잘 읽고 엣지 케이스를 만들어보는 연습을 하자!