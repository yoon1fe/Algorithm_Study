# [2020 카카오 인턴십] 보석 쇼핑 - C++

## 분류
- 투포인트

## 코드
```c++
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
```

## 문제풀이
처음에 어떻게 접근할까 하다가 각 보석이 나오는 인덱스를 구하는 그림을 그렸다.<br>
그림을 그리다보니 투포인트로 풀 수 있다고 생각해서 투포인트로 풀게 되었다.<br>

먼저 gems안에 있는 보석의 수를 알아낸다.
- set을 사용해서 보석의 개수를 카운트 했다.
- 만약 보석의 개수가 1이라면 그냥 1,1을 리턴했다.

보석의 개수가 2 이상인 경우 아래와 같다.
- 보석을 모두 포함하는 첫 번째 인덱스 startIdx, 마지막 인덱스 endIdx를 사용했다.
- 처음에는 map에 보석을 집어 넣는다.
- 그러다가 map안의 보석 종류의 수가 gems 안에 있는 보석 종류의 수와 같다면 문제에서 말한 조건이 참이 된것이다.
   - **진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매**
- 그러면 startIdx에서 endIdx까지의 거리를 계산하고, 현재까지 계산된 최소 거리보다 작다면 dist를 갱신하고, startIdx와 endIdx를 answer에 담는다.

내가 생각하기엔 dist를 갱신한 이후가 핵심인 것 같다.
- dist 갱신 후(갱신이 안 되었을 수도 있다.), map에서 startIdx에 있는 보석의 숫자를 하나 감소시킨다.
   - 하나를 감소시켜서 해당 보석의 개수가 0이되면 그 보석을 제거한다.
   - 그러면 while문 안의 첫 번째 if문을 만족 못하므로 endIdx를 증가시키면서 보석을 담기 시작할 것이다.

보석의 숫자가 줄어들고 startIdx를 증가시키고 다시 while문을 돌았을 때
- 보석의 숫자가 줄어들었는데도 map에 있는 보석의 종류 수가 gems에 있는 보석의 종류 수와 동일하다면 조건을 만족하므로 거리를 구하고 답을 찾아간다.
- 보석의 숫자가 줄어들어서 map에 있는 보석의 종류 수가 gems에 있느 보석의 종류 수와 동일하지 않다면 모든 종류의 보석을 적어도 1개 이상 포함하지 못하므로 보석을 담기 시작한다.

## 후기
map과 set을 이용해서 접근하기는 했지만 여기에 투포인트까지 사용했어야 했다.<br>
투포인트 문제를 많이 풀어봐야겠다.