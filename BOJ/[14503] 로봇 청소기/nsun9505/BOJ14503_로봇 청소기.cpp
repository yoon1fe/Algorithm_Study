#include <iostream>

using namespace std;

char board[51][51];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int N, M;
int rRow, rCol, rDir;

int getLeftDirection(int curDir) {
	return (curDir - 1) == -1 ? 3 : curDir - 1;
}

int getBack(int curDir) {
	return (curDir + 2) % 4;
}

int main(void) {
	cin >> N >> M;
	cin >> rRow >> rCol >> rDir;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			cin >> board[i][j];

	board[rRow][rCol] = '2';
	int cnt = 1;
	while (true) {
		bool flag = false;
		for (int i = 0; i < 4; i++) {
			rDir = getLeftDirection(rDir);
			int nx = rRow + dx[rDir];
			int ny = rCol + dy[rDir];

			if (board[nx][ny] == '1')
				continue;

			if (board[nx][ny] == '0') {
				rRow = nx;
				rCol = ny;
				flag = true;
				board[nx][ny] = '2';
				cnt++;
				break;
			}
		}

		if (!flag) {
			int backRow = rRow + dx[getBack(rDir)];
			int backCol = rCol + dy[getBack(rDir)];

			if (board[backRow][backCol] == '1')
				break;

			rRow = backRow;
			rCol = backCol;
		}
	}

	cout << cnt;

	return 0;
}