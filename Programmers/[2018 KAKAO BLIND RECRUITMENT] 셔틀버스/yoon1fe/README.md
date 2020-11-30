## [2018 KAKAO BLIND RECRUITMENT] 셔틀버스 - Java

###    :bus: ​분류

> 구현



###  :bus: 코드

```java
import java.util.*;

class Solution {
	static class Time implements Comparable<Time> {
		int hours, minute;

		// timetable 파싱
		Time(String time) {
			this.hours = Integer.parseInt(time.substring(0, 2));
			this.minute = Integer.parseInt(time.substring(3, 5));
		}

		// 시간 계산 편하게 하기 위해 분(minute)로 처리
		Time(int min) {
			this.hours = min / 60;
			this.minute = min % 60;
		}

		@Override
		public int compareTo(Time o) {
			if (this.hours == o.hours) return this.minute - o.minute;
			return this.hours - o.hours;
		}
	}

	public String solution(int n, int t, int m, String[] timetable) {
		Time answer = new Time(540); // 09:00
		PriorityQueue<Time> pq = new PriorityQueue<>();

		for (int i = 0; i < timetable.length; i++) {
			Time time = new Time(timetable[i]);
			pq.offer(time);
		}

		// startTime 기준
		for (int i = 0; i < n; i++) {
			Time curTime = new Time(540 + (i * t));
			int people = 0;

			// 버스 별로 탑승할 수 있는 인원수
			for (int j = 0; j < m; j++) {
				if (!pq.isEmpty()) {
					Time curPerson = pq.peek();
					if (curTime.compareTo(curPerson) >= 0) { // 버스 도착 시각 > 크루 도착 시각 (탈 수 있음)
						people++;
						answer = pq.poll();
					}
				}

				// 마지막 버스 체크
				if (i == n - 1 && people < m) {
					answer = curTime;
				} else if (i == n - 1 && people == m) {
					answer = new Time(answer.hours * 60 + answer.minute - 1);
				}
			}
		}

		return String.format("%02d:%02d", answer.hours, answer.minute);
	}
}
```



### :bus: ​풀이 방법

마찬가지로 2018 카카오 블라인드 문제입니다.

문제 이해하는데 꽤 걸려씀니다 ^^;;;;;;;;;;;;;

특정한 알고리즘을 요하진 않고.. 문제에 주어진대로 구현을 잘 하면 됩니다.



먼저 시간은 Time 이란 클래스를 만들어서 다루었습니당. 그리고 애석하게더 timetable 이 정렬된 채로 들어오질 않아서 우선순위 큐를 써서 정렬했습니다 ㅎㅎ.

 

이 문제는 사실 **콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각**을 구해야 하기 때문에 맨 마지막 버스만 체크하면 됩니다!!

 

경우는 두 가지가 있슴니다

1. 마지막 버스가 다 찼을 때 - 맨 마지막 사람보다 1분 더 일찍 오면 됩니다.
2. 마지막 버스가 덜 찼을 때 - 버스가 도착하는 시각에 오면 됩니다.

 



###  :bus: 후기 

화이팅!!!

감사합니다!!