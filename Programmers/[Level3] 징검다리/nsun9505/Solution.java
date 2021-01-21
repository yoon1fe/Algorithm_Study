import java.util.Arrays;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);
        int[] sortedRocks = new int[rocks.length+2];
        sortedRocks[0] = 0;
        sortedRocks[rocks.length+1] = distance;
        for(int i=1; i<=rocks.length; i++)
            sortedRocks[i] = rocks[i-1];

        int left = 1;
        int right = distance;
        while(left <= right){
            int mid = (left + right) / 2;
            int cnt = 0;
            int start = 0;
            for(int i=1; i<sortedRocks.length; i++){
                if(sortedRocks[start] + mid > sortedRocks[i])
                    cnt++;
                else{
                    start = i;
                }
            }
            if(cnt <= n){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}