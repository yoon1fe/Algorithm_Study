import java.util.Arrays;
public class week20_징검다리건너기 {
	public int solution(int[] stones, int k) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for(int i=0; i<stones.length; i++) {
        	left = Math.min(left, stones[i]);
        	right = Math.max(right, stones[i]);
        }
        if(k == 1) return left;
        loop:
        while(true) {
        	int mid = (left + right) / 2;
        	if(mid == left) break;
        	int[] temp = Arrays.copyOf(stones, stones.length);
        	for(int i=0; i<temp.length; i++) {
        		if(temp[i] - mid <= 0) temp[i] = 0;
        		else temp[i] = temp[i] - mid;
        	}
        	int kcnt = 0;
        	for(int i=0; i<temp.length; i++) {
        		if(temp[i] == 0) kcnt++;
        		else kcnt = 0;
        		if(kcnt == k) {
        			right = mid;
        			continue loop;
        		}
        	}
        	left = mid;
        }
        return right;
    }
}