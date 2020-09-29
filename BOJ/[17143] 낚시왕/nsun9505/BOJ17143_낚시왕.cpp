#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#pragma warning(disable:4996)

struct Shark {
	int idx;
	int row;
	int col;
	int speed;
	int dir;
	int size;

	bool operator<(Shark b) {
		return this->size > b.size;
	}
};

using namespace std;
int R, C, M;
vector<Shark> board[101][101];
map<int, Shark> sharkMap;
int dx[5] = {0, -1, 1, 0, 0 };
int dy[5] = {0, 0, 0, 1, -1 };

int reverseDirection(int dir) {
	if (dir == 1)
		return 2;
	else if (dir == 2)
		return 1;
	else if (dir == 3)
		return 4;
	else if (dir == 4)
		return 3;
}

Shark moveUpOrDown(Shark shark) {
	int moveCnt = shark.speed;
	do {
		int mul = 0;
		if (shark.dir == 1)
			mul = shark.row - 1;
		else if (shark.dir == 2)
			mul = R - shark.row;

		if (moveCnt < mul)
			mul = moveCnt;
		shark.row = shark.row + dx[shark.dir] * mul;
		moveCnt -= mul;
		if(moveCnt != 0)
			shark.dir = reverseDirection(shark.dir);
	} while (moveCnt);
	return shark;
}

Shark moveLeftOrRight(Shark shark) {
	int moveCnt = shark.speed;
	do {
		int mul = 0;
		if (shark.dir == 3)
			mul = C - shark.col;
		else if (shark.dir == 4)
			mul = shark.col - 1;

		if (moveCnt < mul)
			mul = moveCnt;

		shark.col = shark.col + dy[shark.dir] * mul;
		moveCnt -= mul;
		if (moveCnt != 0)
			shark.dir = reverseDirection(shark.dir);
	} while (moveCnt);
	return shark;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	freopen("input.txt", "rt", stdin);
	cin >> R >> C >> M;

	int r, c, s, d, z;
	for (int i = 0; i < M; i++) {
		cin >> r >> c >> s >> d >> z;
		Shark shark = { i, r, c, s, d, z };
		board[r][c].push_back({ i, r, c, s, d, z });
		sharkMap[i] = shark;
	}

	int ret = 0;
	for (int col = 1; col <= C; col++) {
		for (int row = 1; row <= R; row++) {
			if (board[row][col].empty())
				continue;

			Shark s = board[row][col][0];
			ret += s.size;
			board[row][col].clear();
			sharkMap.erase(s.idx);
			break;
		}

		vector<Shark> tmp[101][101];
		for (auto shark : sharkMap) {
			if(shark.second.dir == 1 || shark.second.dir == 2)
				shark.second = moveUpOrDown(shark.second);
			else 
				shark.second = moveLeftOrRight(shark.second);
			sharkMap[shark.first] = shark.second;
			tmp[shark.second.row][shark.second.col].push_back(shark.second);
		}

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				board[i][j].clear();
				if (tmp[i][j].size() == 0)
					continue;

				sort(tmp[i][j].begin(), tmp[i][j].end());
				Shark s = tmp[i][j][0];
				for (int idx = 1; idx < tmp[i][j].size(); idx++)
					sharkMap.erase(tmp[i][j][idx].idx);
				board[i][j].push_back(s);
			}
		}

	}
	cout << ret;
	return 0;
}