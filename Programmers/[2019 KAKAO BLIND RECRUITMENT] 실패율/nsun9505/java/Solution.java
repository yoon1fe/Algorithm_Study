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