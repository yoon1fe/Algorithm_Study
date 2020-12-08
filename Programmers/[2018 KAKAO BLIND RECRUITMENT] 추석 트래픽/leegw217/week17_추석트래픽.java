import java.util.Arrays;
import java.util.Comparator;
public class week17_추석트래픽 {
	int translate(String t) {
		String[] times = t.split(":");
    	int hour = Integer.parseInt(times[0]);
    	int minute = Integer.parseInt(times[1]);
    	int second = Math.round(Float.parseFloat(times[2])*1000);
    	int time = hour*60*60*1000 + minute*60*1000 + second;
    	return time;
	}
	
	public int solution(String[] lines) {
        int answer = 0;
        int[][] times = new int[lines.length][2];
        for(int i=0; i<lines.length; i++) {
        	times[i][1] = translate(lines[i].split(" ")[1]);
        	String sec = lines[i].split(" ")[2].replace("s", "");
        	times[i][0] = times[i][1] - (int)(Float.parseFloat(sec)*1000)+1;
        }
        
        Arrays.sort(times, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] - o2[1] == 0)
					return o1[0] - o2[0];
				return o1[1] - o2[1];
			}
		});
        
        for(int idx=0; idx<lines.length; idx++) {
    		int startCnt = 0;
    		int endCnt = 0;
        	int start = times[idx][0];
        	int end = times[idx][1];
        	for(int i=0; i<lines.length; i++) {
        		if(start <= times[i][1] && start+999 >= times[i][0])
        			startCnt++;
        		if(end <= times[i][1] && end+999 >= times[i][0])
        			endCnt++;
        	}
        	answer = Math.max(answer, startCnt);
        	answer = Math.max(answer, endCnt);
        }
        return answer;
    }
}