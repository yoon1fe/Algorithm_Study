# [19236] 청소년 상어 - C++

## 분류
> 구현
>
> 시뮬레이션
>
> 백트래킹

## 코드
```c++
#include <iostream>
#include <vector>
#include <map>
#pragma warning(disable:4996)
#define MAX 4
#define SHARK -1
#define EMPTY 0
using namespace std;

struct Fish {
	int num;
	int row;
	int col;
	int dir;
};

int dx[8] = { -1, -1, 0, 1, 1, 1, 0, -1 };
int dy[8] = { 0, -1, -1, -1, 0, 1, 1, 1 };
Fish shark;
int ans;

void solution(int sum, Fish board[4][4], map<int, Fish> fishMap) {
	Fish tmpBoard[4][4];
	for (int i = 0; i < MAX; i++)
		for (int j = 0; j < MAX; j++)
			tmpBoard[i][j] = board[i][j];

	for (auto cur : fishMap) {
		Fish fish = cur.second;
		while (true) {
			int nx = fish.row + dx[fish.dir];
			int ny = fish.col + dy[fish.dir];

			if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) {
				fish.dir = (fish.dir + 1) % 8;
				continue;
			}

			if (tmpBoard[nx][ny].num == SHARK) {
				fish.dir = (fish.dir + 1) % 8;
				continue;
			}

			if (tmpBoard[nx][ny].num >= 0) {
				Fish tmp = tmpBoard[nx][ny];

				tmp.row = fish.row; tmp.col = fish.col;
				fish.row = nx; fish.col = ny;

				tmpBoard[nx][ny] = fish;
				tmpBoard[tmp.row][tmp.col] = tmp;

				if (tmp.num != 0)
					fishMap[tmp.num] = tmp;
				fishMap[fish.num] = fish;
				break;
			}
		}
	}


	for (int i = 1; i < MAX; i++) {
		int nx = shark.row + (dx[shark.dir] * i);
		int ny = shark.col + (dy[shark.dir] * i);

		if (nx < 0 || ny < 0 || nx >= MAX || ny >= MAX)
			break;

		if (tmpBoard[nx][ny].num > 0) {
			tmpBoard[shark.row][shark.col] = { EMPTY, shark.row, shark.col, -1 };
			Fish bakShark = shark;
			Fish tmp = tmpBoard[nx][ny];

			shark.row = nx;
			shark.col = ny;
			shark.dir = tmp.dir;
			tmpBoard[nx][ny] = shark;
			fishMap.erase(tmp.num);

			solution(sum + tmp.num, tmpBoard, fishMap); 

			fishMap[tmp.num] = tmp;
			shark = bakShark;
			tmpBoard[shark.row][shark.col] = shark;
			tmpBoard[nx][ny] = tmp;
		}
	}

	if (ans < sum)
		ans = sum;
}


int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	freopen("input.txt", "rt", stdin);
	Fish board[4][4];
	map<int, Fish> fishMap;
	for (int i = 0; i < MAX; i++) {
		int num, dir;
		for (int j = 0; j < MAX; j++) {
			cin >> num >> dir;
			board[i][j] = { num, i, j, dir - 1 };
			fishMap[num] = { num, i, j, dir - 1 };
		}
	}

	int sum = board[0][0].num;
	fishMap.erase(board[0][0].num);
	shark = { SHARK, 0, 0, board[0][0].dir };
	board[0][0] = shark;
	
	solution(sum, board, fishMap);

	cout << ans;

	return 0;
}
```

## 문제 풀이
### 1. 입력
map을 사용하여 물고기들을 저장한다. <br>
board라는 4x4 공간에 물고기들의 정보를 저장한다.<br>
board[0][0]에 있는 물고기를 상어가 잡아 먹고, 상어의 위치를 0, 0으로 변경<br>
board[0][0]에 있는 물고기를 map에서 삭제한다.

### 2. 물고기 이동
1번부터 물고기를 이동시킨다.
- 이동하는 곳이 범위를 벗어난다면 반시계 방향으로 방향을 하나 이동
- 이동하는 곳이 상어가 있다면 반시계 방향으로 방향을 하나 이동
- 이동하는 곳이 빈칸이거나 다른 물고기가 있다면, 해당 물고기와 board에서의 위치를 변경한다.
   - map에서 물고기의 정보를 변경한다.
   - 반약 빈칸인 경우에는 map에 빈칸을 의미하는 번호 0이 들어갈 수 있기에 0은 변경하지 않고 이동하는 물고기의 정보만 변경해준다.

### 3. 상어 이동 및 먹기
상어의 방향은 이전에 먹은 물고기의 방향에 영향을 받는다.
- 상어가 (0,0)에 있고 방향이 6이라면, (1,1), (2,2), (3,3)에 물고기가 있으면 먹을 수 있고, 먹은 경우 해당 위치로 이동한다.
- 만약 먹을 물고기가 없을 경우 더 이상 재귀호출을 하지 않는다.
- 먹을 물고기가 있는 경우 해당 위치로 이동하여 해당 물고기를 map에서 삭제하고, board에서 상어가 이동하기 전의 위치를 EMPTY로 바꾸고 이동한다.
- 그리고 변경된 map, board, sum + 상어가 먹은 물고기 번호를 재귀호출의 인자로 넘긴다.
- 해당 위치로 갔을 때의 재귀 호출이 모두 끝이 났다면 상어는 원래 자리로 돌아가고, 먹었던 물고기도 다시 원위치 시킨다.
   - 예를 들어 상어가 (0,0)에 있고, 방향에 따라 (1,1), (2,2), (3,3)을 먹을 수 있을 때 (2,2)를 먹고 진행한 경우를 끝내고 왔을 때 (3,3)이나 (1,1)을 먹고 진행한 경우도 있기에 이를 위해 복구 해주어야 하기 때문이다.

### 4. 상어가 더 이상 움직이지 못함.
상어가 더 이상 먹을 물고기가 없는 경우 지금까지 먹은 물고기 번호의 합과 출력할 답을 비교한다.
- 지금까지 먹은 물고기의 합이 출력할 답보다 크다면 출력할 답을 지금까지 먹은 물고기의 합으로 갱신한다.

## 후기
최근에 나왔던 문제

물고기를 이동시키고, 상어가 먹고 딱 이것만 잘 하면 금방 풀 수 있다.

10.18에 역테 보기 전에 다시 한 번 리뷰를 해보자.