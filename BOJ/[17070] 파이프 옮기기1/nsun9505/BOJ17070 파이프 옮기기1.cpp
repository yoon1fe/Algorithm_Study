#include <iostream>
#include <queue>
#include <vector>
using namespace std;

struct Element {
	int r1;
	int c1;
	int r2;
	int c2;
	int type;
};

int dx[3] = { 0, 1, 1 };
int dy[3] = { 1, 0, 1 };
vector<int> moves[3] = { {0, 2}, {1, 2}, {0, 1, 2} };
int board[16][16];
int N;
int cnt = 0;
void DFS(int row, int col, int type) {
	vector<int> move = moves[type];
	for (int i = 0; i < move.size(); i++) {
		int nx = row + dx[move[i]];
		int ny = col + dy[move[i]];

		if (nx < 0 || ny < 0 || nx >= N || ny >= N)
			continue;

		if (board[nx][ny] == 1)
			continue;

		if (move[i] == 2 && (board[nx - 1][ny] == 1 || board[nx][ny - 1] == 1))
			continue;

		if (nx == (N - 1) && ny == (N - 1)) {
			cnt++;
			return;
		}

		DFS(nx, ny, move[i]);
	}
}

int BFS() {
	int ret = 0;
	queue<Element> Q;
	Q.push({ 0,0,0,1,0 });

	while (!Q.empty()) {
		Element elem = Q.front();
		Q.pop();

		vector<int> move = moves[elem.type];
		for (int i = 0; i < move.size(); i++) {
			int nx = elem.r2 + dx[move[i]];
			int ny = elem.c2 + dy[move[i]];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;

			if (board[nx][ny] == 1)
				continue;

			if (move[i] == 2 && (board[nx-1][ny] == 1 || board[nx][ny-1] == 1))
				continue;

			if (nx == (N - 1) && ny == (N - 1)) {
				ret++;
				continue;
			}

			Q.push({ elem.r2, elem.c2, nx, ny, move[i] });
		}
	}
	return ret;
}

int main(void) {
	cin >> N;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> board[i][j];

	//DFS(0, 1, 0);
	cout << BFS();

	return 0;
}