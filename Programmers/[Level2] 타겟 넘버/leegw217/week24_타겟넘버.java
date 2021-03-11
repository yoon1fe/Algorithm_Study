import java.util.ArrayList;
import java.util.List;

public class week24_타겟넘버 {
	List<Integer> answer;
	
	void calc(int[] numbers, int a, int b, int idx, int tar) {
		if(idx < numbers.length) {
			calc(numbers, a+b, numbers[idx], idx+1, tar);
			calc(numbers, a-b, numbers[idx], idx+1, tar);
		} else {
			if(a+b == tar) answer.add(a+b);
			else if(a-b == tar) answer.add(a-b);
		}
	}
	
	public int solution(int[] numbers, int target) {
        answer = new ArrayList<Integer>();
        calc(numbers, 0, numbers[0], 1, target);
        return answer.size();
    }
}