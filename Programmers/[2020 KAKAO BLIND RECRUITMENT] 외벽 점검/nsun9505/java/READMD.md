# [2020 KAKAO BLIND RECRUITMENT] 외벽점검 - JAVA

## 분류
> 구현
>
> 시뮬레이션
>
> 순열

## 코드
```java
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int[] weaks;
    static int[] dists;
    static Queue<Integer> queue = new LinkedList<>();
    static int ans = Integer.MAX_VALUE;
    static int N = 0;
    public int solution(int n, int[] weak, int[] dist) {
        weaks = weak;
        dists = dist;
        N = n;

        perm(0);
        if(ans == Integer.MAX_VALUE)
            return -1;
        return ans;
    }

    public static void perm(int depth){
        if(depth == dists.length){
            for(int start=0; start<weaks.length; start++){
                queue.clear();
                int idx = 0;

                for(int j=start; j<weaks.length; j++)
                    queue.offer(weaks[j]);
                for(int j=0; j<start; j++)
                    queue.offer(weaks[j] + N);

                int cur = queue.poll() + dists[idx++];
                int cnt = 1;

                boolean check = true;
                while(!queue.isEmpty()){
                    int next = queue.peek();
                    if(next <= cur) {
                        queue.poll();
                    }
                    else{
                        if(idx >= dists.length) {
                            check = false;
                            break;
                        }
                        cur = next + dists[idx++];
                        cnt++;
                        if(ans <= cnt){
                            check = false;
                            break;
                        }
                    }
                }

                if(!check)
                    continue;

                ans = Math.min(ans, cnt);
            }
            return;
        }
        // 순열
        for(int i=depth; i<dists.length; i++){
            swap(dists, depth, i);
            perm(depth+1);
            swap(dists, depth, i);
        }
    }

    public static void swap(int[] arr, int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
```

## 문제 풀이
dist의 최대 갈이가 8이므로 친구들의 순서를 나열해도 수가 크지 않아서 완전탐색으로 돌렸습니다.
   - 친구들의 수 8! = 40320
   - 취약한 외벽위치(15개)에서 각 위치를 시작위치로 해서 탐색
   - 40330 * 15 * 15 = 9072000
   - 그래서 1초안에 충분히 계산할 수 있다고 생각했습니다.

순열로 친구들의 순서를 구한 다음에 각 취약점 위치를 시작점으로 해서 주어진 순열대로 점검을 돌려봅니다.

그리고 각 위치는 시작 위치에 따라 위치값을 변경해줍니다.

예를 들어, n이 12고 [1, 5, 7, 10]이 있을 때 10에서 시작위치를 잡았다고 하겠습니다.
   - 그러면 다음 위치는 1인데 10 -> 1을 가기 위해서는 3칸을 가야 합니다.
   - 이것을 배열로 해서 하면 좀 로직이 복잡?해질거 같았습니다.
   - 그래서 10을 큐에 담고, 그 다음들은 시작위치 10입장에서는 N을 넘어선 숫자들이므로 각 위치에 N을 더하면 시작위치에 따라 각 취약점 위치들이 변경되어 있을 것입니다.
   - weak 배열이 오름차순으로 정렬되어 있기 때문에 가능한 것입니다.
   - 7을 시작위치로 잡으면 배열에 끝으로 갈 때까지는 그냥 담고, 7뒤에 있는 것들은 0번쨰부터 N을 더해줘서 위치값을 변경하면 됩니다.

그리고 시작 위치에서 점검을 시작합니다.
   - 처음 위치는 무조건 체크가 되므로, 시작 위치 + idx번째 침구가 점검할 수 있는 길이를 더합니다.
   - 그러면 시작위치 + dist[idx] 사이에 들어오는 취약한 외벽은 점검이 가능하므로 queue에서 빼면 됩니다.
   - 만약 이 범위를 넘어선다면 한 명의 몫은 끝났으므로, 다른 친구를(idx++) 사용해서 범위를 넘어선 그 취약점 위치부터 점검하도록 하면 됩니다.

그래서 취약점 위치가 담긴 큐가 비워지기 전에 친구들을 다 써버리면 해당 순서로는 점검이 불가능한 것입니다.

친구들을 다 쓰기 전에 큐가 비워졌다면, 친구들을 얼마나 썼는지 cnt를 통해 확인하고, 정답으로 출력할 ans와 비교하여 더 작은 값으로 ans를 갱신하면 됩니다.

## 후기
후아후아!

작년에 풀 때는 왜 순열을 쓰는건지 이해가 잘 안 갔었는데

숫자가 작으면 순열이나 조합으로 충분히 풀 수 있다는 것을 좀 생각할 수 있어야 겠습니다..

그래도 머리에 조금은 남아 있어서 어렵지 않게 풀 수 있었습니다.
