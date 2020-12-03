## [2018 KAKAO BLIND RECRUITMENT] 방금그곡 - Java

###    :musical_score: ​분류

> 구현
>
> 문자열 처리

​

###  :musical_score: 코드

```java
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
}
```



### :musical_score: ​풀이 방법

이제 2018 카카오 3차 문제입니다. 

특별한 알고리즘은 필요없고 문자열 처리랑 아주 쬐끄만 아이디어를 갖고 쉽게 풀 수 있는 문제랍니다.



Music 이란 클래스를 선언해서 써먹었습니다. 문제의 조건 중에

- 조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환한다. 재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.

요 부분 때문에 우선순위 큐를 써먹어야겠다 생각했꼬, 가장 먼저 입력된 음악을 추리기 위해 음악 번호(no)도 추가했습니다.

 

그리고 단순히 m이 재생 시간동안 연주되는 악보(scoreLine)에 포함되는지를 보기 때문에 #이 붙은 친구들을 길이 1짜리 다른 알파벳으로 치환해서 풀었습니당.

 

로직은 간단합니다.

1. musicinfos 배열을 돌면서 Music 인스턴스를 이쁘게 만들어줍니다.
2. scoreLine을 구해서 m이 scoreLine에 포함되어 있으면 pq에 넣습니다.
3. 조건에 해당하는 음악 존재 여부에 따라 출력해야 하는 값이 다르므로 pq가 비어있는지 확인 후 root에 있는 값의 제목을 리턴하면 끝!

**!!!!! 조건에 해당하는 음악이 없으면 "(None)"을 출력해야 됩니다!! "`(None)`" 요따구로 적으면 안됩니다 ㅜㅜ !!!!!**

 



###  :musical_score: 후기 

코드는 쪼끔 긴데 나름 이쁘다고 생각합니다 ㅎ.ㅎ

감사합니다!!!