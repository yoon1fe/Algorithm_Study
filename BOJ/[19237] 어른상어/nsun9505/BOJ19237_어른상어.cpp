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