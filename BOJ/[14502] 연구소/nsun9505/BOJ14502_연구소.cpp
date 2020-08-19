#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int N, M, ans;
char board[10][10];
vector<pair<int, int>> virus;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

int spreadVirus() {
	queue<pair<int, int>> Q;
	bool isVisited[10][10];
	char tmp[10][10];
	int cnt = 0;

	for(int i=0; i<N; i++)
		for (int j = 0; j < M; j++) {
			tmp[i][j] = board[i][j];
			if (board[i][j] == '2')
				isVisited[i][j] = true;
			else
				isVisited[i][j] = false;
		}

	for (int i = 0; i < virus.size(); i++)
		Q.push({ virus[i].first, virus[i].second });
	while (!Q.empty()) {
		pair<int, int> pos = Q.front();
		Q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = pos.first + dx[i];
			int ny = pos.second + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			if (isVisited[nx][ny]) 
				continue;
			
			if (tmp[nx][ny] == '0') {
				Q.push({ nx, ny });
				tmp[nx][ny] = '2';
				isVisited[nx][ny] = true;
			}
		}
	}

	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			if (tmp[i][j] == '0')
				cnt++;

	return cnt;
}

void solve(int depth, int row, int col) {
	if (depth == 3) {
		int ret = spreadVirus();
		if (ans < ret)
			ans = ret;
		return;
	}

	for (int i = row; i < N; i++) {
		for (int j = (i == row ? col : 0); j < M; j++) {
			if (board[i][j] == '0') {
				board[i][j] = '1';
				solve(depth + 1, i, j+1);
				board[i][j] = '0';
			}
		}
	}
}

int main(void) {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> board[i][j];
			if (board[i][j] == '2')
				virus.push_back({ i, j });
		}
	}

	solve(0, 0, 0);

	cout << ans;
	return 0;
}