class Solution {
    public String solution(int n) {
        int[] arr = {4, 1, 2};
        String answer = "";
        while(n!=0){
            int idx = n % 3;
            answer = String.valueOf(arr[idx]) + answer;
            if(idx == 0)
                n -= 1;
            n/=3;
        }
        return answer;
    }
}