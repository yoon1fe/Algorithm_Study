#include <iostream>
#include <vector>
#include <queue>
#pragma warning(disable:4996)

using namespace std;
int N, L, R;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int board[51][51];
bool isVisited[51][51];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie();
	freopen("input.txt", "rt", stdin);
	int ans = 0;
	
	cin >> N >> L >> R;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> board[i][j];
	bool flag = 0;
	do {
		flag = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isVisited[i][j])
					continue;

				queue<pair<int, int>> Q;
				vector<pair<int, int>> countries;
				int sum = board[i][j];
				Q.push({ i, j });
				countries.push_back({ i, j });
				isVisited[i][j] = true;

				while (!Q.empty()) {
					pair<int, int> pos = Q.front();
					Q.pop();

					for (int dir = 0; dir < 4; dir++) {
						int nx = pos.first + dx[dir];
						int ny = pos.second + dy[dir];

						if (nx < 0 || ny < 0 || nx >= N || ny >= N)
							continue;

						int tmp = abs(board[pos.first][pos.second] - board[nx][ny]);
						if (isVisited[nx][ny] || tmp < L || tmp > R)
							continue;

						isVisited[nx][ny] = true;
						countries.push_back({ nx, ny });
						sum += board[nx][ny];
						Q.push({ nx, ny });
					}
				}

				if (countries.size() == 1)
					isVisited[i][j] = false;
				else {
					int avg = sum / countries.size();
					for (auto pos : countries)
						board[pos.first][pos.second] = avg;
					flag = true;
				}
			}
		}
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				isVisited[i][j] = false;
		if(flag)
			ans += 1;
	} while (flag);

	cout << ans;

	return 0;
}