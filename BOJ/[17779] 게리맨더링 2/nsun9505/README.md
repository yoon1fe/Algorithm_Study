# [17779] 게리맨더링 2 - C++

## 분류
> 시뮬레이션
>
> 완전탐색
>
> 구현

## 코드 
```c++
#include <iostream>
#include <vector>
#include <map>
#pragma warning(disable:4996)

using namespace std;
int board[21][21];
int N;

bool isBoundary(int x, int y, int d1, int d2) {
	if (x + d1 + d2 <= N && y - d1 >= 1 && y - d1 < y && y < y + d2 && y + d2 <= N)
		return true;
	return false;
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	freopen("input.txt", "rt", stdin);
	
	cin >> N;
	for (int i = 1; i <= N; i++)
		for (int j = 1; j <= N; j++)
			cin >> board[i][j];

	int ans = 2147000000;
	for (int r = 1; r <= N; r++) {
		for (int c = 1; c <= N; c++) {
			for (int d1 = 1; d1 < N; d1++) {
				for (int d2 = 1; d2 < N; d2++) {
					if (!isBoundary(r, c, d1, d2))
						continue;

					int tmpBoard[21][21];
					for (int i = 1; i <= N; i++)
						for (int j = 1; j <= N; j++)
							tmpBoard[i][j] = 0;

					// 1번 경계선
					for (int i = 0; i <= d1; i++) {
						int nx = r + i;
						int ny = c - i;
						if (nx < 1 || ny < 1 || nx > N || ny > N)
							break;
						tmpBoard[nx][ny] = 5;
					}

					// 2번 경계선
					for (int i = 0; i <= d2; i++) {
						int nx = r + i;
						int ny = c + i;
						if (nx < 1 || ny < 1 || nx > N || ny > N)
							break;
						tmpBoard[nx][ny] = 5;
					}

					// 3번 경계선
					for (int i = 0; i <= d2; i++) {
						int nx = r + d1 + i;
						int ny = c - d1 + i;
						if (nx < 1 || ny < 1 || nx > N || ny > N)
							break;
						tmpBoard[nx][ny] = 5;
					}

					// 4번 경계선
					for (int i = 0; i <= d1; i++) {
						int nx = r + d2 + i;
						int ny = c + d2 - i;
						if (nx < 1 || ny < 1 || nx > N || ny > N)
							break;
						tmpBoard[nx][ny] = 5;
					}

					for (int i = 1; i <= N; i++) {
						int startCol = -1;
						for (int j = 1; j <= N; j++)
							if (tmpBoard[i][j] == 5) {
								startCol = j;
								break;
							}

						if (startCol == -1)
							continue;

						int endCol = -1;
						for(int j= startCol + 1; j<=N; j++)
							if (tmpBoard[i][j] == 5) {
								endCol = j;
								break;
							}

						if (endCol == -1)
							continue;

						for (int j = startCol + 1; j < endCol; j++)
							tmpBoard[i][j] = 5;
					}


					// 1번
					for (int i = 1; i < r + d1; i++)
						for (int j = 1; j <= c; j++) {
							if (tmpBoard[i][j] == 5)
								break;
							tmpBoard[i][j] = 1;
						}

					for (int i = 1; i <= r + d2; i++)
						for (int j = c+1; j <= N; j++) {
							if (tmpBoard[i][j] == 5)
								continue;
							tmpBoard[i][j] = 2;
						}

					for (int i = r+d1; i <= N; i++)
						for (int j = 1; j < c-d1+d2; j++) {
							if (tmpBoard[i][j] == 5)
								break;
							tmpBoard[i][j] = 3;
						}

					for (int i = r+d2 + 1; i <= N; i++)
						for (int j = c-d1+d2; j <= N; j++) {
							if (tmpBoard[i][j] == 5)
								continue;
							tmpBoard[i][j] = 4;
						}

					map<int, int> cnt;
					for (int i = 1; i <= N; i++)
						for (int j = 1; j <= N; j++)
							cnt[tmpBoard[i][j]] += board[i][j];

					int max = -2147000000;
					int min = 2147000000;
					for (auto cur : cnt) {
						if (max < cur.second)
							max = cur.second;

						if (cur.second < min)
							min = cur.second;
					}

					if (abs(max - min) < ans)
						ans = abs(max - min);
				}
			}
		}
	}

	cout << ans;
	
	return 0;
}
```

## 문제 풀이
선거구로 나누는 순서를 그대로 따라가며 구현하면 된다.
<br>

### 1. isBoundary로 경계션이 만들어지는지 점검한다.
```
(d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
```
- row, col, d1, d2 순으로 4중 포문을 돌리면서 위의 조건이 맞을 떄만 선거구를 나눈다.

### 2. 칸의 경계선을 구한다.
```
1. (x, y), (x+1, y-1), ..., (x+d1, y-d1)
2. (x, y), (x+1, y+1), ..., (x+d2, y+d2)
3. (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
4. (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
```
- 위에 속하는 위치에 5를 대입하여 경계선을 생성한다.

### 3. 경계선을 만들었으니 경계선 안 쪽으로 5를 채운다.
- tmpBoard를 전체 탐핵하면서 한 행에서 5시작하는 부분과 5가 끝나는 부분을 구한다.
- 5가 하나이거나, 아예 없으면 채워지지 않음.
- 5가 시작하는 부분과 끝나는 부분을 구했다면 그 사이를 5로 채워서 5의 선거구역을 만든다.

### 4. 5번 선거구에 포함되지 않은 구역을 채운다.
```
1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
```
- 위의 조건에 따라 각 선거구를 tmpBoard에 표시한다.
- tmpBoard는 5또는 0으로 채워져 있기에 5를 만나면 그 행에 대해서 선거구 표시를 멈추고 다음 행으로 넘어가면 된다.

### 5. tmpBoard를 전체적으로 탐색하면서 각 선거구의 인구수를 구한다.
- map을 사용해서 tmpBoard의 선거구 표시에 따라 board[i][j]를 더한다.
    ```c++
        // tmpBoard에는 각 구역의 선거구 번호가 있다.
        // board에는 각 구역의 인구 수가 저장되어 있다.
        map<int, int> cnt;
        for(int i=1; i<=N; i++)
            for(int j=1; j<=N; j++)
                cnt[tmpBoard[i][j]] += board[i][j];
    ```
- 각 선거구의 인구수를 구했다면, map을 처음부터 끝까지 탐색하면서 가장 작은 인구수를 가지는 선거구의 인구수와 가장 큰 인구수를 가지는 선거구의 인구수의 차이를 구해서 결과로 출력할 값보다 작은지 비교하여 작다면 ans에 저장한다.


## 후기
문제를 이해하는데 조금 시간이 걸렸는데, 그냥 따라서 구현하면 되는 거였다!