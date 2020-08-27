# [2020 KAKAO BLIND RECRUITMENT] 가사 검색 - C++

## 분류
> 문자열 처리

## 코드
```c++
#include <iostream>
#include <string>
#include <vector>
#include <map>

using namespace std;

typedef struct _query {
	int length;
	bool isPrefix;
	int wildcardIdx;
	string cmpStr;
	int cnt;

	_query(int len, bool isPre, int wildcard, string cmp)
		: length(len), isPrefix(isPre), wildcardIdx(wildcard), cmpStr(cmp), cnt(0){}
}Query;

Query getQuery(string query) {
	int length = query.length();
	int startWairdcard = 0;
	for (int i = 0; i < length; i++) {
		if (query[i] == '?') {
			startWairdcard = i;
			break;
		}
	}

	bool isPrefix = false;
	int wildCardIdx;
	string cmpStr;
	if (startWairdcard == 0) {
		isPrefix = true;
		for (int i = 0; i < length; i++)
			if (query[i] != '?') {
				wildCardIdx = i;
				break;
			}

		cmpStr = query.substr(wildCardIdx);
	}
	else {
		wildCardIdx = startWairdcard;
		cmpStr = query.substr(0, wildCardIdx);
	}
	return Query(length, isPrefix, wildCardIdx, cmpStr);
}

vector<int> solution(vector<string> words, vector<string> queries) {
	vector<int> answer;
	map<string, Query> queryMap;

	for (int i = 0; i < queries.size(); i++)
		queryMap.insert({ queries[i], getQuery(queries[i]) });

	map<string, int> ret;
	for (auto it = queryMap.begin(); it != queryMap.end(); it++) {
		Query cmpQuery = it->second;

		for (int i = 0; i < words.size(); i++) {
			string word = words[i];
			if (word.size() != cmpQuery.length)
				continue;

			string cmpWord;
			if (cmpQuery.isPrefix){
				cmpWord = word.substr(cmpQuery.wildcardIdx);
				if(cmpWord == ""){
					cmpQuery.cnt += 1;
					continue;
				}
			}
			else
				cmpWord = word.substr(0, cmpQuery.wildcardIdx);

			if (cmpWord == cmpQuery.cmpStr)
				cmpQuery.cnt += 1;
		}
		ret.insert({it->first, cmpQuery.cnt});
	}

	for (int i = 0; i < queries.size(); i++) {
		auto it = ret.find(queries[i]);
		answer.push_back(it->second);
	}

	return answer;
}

int main(void) {
	freopen("input.txt", "rt", stdin);
	int N = 6, M = 5;

	string input;
	vector<string> words;
	vector<string> queries;
	for (int i = 0; i < N; i++) {
		cin >> input;
		words.push_back(input);
	}

	for (int i = 0; i < M; i++) {
		cin >> input;
		queries.push_back(input);
	}

	vector<int> ans = solution(words, queries);
	for (auto cnt : ans)
		cout << cnt << '\n';

	return 0;
}
```

## 문제 풀이
쿼리에서 ?문자는 최소 하나 이상이며, 앞에나 뒤에 존재한다고 했다.
- 중간에 ?가 있는 경우는 없고 오직 접두사, 접미사에만 있다고 문제에 제시되어 있다.
- 그러면 ?의 위치를 알아낸다.

?의 위치가 쿼리의 처음부터 나오면 접두사를 체크하는 것이고, ?의 위치가 문자열의 중간 또는 끝에서부터 시작한다면 접미사를 체크하는 것이다.
- ?가 있는 접미사나 접두사를 제외한 문자열을 쿼리로부터 추출해서 word와 비교하기 위해 따로 저장을 한다.

비교하기 전에는 우선 word와 query의 문자열 길이가 같은지 체크하여 같은 경우에는 아래와 같이 수행한다.
1. 비교하는 word를 접두사의 경우 ?가 끝나는 이후의 문자열을 추출해서 쿼리에서 ?가 아닌 문자열과 비교한다.
1. 접미사의 경우 word의 0번째부터 ?가 나오기 직전 문자열까지 추출해서 쿼리의 ?가 아닌 문자열과 비교한다.

위 비교에서 하나만 참이되면 해당 쿼리에 의해 검색이 되므로 해당 쿼리의 값에 카운트를 하면 된다.

## 후기
출근 후 작성