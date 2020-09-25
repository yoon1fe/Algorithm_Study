# [16236] 아기 상어 - C++

## 분류
> 시뮬레이션
>
> BFS

## 코드
```c++
#include <iostream>
#include <vector>
#include <queue>
#include <map>
#include <algorithm>
#include <fstream>

using namespace std;

struct food {
	int dist;
	int row;
	int col;
};
int N;
int board[21][21];
bool isVisited[21][21];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };

bool cmp(food a, food b) {
	if (a.dist < b.dist)
		return true;
	else if (a.dist == b.dist) {
		if (a.row < b.row)
			return true;
		else if (a.row == b.row)
			return a.col < b.col;
	}
	return false;
}

int main(void) {
	ifstream in("input.txt");
	ios_base::sync_with_stdio(false);
	in >> N;
	pair<int, int> start;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			in >> board[i][j];
			if (board[i][j] == 9) {
				start.first = i;
				start.second = j;
				board[i][j] = 0;
			}
		}
	}
	in.close();

	int ans = 0;
	int babySharkSize = 2;
	int eatCnt = 0;
	while (true) {
		vector<food> foods;
		queue<food> Q;
		Q.push({ 0, start.first, start.second });
		isVisited[start.first][start.second] = true;

		while (!Q.empty()) {
			food pos = Q.front();
			Q.pop();

			for (int dir = 0; dir < 4; dir++) {
				int nx = pos.row + dx[dir];
				int ny = pos.col + dy[dir];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (board[nx][ny] > babySharkSize || isVisited[nx][ny])
					continue;

				if (board[nx][ny] != 0 && board[nx][ny] < babySharkSize)
					foods.push_back({ pos.dist + 1, nx, ny });

				isVisited[nx][ny] = true;
				Q.push({ pos.dist + 1, nx, ny });
			}
		}

		if (foods.size() == 0)
			break;

		sort(foods.begin(), foods.end(), cmp);
		food f = foods[0];
		board[f.row][f.col] = 0;
		eatCnt += 1;
		if (eatCnt == babySharkSize) {
			eatCnt = 0;
			babySharkSize += 1;
		}
		ans += f.dist;
		start.first = f.row;
		start.second = f.col;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				isVisited[i][j] = false;
	}

	cout << ans;
	return 0;
}
```

## 문제 풀이
food 자료구조
- dist : 아기상어와의 거리
- row : 물고기 row
- col : 물고리 col

1. 아기 상어의 위치에서 BFS로 탐색한다.
   - 탐색 시 현재 아기 상어 크기보다 큰 물고기가 있다면 그 위치으로는 BFS 탐색에 포함하지 않는다.
   - 아기 상어의 크기와 물고기의 크기가 같다면 지나갈 수 있다.
   - 아기 상어의 크기보다 물고기의 크기가 작다면 foods라는 vector에 저장한다.
1. 모든 맵에 대해서 BFS를 통해 탐색을 끝냈다면
   - foods에 담긴 물고기가 없다면 더 이상 BFS를 할 필요가 없으므로 종료한다.
   - foods에 담긴 물고기가 있다면 문제에서 원하는 대로 sorting한다.
        ```
        - 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
        - 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
           - 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
        ```
        - 이를 위해 cmp라는 함수를 사용해서 foods 벡터를 정렬했다.
    
    - 문제에서 요구하는 조건을 충족하는 물고기는 cmp함수로 정렬된 foods 벡터의 첫 번째 원소에 위치하므로 foods[0]를 가져온다.
       - foods[0]의 dist 원소는 아기 상어와의 거리 또는 이동 시간이므로 출력할 답에 더해준다.
       - 아기 상어의 위치를 먹은 물고기의 위치로 변경하고, 해당 물고기가 위치하던 곳의 값을 0으로 변경한다.
       - 그리고 다시 BFS를 돌려서 1번부터 다시 시작한다.

## 후기
3번째 푸는 문제!

한달 전에 풀었던 문제인데 조건이 머리에 남아 있어서 안 보고 풀다가 실수를 했다.

자만하지 말고 문제를 꼼꼼히 읽어보고 풀자!