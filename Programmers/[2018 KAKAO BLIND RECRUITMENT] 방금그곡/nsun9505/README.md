# [2018 KAKAO BLIND RECRUITMENT] 방금그곡 - JAVA

## 분류
> 구현

## 코드
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
   static class Music implements Comparable<Music>{
		int index;
		int playTime;
		String title;
		ArrayList<String> sheetMusic;		// 악보가 영어로 Sheet Music 이랍니다..ㅋㅋ
		
		Music(int idx, int playTime, String title, ArrayList<String> sheetMusic){
			this.index = idx;
			this.playTime = playTime;
			this.title = title;
			this.sheetMusic = sheetMusic;
		}

		@Override
		public int compareTo(Music m) {
			if(this.playTime < m.playTime)
				return 1;
			else if(this.playTime == m.playTime)
				if(this.index > m.index)
					return 1;
			return -1;
		}
	}
	
	public String solution(String m, String[] musicinfos) {
        String answer = "";
        Music[] musics = new Music[musicinfos.length];
        
        for(int i=0; i<musicinfos.length; i++) {
        	StringTokenizer st = new StringTokenizer(musicinfos[i], ",");
        	int start = transMin(st.nextToken());
        	int end = transMin(st.nextToken());
        	String title = st.nextToken();
        	String sheet = st.nextToken();
        	ArrayList<String> newSheet = getSheetMusic(end - start, sheet);
        	musics[i] = new Music(i, end - start, title, newSheet);
        }
        
        Arrays.sort(musics);
        
        ArrayList<String> newM = getSheetMusic(0, m);
        answer = "(None)";
        for(int i=0; i<musics.length; i++) {
        	ArrayList<String> sheetMusic = musics[i].sheetMusic;
        	if(isAnswer(sheetMusic, newM)) {
        		answer = musics[i].title;
        		break;
        	}
        		
        }
        return answer;
    }
	
	public static boolean isAnswer(ArrayList<String> sheetMusic, ArrayList<String> m) {
		for(int i=0; i + m.size() - 1 < sheetMusic.size(); i++) {
			int cnt = 0;
    		for(int j=0; j<m.size(); j++) {
    			if(m.get(j).equals(sheetMusic.get(i+j)))
    				cnt++;
    			else
    				break;
    		}
    		if(cnt == m.size())
    			return true;
    	}
		return false;
	}
	
	public static int transMin(String time) {
		int ret = 0;
		StringTokenizer st = new StringTokenizer(time, ":");
		ret = Integer.parseInt(st.nextToken()) * 60;
		ret += Integer.parseInt(st.nextToken());
		return ret;
	}
	
	public static ArrayList<String> getSheetMusic(int playTime, String sheet) {
		ArrayList<String> list = new ArrayList<>();
		int idx = 0;
		for(int i=0; i<sheet.length(); i++) {
			char ch = sheet.charAt(i);
			if(ch == '#')
				list.set(idx-1, list.get(idx-1) + "#");
			else {
				list.add(String.valueOf(ch));
				idx++;
			}
		}
        if(playTime == 0)
			return list;
        
		ArrayList<String> ret = new ArrayList<>();
		for(int i=0; i<playTime/list.size(); i++)
			for(String str : list)
				ret.add(str);
		for(int i=0; i<playTime%list.size(); i++)
			ret.add(list.get(i));
		
		return ret;
	}
}
```

## 문제 풀이
1. musicinfos의 각 문자열을 ","로 파싱해서 Music으로 저장합니다.
- 시작 시간과 끝 시간을 분단위로 변경해서 플레이 시간을 구합니다.(transMin 메서드)
- 악보 정보는 플레이 시간에 따라 반복이 될 수 있으므로 getSheetMusic를 통해 재생시간 동안 나온 악보를 ArrayList로 저장해서 받아옵니다.

1. musics에 저장된 Music을 먼저 재생시간 순으로 오름차순 정렬하고, 재생시간이 같으면 먼저 들어온 순으로 정렬

1. 네오가 기억하는 멜로디 m도 음 단위로 나누어서 ArrayList로 변경

1. 마지막으로 정렬된 musics에서 m과 동일한 멜로디가 있는지 검사(isAnswer) 
- 동일한 멜로디가 있다면 해당 Music의 title을 answer에 담아서 for문을 종료하면 된다.
- 없다면 for문을 돌기 전에 이미 "None"을 넣었으므로 answer를 리턴하면 된다.

## 후기
음 단위로 어떻게 나눌까 하다가 음단위를 String으로 하고 전체 악보 정보를 ArrayLisy로 표현!

처음에는 모르고 그냥 String으로 다 했는데, 바로 틀려버림!ㅋㅋ

코드가 깁니다 좀..ㅎ