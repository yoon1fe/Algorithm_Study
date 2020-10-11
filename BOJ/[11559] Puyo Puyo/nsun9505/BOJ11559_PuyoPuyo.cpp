#include <iostream>
#include <vector>
#include <queue>
#pragma warning(disable:4996)
#define ROW 12
#define COL 6

using namespace std;

char board[12][6];
bool isVisited[12][6];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

vector<pair<int, int>> BFS(int row, int col, char color) {
	queue<pair<int, int>> Q;
	Q.push({ row, col });
	isVisited[row][col] = true;

	vector<pair<int, int>> ret;
	ret.push_back(Q.front());

	while (!Q.empty()) {
		pair<int, int> pos = Q.front();
		Q.pop();

		for (int dir = 0; dir < 4; dir++) {
			int nx = pos.first + dx[dir];
			int ny = pos.second + dy[dir];

			if (nx < 0 || ny < 0 || nx >= ROW || ny >= COL)
				continue;

			if (isVisited[nx][ny] || board[nx][ny] == '.' || board[nx][ny] != color)
				continue;

			Q.push({ nx, ny });
			ret.push_back({ nx, ny });
			isVisited[nx][ny] = true;
		}
	}

	return ret;
}

void movePuyo() {
	for (int i = 10; i >= 0; i--) {
		for (int j = 0; j < COL; j++) {
			if (board[i][j] == '.')
				continue;

			int nextRow = i;
			bool flag = false;
			for (int mul = 1; ; mul++) {
				int nRow = i + (dx[2] * mul);

				if (nRow > ROW || board[nRow][j] != '.')
					break;

				nextRow = nRow;
				flag = true;
			}

			if (flag) {
				board[nextRow][j] = board[i][j];
				board[i][j] = '.';
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	freopen("input.txt", "rt", stdin);

	for (int i = 0; i < 12; i++)
		for (int j = 0; j < 6; j++) 
			cin >> board[i][j];

	int ans = 0;
	int cnt = 0;
	do {
		cnt = 0;
		vector<vector<pair<int, int>>> list;

		for (int i = 0; i < ROW; i++)
			for (int j = 0; j < COL; j++)
				isVisited[i][j] = false;

		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (board[i][j] == '.' || isVisited[i][j])
					continue;

				vector<pair<int, int>> tmp = BFS(i, j, board[i][j]);
				if (tmp.size() >= 4)
					list.push_back(tmp);
			}
		}

		if (list.size() > 0)
			cnt++;

		for (int i = 0; i < list.size(); i++) {
			vector<pair<int, int>> cur = list[i];

			for (int idx = 0; idx < cur.size(); idx++)
				board[cur[idx].first][cur[idx].second] = '.';
		}

		movePuyo();

		ans += cnt;
	} while (cnt);

	cout << ans;
	return 0;
}