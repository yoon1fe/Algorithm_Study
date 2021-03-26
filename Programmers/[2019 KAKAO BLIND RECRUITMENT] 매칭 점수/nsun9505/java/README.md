# [2019 KAKAO BLIND RECRUITMENT] 매칭 점수

## 분류
> 문자열

## 코드
```java
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
```

## 문제 풀이
### URL 찾기
`"<meta property=\"og:url\" content=\"`를 기준으로 자르면 0번째는 이 문자열이 나오기 전이고 1번째는 찾고자하는 URL의 시작부분이 나옵니다.

그러면 1번째 인덱스에는 `https://...`가 나옵니다. 여기서 `"`가 처음 나오는 인덱스까지 짜르면 URL을 구할 수 있습니다.

### 외부 링크 찾기
body 부분에서 문자열의 시작이 `<a href="https:`인 위치를 찾습니다.

그리고 `<a href="` 길이만큼 더해서 외부 링크의 URL 시작 인덱스를 알아옵니다.

마지막으로 URL 시작 인덱스 이후로 `"`가 처음으로 나오는 인덱스인 URL의 마지막 인덱스 부분을 알아옵니다.

URL 시작 인덱스에서 URL 마지막 인덱스 부분만큼 잘라서 현재 페이지 객체의 외부 링크 셋에 저장하면 됩니다.

그리고 다른 위치에 외부 링크가 있을 수도 있으므로 지금까지 본 위치는 잘라버리기 위해 방금 찾은 URL의 마지막 위치 이후의 문자열을 원래의 문자열에 저장합니다.

### 기본 점수 계산
일단 모두 영어 소문자로 만들어주고, 영어소문자가 아닌 것들을 기준으로 자릅니다.

자른 문자열 배열에서 찾는 word가 있으면 기본 점수를 1증가시키면 됩니다.

### 매칭 점수 계산
매칭 점수를 계산하기 위해서 이전에 저장한 외부 링크 셋의 원소를 키로 해서 PageMap에서 해당 페이지를 가져옵니다.

그리고 그 페이지 매칭 점수에 현재 페이지의 `기본 점수 / 외부 링크 수`를 계산해서 더해주면 됩니다.

모든 외부 링크 페이지에 대해서 수행하면 됩니다.

마지막으로 페이지들을 리스트에 담아서 정렬하고 0번째의 인덱스를 리턴하면 됩니다.

## 후기
정규표현식은 넘나 어려운거...