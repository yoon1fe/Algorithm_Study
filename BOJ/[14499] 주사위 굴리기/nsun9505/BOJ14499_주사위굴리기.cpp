#include <iostream>
using namespace std;
int dice[7];
int board[21][21];
int dx[4] = { 0, 0, -1, 1 };
int dy[4] = { 1, -1, 0, 0 };

void changeDicePos(int dir) {
	int tmp_dice[7];
	for (int i = 1; i <= 6; i++)
		tmp_dice[i] = dice[i];

	if (dir == 1) {
		dice[1] = tmp_dice[3];
		dice[3] = tmp_dice[6];
		dice[4] = tmp_dice[1];
		dice[6] = tmp_dice[4];
	}
	else if (dir == 2) {
		dice[1] = tmp_dice[4];
		dice[3] = tmp_dice[1];
		dice[4] = tmp_dice[6];
		dice[6] = tmp_dice[3];
	}
	else if (dir == 3) {
		dice[1] = tmp_dice[2];
		dice[2] = tmp_dice[6];
		dice[5] = tmp_dice[1];
		dice[6] = tmp_dice[5];
	}
	else {
		dice[1] = tmp_dice[5];
		dice[2] = tmp_dice[1];
		dice[5] = tmp_dice[6];
		dice[6] = tmp_dice[2];
	}
}

int main(void) {
	int N, M, row, col, cmdCnt;
	cin >> N >> M >> row >> col >> cmdCnt;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			cin >> board[i][j];

	dice[1] = board[row][col];
	int cmdDir;
	for (int i = 0; i < cmdCnt; i++) {
		cin >> cmdDir;

		int nx = row + dx[cmdDir - 1];
		int ny = col + dy[cmdDir - 1];

		if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

		changeDicePos(cmdDir);
		if (board[nx][ny] == 0) {
			board[nx][ny] = dice[1];
		}
		else {
			dice[1] = board[nx][ny];
			board[nx][ny] = 0;
		}
		row = nx;
		col = ny;

		cout << dice[6] << '\n';
	}
	return 0;
}