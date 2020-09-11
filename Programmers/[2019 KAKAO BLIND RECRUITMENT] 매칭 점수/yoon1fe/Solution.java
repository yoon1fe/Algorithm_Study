package Programmers;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
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
	
	public static void main(String[] args) {
		String word = "blind";
		String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
		
		
		System.out.println(solution(word, pages));
	}
}