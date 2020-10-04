# [17822] 원판 돌리기 - C++

## 분류
> 구현
>
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <deque>
#include <map>
#pragma warning(disable:4996)

using namespace std;
int N, M, T;
deque<deque<int>> board;
int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);

	cin >> N >> M >> T;

	for (int i = 0; i < N; i++) {
		deque<int> tmp;
		int val;
		for (int j = 0; j < M; j++) {
			cin >> val;
			tmp.push_back(val);
		}
		board.push_back(tmp);
	}

	int x, d, k;
	for (int i = 0; i < T; i++) {
		cin >> x >> d >> k;
		for (int i = 1; x*i-1 < board.size(); i++) {
			int row = x * i - 1;
			for (int loop = 0; loop < k; loop++) {
				if (d == 0) {
					int tmp = board[row].back();
					board[row].pop_back();
					board[row].push_front(tmp);
				} 
				else {
					int tmp = board[row].front();
					board[row].pop_front();
					board[row].push_back(tmp);
				}
			}
		}

		map<pair<int, int>, bool> changes;
		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 0)
					continue;
				sum += board[i][j];
				cnt++;

				int prev = j - 1;
				int next = j + 1;
				int down = i + 1;

				if (prev < 0)
					prev = M - 1;
				if (next >= M)
					next = 0;

				bool flag = false;
				if (board[i][j] == board[i][prev]) {
					flag = true;
					changes[{i, prev}] = true;
				}
				if (board[i][j] == board[i][next]) {
					flag = true;
					changes[{i, next}] = true;
				}
				if (down < N && board[i][j] == board[down][j]) {
					flag = true;
					changes[{down, j}] = true;
				}
				if (flag)
					changes[{i, j}] = true;
			}
		}
		if (changes.size() > 0) {
			for (auto cur : changes)
				board[cur.first.first][cur.first.second] = 0;
		}
		else {
			double avg = (double)sum / cnt;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (board[i][j] == 0)
						continue;

					if (board[i][j] > avg)
						board[i][j] -= 1;
					else if(board[i][j] < avg)
						board[i][j] += 1;
				}
			}
		}
	}

	int sum = 0;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < M; j++)
			sum += board[i][j];

	cout << sum;
	return 0;
}
```

## 문제 풀이
### 1. 원판을 회전시킨는 것은 deque를 사용하여 회전함.
- 시계방향으로 회전할 경우 맨 뒤의 원소를 tmp에 저장한 뒤에 pop_back 하고 front로 tmp를 삽입함으로써 시계 방향으로 1회 회전
- 반시계방향으로 회전할 경우 맨 앞의 원소를 tmp에 저장한 뒤에 pop_front 하고 back으로 tmp를 삽입함으로써 반시계 방향으로 1회 회전

### 2. 인접한 수를 찾아서 제거
- (i, j)의 위치에 있는 수와 (i, j-1), (i, j+1), (i+1, j)에 있는 수가 동일할 경우 제거 목록에 넣는다.
- 제거 목록은 map을 활용하여 중복이 없도록 함.
- j-1은 prev로 저장되는데 만약 j-1이 0보다 작으면 prev에는 M-1을 넣어서 맨 끝을 가리키도록 한다.
- j+1은 next로 저장되는데 만약 j+1이 M이랑 같거나 크다면 next에는 0을 넣어서 맨 처음을 가리키도록 한다.
- i+1은 down으로 저장되는데 만약 i+1이 N이랑 같거나 크다면 더 이상 비교할 상위 원판이 없으므로 계산하지 않는다.

### 3. 만약 인접한 수가 하나도 없다면
- 숫자를 모두 더하여 숫자의 수로 나누어서 평균을 구한다.
- 평균은 double 형으로 구해서 board를 전체 탐색한다.
- board[i][j]가 평균보다 작으면 +1, 크면 -1한다.

### 그리고 다시 1번부터 원판을 회전시키고, 인접한 수를 체크하고, 제거하거나 평균을 구하여 +1, -1을 하면 된다.

## 후기
인접한 수가 없을 경우에 평균 계산을 int로 하고 작거나 같은 것으로 하니 예제에 있는 마지막 테케 결과가 이상하게 나와서 몇 번을 디버깅을 함..

문제는 double으로 하고 그냥 크면 -1, 작으면 +1 했어야 했다.. 같은 경우는 필요없었던 것이다.