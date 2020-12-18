import java.util.Arrays;
public class week19_파일명정렬 {
	class File implements Comparable<File> {
		String title;
		String head;
		int number;
		File(String title) {
			this.title = title;
			translate();
		}
		
		void translate() {
			int nidx = 0;
			for(int i=0; i<title.length(); i++) {
				if(title.charAt(i)-'0' >= 0 && title.charAt(i)-'0' <= 9) {
					nidx = i;
					break;
				}
			}
			this.head = title.substring(0, nidx);
			int tidx = 0;
			for(int i=nidx; i<(nidx+5<=title.length()?nidx+5:title.length()); i++) {
				if(title.charAt(i)-'0' < 0 || title.charAt(i)-'0' > 9) {
					tidx = i;
					break;
				}
			}
			if(tidx == 0) tidx = nidx+5<=title.length()?nidx+5:title.length();
			this.number = Integer.parseInt(title.substring(nidx, tidx));
		}

		@Override
		public int compareTo(File o) {
			if(head.toLowerCase().compareTo(o.head.toLowerCase()) == 0) {
				return number - o.number;
			} else return head.toLowerCase().compareTo(o.head.toLowerCase());
		}
	}
	
	public String[] solution(String[] files) {
		int n = files.length;
        String[] answer = new String[n];
        File[] Files = new File[n];
        for(int i=0; i<n; i++) Files[i] = new File(files[i]);
        Arrays.sort(Files);
        for(int i=0; i<n; i++) answer[i] = Files[i].title;
        return answer;
    }
}