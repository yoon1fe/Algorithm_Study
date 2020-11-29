package Programmers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Solution {
	public int solution(String[] lines) throws ParseException {
        int answer = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        Date[] start = new Date[lines.length];
        Date[] end = new Date[lines.length];
        
        // 시작 - 끝 시간 구하기 
        for(int i = 0; i < lines.length; i++) {
        	String[] time = lines[i].split(" ");
        	int throughput = (int)(Double.parseDouble(time[2].substring(0, time[2].length() - 1)) * -1000);
        	
        	end[i] = sdf.parse(time[1]);
        	start[i] = getMillisecond(end[i], throughput + 1);
        }
        
        // 1초전 ~ 끝 구간에 포함된 개수 구하기
        for(int i = 0; i < lines.length; i++) {
        	int cnt = 0; 
        	long e = end[i].getTime();
        	long term = getMillisecond(end[i], 1000).getTime();	// 끝나는 시간으로부터 1초 후

        	for(int j = 0; j < lines.length; j++) {
        		if((term <= start[j].getTime()) || (end[j].getTime() < e)) continue;

        		cnt++;
        	}
        	
        	answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
	
	public Date getMillisecond(Date date, int t) {
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MILLISECOND, t);

        return c.getTime();
	}

	public static void main(String[] args) throws ParseException {
		Solution s = new Solution();
		
		String[] l = {
//		"2016-09-15 20:59:57.421 0.351s",
//		"2016-09-15 20:59:58.233 1.181s",
//		"2016-09-15 20:59:58.299 0.8s",
//		"2016-09-15 20:59:58.688 1.041s",
//		"2016-09-15 20:59:59.591 1.412s",
//		"2016-09-15 21:00:00.464 1.466s",
//		"2016-09-15 21:00:00.741 1.581s",
//		"2016-09-15 21:00:00.748 2.31s",
//		"2016-09-15 21:00:00.966 0.381s",
//		"2016-09-15 21:00:02.066 2.62s"};
				
//				"2016-09-15 01:00:04.002 2.0s",
//				"2016-09-15 01:00:07.000 2s"};
				
				"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
		System.out.println(s.solution(l));
		
	}
}