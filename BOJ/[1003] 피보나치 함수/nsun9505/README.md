# [1003] 피보나치 함수

## 분류
> DP

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] cntOfZero = new int[41];
        int[] cntOfOne = new int[41];
        cntOfZero[0] = 1;
        cntOfOne[1] = 1;
        for(int i=2; i<=40; i++){
            cntOfZero[i] = cntOfZero[i-1] + cntOfZero[i-2];
            cntOfOne[i] = cntOfOne[i-1] + cntOfOne[i-2];
        }

        for(int i=0; i<T; i++){
            int N = Integer.parseInt(br.readLine());
            System.out.println(cntOfZero[N] + " " + cntOfOne[N]);
        }
    }
}
```

## 문제 풀이
이번에는 DP로 생각해서 작은 것부터 해결해서 큰 것을 해결하기 위해 고민했습니다.

0은 0이 나오는 횟수는 1, 1이 나오는 횟수는 0입니다.

1은 0이 나오는 횟수는 0, 1이 나오는 횟수는 1입니다.

이렇게 0과 1을 기본으로 저장합니다.

피보나치 수열은 f(n-1) + f(n-2)이므로 n을 알아보기 위해서는 n-1, n-2에 대한 값만 있으면 되므로 답으로 출력하기 위한 2부터는 0과 1에 대해서 1을 호출한 횟수, 0을 호출한 횟수를 미리 세팅한다.
   - 예를 들어, 2는 2-1 = 1, 2-2 = 0 이므로 1과 0이 되므로 1과 0을 1번씩 호출하게 됩니다.
   - 3은 3-1=2, 3-2=1 이므로 2가 0과 1을 호출하는 횟수 + 1이 0과 1을 호출하는 횟수를 더하면 됩니다!
   - 고로 N이 0과 1을 호출하는 횟수를 알기 위해서는 N-1과 N-2가 0과 1을 호출한 횟수를 각각 더해서 출력하면 됩니다.

## 후기
쉬운 문제부터 감을 잡는게 중요하군요!