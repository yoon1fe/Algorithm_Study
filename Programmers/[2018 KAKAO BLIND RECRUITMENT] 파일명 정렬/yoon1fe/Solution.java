package Programmers;

import java.util.*;

class Solution {
	static class File implements Comparable<File> {
		String name, head, tail;
		int order, number;
		
		File(String filename, int order){
			this.name = filename;
			this.order = order;

			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < filename.length(); i++) {
				if('0' <= filename.charAt(i) && filename.charAt(i) <= '9') {
					this.head = sb.toString();
					sb.delete(0, sb.length());
					
					
					for(int j = i; j < i + 5; j++) {
						if(filename.length() <= j) {						// TAIL 없는 경우
							this.number = Integer.parseInt(sb.toString());
							this.tail = "";
							return;
						}
						if('0' <= filename.charAt(j) && filename.charAt(j) <= '9') {
							sb.append(filename.charAt(j));
						}else {												// NUMBER 끝
							i = j;
							break;
						}
					}
					
					this.tail = sb.length() == 5 ? filename.substring(i+5) : filename.substring(i);
					this.number = Integer.parseInt(sb.toString());

					break;
				}
				sb.append(filename.charAt(i));
			}
		}

		// 정렬 기준: HEAD -> NUMBER -> 들어온 순서 
		public int compareTo(File o) {
			if(this.head.toLowerCase().equals(o.head.toLowerCase())) {
				if(this.number == o.number) return this.order - o.order;
				return this.number - o.number;
			}
			return this.head.toLowerCase().compareTo(o.head.toLowerCase());
		}
	}
	
	public String[] solution(String[] files) {
        String[] answer;
        PriorityQueue<File> pq = new PriorityQueue<>();
        
        for(int i = 0; i < files.length; i++) {
        	File file = new File(files[i], i);
        	pq.offer(file);
        }
        
        answer = new String[files.length];
        int idx = 0;
        
        while(!pq.isEmpty()) {
        	answer[idx++] = pq.poll().name;
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution(new String[] {"F-15", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"}));
	}
}