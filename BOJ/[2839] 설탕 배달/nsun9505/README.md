# [2839] 설탕 배달 - JAVA

## 분류
> 그리디 알고리즘

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
        int ret = solution(N);
        if(ret > 5001)
            System.out.println(-1);
        else
            System.out.println(ret);
    }

    public static int solution(int N){
        if(N == 0)
            return 0;
        else if(N < 0)
            return 5001;

        if(arr[N] != 0)
            return arr[N];

        return arr[N] = Math.min(solution(N - 5), solution(N - 3)) + 1;
    }
}
```

## 문제 풀이
그리디 알고리즘을 사용해서 풀 수 있었습니다!
   - 그리고 arr[N]에 값을 저장해서 이미 구한 경우에는 더 이상 내려가지 않도록 합니다.

주어진 N에서 5와 3을 뺍니다.
   - 그래서 N이 0이면 더 이상 진행하지 않고 0을 리턴!
   - 만약 N에서 5 또는 3을 뺀 값이 0보다 작다면 5와 3으로는 표현할 수 없으므로 주어질 수 입력 값 중에서 가장 큰값 + 1을 리턴합니다.
   - 그리고 N에서 5를 뺀 결과(5만큼 담은 경우)와 3을 뺀 결과(3만큼 담은 경우)를 비교해서 둘 중 작은 값에 +1해서 arr[N]에 값을 담습니다.
   - 5를 빼거나 3을 뺀 것은 5 또는 3만큼 담은 것이므로 +1을 해주면 되는 것입니다.

## 후기
알고 수업 때 들었던 동전문제가 생각나네요~ㅋㅋㅋ