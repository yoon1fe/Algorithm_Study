# [14890] 경사로 - C++

## 분류
> 시뮬레이션

## 코드
```c++
#include <iostream>
#include <vector>

using namespace std;
int N, L;
int map[101][101];
bool isPut[101][101];

void rotate() {
	int tmpMap[101][101];
	for (int i = 0; i < N; i++) {
		int k = 0;
		for (int j = N - 1; j >= 0; j--)
			tmpMap[i][k++] = map[j][i];
	}

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			map[i][j] = tmpMap[i][j];
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin >> N >> L;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> map[i][j];

	int ret = 0;
	for (int loop = 0; loop < 2; loop++) {
		for (int i = 0; i < N; i++) {
			bool flag = true;
			for (int cur = 0; cur < N; cur++) {
				if (cur + 1 < N) {
					if (map[i][cur] == map[i][cur + 1])
						continue;
					else if (map[i][cur] - map[i][cur + 1] == 1) {
						if (cur + L < N) {
							int cnt = 0;
							for (int k = cur + 1; k <= cur + L; k++)
								if (map[i][cur] - map[i][k] == 1 && !isPut[i][k])
									cnt++;
							if (cnt != L) {
								flag = false;
								break;
							}
							for (int k = cur + 1; k <= cur + L; k++)
								isPut[i][k] = true;
							cur += L-1;
						}
						else {
							flag = false;
							break;
						}
					}
					else if (map[i][cur] - map[i][cur + 1] == -1) {
						if (cur - L + 1 >= 0) {
							int cnt = 0;
							for (int k = cur - L + 1; k <= cur; k++)
								if (map[i][k] - map[i][cur+1] == -1 && !isPut[i][k])
									cnt++;
							if (cnt != L) {
								flag = false;
								break;
							}

							for (int k = cur - L + 1; k <= cur; k++)
								isPut[i][k] = true;
						}
						else {
							flag = false;
							break;
						}
					}
					else {
						flag = false;
						break;
					}
				}
				else
					break;
			}
			if (flag)
				ret += 1;
		}
		rotate();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				isPut[i][j] = false;
	}
	cout << ret;

	return 0;
}
```

## 문제 풀이
현재 칸에서 앞에 칸의 값이랑 뺄셈을 하여 1인, -1인지 알아봅니다.
- -1이라면 올라가는 경사로를 생성해야 하고, 1이라면 내려가는 경사로를 생성해야 합니다.
- 올라가는 경사로를 선택 시 현재 칸에서 경사로 길이만큼 뒤부터 평평한지 체크, 평평하지 않거나 이미 경사로가 있다면 해당 길은 더 이상 경사로를 만들 수 없고 지나갈 수 없는 길이다.
- 내려가는 경사로를 생성 시 현재 칸에서 경사로 길이만큼 앞까지 평평한지 체크, 평평하지 않거나 이미 경사로가 있다면 해당 길은 더 이상 경사로를 만들 수 없고 지나갈 수 없는 길이다.
- 만약 경사로를 생성할 수 있다면, 경사로가 생성되었다고 표시를 한 뒤에 다시 올바른 길인지 탐색한다.


## 후기
풀어보았던 문제라서 빨리 풀 수 있었다.

처음 풀었을 떄는 하루종일 걸렸던 것 같은데 이번에는 빨리 끝났다.

연습을 많이하자.