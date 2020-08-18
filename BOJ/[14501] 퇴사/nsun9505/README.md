# [14499] 주사위 굴리기 - C++

## 분류
> 완전탐색(DFS)

## 코드
```c++
#include <iostream>
#include <vector>

using namespace std;

struct Task {
public:
	int day;
	int money;

	Task(int d, int m) : day(d), money(m) {}
};

int N;
int ans = -1;
vector<Task> list;

void DFS(int idx, int sum) {
	if (idx == N) {
		if (ans < sum)
			ans = sum;
		return;
	}
	else if (idx > N)
		return;

	DFS(idx + list[idx].day, sum + list[idx].money);
	DFS(idx + 1, sum);
}

int main(void) {
	cin >> N;

	int d, m;
	for (int i = 0; i < N; i++) {
		cin >> d >> m;
		list.push_back(Task(d, m));
	}

	DFS(0, 0);

	cout << ans;
	return 0;
}
```

## 풀이방법
- DFS로 해서 완전탐색을 했다.
- 각 요소를 포함하거나 포함하지 않는 것으로 DFS를 돌렸다.
  - index i번째 날에 일을 하게 되면 상담이 걸리는 날만큼 인덱스를 증가시켜 준다.
  - index i번째 날에 상담을 하지 않는다면 인덱스 하나만 증가켜서 다음 날에 할 수 있는 일을 본다.


## 후기
DP로 풀 수 있는 방법이 있지 않을까
DP로 풀 수 있는 방법 생각 해보자.
SWEA에 있는 수영장 문제하고 유형이 똑같은 것 같다.
그 때는 강의 영상을 보면서 DP로 푸는 방법을 배웠는데 까먹었다..