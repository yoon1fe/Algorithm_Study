#include <iostream>
#include <vector>

using namespace std;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int board[501][501];
bool isVisited[501][501];
int N, M;
int maxRet = -217400000;
vector<pair<int, int>> stack;

void DFS(int cnt, int sum, int row, int col) {
	sum += board[row][col];

	if (cnt == 3 && maxRet < sum) {
		maxRet = sum;
		return;
	}
	else if (cnt == 3)
		return;

	isVisited[row][col] = true;
	stack.push_back({ row, col });

	for (int i = 0; i < stack.size(); i++) {
		int tmp_row = stack[i].first;
		int tmp_col = stack[i].second;

		for (int dir = 0; dir < 4; dir++) {
			int nx = tmp_row + dx[dir];
			int ny = tmp_col + dy[dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			if (isVisited[nx][ny])
				continue;

			DFS(cnt + 1, sum, nx, ny);
		}
	}

	stack.pop_back();
	isVisited[row][col] = false;
}

int main(void) {
	cin >> N >> M;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			cin >> board[i][j];

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			DFS(0, 0, i, j);

	cout << maxRet;
	return 0;
}