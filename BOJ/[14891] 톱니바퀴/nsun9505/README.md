# [14890] 톱니바퀴 - C++

## 분류
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>
#include <string>
#include <deque>
#include <map>

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
```

## 문제 풀이
하나의 톱니바퀴가 회전하면 전체에 영향을 줄 수도 있고 부분적으로 영향을 줄 수 있다.

1. 현재 회전하는 톱니바퀴의 2번 인덱스 값과 오른쪽 톱니바퀴의 6번 인덱스 값이 같다면 회전 X
   - 같지 않다면 현재 회전하는 톱니바퀴의 회전 방향의 반대 방향으로 회전
   - 다시 오른쪽 톱니바퀴를 현재 톱니바퀴로 설정하여 재귀호출
1. 현재 회전하는 톱니바퀴의 6번 인덱스 값과 왼쪽 톱니바퀴의 2번 인덱스 값이 같다면 회전 X
   - 같지 않다면 현재 회전하는 톱니바퀴의 회전 방향의 반대 방향으로 회전
   - 다시 왼쪽 톱니바퀴를 현재 톱니바퀴로 설정하여 재귀호출
1. 설정한 회정방향으로 각 톱니바퀴를 회전
1. 각 톱니바퀴의 0번째 인덱스가 1인 경우 값을 더 해서 출력

## 후기
뭔가 그림도 많고 해서 어려울 줄 알았는데 겁먹지 말자!