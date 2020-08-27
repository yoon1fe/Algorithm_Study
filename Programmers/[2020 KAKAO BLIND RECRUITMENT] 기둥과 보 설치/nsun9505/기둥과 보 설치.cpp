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