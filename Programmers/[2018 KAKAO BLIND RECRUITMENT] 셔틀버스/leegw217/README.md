# [2018 KAKAO BLIND RECRUITMENT] 셔틀버스 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
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
```

### :octocat: 풀이 방법

1. 입력받은 문자열을 분 단위로 치환하고 오름차순으로 정렬가능한 class를 만든다.
2. 가장 늦게 도착할 수 있는 시간을 찾아야하므로 가장 마지막차에 타야한다.
3. 각 셔틀버스 별로 태울 수 있는 사람들을 다 태운다.
4. 가장 마지막 셔틀버스에 남은 사람들 중 태울 수 있는 사람들을 다 태우고 만약 더 태울자리가 남아있으면
셔틀 출발 시간에 탄다.
5. 더 태울 자리가 없으면 가장 마지막으로 탄 사람보다 1분 빨리 탄다.

### :octocat: 후기

처음에는 문제보고 이해 못해서 어려운거같았는데 생각해보니 마지막차만 타면 되니까 엄청 쉬워졌다.
확실히 추석트래픽으로 혼쭐나고 푸니까 힐링되네...
