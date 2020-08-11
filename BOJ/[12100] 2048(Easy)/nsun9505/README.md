# [12100] 2048(Easy)

```c++
#include <iostream>
#include <fstream>

using namespace std;

int input[21][21];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int N;
int ans = 0;

void moveUp(int tmp_board[21][21], int dir) {
	bool isMoved[21][21];
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			isMoved[i][j] = false;

	for (int row = 0; row < N; row++) {
		for (int col = 0; col < N; col++) {
			if (tmp_board[row][col] != 0) {
				int curRow = row;
				int curCol = col;
				while (true) {
					int nx = curRow + dx[dir];
					int ny = curCol + dy[dir];

					if (nx >= N || ny >= N || nx < 0 || ny < 0)
						break;

					if (tmp_board[nx][ny] == 0) {
						tmp_board[nx][ny] = tmp_board[curRow][curCol];
						tmp_board[curRow][curCol] = 0;
						curRow = nx;
						curCol = ny;
					}
					else if (!isMoved[nx][ny] && tmp_board[nx][ny] == tmp_board[curRow][curCol]) {
						tmp_board[nx][ny] *= 2;
						tmp_board[curRow][curCol] = 0;
						isMoved[nx][ny] = true;
						break;
					}
					else
						break;
				}
			}
		}
	}
}

void moveRight(int tmp_board[21][21], int dir) {
	bool isMoved[21][21];
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			isMoved[i][j] = false;

	for (int col = N-1; col >= 0; col--) {
		for (int row = 0; row < N; row++) {
			if (tmp_board[row][col] != 0) {
				int curRow = row;
				int curCol = col;
				while (true) {
					int nx = curRow + dx[dir];
					int ny = curCol + dy[dir];

					if (nx >= N || ny >= N || nx < 0 || ny < 0)
						break;

					if (tmp_board[nx][ny] == 0) {
						tmp_board[nx][ny] = tmp_board[curRow][curCol];
						tmp_board[curRow][curCol] = 0;
						curRow = nx;
						curCol = ny;
					}
					else if (!isMoved[nx][ny] && tmp_board[nx][ny] == tmp_board[curRow][curCol]) {
						tmp_board[nx][ny] *= 2;
						tmp_board[curRow][curCol] = 0;
						isMoved[nx][ny] = true;
						break;
					}
					else
						break;

				}
			}
		}
	}
}

void moveDown(int tmp_board[21][21], int dir) {
	bool isMoved[21][21];
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			isMoved[i][j] = false;
	for (int row = N - 1; row >= 0; row--) {
		for (int col = 0; col < N; col++) {
			if (tmp_board[row][col] != 0) {
				int curRow = row;
				int curCol = col;
				while (true) {
					int nx = curRow + dx[dir];
					int ny = curCol + dy[dir];

					if (nx >= N || ny >= N || nx < 0 || ny < 0)
						break;

					if (tmp_board[nx][ny] == 0) {
						tmp_board[nx][ny] = tmp_board[curRow][curCol];
						tmp_board[curRow][curCol] = 0;
						curRow = nx;
						curCol = ny;
					}
					else if (!isMoved[nx][ny] && tmp_board[nx][ny] == tmp_board[curRow][curCol]) {
						tmp_board[nx][ny] *= 2;
						tmp_board[curRow][curCol] = 0;
						isMoved[nx][ny] = true;
						break;
					}
					else
						break;

				}
			}
		}
	}
}

void moveLeft(int tmp_board[21][21], int dir) {
	bool isMoved[21][21];
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			isMoved[i][j] = false;
	for (int col = 0; col < N; col++) {
		for (int row = 0; row < N; row++) {
			if (tmp_board[row][col] != 0) {
				int curRow = row;
				int curCol = col;
				while (true) {
					int nx = curRow + dx[dir];
					int ny = curCol + dy[dir];

					if (nx >= N || ny >= N || nx < 0 || ny < 0)
						break;

					if (tmp_board[nx][ny] == 0) {
						tmp_board[nx][ny] = tmp_board[curRow][curCol];
						tmp_board[curRow][curCol] = 0;
						curRow = nx;
						curCol = ny;
					}
					else if (!isMoved[nx][ny] && tmp_board[nx][ny] == tmp_board[curRow][curCol]) {
						tmp_board[nx][ny] *= 2;
						tmp_board[curRow][curCol] = 0;
						isMoved[nx][ny] = true;
						break;
					}
					else
						break;

				}
			}
		}
	}
}

void solve(int cur, int board[21][21]) {
	if (cur == 5) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (board[i][j] > ans)
					ans = board[i][j];

		return;
	}

	for (int dir = 0; dir < 4; dir++) {
		int tmp[21][21];
		for (int r = 0; r < N; r++)
			for (int c = 0; c < N; c++)
				tmp[r][c] = board[r][c];

		if (dir == 0)
			moveUp(tmp, dir);
		else if (dir == 1)
			moveRight(tmp, dir);
		else if (dir == 2)
			moveDown(tmp, dir);
		else
			moveLeft(tmp, dir);
		solve(cur + 1, tmp);
	}
}

int main(void) {
	ifstream in("input.txt");
	ios::sync_with_stdio(0);
	cin.tie(0);

	in >> N;	
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			in >> input[i][j];

	solve(0, input);
	cout << ans;

	return 0;
}
```