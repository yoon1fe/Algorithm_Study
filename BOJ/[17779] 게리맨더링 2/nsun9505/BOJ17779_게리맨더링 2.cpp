#include <iostream>
#include <vector>
#include <map>
#pragma warning(disable:4996)

using namespace std;
int board[21][21];
int N;

bool isBoundary(int x, int y, int d1, int d2) {
	if (x + d1 + d2 <= N && y - d1 >= 1 && y - d1 < y && y < y + d2 && y + d2 <= N)
		return true;
	return false;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);
	
	cin >> N;
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			cin >> board[i][j];

	int ans = 2147000000;
	for (int r = 1; r <= N; r++) {
		for (int c = 1; c <= N; c++) {
			for (int d1 = 1; d1 < N; d1++) {
				for (int d2 = 1; d2 < N; d2++) {
					if (!isBoundary(r, c, d1, d2))
						continue;

					int tmpBoard[21][21];
					for (int i = 1; i <= N; i++)
						for (int j = 1; j <= N; j++)
							tmpBoard[i][j] = 0;

					// 1번 경계선
					for (int i = 0; i <= d1; i++) {
						int nx = r + i;
						int ny = c - i;
						if (nx < 1 || ny < 1 || nx > N || ny > N)
							break;
						tmpBoard[nx][ny] = 5;
					}

					// 2번 경계선
					for (int i = 0; i <= d2; i++) {
						int nx = r + i;
						int ny = c + i;
						if (nx < 1 || ny < 1 || nx > N || ny > N)
							break;
						tmpBoard[nx][ny] = 5;
					}

					// 3번 경계선
					for (int i = 0; i <= d2; i++) {
						int nx = r + d1 + i;
						int ny = c - d1 + i;
						if (nx < 1 || ny < 1 || nx > N || ny > N)
							break;
						tmpBoard[nx][ny] = 5;
					}

					// 4번 경계선
					for (int i = 0; i <= d1; i++) {
						int nx = r + d2 + i;
						int ny = c + d2 - i;
						if (nx < 1 || ny < 1 || nx > N || ny > N)
							break;
						tmpBoard[nx][ny] = 5;
					}

					for (int i = 1; i <= N; i++) {
						int startCol = -1;
						for (int j = 1; j <= N; j++)
							if (tmpBoard[i][j] == 5) {
								startCol = j;
								break;
							}

						if (startCol == -1)
							continue;

						int endCol = -1;
						for(int j= startCol + 1; j<=N; j++)
							if (tmpBoard[i][j] == 5) {
								endCol = j;
								break;
							}

						if (endCol == -1)
							continue;

						for (int j = startCol + 1; j < endCol; j++)
							tmpBoard[i][j] = 5;
					}


					// 1번
					for (int i = 1; i < r + d1; i++)
						for (int j = 1; j <= c; j++) {
							if (tmpBoard[i][j] == 5)
								break;
							tmpBoard[i][j] = 1;
						}

					for (int i = 1; i <= r + d2; i++)
						for (int j = c+1; j <= N; j++) {
							if (tmpBoard[i][j] == 5)
								continue;
							tmpBoard[i][j] = 2;
						}

					for (int i = r+d1; i <= N; i++)
						for (int j = 1; j < c-d1+d2; j++) {
							if (tmpBoard[i][j] == 5)
								break;
							tmpBoard[i][j] = 3;
						}

					for (int i = r+d2 + 1; i <= N; i++)
						for (int j = c-d1+d2; j <= N; j++) {
							if (tmpBoard[i][j] == 5)
								continue;
							tmpBoard[i][j] = 4;
						}

					map<int, int> cnt;
					for (int i = 1; i <= N; i++)
						for (int j = 1; j <= N; j++)
							cnt[tmpBoard[i][j]] += board[i][j];

					int max = -2147000000;
					int min = 2147000000;
					for (auto cur : cnt) {
						if (max < cur.second)
							max = cur.second;

						if (cur.second < min)
							min = cur.second;
					}

					if (abs(max - min) < ans)
						ans = abs(max - min);
				}
			}
		}
	}

	cout << ans;
	
	return 0;
}