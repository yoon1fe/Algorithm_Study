class Solution {
    static int cnt = 0;
    public int solution(int[] numbers, int target) {
        DFS(numbers, target, 0, 0);
        return cnt;
    }
    
    public static void DFS(int[] numbers, int target, int idx, int sum){
        if(numbers.length <= idx){
            if(sum == target)
                cnt++;
            return;
        }
        
        DFS(numbers, target, idx+1, sum + numbers[idx]);
        DFS(numbers, target, idx+1, sum - numbers[idx]);
    }
}