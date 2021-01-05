# [2467] 용액 - JAVA

## 분류
> 투포인터
>
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
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = N-1;
        int[] ans = new int[2];
        int min = Integer.MAX_VALUE;
        while(left < right){
            int sum = arr[left] + arr[right];
            if(Math.abs(sum) < min){
                min = Math.abs(sum);
                ans[0] = arr[left];
                ans[1] = arr[right];
            }

            if(sum < 0)
                left++;
            else
                right--;
        }

        System.out.println(ans[0] + " " + ans[1]);
    }
}
```

## 문제 풀이
투 포인트를 사용한 문제!

이미 정렬이 되어 있다는 점이 문제를 좀 더 쉽게 접근해주지 않았나 싶습니다.

left = 0, right = N-1로 초깃값을 세팅합니다.

그리고 min 값도 Int의 최댓값으로 세팅합니다.

left와 right 위치에 있는 원소를 더합니다.
   - 더한 값의 절대값이 min보다 작다면 left, right 위치를 답으로 갱신
   - min값도 `sum = arr[left] + arr[right]`의 절대값을 저장

left와 right의 위치 변경
   - sum 값이 0보다 작은 경우 오름차순으로 정렬되어 있기 때문에 left 값을 증가시키면 left 값보다 큰 값을 right 위치에 더하게 되므로 더 작은 값을 취하기 때문에 left 값을 +1 증가시킵니다.
   - sum 값이 양수인 경우 오름차순으로 정렬되어 있기 때문에 right 값을 1감소 시키면 right 값보다 작은 값을 left 위치에 더하게 되므로 더 작은 값을 취할 수 있기 때문에 right 값을 -1을 감소시킵니다.

## 후기
아무래도 left와 right를 어떻게 증가 또는 감소시키느냐가 핵심이였던거 같습니다!

오늘도 파이팅!!