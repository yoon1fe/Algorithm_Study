import java.util.*;

public class Solution {
    static HashMap<String, Page> pageMap = new HashMap<>();
    static final String aHrefTagExp = "<a href=\"https:";
    static final String metaTagExp = "<meta property=\"og:url\" content=\"";
    public int solution(String word, String[] pages) {
        int answer = 0;
        word = word.toLowerCase();
        int index = 0;
        for(String page : pages)
            createPage(page.toLowerCase(), word, index++);

        Set<String> keys = pageMap.keySet();
        ArrayList<Page> list = new ArrayList<>();
        for(String key : keys){
            Page page = pageMap.get(key);
            page.calcMatchingScore();
            list.add(page);
        }

        Collections.sort(list);
        answer = list.get(0).index;
        return answer;
    }

    public void createPage(String pageString, String word, int index){
        String url = getUrl(pageString);
        Page page = new Page(url, index);
        pageMap.put(url, page);

        searchExternalLink(pageString, page);

        page.setBaseScore(pageString, word);
    }

    public void searchExternalLink(String pageString, Page page) {
        int bodyStart = pageString.indexOf("<body>");
        int bodyEnd = pageString.indexOf("</body>");
        String body = pageString.substring(bodyStart, bodyEnd);
        while(true){
            int index = body.indexOf(aHrefTagExp);
            if(index == -1)
                break;
            int httpsStartIndex = index + 9;
            int httpsEndIndex = body.indexOf("\"", httpsStartIndex);
            String externalLink = body.substring(httpsStartIndex, httpsEndIndex);
            page.externalLinkSet.add(externalLink);
            body = body.substring(httpsEndIndex);
        }
    }

    public String getUrl(String page){
        String[] splitStr = page.split(metaTagExp);
        String url = splitStr[1].substring(0, splitStr[1].indexOf("\""));
        return url;
    }

    static class Page implements Comparable<Page>{
        int index;
        String url;
        int baseScore;
        double matchingScore;
        Set<String> externalLinkSet;

        public Page(String url, int index) {
            this.index = index;
            this.url = url;
            this.baseScore = 0;
            this.matchingScore = 0.0;
            this.externalLinkSet = new HashSet<>();
        }

        public void calcMatchingScore(){
            for(String pageUrl : this.externalLinkSet){
                Page page = pageMap.get(pageUrl);
                if(page == null)
                    continue;
                if(this.externalLinkSet.size() > 0)
                    page.matchingScore += (this.baseScore / (double)this.externalLinkSet.size());
            }
        }

        public void setBaseScore(String body, String word) {
            String[] splitBody = body.toLowerCase().split("[^a-z]");
            for(String splitStr : splitBody)
                if(word.equals(splitStr))
                    this.baseScore++;
            this.matchingScore = this.baseScore;
        }

        @Override
        public int compareTo(Page o) {
            if(this.matchingScore < o.matchingScore)
                return 1;
            else if(this.matchingScore > o.matchingScore)
                return -1;
            return this.index - o.index;
        }
    }
}