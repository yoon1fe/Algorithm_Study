# [15684] 사다리 조작 - Java

###  :orange: 분류

> 시뮬레이션
>
> 완전 탐색(조합)



### :orange: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, Dir> map;
    static int[][] ladder;
    static int answer = -1;
    static int N, M, H;

    static List<Integer> addIdx = new ArrayList<>();
    static class Dir{
        int y, x;
        Dir(int y, int x){
            this.y = y; this.x = x;
        }
    }

    public static void checkLadder(int depth) {
        for(int i = 1; i < N; i++) {
            Dir cur = new Dir(1, i);

            while(cur.y <= H) {
                if(ladder[cur.y][cur.x] == 1) cur.x++;
                else if(ladder[cur.y][cur.x - 1] == 1) cur.x--;
                cur.y++;
            }
            if(cur.x != i) return;
        }
        answer = answer == -1 ? depth : answer;
    }

    public static void comb(int idx, int depth, int cnt) {
        if(cnt == depth) {
            checkLadder(depth);
            return;
        }

        for(int i = idx; i <= map.size(); i++) {
            // 연속으로 가로선 놓지 말기
            if(ladder[map.get(i).y][map.get(i).x - 1] == 1 || ladder[map.get(i).y][map.get(i).x + 1] == 1) continue;

            ladder[map.get(i).y][map.get(i).x] = 1;
            comb(i+1, depth, cnt+1);
            ladder[map.get(i).y][map.get(i).x] = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        map = new HashMap<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H + 1][N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            ladder[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        int idx = 1;
        for(int i = 1; i <= H; i++) {
            for(int j = 1; j < N; j++) {
                if(ladder[i][j] == 0) map.put(idx++, new Dir(i, j));
            }
        }

        for(int i = 0; i <= 3; i++) {        // depth
            if(answer != -1) break;
            comb(1, i, 0);
        }

        System.out.println(answer);
    }
}
```



### :orange: 풀이 방법

전형적인 완전 탐색 + 시뮬레이션 문제입니다.



조건을 만족할 때 가로선의 개수가 최소인 경우를 찾으면 되니깐 0개 놓는 경우부터 봐주면서 answer가 업데이트되면 그 뒤에는 봐주지 않아도 된답니다.

조합을 이용해서 가로선을 놔주는데, 이 때 놓을 수 있는 위치 조합을 만들 때 Map을 사용해서 쉽게 만들었습니다.

가로선 연속으로 놓이는 경우를 조합을 만들 때 체크했는데 map에 넣을 때 체크하고 넣을 수도 있겠네요.

depth(놓는 가로선의 개수)만큼 놓고 나면 i번째에서 출발해서 i번에 도착하는지를 체크해줍시다.

ladder[i][j] == 1 이라면 오른쪽으로 가로선이 놓여 있단 의미입니다. 고로 오른쪽으로 가줍시다.

ladder[i][j-1] == 1 이라면 현재 내 위치의 왼쪽으로 가로선이 놓여 있단 의미입니당.



문제에서 요구하는 조건을 모두 만족하는 경우에만 answer를 갱신해주기 때문에 답이 3을 넘어가거나 답이 없는 경우(둘다 진배없슴니다) -1을 그대로 갖고 가주게 되는거져.



### :orange: 후기

항상 문제 설계에 많은 시간을 할애합시다.

감사합니다!!