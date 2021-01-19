# [Summer/Winter Coding(~2018)] 기지국 설치 - JAVA

## 분류
> 구현

## 코드
```java
class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int cur = 1;
        boolean flag = false;
        for(int i=0; i<stations.length; i++){
            int next = stations[i] - w - 1;
            if(next <= 0) {
                cur = stations[i] + w + 1;
                continue;
            }
            int ret = next - cur + 1;
            cur = stations[i] + w + 1;
            if(n < cur)
                flag = true;

            if(ret > 0){
                answer += ret / (2*w+1);
                int remain = ret % (2*w+1);
                if(remain != 0)
                    answer+=1;
            }
        }
        if(flag)
            return answer;

        int ret = n - cur + 1;
        if(ret > 0){
            answer += ret / (2*w+1);
            int remain = ret % (2*w+1);
            if(remain != 0)
                answer += 1;
        }

        return answer;
    }
}
```

## 문제 풀이
처음에는 BFS로 풀까? 했지만 그럴 필요 없이 이미 정렬된 stations이 있으므로 i번째와 i+1번째 사이에 전파가 닿지 않는 곳을 계산하면 됩니다.

전파가 닿지 않는 범위(=거리)를 계산합니다.
   - 거기에 하나의 기지국이 가지는 전파 범위(w * 2 + 1)로 나눠서 몫값을 answer에 더해서 몇 개의 기지국을 건설할 수 있는지 봅니다.
      - 전파는 양방향이고 +1은 기지국이 설치된 곳!
   - 그리고 나머지가 0이 아니라면 하나 더 설치할 수 있으므로 answer에 +1을 합니다.

i번째와 i+1번째 사이를 계산
   - i번째는 cur, i+1은 next 입니다.
   - cur은 초기값으로 1로 설정합니다.
   - `next = stations[i] - w - 1`로 세팅해서 기지국 전파가 안 닿는 범위의 끝부분을 의미합니다.
      - 만약 계산된 next가 0보다 작다면 바로 다음으로 넘어가기 위해 cur을 stations[i] + w + 1로 해서 stations[i] 이후의 전파가 안 닿는 범위의 시작부분으로 변경해줍니다.
   - ret은 전파가 안 닿는 범위를 계산합니다.
      - next - cur + 1을 함으로써 i번째 기지국과 i+1 기지국 사이에 전파가 퍼지지 않은 범위를 구해서 그 구역을 기지국 하나를 설치함으로써 가지는 전파 범위(2*w+1)로 나눈 몫과 나머지를 구합니다.
      - 몫은 그대로 answer에 더하고, 나머지는 0이 아니면 기지국 하나의 범위를 다 쓰지는 못하지만 남는 공간을 커버하기 위해서 answer + 1을 합니다.

그리고 다음 구역(=전파가 퍼지지 않은 구역)의 시작 부분을 계산하기 위해서 cur을 stations[i]+w+1로 세팅합니다.

stations에는 n번째에 기지국이 설치도지 않을 수 있습니다.
   - 그러므로 n번째를 stations에 n을 마지막에 넣어줘도 되지만
   - 저는 그냥 cur을 계속 바꾸면서 cur값이 n까지 충분히 포함하는 경우는 stations[i] + w + 1부분이 n까지 포함하는 것이므로 n과 cur 사이에 몇 개의 기지국을 설치할지를 계산할 필요가 없으므로 flag가 true로 세팅되어서 바로 answer를 리턴합니다.
   - 하지만, cur이 n을 넘어서지 않는다면, flag는 계속 false로 남아 있으므로 stations의 마지막 원소 값 + w + 1을 한 것과 n 사이에 몇 개의 기지국이 들어가는지 구해주면 됩니다.

## 후기
특별한 알고리즘을 사용하지 않는 이런 문제를 좀 많이 풀어봐야 할 것 같습니다.

n이 2억이 될 수 있어서 이진탐색이나 BFS도 생각해봤는데 좀 더 쉽게 풀 수 있는 방법도 생각하는 그런 사고력.. 후움..

오늘 하루도 파이팅!!