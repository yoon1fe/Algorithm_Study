# [20055] 컨베이어 벨트 위의 로봇 - JAVA

## 분류
> 구현
>
> 시뮬레이션

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

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] belt = new int[2*N+1];
        int[] robots = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=2*N; i++)
            belt[i] = Integer.parseInt(st.nextToken());


        for(int level = 1;; level++) {
            int prev = belt[2*N];
            int save = belt[2*N];

            for (int i = 1; i <= 2 * N; i++) {
                save = belt[i];
                belt[i] = prev;
                prev = save;
            }

            for(int i=N; i>=1; i--){
                if(i == N && robots[i] == 1){
                    robots[i] = 0;
                    continue;
                }

                int next = i + 1;
                if(robots[i] == 1) {
                    robots[next] = 1;
                    robots[i] = 0;
                }
            }

            for(int i=N; i>=1; i--){
                if(i == N){
                    robots[i] = 0;
                    continue;
                }
                if(robots[i] == 0)
                    continue;
                if(belt[i+1] == 0 || robots[i+1] == 1)
                    continue;

                robots[i] = 0;
                robots[i + 1] = 1;
                belt[i+1] -= 1;
            }

            if(belt[1] > 0 && robots[1] == 0){
                robots[1] = 1;
                belt[1] -=1;
            }

            int cnt = 0;
            for(int i=1; i<=2*N; i++)
                if(belt[i] == 0)
                    cnt++;

            if(cnt >= K){
                System.out.println(level);
                break;
            }
        }
    }
}
```

## 문제 풀이
문제를 쪼개서 풀었습니다.

1. 컨베이어를 이동시킨다.
   - 컨베이어를 한 칸씩 이동시킵니다.

1. 컨베이어 위의 로봇들도 이동시킨다.
   - 로봇들이 있으면 한 칸씩 이동시킵니다.
   - 대신, 컨베이어에 의해 이동된 로봇들 중에서 이제 땅으로 내려올 로봇이 있을 수 있으므로 해당 로봇을 컨베이어 벨트 위에서 제거합니다.

1. 로봇들이 스스로 한 칸 움직인다.
   - 이제 로봇이 스스로 한 칸씩 움직이게 됩니다.
   - 컨베이어에 의해 움직여진 로봇들이 이제 자기 스스로 한 칸씩 이동하는 것입니다.
   - 그러면 N칸(내려갈 위치)에 있는 로봇은 땅으로 내려가게 됩니다.
   - 나머지는 다음 칸의 벨트의 내구성이 1이상이고 다음 칸에 로봇이 없다는 조건을 만족하면 로봇을 이동시키면 됩니다.

1. 올라가는 위치에 로봇을 올릴 수 있으면 올린다.
   - belt[1]의 내구성이 1이상이고, robots[1]에 로봇이 없다면 로봇을 하나 올립니다.

1. 내구성이 0인 벨트의 수를 카운트해서 K이상이면 현재 단계를 출력하고 끝낸다.

## 후기
처음에 뭔 소린가 싶었는데 컨베이어벨트 돌리고 로봇 옮기고 로봇 움직이고 로봇 올리고 카운트하는! 단순한 구현 문제였습니다!

2월도 파이팅!!!