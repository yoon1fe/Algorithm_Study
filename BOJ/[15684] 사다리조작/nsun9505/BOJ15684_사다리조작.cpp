#include <iostream>
#pragma warning(disable:4996)
using namespace std;

int N, M, H;
bool board[31][11];
bool isBreak;

void solution(int cnt, int maxCnt, int row, int col) {
	if (isBreak)
		return;

	if (cnt > maxCnt)
		return;
	else if (cnt == maxCnt) {
 		for (int i = 1; i <= N; i++) {
			int col = i;
			for(int row = 1; row <= H; row++){
				if (col > 1 && board[row][col-1])
					col -= 1;
				else if (board[row][col])
					col += 1;
			}

			if (i != col)
				return;
		}

		isBreak = true;
		return;
	}

	for (int i = row; i <= H; i++) {
		for (int j = col; j < N; j++) {
			if (board[i][j])
				continue;
			else if (j > 1 && board[i][j - 1])
				continue;
			else if (j < N - 1 && board[i][j + 1])
				continue;

			board[i][j] = true;
			solution(cnt + 1, maxCnt, i, j+1);
			board[i][j] = false;
		}
		col = 1;
	}
}


int main(void) {
	ios_base::sync_with_stdio(0);
	freopen("input.txt", "rt", stdin);
	cin >> N >> M >> H;

	int a, b;
	for (int i = 0; i < M; i++) {
		cin >> a >> b;
		board[a][b] = true;
	}

	for (int i = 0; i < 4; i++) {
		isBreak = false;
		solution(0, i, 1, 1);
		if (isBreak) {
			cout << i;
			return 0;
		}
	}

	cout << -1;
	return 0;
}