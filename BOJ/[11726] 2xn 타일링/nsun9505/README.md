# [11726] 2xn 타일링 - JAVA

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
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+2];
        arr[1] = 1;
        arr[2] = 2;
        for(int i=3; i<=N; i++)
            arr[i] = (arr[i-1] + arr[i-2]) % 10007 ;
        System.out.println(arr[N]);
    }
}
```

## 문제 풀이
주어지는 N은 최소 1이므로 arr[1]과 arr[2]를 초기화하기 위해 N+2 사이즈로 초기화를 합니다.

2xN 타일을 만든 방법은 arr[N-1] + arr[N-2]입니다.
   - 직접 손으로 5까지 만들어보면 식이 보입니다!
   - N은 N-1에서 2x1 타일을 붙이면 되는 것과 N-2에서 2xn을 붙인 것을 더한 것입니다.
   - 그래서 `f(n) = f(n-1) + f(n-2)`라는 식이 나와서 그대로 적용하면 문제를 해결할 수 있습니다.

## 후기
새해 복 많이 받아요~

1월 1일이니깐 1문제만..ㅋ