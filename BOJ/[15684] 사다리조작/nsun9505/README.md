# [15684] 사다리 조작 - C++

## 분류
> 구현
> 
> 브루트포스
>
> 백트래킹

## 코드
```c++
#include <iostream>
#pragma warning(disable:4996)
using namespace std;

int N, M, H;
bool board[31][11];
bool isBreak;

void solution(int cnt, int maxCnt, int row, int col) {
	if (isBreak)
		return;

	if (cnt > maxCnt)
		return;
	else if (cnt == maxCnt) {
 		for (int i = 1; i <= N; i++) {
			int col = i;
			for(int row = 1; row <= H; row++){
				if (col > 1 && board[row][col-1])
					col -= 1;
				else if (board[row][col])
					col += 1;
			}

			if (i != col)
				return;
		}

		isBreak = true;
		return;
	}

	for (int i = row; i <= H; i++) {
		for (int j = col; j < N; j++) {
			if (board[i][j])
				continue;
			else if (j > 1 && board[i][j - 1])
				continue;
			else if (j < N - 1 && board[i][j + 1])
				continue;

			board[i][j] = true;
			solution(cnt + 1, maxCnt, i, j+1);
			board[i][j] = false;
		}
		col = 1;
	}
}


int main(void) {
	ios_base::sync_with_stdio(0);
	freopen("input.txt", "rt", stdin);
	cin >> N >> M >> H;

	int a, b;
	for (int i = 0; i < M; i++) {
		cin >> a >> b;
		board[a][b] = true;
	}

	for (int i = 0; i < 4; i++) {
		isBreak = false;
		solution(0, i, 1, 1);
		if (isBreak) {
			cout << i;
			return 0;
		}
	}

	cout << -1;
	return 0;
}
```

## 문제풀이
1. 가로선을 놓기 위한 조건 체크(DFS)
   1. 현재 위치에 가로선이 있으면 놓지 못함
   1. 현재 가로 기준으로 이전 세로선에서 현재 세로선으로 오는 가로선이 있다면 놓지 못함.
   1. 현재 가로 기준으로 다음 세로선에 가로선이 있다면 가로선을 놓지 못함.
1. maxCnt만큼 가로선을 추가하였다면 출발 번호와 도착 번호가 같은지 검사
   - 같다면, 앞으로의 계산은 하지 않음.
   - 같지 않다면, 다시 탐색

## 후가
map을 사용하고, 가로선을 놓을 수 있는 곳을 미리 구해서 조합을 구했는데 시간초과..

그래서 map 안 쓰고, 가로선 놓을 수 있는 곳 미리 안 구하고 조합을 구했는데 통과...

쉽게 쉽게 갑시다.