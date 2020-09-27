# [15686] 치킨 배달 - Java

###  :chicken: 분류

> 시뮬레이션
>
> 조합



### :chicken: 코드

```java
import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 999999999;
    static int N, M;
    static int answer = MAX;
    static List<Dir> house = new ArrayList<>();
    static List<Dir> chicken = new ArrayList<>();
    static Dir[] selected;

    static class Dir{
        int y, x;
        Dir(int y, int x){
            this.y = y; this.x = x;
        }
    }

    static void comb(int cnt, int idx) {
        if(cnt == M) {
            answer = Math.min(answer, getChickenDistance());
            return;
        }

        for(int i = idx; i < chicken.size(); i++) {
            selected[cnt] = chicken.get(i);
            comb(cnt+1, i+1);
        }
    }

    static int getChickenDistance() {
        int sum = 0;
        for(int i = 0; i < house.size(); i++) {
            int chickenDist = MAX;
            for(int j = 0; j < selected.length; j++) {
                chickenDist = Math.min(chickenDist, getDistance(house.get(i), selected[j])); 
            }
            sum += chickenDist;
        }
        return sum;
    }

    static int getDistance(Dir d1, Dir d2) {
        return Math.abs(d1.y - d2.y) + Math.abs(d1.x - d2.x);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        selected = new Dir[M];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if(n == 1) house.add(new Dir(i, j));
                else if(n == 2) chicken.add(new Dir(i, j));
            }
        }

        comb(0, 0);
        System.out.println(answer);
    }
}
```



### :chicken: 풀이 방법

조합을 만들어서 그거갖고 문제에서 요구하는 답을 구하는 문제입니다.

2차원 배열이 입력으로 주어지지만 좌표값을 담은 리스트들로 표현해서 풀 수 있답니다.

 

가장 먼저 입력받을 때 house 리스트랑 chicken 리스트에 각각 위치값을 넣어줍니다.

그리고 치킨집 중에서 M개를 선택합니다. 순서에 상관없이 골라주면 되니까 순열이 아니라 조합인거져

 

다 뽑으면 뽑힌 치킨집을 갖고 **도시의 치킨 거리**를 구해줍니다.

구한 **도시의 치킨 커리**를 받아오면서 answer를 업뎃해주면 끝입니다.

 

 

5개월 전에 풀었을 때는 2차원 배열에서 2(치킨집)을 0으로 바꿔가면서 풀었는데 정말 쓸데없는 짓이었습니다..

그리고 순열/조합 개념도 제대로 안잡혀있어서 중복된 값을 많이 체크했었네여



### :chicken: 후기

오늘도 어제보다 조금 더 성장한게 느껴져서 기부니가 좋슴니다 ^_^