# [2019 KAKAO BLIND RECRUITMENT] 실패율 - JAVA

## 분류
> 구현
> 
> 최대공약수

## 코드
```java
import java.util.Arrays;

public class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        Stage[] gameStages = new Stage[N+2];
        for(int i=0; i<=N+1; i++)
            gameStages[i] = new Stage(0, 0, i);

        for(int i=0; i<stages.length; i++){
            for(int j=1; j<=stages[i]; j++)
                gameStages[j].totalOfArrive += 1;
            gameStages[stages[i]].numOfNotClear += 1;
        }

        for(int i=1; i<gameStages.length; i++){
            int a = gameStages[i].numOfNotClear;
            int b = gameStages[i].totalOfArrive;

            if(a < b){
                int tmp = a;
                a = b;
                b = tmp;
            }

            int num = gcd(a, b);
            if(gameStages[i].numOfNotClear != 0)
                gameStages[i].numOfNotClear /= num;
            if(gameStages[i].totalOfArrive != 0)
                gameStages[i].totalOfArrive /= num;
            gameStages[i].calcFailRate();
        }

        Arrays.sort(gameStages, (o1, o2) -> {
            if(o1.failRate < o2.failRate)
                return 1;
            else if(o1.failRate == o2.failRate)
                return o1.stageIdx - o2.stageIdx;
            return -1;
        });

        answer = new int[N];
        int idx=0;
        for(Stage stage : gameStages) {
            if(stage.stageIdx == N+1 || stage.stageIdx == 0)
                continue;
            answer[idx++] = stage.stageIdx;
        }

        return answer;
    }

    public int gcd(int a, int b){
        if(b == 0)
            return a;
        return gcd(b, a%b);
    }

    static class Stage{
        int numOfNotClear;
        int totalOfArrive;
        int stageIdx;
        double failRate;

        public Stage(int numOfNotClear, int totalOfArrive, int idx) {
            this.numOfNotClear = numOfNotClear;
            this.totalOfArrive = totalOfArrive;
            this.stageIdx = idx;
            this.failRate = 0.0;
        }

        public void calcFailRate(){
            if(this.totalOfArrive == 0 || this.numOfNotClear == 0)
                return;
            this.failRate = (double)this.numOfNotClear / this.totalOfArrive;
        }
    }
}
```

## 문제 풀이
최대공약수로 분수를 기약분수로 만든 후에 실패율을 계산했습니다.

그냥 계산하면 이상하게 안 나와서 처음에는 분모, 분자 비교했다가

1/2, 2/4 같은 경우가 있어서 최대공약수(gcd) 함수 만들어서 실패율을 계산하고 정렬했습니다.

stages 배열에서 0부터 stages[i]까지 stage에 도달한 사람들을 카운트합니다.

그리고 stages[i]번째 스테이지는 아직 풀지 못한 사람을 카운트해줍니다.

주어진 stages를 다 돌고나면, 이제 실패율을 계산합니다.

실패율을 계산할 떄는 도달한 사람의 수와 아직 해결중인 사람의 최대공약수를 구해서 각각 나눠줍니다.

그리고 `아직 해결 중인 사람 / 도달한 사람의 수`를 계산합니다.

최대공약수로 안 나누고 계산하니 테스트케이스에서 틀렸다는 것이 있었습니다. 

그래서 분모, 분자를 각각 비교해서 할려고 했는데, 위에서 말했다시피 1/2, 2/4와 같은 경우가 있기 때문에 최대 공약수로 나눠보자라는 아이디어가 나왔습니다.

그래서 gcd 함수를 사용해서 두 수의 최대 공약수를 구하고 나누고, 실패율을 계산했습니다.

마지막으로 실패율에 대해서는 내림차순, 실패율이 같다면 스테이지 번호에 대해서는 오름차순으로 정렬하면 끝!

## 후기
처음에는 쉬운 문제인줄 알았지만, 최대 공약수를 사용한다는 아이디어는 1/2, 2/4를 생각하기 전까지는 어려웠습니다.

그리고 그냥 최대공약수 이런거 없이 계산하면 될 줄 알았습니다.

앞으로 무슨 XXX율, XXX률을 구할 때는 기약분수로 만들어서 구하기를 머리에 새겨 넣었습니다.