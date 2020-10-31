## [17406] 배열 돌리기 4 - Java

### :dizzy_face: 분류

> 시뮬레이션
>
> 순열

​

### :dizzy_face: 코드

```java
import java.io.*;
import java.util.*;

public class Main{
    static int[][] map;
    static boolean[] isSelected;
    static int N, M, K;
    static int[][] operation;
    static int[] order;
    static int answer = 99999999;

    public static void rotate(int idx, int[][] map) {
        int r = operation[idx][0]; int c = operation[idx][1]; int s = operation[idx][2];
        int mR = r-s; int mC = c-s; int MR = r+s; int MC = c+s;

        for(int i = 0; i < s; i++ ) {
            int start = map[mR+i][mC+i];
            for(int j = mR + i; j < MR - i; j++) map[j][mC+i] = map[j+1][mC+i];
            for(int j = mC + i; j < MC - i; j++) map[MR-i][j] = map[MR-i][j+1];
            for(int j = MR - i; j > mR + i; j--) map[j][MC-i] = map[j-1][MC-i];
            for(int j = MC - i; j > mC + i + 1; j--) map[mR+i][j] = map[mR+i][j-1];
            map[mR+i][mC+i+1] = start;
        }
    }

    public static void perm(int cnt) {
        if(cnt == K) {
            int[][] tempMap = new int[N+1][M+1];
            for(int i = 1; i <= N; i++) System.arraycopy(map[i], 0, tempMap[i], 0, map[i].length);

            for(int i = 0; i < K; i++) rotate(order[i], tempMap);

            answer = Math.min(getArrayValue(tempMap), answer);

            return;
        }

        for(int i = 0; i < K; i++) {
            if(isSelected[i]) continue;
            isSelected[i] = true;
            order[i] = cnt;
            perm(cnt+1);
            isSelected[i] = false;
        }
    }

    public static int getArrayValue(int[][] map) {
        int ret = 99999999;
        for(int i = 1; i <= N; i++) ret = Math.min(Arrays.stream(map[i]).sum(), ret);

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        isSelected = new boolean[K];
        operation = new int[K][3];
        order = new int[K];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 3; j++) {
                operation[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        perm(0);

        System.out.println(answer);
    }
}
```



### :dizzy_face: 풀이 방법

2차원 배열을 얼마나 잘 다루는지 요구하는 문제라고 생각합니다.

돌리는 연산의 순서에 따라 배열의 값이 달라지기 때문에 순열을 만들어서 모든 케이스를 체크합시다.



먼저 순열을 만들어 줍시다. K! 개가 나옵니다.

다 만들어 주면 주어진 연산대로 배열을 돌리면 됩니다.

 

회전 연산을 보면 회전을 해야 하는 배열의 가로 세로 길이는 항상 홀수이므로 짝수일 때 홀수일 때 구분지을 필요가 없습니다.

 

연산 굴러가는걸 보면 총 s 개의 배열이 도는 걸 알 수 있습니다. 

이를 토대로 반복문을 써서 쭉쭉 땡겨줍니다.



### :dizzy_face: 후기

이번 문제도 2차원 배열에서 인덱스 갖고 노는게 좀 골치 아팠씁니다.

감사함니다 ._.