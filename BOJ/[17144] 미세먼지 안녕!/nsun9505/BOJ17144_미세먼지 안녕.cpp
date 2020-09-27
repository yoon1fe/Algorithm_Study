#include <iostream>
#include <vector>
#include <queue>
#include <fstream>

using namespace std;

struct vacuum {
	int row;
	int col;
	vector<pair<int, int>> cleanArea;
};

int N, M, T;
int board[51][51];
vector<vacuum> vacuums;

int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

int moveUpOrDown(int row, int col, int inc, vector<pair<int, int>>& cleanArea) {
	for (int r = row + inc; ; r+=inc) {
		if (r < 0 || r >= N)
			break;
		if (board[r][col] == -1)
			break;

		cleanArea.push_back({ r, col });
		row = r;
	}
	return row;
}

int moveLeftOrRight(int row, int col, int inc, vector<pair<int, int>>& cleanArea) {
	for (int c = col + inc; ; c += inc) {
		if (c < 0 || c >= M)
			break;
		cleanArea.push_back({ row, c });
		col = c;
	}
	return col;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	ifstream in("input.txt");

	in >> N >> M >> T;
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			in >> board[i][j];
			if (board[i][j] == -1) {
				vector<pair<int, int>> cleanArea;
				int row = i, col = j;
				if (cnt == 0) {
					col = moveLeftOrRight(row, col, 1, cleanArea);
					row = moveUpOrDown(row, col, -1, cleanArea);
					col = moveLeftOrRight(row, col, -1, cleanArea);
					moveUpOrDown(row, col, 1, cleanArea);
				}
				else {
					col = moveLeftOrRight(row, col, 1, cleanArea);
					row = moveUpOrDown(row, col, 1, cleanArea);
					col = moveLeftOrRight(row, col, -1, cleanArea);
					moveUpOrDown(row, col, -1, cleanArea);
				}
				vacuums.push_back({ i, j, cleanArea });
				cnt++;
			}
		}
	}

	int tmp[51][51];
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			tmp[i][j] = 0;


	for (int t = 0; t < T; t++) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] <= 0)
					continue;

				int addDust = board[r][c] / 5;
				
				if (addDust == 0)
					continue;

				int cnt = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nx = r + dx[dir];
					int ny = c + dy[dir];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;

					if (board[nx][ny] == -1)
						continue;

					tmp[nx][ny] += addDust;
					cnt++;
				}
				board[r][c] -= (addDust * cnt);
			}
		}

		for (int r = 0; r < N; r++)
			for (int c = 0; c < M; c++) {
				board[r][c] += tmp[r][c];
				tmp[r][c] = 0;
			}

		for (auto v : vacuums) {
			int save = 0;
			int move = 0;
			for (auto pos : v.cleanArea) {
				save = board[pos.first][pos.second];
				board[pos.first][pos.second] = move;
				move = save;
			}
		}
	}
	long long sum = 0;
	for(int i=0; i<N;i++)
		for (int j = 0; j < M; j++) {
			if (board[i][j] == -1)
				continue;
			sum += board[i][j];
		}

	cout << sum;
	return 0;
}