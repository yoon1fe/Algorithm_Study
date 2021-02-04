# [2021 KAKAO BLIND RECRUITMENT] 합승 택시 요금

## 분류
> 플로이드와샬

## 코드
```java
public class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int[][] map = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                map[i][j] = Integer.MAX_VALUE;

        for(int i=0; i<n; i++)
            map[i][i] = 0;

        for(int i=0; i<fares.length; i++){
            int u = fares[i][0] - 1;
            int v = fares[i][1] - 1;
            map[u][v] = fares[i][2];
            map[v][u] = fares[i][2];
        }

        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                if(i == k)
                    continue;
                for(int j=0; j<n; j++){
                    if(i == j || j == k)
                        continue;
                    if(map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE)
                        continue;

                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int min = map[s-1][a-1] + map[s-1][b-1];
        for(int i=0; i<n; i++){
            if(i != (s-1)){
                min = Math.min(min, map[i][a-1] + map[i][b-1] + map[s-1][i]);
            }
        }

        answer = min;

        return answer;
    }
}
```

## 문제 풀이
정점 개수가 작아서 플로이등 와샬로 풀면 풀리는 문제입니다.
   - O(N^3)

플로이드 와샬로 각 정점에서 다른 정점으로 가는 최소 경로를 계산합니다.

min 값을 둘이 합승하지 않은 값으로 세팅합니다.

그리고 min과 s에서 a, b를 포함하지 않는 정점 중에서 하나(i)를 거쳐서 a, b를 갈 떄 최소 값을 구하면 됩니다.

## 후기
다익스트라로도 충분히 풀 수 있습니다.

오늘도 화이팅!!
