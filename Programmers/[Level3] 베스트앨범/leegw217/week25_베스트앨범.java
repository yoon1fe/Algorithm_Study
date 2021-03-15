import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class week25_베스트앨범 {
	public int[] solution(String[] genres, int[] plays) {
        List<Integer> ans = new ArrayList<Integer>();
        Map<String, Integer> genreCnt = new HashMap<String, Integer>();
        Map<String, PriorityQueue<int[]>> playCnt = new HashMap<String, PriorityQueue<int[]>>();
        
        for(int i=0; i<genres.length; i++) {
        	if(!genreCnt.containsKey(genres[i])) genreCnt.put(genres[i], plays[i]);
        	else genreCnt.put(genres[i], genreCnt.get(genres[i]) + plays[i]);
        	
        	if(!playCnt.containsKey(genres[i])) {
        		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						if(o1[1] == o2[1]) return o1[0] - o2[0];
						else return o2[1] - o1[1];
					}
        		});
        		playCnt.put(genres[i], pq);
        	} 
        	playCnt.get(genres[i]).offer(new int[] {i, plays[i]});
        }
        
        List<Entry<String, Integer>> entries = new ArrayList<Map.Entry<String,Integer>>(genreCnt.entrySet());
        Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
        
        for(Entry<String, Integer> entry : entries) {
        	int size = 0;
        	if(playCnt.get(entry.getKey()).size() >= 2) size = 2;
        	else size = 1;
        	for(int i=0; i<size; i++) 
        		ans.add(playCnt.get(entry.getKey()).poll()[0]);        		
        }
        int[] answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++) answer[i] = ans.get(i);
        return answer;
    }
}