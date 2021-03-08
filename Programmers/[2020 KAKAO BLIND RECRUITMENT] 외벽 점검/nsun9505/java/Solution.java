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