import java.util.PriorityQueue;
public class week17_셔틀버스 {
	class Time implements Comparable<Time> {
		String ts;
		int minute;
		Time(String ts) {
			this.ts = ts;
			this.minute = calcMinute(ts);
		}
		int calcMinute(String t) {
			String[] times = t.split(":");
			int hour = Integer.parseInt(times[0]);
			int minute = Integer.parseInt(times[1]);
			return hour*60 + minute;
		}
		@Override
		public int compareTo(Time o) {
			return this.minute - o.minute;
		}
	}
	
	public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        PriorityQueue<Time> times = new PriorityQueue<Time>();
        for(String s : timetable) times.offer(new Time(s));
        int lastTime = 0;
        // 셔틀 운행 횟수
        for(int i=0; i<n; i++) {
        	int curTime = 9*60 + i*t;
        	int curM = m;
        	// 셔틀에 크루 태우기
        	while(!times.isEmpty()) {
        		if(times.peek().minute <= curTime) {
        			if(curM == 0) break;
        			lastTime = times.poll().minute;
        			curM--;
        		} else break;
        	}
        	// 막차일때
        	if(i == n-1) {
        		if(curM != 0) { // 더 탈자리가 남아있으면 출발시간에 타기
        			if(curTime/60 < 10) answer = "0";
        			answer += curTime/60+":";
        			if(curTime%60 < 10) answer += "0";
        			answer += curTime%60;
        		} else { // 더 탈자리가 없으면 마지막에 탄사람보다 1분 빨리 타기
        			if((lastTime-1)/60 < 10) answer = "0";
        			answer += (lastTime-1)/60+":";
        			if((lastTime-1)%60 < 10) answer += "0";
        			answer += (lastTime-1)%60;
        		}
        	}
        }
        return answer;
    }
}