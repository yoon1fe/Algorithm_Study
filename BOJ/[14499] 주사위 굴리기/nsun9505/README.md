# [14499] 주사위 굴리기 - C++

## 분류
> 시뮬레이션

## 코드
```c++
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
```

## 풀이방법
주사위가 굴려졌을 때 각 면의 숫자들이 어떻게 이동하는지를 생각하면 된다.
주사위의 처음 전개도는 아래와 같다.
```
// 주사위 전개도
    [2]              
 [4][1][3]           
    [5]              
    [6]          
```
예를 들어 위의 전개도에서 오른쪽으로 굴린다고(동쪽으로 굴림) 할 때, 다음의 전개도가 된다.
```
// 주사위를 동쪽(1)으로 굴렸을 때 주사위 전개도
   [2]
[1][3][6]           : 3 -> 1, 1 -> 4, 6 -> 3 이동
   [5]
   [4]              : 4 -> 6 이동
```
- 바닥면은 무조건 1이고, 바닥면이 1일 때 주사위 윗변은 무조건 6으로 가정을 한다.
- 그러면 위와 같이 주사위의 각 위치의 값만 인덱스 변화에 맞게 바꿔주면 된다.
- 바꿔주는 코드는 `changeDicePos()`를 참조하면 된다.
- 각 방향으로 굴러질 때마다 주사위 위치의 값들이 변경되므로 입력된 방향으로 이동했을 때 유효한 범위라면 해당 격자의 값을 보고 문제에서 요구하는 사항을 만족시키면 된다.
- dice[1]에는 이동한 격자의 값이 0이면 dice[1]의 값이 복사가 되고, 0이 아니라면 격자 값이 dice[1]으로 복사된 후 해당 격자 값을 0으로 만들어 주면된다. 
- 그리고 dice[6]을 출력하면 된다.

> 참고 : https://mygumi.tistory.com/244

## 후기
주사위의 전개도를 보고 생각이 바로 나면 빨리 풀 수 있는 문제 같다. 주사위 전개도만 잘 봤더라면 금방 풀 문제!
전개도를 활용하지 않고 푸는 방법도 있겠지만 그런 방법을 생각해보느라 시간이 오래걸렸다.
그리고 언제나 문제를 잘 읽고 이해하자.