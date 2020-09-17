#include <iostream>
#include <vector>
#include <string>
#include <deque>
#include <map>
#pragma warning(disable:4996)

using namespace std;
vector<deque<char>> gears;
map<int, int> rotateInfo;

void rotateGear(int cur, int next, int curGear, int nextGear, int move) {
	if (next >= gears.size() || next <= 0)
		return;

	if (gears[cur][curGear] == gears[next][nextGear])
		rotateInfo[next] = 0;
	else
		rotateInfo[next] = rotateInfo[cur] * -1;
	rotateGear(next, next + move, curGear, nextGear, move);
}

int main(void) {
	freopen("input.txt", "rt", stdin);
	char val;
	deque<char> dummy;
	gears.push_back(dummy);

	for (int i = 0; i < 4; i++) {
		deque<char> gear;
		for (int idx = 0; idx < 8; idx++) {
			cin >> val;
			gear.push_back(val);
		}
		gears.push_back(gear);
	}

	int cmdCnt = 0;
	cin >> cmdCnt;
	int gearIdx, dir;
	for (int i = 0; i < cmdCnt; i++) {
		cin >> gearIdx >> dir;
		rotateInfo[gearIdx] = dir;

		rotateGear(gearIdx, gearIdx + 1, 2, 6, 1);
		rotateGear(gearIdx, gearIdx - 1, 6, 2, -1);

		for (auto rotate : rotateInfo) {
			if (rotate.second == 1) {
				char move = gears[rotate.first].back();
				gears[rotate.first].pop_back();
				gears[rotate.first].push_front(move);
			}
			else if (rotate.second == -1) {
				char move = gears[rotate.first].front();
				gears[rotate.first].pop_front();
				gears[rotate.first].push_back(move);
			}
		}
	}
	int sum = 0;
	int mul = 1;
	for (int idx = 1; idx < gears.size(); idx++) {
		if (gears[idx][0] == '1') 
			sum += mul;
		mul *= 2;
	}
	cout << sum;
	return 0;
}