# [1463] 1로 만들기 - JAVA

## 분류
> DP

## 코드
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        for(int i=0; i<N; i++)
            arr[i] = Integer.MAX_VALUE;
        arr[N] = 0;

        for(int i=N; i>=1; i--){
            if(i % 3 == 0)
                arr[i/3] = Math.min(arr[i/3], arr[i]+1);
            if(i % 2 == 0)
                arr[i/2] = Math.min(arr[i/2], arr[i]+1);
            arr[i-1] = Math.min(arr[i-1], arr[i]+1);
        }

        System.out.println(arr[1]);
    }
}
```

## 문제 풀이
주어지는 입력이 작아서 완탐을 돌리면 터질까나 싶어서 분류를 보고 DP를 사용하면 되겠구나 생각해서 직접 수를 정해서 손으로 써보고 로직을 짰습니다.

현재 i를 3이나 2로 나누어 떨어질 때
   - arr[i/3](또는 arr[i/2])의 값을 현재까지(i) 계산된 연산횟수인 arr[i]+1과 arr[i/3](또는 arr[i/2])비교하여 더 작은 값으로 갱신합니다.
   - 그러면 arr[1]의 값은 1을 만들기 위해 연산한 횟수가 들어가게 됩니다!

## 후기
이렇게 푸는게 맞을라남..?