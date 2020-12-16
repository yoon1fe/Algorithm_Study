# [2018 KAKAO BLIND RECRUITMENT] 파일명 정렬 - JAVA

## 분류
> 구현

## 코드
```java
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
```

## 문제 풀이
Comparable을 사용해서 정렬을 했습니다!

### 파싱부분
1. 파일의 Head는 숫자가 나올 때까지 문자열에서 0번부터 찾아서 숫자가 나오면 해당 문자열까지 잘라서 리턴
1. head만큼 원래 문자열을 자름.
   - 그러면 number부분부터 시작!
1. number부분을 알아내기 위해 숫자가 끝나는 부분을 찾아서 해당 문자열까지 잘라서 리턴!
1. number만큼 원래 문자열에서 자르면 tail만 남음!
1. head, number, tail을 모두 구했으니 배열에 저장하고 모두 저장하면 정렬!

### 정렬부분
1. 파일의 Head를 먼저 비교합니다.
   - String의 compareToIgnoreCase()를 사용했습니다.
   - 대소문자를 구분하지 않고 비교해주는 것 같아서 사용했습니다.
   - 그리고 A.compareToIgnoreCase(B) > 0이라면 A가 B보다 크거나 문자열 길이가 긴 것이므로 return 1을 해서 head를 오름차순으로 정렬할 수 있게끔 했습니다.
1. 파일 head가 같다면, number를 비교합니다.(오름차순)
1. 파일 number도 같다면, 먼저 들어온 순으로 비교합니다.
   - index 필드를 사용해서 오름차순으로 정렬했습니다.

## 후기
Comparable과 Comparator의 차이는 무엇일까에 대해서 공부를 해봐야 할 것 같습니다!

재미있드아아아앙