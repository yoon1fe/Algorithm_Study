# [19238] 스타트 택시 - C++

## 분류
> BFS
> 
> 구현
>
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>
#include <queue>
#include <map>
#include <algorithm>
#pragma warning(disable:4996)

using namespace std;

struct Element {
	int row;
	int col;
	int dist;
};

int N, M;
int tRow, tCol, fuel;
int board[21][21];
bool isVisited[21][21];
int dx[4] = { -1, 0, 1, 0 };
int dy[4] = { 0, 1, 0, -1 };
map<pair<int, int>, pair<int, int>> srcToDest;

bool cmp(Element a, Element b) {
	if (a.dist < b.dist)
		return true;
	else if (a.dist == b.dist) {
		if (a.row < b.row)
			return true;
		else if (a.row == b.row)
			return a.col < b.col;
		else
			return false;
	}
	else
		return false;
}

int getSrcToDestDist(int sRow, int sCol, int dRow, int dCol, int curFuel) {
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			isVisited[i][j] = false;

	queue<Element> Q;
	Q.push({ sRow, sCol, 0 });
	isVisited[sRow][sCol] = true;

	while (!Q.empty()) {
		Element pos = Q.front();
		Q.pop();

		if (pos.dist + 1 > curFuel)
			continue;

		for (int dir = 0; dir < 4; dir++) {
			int nx = pos.row + dx[dir];
			int ny = pos.col + dy[dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;

			if (isVisited[nx][ny] || board[nx][ny] == 1)
				continue;

			if (nx == dRow && ny == dCol)
				return pos.dist + 1;

			Q.push({ nx, ny, pos.dist + 1 });
			isVisited[nx][ny] = true;
		}
	}
	return -1;
}

Element getPassenger() {
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			isVisited[i][j] = false;

	queue<Element> Q;
	Q.push({ tRow, tCol, 0 });
	isVisited[tRow][tCol] = true;

	vector<Element> pList;
	if (board[tRow][tCol] != 0)
		return { tRow, tCol, 0 };

	while (!Q.empty()) {
		Element pos = Q.front();
		Q.pop();

		if (pos.dist + 1 > fuel)
			continue;
		
		for (int dir = 0; dir < 4; dir++) {
			int nx = pos.row + dx[dir];
			int ny = pos.col + dy[dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N)
				continue;

			if (isVisited[nx][ny] || board[nx][ny] == 1)
				continue;

			Q.push({ nx, ny, pos.dist + 1 });
			isVisited[nx][ny] = true;
			if (board[nx][ny] != 0)
				pList.push_back({ nx, ny, pos.dist + 1 });
		}
	}

	if (pList.size() == 0)
		return { -1, -1, -1 };

	sort(pList.begin(), pList.end(), cmp);

	return pList[0];
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	freopen("input.txt", "rt", stdin);

	cin >> N >> M >> fuel;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> board[i][j];

	cin >> tRow >> tCol;
	tRow -= 1;
	tCol -= 1;
	int sRow, sCol, dRow, dCol;
	for (int i = 0; i < M; i++) {
		cin >> sRow >> sCol >> dRow >> dCol;
		srcToDest[{sRow-1, sCol-1}] = { dRow-1, dCol-1 };
		board[sRow-1][sCol-1] = 2;
	}
	
	bool flag = false;
	while (!srcToDest.empty()) {
		Element src = getPassenger();
		if (src.row == -1 && src.col == -1) {
			flag = false;
			break;
		}

		fuel -= src.dist;
		pair<int, int> dest = srcToDest[{src.row, src.col}];
		int dist = getSrcToDestDist(src.row, src.col, dest.first, dest.second, fuel);
		if (dist == -1) {
			flag = false;
			break;
		}

		fuel += dist;
		srcToDest.erase({ src.row, src.col });
		tRow = dest.first;
		tCol = dest.second;
		board[src.row][src.col] = 0;
		flag = true;
	}

	if (flag)
		cout << fuel;
	else
		cout << -1;

	return 0;
}
```

## 문제 풀이
### 입력
board에 0은 이동 가능한 칸, 1은 벽, 2는 승객을 나타낸다.<br>
map을 사용하여 승객의 위치를 key로 하고, value는 목적지를 의미한다.<br>

### 1. 택시가 원료가 있는 만큼 승객이 있는 위치까지 이동
시작위치부터 승객이 있을 수 있다. 
- 승객의 도착지에 도착했는데 거기에 또 다른 승객이 있을 수 있다.
- 이 경우 해당 승객을 태우는 것이 다른 승객들과의 거리 중에서 가장 짧기 때문에(거리 : 0)
- 해당 승객을 태운다.
- 그러면 BFS를 돌리지 않아도 된다.

택시 위치에 승객이 없다면 택시 위치부터 승객들의 위치까지 거리를 구한다.
- 가장 짧은 거리에 있는 승객 선택
- 그런 승객이 여러 명이라면, 행 번호가 작은 승객을 선택
- 그런 승객이 여러 명이라면, 열 번호가 작은 승객을 선택
- 이를 위해 현재 연료로 갈 수 있는 범위 내에 있는 승객들을 list에 담고
- sorting하고 0번째 원소를 리턴한다.
- 만약 승객이 하나도 없다면 row = -1, col = -1를 리턴한다.

아직 승객은 남아 있지만, 현재 연료로 갈 수 있는 거리 내에 승객이 없다면(row = -1, col = -1)
- -1을 출력하며 프로그램을 종료한다.

현재 연료를 가지고 승객을 태울 수 있다면
- 현재 연료에서 승객까지의 거리를 빼준다.

### 2. 승객을 태우고 해당 승객의 목적지로 이동한다.
현재 연료를 기반으로 BFS를 사용해서 승객의 목적지로 이동할 수 있는지 검사한다.
- 만약 현재 연료로 승객이 원하는 목적지까지 갈 수 없다면 -1을 출력하고 프로그램을 종료한다.
- 현재 연료로 승객이 원하는 목적지까지 갈 수 있다면 
   ```
        현재 연료 = 현재 연료 - 목적지 거리 - (목적지 거리*2)
                 = 현재 연료 + 목적지 거리
   ```
   와 같이 계산한다.

### 3. 목적지 도착 후
목적지 도착 후 board에서 승객의 위치를 제거한다.

택시의 위치를 승객의 목적지 위치로 변경한다.

srcToDest라는 승객의 위치와 목저지 위치를 담은 map에서 방금 도착한 승객의 위치를 키로 사용하여 해당 승객의 정보를 지운다.

승객을 태웠기에 flag에 true를 넣어서 while문이 종료되었을 때 현재 연료를 출력할 수 있도록 해준다.
- while문은 현재 map이 empty인지 검사하여 empty()라면 더 이상 진행하지 않는다.
- flag는 연료가 부족하여 승객을 태우러 갈 수 없거나, 승객을 태우고 목적지까지 이동할 수 없는 경우에 false가 된다.
- flag는 승객을 태워서, 목적지까지 성공적으로 이동시키면 true가 된다.

## 후기
상반기 때는 시간이 없어서 못 풀었는데

아쉽다!