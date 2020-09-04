#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <set>

using namespace std;

vector<int> solution(vector<string> gems) {
	vector<int> answer;
	map<string, int> gemsMap;
	set<string> gemsSet;
	map<vector<int>, int> ret;

	for (int i = 0; i < gems.size(); i++)
		gemsSet.insert(gems[i]);

	int numOfGems = gemsSet.size();
	if (numOfGems == 1)
		return { 1,1 };

	int startIdx = 0;
	int endIdx = 0;
	int dist = gems.size();

	gemsMap[gems[0]] = 1;
	while (endIdx < gems.size()) {
		if (gemsMap.size() == numOfGems) {
			if (endIdx - startIdx < dist) {
				dist = endIdx - startIdx;
				answer = { startIdx + 1, endIdx + 1 };
			}
			gemsMap[gems[startIdx]] -= 1;
			if (gemsMap[gems[startIdx]] == 0)
				gemsMap.erase(gems[startIdx]);
			startIdx++;
		
		}
		else if(++endIdx < gems.size()){
			gemsMap[gems[endIdx]]++;
		}
	}

	return answer;
}

int main(void) {
	// { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" }
	// "XYZ", "XYZ", "XYZ"
	vector<string> games = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };
	vector<int> ret = solution(games);
	for (auto idx : ret)
		cout << idx << ' ';
	return 0;
}