# [19238] 스타트 택시 - Java

###  :taxi: 분류

> 시뮬레이션
>



### :taxi: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Dir implements Comparable<Dir>{
        int y, x;

        public Dir(int y, int x) {
            this.y = y; this.x = x;
        }

        public int compareTo(Dir o) {
            if(this.y == o.y) return this.x - o.x;
            return this.y - o.y;
        }
    }

    static int N, M, fuel, cnt;
    static List<Integer>[][] map;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static Dir taxi;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    public static int solve() {

        for(int i = 0; i < M; i++) {
            // 택시 -> 승객 구하기

            Dir nextCustomer = selectCustomer(taxi);
            if(nextCustomer == null) return -1;
            Collections.sort(map[nextCustomer.y][nextCustomer.x], Collections.reverseOrder());
            int custNo = map[nextCustomer.y][nextCustomer.x].get(0);

            taxi.y = nextCustomer.y; taxi.x = nextCustomer.x;
            map[taxi.y][taxi.x].remove((Integer)custNo);


            // 목적지로 이동
            Dir nextDest = goToDest(nextCustomer, custNo * -1);

            if(nextDest == null) return -1;
            taxi.y = nextDest.y; taxi.x = nextDest.x;
            map[taxi.y][taxi.x].remove((Integer)(-1 * custNo));

        }

        return fuel;
    }

    public static Dir goToDest(Dir s, int no) {

        Queue<Dir> q = new LinkedList<>();
        int[][] v = new int[N][N];
        Dir dest = null;
        q.offer(s);
        v[s.y][s.x] = 1;

        outer:
        while(!q.isEmpty()) {
            Dir cur = q.poll();

            for(int i = 0; i < 4; i++) {
                Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
                if(!canGo(next) || v[next.y][next.x] != 0) continue;

                v[next.y][next.x] = v[cur.y][cur.x] + 1;
                q.offer(next);

                if(map[next.y][next.x].size() == 0 ) continue;
                if(map[next.y][next.x].contains(no) == true) {
                    dest = new Dir(next.y, next.x); break outer;
                }
            }
        }

        if(dest == null) return null;
        fuel -= (v[dest.y][dest.x] - 1); 
        fuel = fuel < 0 ? -1 : fuel + (v[dest.y][dest.x] - 1) * 2; 
        return fuel < 0 ? null : dest;
    }

    public static Dir selectCustomer(Dir s) {

        PriorityQueue<Dir> pq = new PriorityQueue<>();
        Queue<Dir> q = new LinkedList<>();

        int[][] v = new int[N][N];
        int minDist = Integer.MAX_VALUE;

        q.offer(s);
        v[s.y][s.x] = 1;

        if(map[s.y][s.x].size() != 0) {
            Collections.sort(map[s.y][s.x], Collections.reverseOrder());
            if(map[s.y][s.x].get(0) > 1) return s;
        }

        while(!q.isEmpty()) {
            Dir cur = q.poll();

            for(int i = 0; i < 4; i++) {
                Dir next = new Dir(cur.y + dy[i], cur.x + dx[i]);
                if(!canGo(next) || v[next.y][next.x] != 0) continue;

                v[next.y][next.x] = v[cur.y][cur.x] + 1;
                q.offer(next);
                if(map[next.y][next.x].size() == 0) continue;
                Collections.sort(map[next.y][next.x], Collections.reverseOrder());
                if(map[next.y][next.x].get(0) > 1 && v[next.y][next.x] <= minDist ) {
                    minDist = Math.min(minDist, v[next.y][next.x]); pq.offer(next);
                }
            }
        }
        if(pq.size() == 0) return null;
        fuel -= (v[pq.peek().y][pq.peek().x] - 1);
        return fuel < 1 ? null : pq.peek();
    }

    public static boolean canGo(Dir c) {
        if(0 <= c.y && c.y < N && 0 <= c.x && c.x < N && map[c.y][c.x].contains(1) == false ) return true;
        else return false;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken()); fuel = Integer.parseInt(st.nextToken());
        map = new List[N][N];
        cnt = M;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
                int t = Integer.parseInt(st.nextToken());
                if(t == 0) continue;
                map[i][j].add(t);
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int y = Integer.parseInt(st.nextToken()) - 1; int x = Integer.parseInt(st.nextToken()) - 1;
        taxi = new Dir(y, x);

        for(int i = 2; i < 2 + M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].add(i);        // 승객 번호 (2번부터 시작)
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].add(i * -1);    // 목적지 번호 (승객 번호 * -1)
        }

    }
}
```



### :taxi: 풀이 방법

삼성 SW 역량 테스트 기출 문제 끝~~~~!!!

이제 실전입니다 허허

이번엔 반례 도움을 조금 받고 풀어씀다....^^;

시험땐 반례 없는데 어쩌나~~

 

List<>[][] map 으로 지도를 표현했습니다. 여기에 벽(1), 손님(2~M+2), 도착지(-2~-(M+2)) 를 놔둘겁니다.

첨에 int[][] 로 만들었는데 누군가의 출발지가 누군가의 도착지가 될 수 있으므로 중간에 List<>로 바꿨습니다.. 

 

이번에도 열심히 문제 구성을 생각하고 풀기 시작했습니다. 확실히 머리에 더 잘 들어오는 너낌~

 

문제 로직은 이렇습니다.

1. 택시 위치에서 BFS로 태울 승객을 정합니다. 이 때 찾고 나서 연료를 차감시켜 1보다 작으면 바로 -1을 리턴하도록 해줍니다.
2. 승객에서 BFS로 도착지까지 갑니다. 이 때도 도착 후 연료를 차감시킵니다. 이때는 0보다 작은 경우에 -1을 리턴시킵니다. 왜냐면 도착하자마자 연료를 채우면 되니깐요. 그러고 연료 충전까지 시켜줍시다.
3. 이걸 M번 반복합니다. 

로직은 아주 간단합니다. 사실 문제 자체도 그리 어렵지가 않습니다. 다만 반례를 찾는게 관건이 되겠습니다.. 문제에 적혀 있지 않은 제한 사항은 다 허용된다고 생각하고 반례를 만들도록 노력해야겠습니다!!

 

### :taxi: 후기

내일 코테치고나면 당분간 삼성 기출 지옥 끝~!