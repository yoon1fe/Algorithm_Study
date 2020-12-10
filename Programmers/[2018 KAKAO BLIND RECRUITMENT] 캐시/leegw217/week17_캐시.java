import java.util.LinkedList;
import java.util.Queue;
public class week17_캐시 {
	public int solution(int cacheSize, String[] cities) {
		if(cacheSize == 0)  return cities.length * 5;
        int answer = 0;
        Queue<String> q = new LinkedList<String>();
        for(int i=0; i<cities.length; i++) {
        	String city = cities[i].toLowerCase();
        	// cache hit
        	if(q.contains(city)) {
        		q.remove(city);
        		q.offer(city);
        		answer += 1;
        	} else {
        		if(q.size() < cacheSize) {
        			q.offer(city);
        			answer += 5;
        		} else {
        			q.poll();
        			q.offer(city);
        			answer += 5;
        		}
        	}
        }
        return answer;
    }
}