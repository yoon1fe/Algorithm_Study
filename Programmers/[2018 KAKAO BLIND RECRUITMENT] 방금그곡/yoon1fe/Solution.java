package Programmers;

import java.util.*;

class Solution {
	static class Music implements Comparable<Music> {
		int no, time;
		// 제목, 악보, time동안 진행되는 악보
		String title, score, scoreLine;
		
		public int compareTo(Music m) {
			if(this.time == m.time) return this.no - m.no;
			return m.time - this.time;
		}
	}
	
	public String solution(String m, String[] musicinfos) {
		PriorityQueue<Music> pq = new PriorityQueue<>();
		
		m = replaceSharp(m);
        
        for(int i = 0; i < musicinfos.length; i++) {
        	Music music = getMusic(musicinfos[i], i);
        	
        	if(music.scoreLine.contains(m)) {
        		pq.offer(music);
        	}
        }

        return pq.isEmpty() == false ? pq.peek().title : "(None)";
    }

	public Music getMusic(String mi, int no) {
		Music music = new Music();
		String[] temp = mi.split(",");
		
		music.no = no;
		music.time = getTime(temp[0], temp[1]);
		music.title = temp[2];
		music.score = replaceSharp(temp[3]);
		music.scoreLine = getLine(music.time, music.score);
		
		return music;
	}
	
	public String replaceSharp(String str) {
		str = str.replace("C#", "H");
		str = str.replace("D#", "I");
		str = str.replace("F#", "J");
		str = str.replace("G#", "K");
		str = str.replace("A#", "L");
		
		return str;
	}

	public String getLine(int time, String score) {
		StringBuilder sb = new StringBuilder();
		int n = time / score.length();
		
		while(n-- > 0) {
			sb.append(score);
		}
		sb.append(score.substring(0, time % score.length()));
		
		return sb.toString();
	}

	public int getTime(String str1, String str2) {
		String[] start = str1.split(":");
		String[] end = str2.split(":");
		
		return (Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1])) - (Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]));
	}

	public static void main(String[] args) {
		Solution s = new Solution();
//		String m = "ABCDEFG";
		String m = "ABC";
//		String[] mi = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		String[] mi = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		System.out.println(s.solution(m, mi));
		
	}
}