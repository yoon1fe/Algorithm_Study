# [14888] 연산자 끼워넣기 - JAVA

## 분류
> 완전탐색

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] operator = new int[4];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++)
            operator[i] = Integer.parseInt(st.nextToken());

        solution(1, arr[0]);
        sb.append(max + "\n" + min);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int idx, int sum){
        if(idx == arr.length){
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for(int i=0; i<operator.length; i++){
            if(operator[i] == 0)
                continue;

            int tmp = sum;
            if(i == 0) tmp += arr[idx];
            else if(i == 1) tmp -= arr[idx];
            else if(i == 2) tmp *= arr[idx];
            else if(i == 3) tmp /= arr[idx];

            operator[i] -= 1;
            solution(idx+1, tmp);
            operator[i] += 1;
        }
    }
}
```

## 문제 풀이
완점탐색으로 풀었습니다!

연산자를 사용할 수 있는만큼 사용해서 계산해가며 답을 도출하면 됩니다.

operator[i]가 0이면 사용할 수 있는 i번째 연산자가 없다는 의미입니다.

operator[i]가 0이 아니라면 해당 i번째 연산자를 사용할 수 있으므로 연산자에 따라 sum에 연산을 한 후에 DFS로 다시 solution을 호출하면 됩니다.
   - i번째 연산자를 하나 사용하였으므로 -1을 하여 감소시켜서 0일 경우에는 다음에 사용하지 못하도록 합니다.
   - solution()을 돌고 나와서는 다시 i번째 연산자를 하나 쓸 수 있다는 의미로 operator[i]를 하나 증가시키면 됩니다.

solution의 idx변수는 주어진 피연산자들을 가리키는 변수입니다.
   - idx가 N에 도달하면 피연산자도 다 쓰고, 연산자도 다 썼으므로 계산된 sum과 max와 min을 비교하여 갱신하면 됩니다.

## 후기
오늘도 화이팅~