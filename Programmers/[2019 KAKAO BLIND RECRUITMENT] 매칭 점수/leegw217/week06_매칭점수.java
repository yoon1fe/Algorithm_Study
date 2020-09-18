package week06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
public class week06_매칭점수 {
	class Page{
		int idx;
		double score;
		List<String> link;
		double matching = 0;
		Page(int idx){
			this.idx = idx;
			link = new ArrayList<String>();
		}
	}
	public int solution(String word, String[] pages) {
		int answer = 0;
        double max = Double.MIN_VALUE;
        HashMap<String, Page> pageInfo = new HashMap<String, Page>();
        for(int i=0; i<pages.length; i++) {
        	String s = "<meta property=\"og:url\" content=\"";
        	String key = pages[i].substring(pages[i].indexOf(s)+s.length(),pages[i].length());
        	key = key.substring(0,key.indexOf("\""));
        	if(pageInfo.containsKey(key))
        		pageInfo.get(key).idx = i;
        	else pageInfo.put(key, new Page(i));
        	s = "<a href=\"";
        	String address = pages[i];
        	while(true) {
        		int idx = address.indexOf(s);
        		if(idx == -1) break;
        		address = address.substring(idx+s.length(), address.length());
        		String link = address.substring(0, address.indexOf("\""));
        		address = address.substring(address.indexOf("\""), address.length());
        		pageInfo.get(key).link.add(link);
        	}
        	word = word.toLowerCase();
        	String html = pages[i].toLowerCase();
        	html = html.replaceAll("[^a-z]", " ");
        	StringTokenizer st = new StringTokenizer(html);
        	while(st.hasMoreTokens()) {
        		if(st.nextToken().equals(word)) pageInfo.get(key).score += 1;
        	}
        	
        	pageInfo.get(key).matching += pageInfo.get(key).score;
        	for(int k=0; k<pageInfo.get(key).link.size(); k++) {
        		String linkKey = pageInfo.get(key).link.get(k);
        		if(!pageInfo.containsKey(linkKey)) 
        			pageInfo.put(linkKey, new Page(21));
        		pageInfo.get(linkKey).matching += pageInfo.get(key).score / (double)pageInfo.get(key).link.size();
        	}
        }
        
        Set<String> keySet = pageInfo.keySet();
        Iterator<String> it = keySet.iterator();
        while(it.hasNext()) {
        	String k = it.next();
        	if(max <= pageInfo.get(k).matching) {
        		if(max == pageInfo.get(k).matching) {
        			if(answer > pageInfo.get(k).idx) {
        				answer = pageInfo.get(k).idx;
        			}
        		}else {
        			max = pageInfo.get(k).matching;
            		answer = pageInfo.get(k).idx;
        		}
        	}
        }
        return answer;
    }
}