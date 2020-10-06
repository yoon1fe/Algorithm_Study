# [17142] 연구소 3 - Java

###  :hospital: 분류

> 시뮬레이션
>
> BFS
>
> 조합



### :hospital: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Dir{
        int y, x;
        Dir(int y, int x){
            this.y = y; this.x = x;
        }
    }

    static int N, M, answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static List<Dir> virus = new ArrayList<>();
    static boolean[] selectedVirus;
    static int emptySpace;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virus.add(new Dir(i, j));
                else if(map[i][j] == 0) emptySpace++;
            }
        }

        selectedVirus = new boolean[virus.size()];
        comb(0, 0);

        System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
    }

    public static void comb(int cnt, int idx) {
        if(cnt == M) {
            answer = Math.min(answer, spreadVirus());
            return;
        }

        for(int i = idx; i < virus.size(); i++) {
            selectedVirus[i] = true;
            comb(cnt + 1, i + 1);
            selectedVirus[i] = false;
        }
    }

    public static int spreadVirus() {
        int maxDay = 0;
        int remains = emptySpace;
        Queue<Dir> q = new LinkedList<>();
        int[][] v = new int[N][N];

        for(int i = 0; i < virus.size(); i++) {
            if(selectedVirus[i]) q.offer(virus.get(i));
            v[virus.get(i).y][virus.get(i).x] = selectedVirus[i] == true ? 1 : -1;
        }

        while(!q.isEmpty()) {
            if(remains == 0) break;
            Dir cur = q.poll();

            for(int i = 0; i < 4; i++) {
                Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
                if(!isIn(next) || map[next.y][next.x] == 1 || v[next.y][next.x] > 0) continue;
                remains = map[next.y][next.x] == 0 ? remains - 1 : remains;
                v[next.y][next.x] = v[cur.y][cur.x] + 1;
                maxDay = Math.max(maxDay, v[next.y][next.x]);
                q.offer(next);
            }
        }

        return remains == 0 ? (maxDay == 0 ? 0 : maxDay - 1) : Integer.MAX_VALUE;
    }

    public static boolean isIn(Dir c) {
        if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N) return true;
        else return false;
    }
}
```



### :hospital: 풀이 방법

조합에 BFS를 곁들인 완전 탐색 시뮬레이션 문제입니다.

종합 선물세트네영.



문제에서 기본적인 요구사항은 까다롭지 않습니다.

바이러스 중에서 M개를 골라서 활성 바이러스로 두고, 여기서 BFS로 퍼져나가게끔 하면 되겠지용.

 

근데 예제를 잘 보면 맨 마지막에 비활성 바이러스는 굳이 활성 바이러스로 바꿔주지 않습니다. 왜냐면 문제에서 구해야 하는 것이 **모든 빈 칸에 바이러스가 있게 되는** 최소 시간이기 때문이죵.

그렇기 때문에 맨 처음 빈 칸의 개수를 입력받을 때 구해서 얘를 갖고 BFS를 종료시켜 주는 것이죠~!

BFS를 돌면서 해당 위치의 map의 값이 0 인 경우에 하나씩 줄여나가 주면 되겠져.

 

각 경우에 maxDay를 구해주면 되는데, 문제를 보면은 바이러스가 없는 공간이 남아있다면 -1을 출력해야 합니다. 근데 모든 경우가 이럴 때 -1을 출력해야 하기 때문에 요런 경우에는 Integer.MAX_VALUE를 리턴해서 봐줬씁니다. 

answer는 최솟값이니깐 맨 마지막에 answer가 여전히 Integer.MAX_VALUE라면 이는 어떤 경우에도 빈 칸이 남아있다는 의미가 되져 ㅎ.ㅎ

 

아 그리고 spreadVirus() 메소드 리턴할 때 저렇게 복잡한 이유는 v 배열 시작할 때 1부터 줘서 그렇습니당 ㅎㅎ



### :hospital: 후기

이제 BFS 코드는 눈 감고도 술술 적힙니당.

45분이 채 안걸렸습니다 어예~!~!