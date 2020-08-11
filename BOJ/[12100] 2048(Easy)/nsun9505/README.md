# [12100] 2048(Easy)

## 분류
> 시뮬레이션
> 완전탐색

## 코드
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

## 풀이방법
2048 게임을 해봤다면 구현하면서 재미를 느꼈습니다. 또한, 문제를 해결하기 위해서 2048 게임을 다시 해봤습니다.
문제에서는 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하는 것이라서 완전탐색으로 충분히 구현이 가능한 것으로 생각이 들었다.
문제를 풀기 위해서
  1. 입력값으로 들어온 보드를 상하좌우로 돌렸을 때(?) 아니면 이해하기 쉽게 기울였을 때 기울인 쪽으로 각 숫자들이 이동하는 것이다.
  2. 각 방향으로 기울였을 때 격자에 있는 숫자가 움직이게 되는데 이때 같은 숫자는 합쳐지게 된다.
  3. 이때 합쳐진 숫자는 이번 이동에서는 또 한 번 합쳐질 수 없다.
    - 예를 들어, [ &nbsp;&nbsp; | 4 | &nbsp;&nbsp; | 2 | 2 ]가 있을 때 왼쪽으로 이동하게 한다면 
    - [ 4 | 4 | &nbsp;&nbsp; | &nbsp;&nbsp; | &nbsp;&nbsp; ]가 된다.
    - 즉, 오른쪽에 있던 두 개의 2가 합쳐지면서 4가 되면서 왼쪽에 있는 4와 또 합쳐질 수 있는 것처럼 보이지만
    - 실제로 게임에서도 합쳐지지 않는다.
    - 그래서 이미 합쳐졌는지 아닌지를 체크하는 bool 변수가 필요하다.
    - 만약에 예시에서 두 개의 4를 합치고 싶다면 다시 왼쪽으로 움직이도록 하면 된다.
  4. 위의 방법을 사용해서 재귀 함수의 depth가 5가 되면 격자에 있는 숫자 중 가장 큰 값을 취하면 된다.
  
## 후기
- 음 moveXXX 함수의 경우 겹치는 부분이 매우 많다.
- 이점을 개선하기 위해서 N x N 보드를 회전시키면 된다.
  - 내가 푼 것은 격자를 옮기는 것이지만, 코드의 양을 줄이기 위해서 N x N을 회전시키고 왼쪽으로만 옮기는 기능을 구현한다면
  - 좀 더 코드가 간결해질 것 같다.
