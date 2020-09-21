#include <iostream>
#include <vector>
#pragma warning(disable:4996)
using namespace std;
int N;
int dx[4] = { 0, -1, 0, 1 };
int dy[4] = { 1, 0, -1, 0 };
bool board[101][101];

int main(void) {
	freopen("input.txt", "rt", stdin);
	cin >> N;
	int col, row, d, g;
	for (int i = 0; i < N; i++) {
		cin >> col >> row >> d >> g;

		board[row][col] = true;
		vector<int> path = { d };
		for (int i = 1; i <= g; i++) {
			for (int idx = path.size() - 1; idx >= 0; idx--) {
				path.push_back((path[idx] + 1)%4);
			}
		}

		for (auto p : path) {
			row += dx[p];
			col += dy[p];

			board[row][col] = true;
		}
		
	}

	int cnt = 0;
	for (int i = 0; i < 100; i++)
		for (int j = 0; j < 100; j++)
			if (board[i][j] && board[i + 1][j] && board[i][j + 1] && board[i + 1][j + 1])
				cnt++;
	cout << cnt;

	return 0;
}