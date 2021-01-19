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