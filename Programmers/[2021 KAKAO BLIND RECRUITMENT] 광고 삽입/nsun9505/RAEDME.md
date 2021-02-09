# [2021 KAKAO BLIND RECRUITMENT] 광고 삽입 - JAVA

## 분류
> 구현

## 코드
```java
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
        	total_time[logs_start_sec[i]] += 1; // 시작은 +1
        	total_time[logs_end_sec[i]] -= 1;   // 끝에 -1
        }
        
        // 1초 구간에 몇 개가 재생되고 있는지
        for(int i=1; i<play_time_sec; i++) 
        	total_time[i] += total_time[i-1];
        
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
```

## 문제풀이
구간을 표시하기 위해서 전체 시간을 의미하는 total_time을 하나 만듭니다.
   - 전체 구간은 00:00:00 ~ 99:59:59 이므로 100 * 3600으로 100시간짜리 배열을 하나 잡습니다.

각 로그의 구간의 시작과 끝에 +1, -1을 기록합니다.
   - 시작 부분에 +1, 끝나는 부분에 -1을 합니다.
   - 왜냐하면 +1한 부분에서 total_time[i] += total_time[i-1]로 쭉 이어나가면 -1을 만나기 전까지(재생기간 끝부분) 1로 채워지게 될 것입니다.
   - 재생 시간 중간에 또다른 재생시간이 있어도 +1을 해서 해당 i ~ i+1초 사이에 2개가 있다는 것을 표현할 수 있습니다.
   - 그리고 끝나는 부분에서 다시 -1을 하므로 1씩 기록됩니다.

이제 재생 구간을 체크합니다.
   - 어디서 시작하고 어디서 끝나는지를 체크했으므로
   - total_time[i] += total_time[i-1]로 구간에 몇 개의 재생 구간이 있는지 체크합니다.

i~i+1 사이에 재생 되는 것들을 체크했으므로 이제 누적합을 구하면 됩니다.
   - 누적합은 위의 for문과 동일하게 돌면 됩니다.
   - 누적합은 이전에 것이랑 현재 것을 더해주면 i초일떄의 누적합을 쉽게 구할 수 있습니다.

마지막으로 광고 재생 시간부터 전체 시간 사이에서 가장 누적합이 높은 구간을 찾습니다.
   - 그리고 그 구간의 시간을 구하면 됩니다.
   - 예를 들어 i가 광고 재생 시간부터 시작하고 1씩 늘어나면 다음과 같은 구간을 찾으면 된다. 
   - [i - (광고재생시간) + 1 ~ i] 구간 누적합이 가장 큰 것

## 후기
와우 끝에 표시를 하고 하는거는 정말 몰랐습니다..

이분탐색인가 어떻게 구하지 하다가 카카오 해설보고 풀었습니다!