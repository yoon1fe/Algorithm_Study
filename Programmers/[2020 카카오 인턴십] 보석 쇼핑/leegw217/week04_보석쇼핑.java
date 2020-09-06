import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class week04_보석쇼핑 {
	public int[] solution(String[] gems) {
        HashMap<String, Integer> gem = new HashMap<String, Integer>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]-o1[0] == o2[1]-o2[0]) 
					return o1[0] - o2[0];
				else
					return (o1[1]-o1[0]) - (o2[1]-o2[0]);
			}
		});
        for(int i=0; i<gems.length; i++) gem.put(gems[i],0);
        int startIdx = 0, endIdx = 0;
        while(true) { // 처음 보석 다 찾는 경우
        	if(gem.containsValue(0)) {
        		gem.put(gems[endIdx], gem.get(gems[endIdx])+1);
        		endIdx++;
        	}
        	if(!gem.containsValue(0)) {
        		pq.add(new int[] {startIdx+1, endIdx--});
        		break;
        	}
        }       
        int cnt = gem.size();
        while(true) { // 가장 짧고 시작 idx가 빠른 부분 찾기
        	if(cnt == gem.size()) { // 찾았다!
        		pq.add(new int[] {startIdx+1,endIdx+1});
        		gem.put(gems[startIdx], gem.get(gems[startIdx])-1);
        		if(gem.get(gems[startIdx]) == 0) cnt--;
        		startIdx++;
        	} else { // 못찾았다!
        		endIdx++;
        		if(endIdx==gems.length) break;
        		if(gem.get(gems[endIdx]) == 0) cnt++;
        		gem.put(gems[endIdx], gem.get(gems[endIdx])+1);
        	}
        }
        return pq.poll();
    }
}