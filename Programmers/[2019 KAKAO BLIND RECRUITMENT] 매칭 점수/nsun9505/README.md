# [2019 KAKAO BLIND RECRUITMENT] 매칭 점수 - C++

## 분류
> 문자열 처리

## 코드
```c++
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

struct pageInfo {
    int idx;
    int wordCnt;
    double score;
    vector<string> exUrl;
};

string transformLower(string page) {
    for (int i = 0; i < page.length(); i++) {
        if (page[i] >= 'A' && page[i] <= 'Z')
            page[i] += 32;
    }
    return page;
}

string getUrl(string page) {
    int headStart = page.find("<head>");
    int headEnd = page.substr(headStart).find("</head>");
    string headTag = page.substr(headStart, headEnd);

    string metaUrl = "<meta property=\"og:url\" content=\"";
    int urlStart = headTag.find(metaUrl) + metaUrl.length();
    int urlEnd = headTag.substr(urlStart).find("\"/>");

    string url = headTag.substr(urlStart, urlEnd);
    return url;
}

string getBody(string page) {
    int bodyStart = page.find("<body>") + 6;
    int bodyEnd = page.substr(bodyStart).find("</body>");
    return page.substr(bodyStart, bodyEnd);
}

int wordCount(string body, string word) {
    int ret = 0;
    int idx = body.find(word, 0);
    while (idx != string::npos) {
        ret++;
        if ((idx >= 1 && isalpha(body.at(idx - 1))) || (idx + word.length() < body.length() && isalpha(body[idx + word.length()])))
            ret--;
        idx = body.find(word, idx + word.length());
    }
    return ret;
}

vector<string> getExternalUrl(string body) {
    vector<string> ret;
    int hrefStart = body.find("<a href=\"");
    while (hrefStart != string::npos) {
        hrefStart += 9;
        int hrefEnd = body.substr(hrefStart).find("\">");
        ret.push_back(body.substr(hrefStart, hrefEnd));
        hrefStart = body.find("<a href=\"https://", hrefStart + hrefEnd);
    }
    return ret;
}

int solution(string word, vector<string> pages) {
    int answer = 0;
    map<string, pageInfo> pageMap;
    int idx = 0;
    word = transformLower(word);
    for (auto page : pages) {
        page = transformLower(page);
        string url = getUrl(page);
        string body = getBody(page);
        int wordCnt = wordCount(body, word);
        vector<string> exUrl = getExternalUrl(body);
        pageMap[url] = { idx++, wordCnt, (double)wordCnt,exUrl };
    }
    for (auto page : pageMap) {
        pageInfo pInfo = page.second;
        for (auto ex : pInfo.exUrl) {
            if (pageMap.count(ex) == 1) {
                pageMap[ex].score += (double)pInfo.wordCnt / pInfo.exUrl.size();
            }
        }
    }
    
    double maxScore = -2147000000.0;
    for (auto page : pageMap) {
        if (maxScore < page.second.score) {
            answer = page.second.idx;
            maxScore = page.second.score;
        }
        else if (maxScore == page.second.score && page.second.idx < answer)
            answer = page.second.idx;
    }
    
    return answer;
}

int main(void) {
    string word = "Muzi";
    vector<string> pages = { "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>" };

    cout << solution(word, pages);

    return 0;
}
```

## 문제 풀이
1. 각 page와 word를 소문자로 바꾼다.
1. page마다 og:url 프로퍼티를 가지는 meta 태그의 content의 값이 해당 page의 URL이 된다.
   - 그래서 meta 태그 중 property 속성의 값이 og:url인 content의 값을 page의 URL로 설정하면 된다.
1. page에서 body 부분을 추출한다.
   - body 부분에서 word를 찾는다. 
   - word를 구분하는 것은 빈칸 혹은 알파벳이 아닌 문자들로 구분하면 된다.
1. page에서 외부 링크를 찾는다.
   - a 태그는 href 속성만을 가진다고 했으니
   - ```\<a href="">```와 같은 문자열이 시작하는 곳을 찾아서 ```\"```까지 잘라주면 외부 URL을 추출할 수 있다.
   - 여러 개가 있을 수 있으므로 while문을 돌면서 찾으면 된다.
1. 매칭 점수는 페이지에 대한 URL, 기본 점수, 외부 링크를 모두 구했을 때 구한다.
   - map에 키를 페이지의 URL로 설정하고 값을 페이지 인덱스, 기본 점수, 외부 링크로 설정한다.
   - 그러면 현재 페이지가 참조하는 외부링크에 자신의 기본 점수 / 외부 링크 수를 계산해서 외부 링크의 score에 더해주면 된다.
1. map을 처음부터 끝까지 탐색하면서 score가 가장 높은 페이지를 찾으면 된다.
   - 가장 높은 score를 가지는 페이지가 다수일 수 있으므로
   - 현재 maxScore와 페이지가 가지는 score가 같은 경우에는 인덱스가 더 작은 것을 선택하여 answer에 저장하면 된다.
1. answer를 리턴

## 후기
처음에는 어떻게 할까라는 생각이 많이 들었다.

그래서 다른 사람들은 어떻게 풀었는지 보았더니 태그를 잘라서 그 안에서 기본점수를 계산하고, 외부 링크를 추출하는 방식이었다.

c++ string의 find를 잘 알았다면 이 문제를 푸는데 어려움이 없었을 것 같다.