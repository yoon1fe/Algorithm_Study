# [2018 KAKAO BLIND RECRUITMENT] 방금 그곡 - Java

###  :octocat: 분류

> 구현

### :octocat: 코드

```java
import java.util.Arrays;
public class week18_방금그곡 {
	class musicInfo implements Comparable<musicInfo> {
		int time;
		String title;
		String music;
		
		musicInfo(int time) {
			this.time = time;
		}

		@Override
		public int compareTo(musicInfo o) {
			return o.time - this.time;
		}
	}
	
	String translate(char[] l) {
		StringBuilder sb = new StringBuilder();
        for(int i=0; i<l.length; i++) {
        	if(l[i] != '#') sb.append(l[i]);
        	else {
        		char ch = sb.charAt(sb.length()-1);
        		sb.deleteCharAt(sb.length()-1);
        		char t = ch=='A'?'a':ch=='C'?'c':ch=='D'?'d':ch=='F'?'f':'g';
        		sb.append(t);
        	}
        }
        return sb.toString();
	}
	
	public String solution(String m, String[] musicinfos) {
        String answer = "";
        int n = musicinfos.length;
        musicInfo[] musicInfos = new musicInfo[n];
        char[] l = m.toCharArray();
        String listen = translate(l);
        for(int i=0; i<n; i++) {
        	String[] str = musicinfos[i].split(",");
        	String[] start = str[0].split(":");
        	String[] end = str[1].split(":");
        	int s = Integer.parseInt(start[0])*60 + Integer.parseInt(start[1]);
        	int e = Integer.parseInt(end[0])*60 + Integer.parseInt(end[1]);
        	musicInfos[i] = new musicInfo(e-s);
        	musicInfos[i].title = str[2];
        	char[] lt = str[3].toCharArray();
        	String mu = translate(lt);
        	if(musicInfos[i].time <= mu.length()) musicInfos[i].music = mu.substring(0, musicInfos[i].time);
        	else {
        		String music = "";
        		int limit = musicInfos[i].time / mu.length();
        		for(int k=0; k<limit; k++) music += mu;
        		limit = musicInfos[i].time % mu.length();
        		music += mu.substring(0, limit);
        		musicInfos[i].music = music;
        	}
        }        
        Arrays.sort(musicInfos);
        for(int i=0; i<n; i++){
        	if(musicInfos[i].music.contains(listen)) {
        		answer = musicInfos[i].title;
        		break;
        	}
        }
        if(answer.equals("")) answer = "`(None)`";
        return answer;
    }
}
```

### :octocat: 풀이 방법

0. 재생시간과 음악 제목, 악보를 담은 클래스를 만든다.
1. 입력받은 문자열 m과 악보정보 중 #이 붙은 음은 소문자로 치환한다.
2. 음악이 재생된 시간은 분으로 치환한다. 만약 음악 재생 시간이 음악 총 시간보다 짧으면 해당 부분만 악보에 넣고
길면 음악을 반복재생해서 악보에 넣는다.
3. 음악 재생시간이 긴 순으로 정렬해서 악보에 문자열 m이 있는지 검사해서 있으면 정답으로 출력
없으면 (None) 출력한다.

### :octocat: 후기

#을 어떻게 처리할까 고민했는데 기술블로그에 다른 문자로 치환해서 하는 방법이 적혀있어서 그걸로 풀었다!
