## [2019 KAKAO BLIND RECRUITMENT] 매칭 점수 - Java

###    :spider_web: 분류

> 문자열 처리
>
> 정규 표현식



###  :spider_web: 코드

```java
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    static class Page implements Comparable<Page> {
		int pageNo;
		int basicPoint;
		int linkCnt;
		double linkPoint;
		String html;
		
		public Page(int pageNo, int basic, int link, double linkPoint, String html) {
			super();
			this.pageNo = pageNo;
			this.basicPoint = basic;
			this.linkCnt = link;
			this.linkPoint = linkPoint;
			this.html = html;
		}

		@Override
		public int compareTo(Page o) {
			if(this.basicPoint + this.linkPoint == o.basicPoint + o.linkPoint) return this.pageNo - o.pageNo;
			return (int)(o.basicPoint + o.linkPoint) - (int)(this.basicPoint + this.linkPoint);
		}
		
	}
	
	static Map<String, Page> pageMap;

	public static int solution(String word, String[] pages) {
        word = word.toLowerCase();
        pageMap = new HashMap<>();
        
        int idx = 0;
        for(String s : pages) {
        	pageMap.put(findUrl(s), new Page(idx++, getBasicPoint(s.toLowerCase(), word), getLinkCnt(s.toLowerCase()), 0, s.toLowerCase()));
        }
        
        for(String url : pageMap.keySet()) {
        	getLinkPoint(pageMap.get(url));
        }

        List<Page> list = new ArrayList(pageMap.values());
        Collections.sort(list);
        
        return list.get(0).pageNo;
    }
	
	public static String findUrl(String page) {
		Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.+?)\"/>");
		String url = null;
		
		Matcher matcher = pattern.matcher(page);
		while(matcher.find()) {
			url = matcher.group(1);
		}
		
		return url;
	}
	
	public static int getBasicPoint(String page, String word) {
		int cnt = 0;
		
		int idx = page.indexOf(word);
		while(idx != -1) {
			char pre = page.charAt(idx - 1);
			char post = page.charAt(idx + word.length());
			
			if(!Character.isLowerCase(pre) && !Character.isLowerCase(post)) {
				cnt++;
			}
			idx = page.indexOf(word, idx+1);
		}	
		
		return cnt;
	}
	
	public static int getLinkCnt(String page) {
		int idx = page.indexOf("<a href=");
		int cnt = 0;
		while(idx != -1) {
			cnt++;
			idx = page.indexOf("<a href=", idx + 1);
		}
		
		return cnt;
	}
	
	public static void getLinkPoint(Page cur) {
		Pattern pattern = Pattern.compile("<a href=\"https://(.+?)\">");
		Matcher matcher = pattern.matcher(cur.html);
		
		while(matcher.find()) {
			String external = matcher.group(1);
			if(pageMap.containsKey(external)) {
				pageMap.get(external).linkPoint += (double)cur.basicPoint / cur.linkCnt;
			}
		}
	}
}
```



### :spider_web: 풀이 방법

문제 에서 요구하는건 어렵지 않지만 문자열 처리가 너무너무 골치 아픕니다.

일반적으로 split하면서 푸는건 현실적으로 불가능할 듯...



정규 표현식 구글링해가면서 풀었습니다...ㅜ



###  :video_game: 후기

정규 표현식을 공부해야겠습니다 ㅜ