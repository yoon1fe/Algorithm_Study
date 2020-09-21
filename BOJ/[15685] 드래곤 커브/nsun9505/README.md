# [15685] 드래곤 커브 - C++

## 분류
> 구현
> 
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>
using namespace std;
int N;
int dx[4] = { 0, -1, 0, 1 };
int dy[4] = { 1, 0, -1, 0 };
bool board[101][101];

int main(void) {
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
```

## 문제풀이
드래곤 커브를 만드는 과정에서 규칙을 찾았다.
- 만약에 0세대의 방향이 0일때, 시계 방향으로 90도 회전하면 방향은 1이 된다.(왼쪽으로 가는 방향에서 위로 올라가게 되면 시계 방향으로 회전한 것을 만들 수 있음)
- 1세대를 위한 방향이 {0, 1}이 된다. 
- 2세대 드래곤 커브를 만들기 위해서 현재 방향에 대한 정보에서 역순으로 읽으면서 (방향+1) % 4를 하면 다음 세대 드래곤 커브가 생성된다.

드래곤 커브를 모두 생성했다면 시작 위치부터 방향대로 드래곤커브를 그리며 나간다.
- 방문하는 위치마다 방문했다는 표시를 남긴다.

전체적으로 1x1 정사각형 꼭짓점이 모두 true인 정사각형을 카운트하여 출력한다.

## 후기
방향에 대해서 아이디어를 얻어서 금방 풀 수 있었다!