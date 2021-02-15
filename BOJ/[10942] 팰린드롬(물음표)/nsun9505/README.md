# [10942] 팰린드롬? - JAVA

## 분류
> 문자열
>
> DP

## 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++)
            map[0][i] = Integer.parseInt(st.nextToken());

        for(int i=1; i<=N; i++)
            for(int j=i; j<=N; j++)
                map[i][j] = isPalindrome(i, j);

        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(map[s][e] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int isPalindrome(int start, int end){
        int len = (end - start + 1)/2;
        if(len % 2 == 1)
            len++;
        for(int i=0; i<len; i++){
            if(map[0][start++] != map[0][end--])
                return 0;
        }
        return 1;
    }
}
```

## 문제 풀이
주어진 문자에 대해서 모든 질문을 구하기 위해서 2차원 배열을 사용합니다.

2차원 배열 (i, j) 위치의 값은 start가 i이고 j가 end일 때의 부분 문자열이 팰린드롬인지를 알려줍니다.

주어진 문자의 길이는 2000이고 이차원 배열의 절반만 사용(s <= e 이므로)하니깐 미리 질문에 대한 모든 경우의 수를 계산한다면

시간 복잡도 안에 들어온다고 생각했습니다.

그래서 (i, j)에 대해 팰린드롬을 검사하고 팰린드롬이면 1, 아니면 0으로 map을 세팅했습니다.

M만큼 주어진 질문에 대해서는 바로 map[s][e]로 답을 찍어냈습니다.

## 후기
문제를 풀고 보니 DP라고 나와있었습니다..

DP로 어케 풀어야 하는거지..

오늘도 파이팅!