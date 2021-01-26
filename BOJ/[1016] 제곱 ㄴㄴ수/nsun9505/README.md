# [1016] 제곱 ㄴㄴ수

## 분류
> 수학
>
> 에라토스테네스의 체

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
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        int size = (int)(max - min + 1);
        boolean[] arr = new boolean[size];
        
        for(long i=2; i<=1000000; i++) {
            // 제곱수
            long num = i*i;
            if(num > max)
                break;
            // 제곱의 배수 중 min ~ max 사이에 속하는지 체크
            for(long j = min / num; j * num <=max; j++)
                if(num * j >= min)
                    arr[(int)(j*num - min)] = true;
        }

        int cnt = 0;
        for(int i=0; i<size; i++)
            if(!arr[i])
                cnt++;
        System.out.println(cnt);
    }
}
```

## 문제 풀이
한 두 시간을 잡고 있었는데 어려워서 바킹독님 코드 이해하고 제출했습니다.

문제에서 제곱 ㄴㄴ수는 어떤 수가 1보다 큰 제곱수로 나누어 떨어지지 않을 때의 수입니다.

제곱수이기 때문에 i = 2~1,000,000까지 가면서 i*i의 배수가 min~max 사이에 있다면 체크하면 됩니다!
   - i * i는 int 범위를 넘어설 수 있으므로 혹시 모르니 long 타입에 num에 담아줍니다.
   - 그리고 i * i 값이 max 값보다 크다면 더 이상 계산할 필요 없으므로 외부 for문을 종료하면 됩니다.

num(=i * i)으로 제곱수를 표현하고, min / num로 j의 시작점 즉, `num * (min / num)`이 min에 가장 가까워지기 때문에

j를 min / num를 num의 배수 시작점으로 설정합니다.

그래서 num * j가 min보다 크고 max보다 작다면 해당 값을 true로 표시해서 나중에 카운트 되지 않도록 합니다.
   - j를 1씩 증가시키면서 num의 배수가 min~max 범위에 들어가는지 체크!

그러면 min ~ max 범위에서 아직 false인 애들이 제곱 ㄴㄴ수이므로 카운트해서 출력해주면 됩니다.

## 후기
와 어렵다 어려웡