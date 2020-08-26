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
			if (cmpQuery.isPrefix)
				cmpWord = word.substr(cmpQuery.wildcardIdx);
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