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