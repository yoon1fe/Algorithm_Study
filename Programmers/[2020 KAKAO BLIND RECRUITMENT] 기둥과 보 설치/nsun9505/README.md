# [2020 KAKAO BLIND RECRUITMENT] 기둥과 보 설치 - C++

## 분류
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <map>
#pragma warning(disable:4996)

using namespace std;

map<tuple<int, int, int>, bool> buildMap;

bool cmp(vector<int>a, vector<int> b) {
	if (a[0] < b[0])
		return true;
	else if (a[0] == b[0]) {
		if (a[1] < b[1])
			return true;
		else if (a[1] == b[1])
			return a[2] < b[2];
		else
			return false;
	}
	else
		return false;
}

bool isOk() {
	for (auto cur : buildMap) {
		int x, y, a;
		tie(x, y, a) = cur.first;

		if (a == 0) {
			if (y == 0) continue;
			else if (buildMap.count(make_tuple(x - 1, y, 1)) || buildMap.count(make_tuple(x, y, 1))) continue;
			else if (buildMap.count(make_tuple(x, y - 1, 0))) continue;
			else return false;
		}
		else {
			if (buildMap.count(make_tuple(x, y - 1, 0)) || buildMap.count(make_tuple(x + 1, y - 1, 0))) continue;
			else if (buildMap.count(make_tuple(x - 1, y, 1)) + buildMap.count(make_tuple(x + 1, y, 1)) == 2) continue;
			else return false;
		}
	}
	return true;
}

vector<vector<int>> solution(int n, vector<vector<int>> build_frame) {
	vector<vector<int>> answer;

	for (auto elem : build_frame) {
		tuple<int, int, int> structure = make_tuple(elem[0], elem[1], elem[2]);
		int cmd = elem[3];

		// 설치
		if (cmd == 1) {
			buildMap[structure] = true;

			if (!isOk())
				buildMap.erase(structure);
		}
		// 제거
		else {
			buildMap.erase(structure);
			if (!isOk())
				buildMap[structure] = true;
		}
	}

	for (auto cur : buildMap) {
		int x, y, a;
		tie(x, y, a) = cur.first;
		answer.push_back({ x,y,a });
	}

	sort(answer.begin(), answer.end(), cmp);

	return answer;
}

int main(void) {
	int N;
	cin >> N;
	int num = 0;
	cin >> num;
	int x, y, a, b;
	vector<vector<int>> build_frame;
	for (int i = 0; i < num; i++) {
		cin >> x >> y >> a >> b;
		build_frame.push_back({ x,y,a,b });
	}

	vector<vector<int>> ret = solution(N, build_frame);
	for (auto cur : ret)
		cout << cur[0] << ' ' << cur[1] << ' ' << cur[2] << '\n';

	return 0;
}
```

## 문제 풀이
기둥을 설치하기 위한 조건(아래 조건 중 하나만 해당되면 된다.)
1. 기둥은 바닥 위에 있거나 : y == 0
1. 보의 한쪽 끝 위에 있거나 : (x,y) == (x-1,y) == 1
   - 보는 오른쪽 방향으로 설치가 되므로 (x-1, y)와 (x,y) 둘중 하나가 보인지를 확인하면 된다.
1. 다른 기둥 위에 있거나 : (x,y-1) == 0

보를 설치하기 위한 조건(아래 조건 중 하나만 해당되면 된다.)
1. 보는 한 쪽 끝부분이 기둥 위에 있거나 : (x, y-1) == 0 || (x+1, y-1) == 0
   - 보가 설치되는 위치(x,y) 밑에 기둥이 있거나
   - 보는 오른쪽으로 설치되니 (x+1, y-1) 위치에 기둥이 있으면 설치할 수 있다.
1. 양쪽 끝 부분이 다른 보와 연결되어 있거나 : (x-1, y) == 1 && (x+1, y) == 1

설치는 기둥을 설치하기 위한 조건과 보를 설치하기 위한 조건을 만족한다면 해당 기둥 또는 보를 설치한다.
- map을 사용해서 각 기둥 또는 보를 하나의 고유한 키 값으로 설정했다.
- 키값으로 설정하면 인덱스 범위를 벗어나도 인덱스를 벗어나는 기둥 또는 보는 없으므로 설치 시 조건을 만족시킬 수 없으므로 설치가 되지 않을 것이다.
- map을 사용할 경우 기둥이나 보가 있을 때 유효한 값을 건네주므로 따로 배열을 사용하지 않아도 된다.

제거는 해당 기둥 또는 보를 제거한 후에도
- 기동 또는 보들이 설치 조건을 만족한다면 복구할 필요가 없다.
- 하지만 어떤 기둥 또는 보가 설치 조건에 만족하지 않으면 제거한 기둥 또는 보를 복구시켜야 한다.

## 후기
처음에는 어떻게 풀어야하는가를 너무 고민했다.

그래서 여러 블로그들을 돌아보면서 아이디어가 가장 이해가 잘 되는 것을 골라서 그 아이디어로 문제를 해결했다.

문제를 끝까지 잡고 푸는 것도 좋다고 생각하지만 하루종일 이것만 잡고 있으면 안되니 고집 적당히 부리자..