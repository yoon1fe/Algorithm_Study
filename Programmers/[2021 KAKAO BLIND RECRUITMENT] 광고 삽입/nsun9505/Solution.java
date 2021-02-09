package 광고삽입;

import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Solution {
	public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        if(play_time.equals(adv_time))
        	return "00:00:00";
        
        int play_time_sec = (int) strToIntTime(play_time);
        int adv_time_sec = (int) strToIntTime(adv_time);
        int[] logs_start_sec = new int[logs.length];
        int[] logs_end_sec = new int[logs.length];
        long[] total_time = new long[100*60*60+1];
        for(int i=0; i<logs.length; i++) {
        	StringTokenizer st = new StringTokenizer(logs[i], "-");
        	logs_start_sec[i] = (int)strToIntTime(st.nextToken());
        	logs_end_sec[i] = (int)strToIntTime(st.nextToken());
        }
        
        for(int i=0; i<logs.length; i++) {
        	total_time[logs_start_sec[i]] += 1; // 시작 +1
        	total_time[logs_end_sec[i]] -= 1;   // 끝에 -1
        }

        // 재생 구간 체크
        for(int i=1; i<play_time_sec; i++) {
        	if(total_time[i] == 1 || total_time[i] == -1)
        		System.out.println("-");
        	total_time[i] += total_time[i-1];
        }
        
        // 누적 재생시간을 구함.
        for(int i=1; i<play_time_sec; i++) 
        	total_time[i] += total_time[i-1];
        
        long max_time = 0;
        long answer_time = 0;
        for(int i=adv_time_sec-1; i<play_time_sec; i++) {
        	if(i >= adv_time_sec) {
        		if(max_time < total_time[i] - total_time[i-adv_time_sec]) {
        			max_time = total_time[i] - total_time[i-adv_time_sec];
        			answer_time = i - adv_time_sec + 1;
        		}
        	}
        	else {
        		max_time = Math.max(max_time, total_time[i]);
        		if(max_time < total_time[i]) {
        			answer_time = i;
        			max_time = total_time[i];
        		}
        		
        	}
        }
        
        long hour = answer_time / 3600;
        answer_time %= 3600;
        answer += String.valueOf(hour) +":";
        if(hour < 10)
        	answer = "0" + answer;
        
        long min = answer_time / 60;
        answer_time %= 60;
        if(min < 10)
        	answer += "0";
        answer += String.valueOf(min) + ":";
        
        if(answer_time < 10)
        	answer += "0";
        answer += String.valueOf(answer_time);
        return answer;
    }
	
	public static long strToIntTime(String str) {
		StringTokenizer st = new StringTokenizer(str, ":");
		long ret = Long.parseLong(st.nextToken()) * 3600;
		ret += Long.parseLong(st.nextToken()) * 60;
		ret += Long.parseLong(st.nextToken());
		return ret;
	}
}