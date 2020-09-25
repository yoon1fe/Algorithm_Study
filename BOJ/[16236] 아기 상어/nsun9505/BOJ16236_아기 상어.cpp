#include <iostream>
#include <vector>
#include <queue>
#include <map>
#include <algorithm>
#include <fstream>

using namespace std;

struct food {
	int dist;
	int row;
	int col;
};
int N;
int board[21][21];
bool isVisited[21][21];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

bool cmp(food a, food b) {
	if (a.dist < b.dist)
		return true;
	else if (a.dist == b.dist) {
		if (a.row < b.row)
			return true;
		else if (a.row == b.row)
			return a.col < b.col;
	}
	return false;
}

int main(void) {
	ifstream in("input.txt");
	ios_base::sync_with_stdio(false);
	in >> N;
	pair<int, int> start;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			in >> board[i][j];
			if (board[i][j] == 9) {
				start.first = i;
				start.second = j;
				board[i][j] = 0;
			}
		}
	}
	in.close();

	int ans = 0;
	int babySharkSize = 2;
	int eatCnt = 0;
	while (true) {
		vector<food> foods;
		queue<food> Q;
		Q.push({ 0, start.first, start.second });
		isVisited[start.first][start.second] = true;

		while (!Q.empty()) {
			food pos = Q.front();
			Q.pop();

			for (int dir = 0; dir < 4; dir++) {
				int nx = pos.row + dx[dir];
				int ny = pos.col + dy[dir];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (board[nx][ny] > babySharkSize || isVisited[nx][ny])
					continue;

				if (board[nx][ny] != 0 && board[nx][ny] < babySharkSize)
					foods.push_back({ pos.dist + 1, nx, ny });

				isVisited[nx][ny] = true;
				Q.push({ pos.dist + 1, nx, ny });
			}
		}

		if (foods.size() == 0)
			break;

		sort(foods.begin(), foods.end(), cmp);
		food f = foods[0];
		board[f.row][f.col] = 0;
		eatCnt += 1;
		if (eatCnt == babySharkSize) {
			eatCnt = 0;
			babySharkSize += 1;
		}
		ans += f.dist;
		start.first = f.row;
		start.second = f.col;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				isVisited[i][j] = false;
	}

	cout << ans;
	return 0;
}