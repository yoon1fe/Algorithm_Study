# [17281] ⚾ - C++

## 분류
> 구현
>
> 완전탐색

## 코드
```c++
#include <iostream>
#include <vector>
#include <algorithm>
#pragma warning(disable:4996)

using namespace std;
int N;
bool isUsed[10];
int order[10];
int innings[51][10];
int ans = -2147000000;

void solution(int cur) {
	if (cur > 9) {
		if (order[4] != 1)
			return;

		int hitter = 1;
		int score = 0;
		for (int i = 0; i < N; i++) {
			int outCnt = 0;
			int base[4] = { 0, 0, 0, 0 };
			while (outCnt < 3) {
				if (innings[i][order[hitter]] == 0)
					outCnt++;
				else {
					base[0] = 1;
					for (int pos = 3; pos >= 0; pos--) {
						if (base[pos] == 0)
							continue;

						if (pos + innings[i][order[hitter]] >= 4) {
							base[pos] = 0;
							score++;
						}
						else {
							base[pos + innings[i][order[hitter]]] = 1;
							base[pos] = 0;
						}
					}
				}
			
				hitter += 1;
				if (hitter >= 10)
					hitter = 1;
			}
		}

		if (ans < score)
			ans = score;
		return;
	}

	for (int i = 1; i <= 9; i++) {
		if (!isUsed[i]) {
			isUsed[i] = true;
			order[cur] = i;
			solution(cur + 1);
			isUsed[i] = false;
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	freopen("input.txt", "rt", stdin);

	cin >> N;
	for (int i = 0; i < N; i++)
		for (int j = 1; j < 10; j++)
			cin >> innings[i][j];

	solution(1);
	cout << ans;

	return 0;
}
```

## 문제 풀이
1~9번 선수의 순열을 구한다.
- 대신 1번 선수가 4번 타자가 된 경우만 계산한다.

그리고 문제에서 원하는 대로 시뮬레이션을 돌린다.
- hiiter는 현재 선수를 의미한다.
- hitter는 전체 이닝이 종료될 때까지 새로 초기화 되지 않는다.

현재 이닝에서 선수의 결과가 0인 경우 outCnt를 하나 증가시킨다.
- 만약 outCnt가 3이라면 해당 이닝을 종료한다.

현재 이닝에서 선수의 결과가 0이 아닌 경우 3, 2, 1, 0(선수) 순으로 현재 타자의 결과에 따라 이동을 한다.
- pos(pos = 3, 2, 1, 0)루가 만약 0이라면 이동하지 않는다.
- base[pos]가 0이 아니라면 해당 선수를 방금 공을 친 선수의 결과만큼 이동시킨다.
- 만약 그 결과가 4 이상이라면 해당 pos루에 있던 선수는 홈으로 들어가게 되고 score를 하나 증가시킨다.
- 만약 그 결과가 4 미만이라면 해당 pos루에 있던 선수를 안타면 1칸, 2루타면 2칸, 3루타면 3칸 이동시킨다.

선수의 결과가 0이든 아니든 다음 선수를 가리키기 위해 hitter를 1증가 시킨다.
- 만약 hitter가 10 이상이라면 1번 선수로 바꾸기 위해 hitter를 1로 변경한다.
- 그리고 while 조건에서 outCnt가 3 이상이라면 while문을 종료하고 다음 이닝을 진행하도록 한다.
- 다음 이닝을 진행할 떄는 hitter는 유지되고, base(1루, 2루, 3루)는 0으로 초기화된다.

선수의 순열 중 가장 점수가 높은 순서에 대한 득점을 답으로 출력한다.

## 후기
처음부터 문제가 이해가 되질 않았다.

그래서 하나씩 해보면서 순열을 써야된다는 사실을 알았고

순열을 적용해서 문제를 해결할 수 있었다.