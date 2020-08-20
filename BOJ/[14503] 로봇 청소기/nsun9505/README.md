# [14503] 로봇청소기 - C++

## 분류
> 시뮬레이션

## 코드
```c++
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
```

## 풀이 방법
문제에서 원하는 대로 시뮬레이션을 돌리면 된다.
- 4방향을 체크하면서 청소할 곳이 있으면 이동하고, 없으면 이동하지 않는다.
- 4방향 모두 청소가 되어 있으면 방향을 유지하면서 뒤로 이동한다.
  - 코드에서는 '0'인 경우에만 이동하므로 벽이든 청소가 된 곳이든 상관없다. 청소가 안 된 곳으로 이동할 수 있냐 없냐가 문제인것 같다.
- 뒤로 이동할 때 뒤가 벽이라면 프로그램을 종료한다.
- 그리고 가장자리가 벽(1)으로 되어 있어서 로봇이 격자에서 나가는 일은 없다.

## 후기
- 상반기 삼성전자 인턴을 준비하면서 풀어 본 문제
- 그때는 어떻게 풀지 잘 몰랐고, 지금보다 시간이 배 아니 좀 오래 걸렸던 것 같았는데 다시 풀어보니 그 전보다는 빨리 풀 수 있었다.
- 회고가 중요한 것 같다. 
