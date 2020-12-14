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

        /* 
            나누기 귀찮아서 기능 두 개를 하나에 넣음..ㅎ 
            원래는 위 아래가 다른 기능으로 나누어져서 구현되어야 함..ㅠ
        */

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