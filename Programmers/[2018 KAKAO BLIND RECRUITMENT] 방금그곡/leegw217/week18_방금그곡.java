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