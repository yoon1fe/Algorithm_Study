package Programmers;

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

	public static void main(String[] args) {
		Solution s = new Solution();

//		String[] t = {"09:10", "09:09", "08:00"};
//		String[] t = {"09:00", "09:00", "09:00", "09:00"};
		String[] t = { "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59",
				"23:59", "23:59", "23:59", "23:59", "23:59", "23:59" };
//		System.out.println(s.solution(1, 1, 1, t));

		System.out.println(s.solution(10, 60, 45, t));
	}
}