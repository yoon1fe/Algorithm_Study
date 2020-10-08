# [19237] 어른 상어 - C++

## 분류
> 구현
>
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>
#include <map>
#include <set>
#pragma warning(disable:4996)

using namespace std;
struct Hormone {
	int idx;
	int sec;
};

struct Shark {
	int idx;
	int row;
	int col;
	int dir;
};

int dx[4] = { -1, 1, 0, 0 };
int dy[4] = { 0, 0, -1, 1 };
Hormone board[21][21];
map<int, Shark> sharkMap;
map<int, vector<vector<int>>> dirPriority;
int N, M, K;

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	freopen("input.txt", "rt", stdin);

	cin >> N >> M >> K;
	int idx;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> idx;

			if (idx) {
				board[i][j] = { idx, K };
				sharkMap[idx] = { idx, i, j, 0 };
			}
			else {
				board[i][j] = { 0, 0 };
			}
		}
	}

	int d;
	for (int i = 1; i <= sharkMap.size(); i++) {
		cin >> d;
		sharkMap[i].dir = d - 1;
	}

	for (int idx = 1; idx <= sharkMap.size(); idx++) {
		vector<vector<int>> pri;
		for (int i = 0; i < 4; i++) {
			vector<int> tmp;
			int dir;
			for (int j = 0; j < 4; j++) {
				cin >> dir;
				tmp.push_back(dir - 1);
			}
			pri.push_back(tmp);
		}
		dirPriority[idx] = pri;
	}

	
	for (int t = 1; t <= 1000; t++) {
		for (auto cur : sharkMap) {
			vector<vector<int>> pri = dirPriority[cur.first];
			vector<int> dir = pri[sharkMap[cur.first].dir];

			bool flag = true;
			for (int i = 0; i < dir.size(); i++) {
				int nx = sharkMap[cur.first].row + dx[dir[i]];
				int ny = sharkMap[cur.first].col + dy[dir[i]];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (board[nx][ny].idx != 0)
					continue;

				sharkMap[cur.first].row = nx;
				sharkMap[cur.first].col = ny;
				sharkMap[cur.first].dir = dir[i];
				flag = false;
				break;
			}

			if (flag) {
				for (int i = 0; i < dir.size(); i++) {
					int nx = sharkMap[cur.first].row + dx[dir[i]];
					int ny = sharkMap[cur.first].col + dy[dir[i]];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N)
						continue;

					if (board[nx][ny].idx == cur.first) {
						sharkMap[cur.first].row = nx;
						sharkMap[cur.first].col = ny;
						sharkMap[cur.first].dir = dir[i];
						break;
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j].idx == 0)
					continue;

				board[i][j].sec -= 1;
				if (board[i][j].sec == 0)
					board[i][j].idx = 0;
			}
		}

		set<int> removeSet;
		for (int i = 1; i < M; i++) {
			if (sharkMap.count(i) == 0)
				continue;

			for (int j = i + 1; j <= M; j++) {
				if (sharkMap.count(j) == 0)
					continue;

				if (sharkMap[i].row == sharkMap[j].row && sharkMap[i].col == sharkMap[j].col)
					removeSet.insert(j);
			}
		}

		if (removeSet.size() > 0)
			for (auto index : removeSet)
				sharkMap.erase(index);

		if (sharkMap.size() == 1) {
			cout << t;
			return 0;
		}

		for (auto cur : sharkMap) {
			Shark shark = cur.second;
			board[shark.row][shark.col] = { shark.idx, K };
		}
	}

	cout << -1;
	return 0;
}
```

## 문제 풀이
### 1. 입력
board에는 호르몬을 표시한다.
- idx는 상어의 번호, sec은 호르몬의 남은 시간이다.

sharkMap에는 상어들의 번호가 키이고, 상어의 위치와 방향이 저장된다.

dirPriodirty는 각 상어들의 방향에 대한 우선순위 정보이다.

### 2. 상어가 이동
1번 상어부터 움직이기 시작한다.
- 해당 상어의 현재 방향에 맞는 우선순위를 가져온다.
- 우선순위에 따라 현재 위치에서 탐색한다.
- 만약 호르몬이 없는 칸이 있다면 해당 칸을 상어의 위치로 저장하고, 그 칸을 바라보는 방향으로 상어의 방향을 변경한다. 그리고 더 이상 진행하지 않도록 break를 한다.

만약 모든 우선순위 방향에 호르몬이 있다면, 자신의 호르몬이 있는 곧으로 이동한다.
- 자신의 호르몬이 있는 칸을 찾는 것도 우선순위에 의해 결정한다.

### 3. 호르몬의 시간을 -1
호르몬의 남은 시간을 -1한다.
- 만약 0이되면 해당 칸에는 호르몬을 제거한다.
- idx를 0, sec을 0으로 대입하면 된다.

### 4. 이동을 마친 상어들이 겹치는지 확인한다.
상어가 같은 칸에 여러 마리 들어가 있다면 거기서 가장 작은 번호를 가지는 상어를 제외하고 모두 제거한다.
- set을 사용해서 같은 칸에 있는 상어들 중 가장 작은 번호를 가지는 상어를 제외한 나머지를 저장한다.
- set에 저장되어 있는 상어들의 번호를 map에서 제거한다.

제거 후에 남은 상어의 수가 1이라면 현재 초를 출력하고 프로그램을 종료한다.
- 아니라면 계속 진행하다가 1000초를 넘으면 -1을 출력한다.

### 5. 남은 상어들의 위치에 호르몬을 주입한다.
제거가 끝났다면 이제 이동을 한 상어의 위치에 해당 상어의 번호와 K를 주입한다.

## 후기
상반기에 풀었던 문제!

그때보다는 확실히 실수 없이 푼 것 같음.

근데 굳이 map으로 관리하는게 맞는걸까 싶다.

vector로 충분히 풀 수 있을 것 같다.

그때도 vector로 다 풀었는디..