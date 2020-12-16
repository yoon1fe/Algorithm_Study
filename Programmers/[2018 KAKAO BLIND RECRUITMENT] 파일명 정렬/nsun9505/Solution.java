import java.util.Arrays;

class Solution {
	static class File implements Comparable<File>{
		int index;
		String filename;
		String head;
		int number;
		String tail;
		
		File(int idx, String filename, String head, int number, String tail){
			this.index = idx;
			this.filename = filename;
			this.head = head;
			this.number = number;
			this.tail = tail;
		}

		@Override
		public int compareTo(File f) {
			if(this.head.compareToIgnoreCase(f.head) > 0)
				return 1;
			else if(this.head.compareToIgnoreCase(f.head) == 0) {
				if(this.number > f.number)
					return 1;
				else if(this.number == f.number)
					return (this.index > f.index ? 1 : -1);
			}
			return -1;
		}
	}
	
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        File[] sortedFiles = new File[files.length];
        for(int i=0; i<files.length; i++) {
        	String filename = files[i];
        	String head = getHead(filename);
        	filename = filename.substring(head.length());
        	String number = getNumber(filename);
        	String tail = filename.substring(number.length());
        	sortedFiles[i] = new File(i, files[i], head, Integer.parseInt(number), tail);
        }
        
        Arrays.sort(sortedFiles);
        for(int i=0; i<sortedFiles.length; i++)
        	answer[i] = sortedFiles[i].filename;
        return answer;
    }
    
    public static String getHead(String file) {
    	String ret = "";
    	int end = 0;
    	for(; end<file.length(); end++)
    		if(file.charAt(end) >= '0' && file.charAt(end) <= '9')
    			break;
    	return file.substring(0, end);
    }
    
    public static String getNumber(String file) {
    	int end = 0;
    	for(;end<file.length(); end++)
    		if(file.charAt(end) < '0' || file.charAt(end) > '9')
    			break;
    	return file.substring(0, end);
    }
}