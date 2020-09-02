# [2020 KAKAO BLIND RECRUITMENT] 벽돌 이동하기 - C++

## 분류
> BFS
>
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>
#include <queue>
#include <map>
#pragma warning(disable:4996)

using namespace std;

map<vector<int>, int> isVisited;
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
int N;

bool isOutOfIndex(int row, int col) {
	if (row < 0 || col < 0 || row >= N || col >= N)
		return true;
	return false;
}

bool isArrive(int row, int col) {
	if (row == N - 1 && col == N - 1)
		return true;
	return false;
}

int solution(vector<vector<int>> board) {
	int ans = 0;
	N = (int)board.size();
	queue<vector<int>> Q;
	vector<int> r = { 0, 0, 0, 1 };
	Q.push(r);
	isVisited[r] = 0;

	while (!Q.empty()) {
		vector<int> pos = Q.front();
		Q.pop();


		for (int dir = 0; dir < 4; dir++) {
			vector<int> newPos = { 0,0,0,0 };
			newPos[0] = pos[0] + dx[dir];
			newPos[1] = pos[1] + dy[dir];
			newPos[2] = pos[2] + dx[dir];
			newPos[3] = pos[3] + dy[dir];

			if (isOutOfIndex(newPos[0], newPos[1]) 
				|| isOutOfIndex(newPos[2], newPos[3]))
				continue;

			if (isVisited.count(newPos))
				continue;

			if (board[newPos[0]][newPos[1]] == 1)
				continue;
			if (board[newPos[2]][newPos[3]] == 1)
				continue;

			if (isArrive(newPos[0], newPos[1]) || isArrive(newPos[2], newPos[3]))
				return isVisited[pos] + 1;

			isVisited[newPos] = isVisited[pos] + 1;
			Q.push(newPos);
		}

		if (pos[0] == pos[2]) {
			//위
			if (pos[0] - 1  >= 0 && pos[0] - 1 < N) {
				if (board[pos[0]-1][pos[1]] == 0
					&& board[pos[2]-1][pos[3]] == 0) {
					vector<int> tmp = { pos[0] - 1, pos[1], pos[0], pos[1] };
					if (isVisited.count(tmp) == 0) {
						isVisited[tmp] = isVisited[pos] + 1;
						Q.push(tmp);
					}
					tmp = { pos[0] - 1, pos[3], pos[2], pos[3] };
					if (isVisited.count(tmp) == 0) {
						isVisited[tmp] = isVisited[pos] + 1;
						Q.push(tmp);
					}
				}
			}
			// 아래
			if (pos[0] + 1 >= 0 && pos[2] + 1 < N){
				if (board[pos[0] + 1][pos[1]] == 0
					&& board[pos[2] + 1][pos[3]] == 0) {
					
					vector<int> tmp = { pos[0], pos[1], pos[0] + 1, pos[1] };
					if (isVisited.count(tmp) == 0) {
						isVisited[tmp] = isVisited[pos] + 1;
						Q.push(tmp);
					}
					tmp = { pos[2],pos[3], pos[0] + 1, pos[3] };
					if (isVisited.count(tmp) == 0) {
						isVisited[tmp] = isVisited[pos] + 1;
						Q.push(tmp);
					}
				}
			}
		}
		else if (pos[1] == pos[3]) {
			// 왼쪽
			if (pos[1] - 1 >= 0 && pos[1] - 1 < N) {
				if (board[pos[0]][pos[1] - 1] == 0
					&& board[pos[2]][pos[3] - 1] == 0) {

					vector<int> tmp = { pos[0], pos[1] - 1, pos[0], pos[1] };
					if (isArrive(tmp[0], tmp[1]) || isArrive(tmp[2], tmp[3]))
						return isVisited[pos] + 1;
					if (isVisited.count(tmp) == 0) {
						isVisited[tmp] = isVisited[pos] + 1;
						Q.push(tmp);
					}

					tmp = { pos[2], pos[1] - 1, pos[2], pos[3] };
					if (isArrive(tmp[0], tmp[1]) || isArrive(tmp[2], tmp[3]))
						return isVisited[pos] + 1;
					if (isVisited.count(tmp) == 0) {
						isVisited[tmp] = isVisited[pos] + 1;
						Q.push(tmp);
					}
				}
			}
			// 오른쪽
			if (pos[1] + 1 >= 0 && pos[1] + 1 < N) {
				if (board[pos[0]][pos[1] + 1] == 0
					&& board[pos[2]][pos[3] + 1] == 0) {
					vector<int> tmp = { pos[0], pos[1], pos[0], pos[1] + 1 };
					if (isArrive(tmp[0], tmp[1]) || isArrive(tmp[2], tmp[3]))
						return isVisited[pos] + 1;

					if (isVisited.count(tmp) == 0) {
						isVisited[tmp] = isVisited[pos] + 1;
						Q.push(tmp);
					}

					tmp = { pos[2], pos[3], pos[2], pos[3] + 1 };
					if (isArrive(tmp[0], tmp[1]) || isArrive(tmp[2], tmp[3]))
						return isVisited[pos] + 1;
					if (isVisited.count(tmp) == 0) {
						isVisited[tmp] = isVisited[pos] + 1;
						Q.push(tmp);
					}
				}
			}
		}
	}
	return ans;
}

int main(void) {
	freopen("input.txt", "rt", stdin);
	int n;
	vector<vector<int>> board;

	cin >> n;
	for (int i = 0; i < n; i++) {
		vector<int> tmp;
		int val;
		for (int j = 0; j < n; j++) {
			cin >> val;
			tmp.push_back(val);
		}
		board.push_back(tmp);
	}

	cout << solution(board);

	return 0;
}
```

## 풀이 방법
일단 코드가 어마어마 하다.. 중복되는 곳이 너무 많아보임..

저번에 삼성 코테를 풀었을 때 방문여부를 4차원 배열로 했던 기억이 났고, 그것 말고도 map으로 로봇이 움직인 위치를 카운트하면 될 것 같아서
방문 여부를 map으로 카운트 했다.

로봇이 움직이는 방법은 아래와 같다.
1. 상, 하, 좌, 우로 움직일 수 있는지 체크
   - 움직일 수 있다면 해당 위치를 큐에 담는다.

1. 상하좌우 모두를 살펴보고, 로봇을 회전할 수 있는지 체크한다.
   - 먼저 로봇이 수평 모양인지 수직 모양인지에 따라 회전하는 방법이 다르다.
   - 수평일 경우 로봇의 위쪽으로 1(벽)이 하나라도 있으면 회전을 못하며, 로봇 아래쪽에 1(벽)이 하나라도 존재하면 회전하지 못한다.
   - 수직일 경우 로봇의 오른쪽으로 1(벽)이 하나라도 있으면 회전하지 못하며, 로봇 왼쪽에 1(벽)이 하나라도 존재하면 회전하지 못한다.
   - 결론은 회전하려는 방향으로 1이 하나라도 없어야 한다는 뜻이고, 0만이 존재해야 한다.
   - 또한, 이미 방문했던 위치를 체크하기 위해 map을 사용했고, 이미 방문한 위치라면 큐에 넣지 않는다.
   - 조건을 충족한다면 로봇을 회전시키고, 시간을 +1 하고, 큐에 넣는다.

1. N, N에 하나라도 도착한다면 종료한다.

## 후기
4차원 배열로 로봇의 방문여부를 체크할 수 있을 것 같다.
참 문제 재미있게 내는것 같다.
완전 빡꾸현