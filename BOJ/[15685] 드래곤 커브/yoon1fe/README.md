# [15685] 드래곤 커브 - Java

###  :dragon: 분류

> 시뮬레이션
>



### :dragon: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] map = new boolean[101][101];

    public static void makeDragonCurve(int x, int y, int d, int g) {
        List<Integer> direction = new ArrayList<>();
        direction.add(d);

        for(int i = 1; i <= g; i++) {
            for(int j = direction.size() - 1; j >= 0; j--) direction.add((direction.get(j) + 1) % 4);
        }

        map[y][x] = true; 
        for(Integer i : direction) {
            y += dy[i]; x += dx[i];
            map[y][x] = true; 
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            makeDragonCurve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < 100; i++) {
            for(int j = 0 ; j < 100; j++) {
                if(map[i][j] == true && map[i][j+1] == true && map[i+1][j] == true && map[i+1][j+1] == true) answer++;
            }
        }
        System.out.println(answer);
    }
}
```



### :dragon: 풀이 방법

전형적인 시뮬레이션에 규칙을 찾으면 아주 쉽게 풀 수 있는 문제입니다.

첫 방향 0인 경우 각 세대별로 이동하는 방향을 살펴봅시다.

 

0세대 - 0

1세대 - 0 1

2세대 - 0 1 2 1

3세대 - 0 1 2 1 2 3 2 1

 

이런 식이 됩니다.

규칙이 보이시나여??

i세대때 이동 방향은 i-1세대 이동 방향을 역으로 보면서 +1을 해주는 겁니다.

이는 [yoon1fe.tistory.com/8](https://yoon1fe.tistory.com/8) 이 문제의 규칙이랑 꽤 비슷하져.

 

이러한 규칙만 잘 찾아주면 끝입니다.

`[101][101]` 배열을 만들어서 이동하는 좌표를 체크해주면 되는거죠.

 

입력 데이터로 배열에 다 체크해주고 나서 네 점을 체크해주면 끝입니당!



### :dragon: 후기

생각보다 규칙 생각이 빨리 나서 호로록 풀어따!

감사합니다!!