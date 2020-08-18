# [14499] 주사위 굴리기 - C++

## 분류
> 완전탐색(DFS)
>
> DP

## 코드(DFS)
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

## 코드(DP)
```c++
#include <iostream>
using namespace std;

int dp[1000000];

int main(void) {
	int N;
	cin >> N;

	int d, m;
	int ans = 0;
	for (int i = 0; i < N; i++) {
		cin >> d >> m;
		if (i + d <= N && dp[i + d] < dp[i] + m) {
			dp[i + d] = dp[i] + m;
			ans = ans < dp[i + d] ? dp[i + d] : ans;
		}
		dp[i + 1] = dp[i + 1] < dp[i] ? dp[i] : dp[i + 1];
		ans = ans < dp[i + 1] ? dp[i + 1] : ans;
	}

	cout << ans;
	return 0;
}
```

## 풀이방법
### DFS
- DFS로 해서 완전탐색을 했다.
- 각 요소를 포함하거나 포함하지 않는 것으로 DFS를 돌렸다.
  - index i번째 날에 일을 하게 되면 상담이 걸리는 날만큼 인덱스를 증가시켜 준다.
  - index i번째 날에 상담을 하지 않는다면 인덱스 하나만 증가켜서 다음 날에 할 수 있는 일을 본다.

### DP
- 입력을 받으면서 충분히 처리가 가능하다고 생각해서 입력을 받으면서 문제를 풀었다.
- i번째 날에 일을 하다면 i+d번째 날에 보상을 받을 것이고
- i번째 날에 일을 안 한다면 지금까지의 보상을 그냥 다음날로 넘기면 된다.
- 결국 DFS에서 풀었던 것처럼 선택하느냐 선택하지 않느냐에 대한 문제를 DP로 가져오면 된다.
- 대신 선택한 경우와 선택하지 않은 경우는 지금까지의 보상이 i+1 또는 i+d의 값보다 큰지를 봐서 dp[i+d]나 dp[i+1]에 값을 넣어줘야 한다.


## 후기
### DFS
- DP로 풀 수 있는 방법이 있지 않을까
- DP로 풀 수 있는 방법 생각 해보자.
- SWEA에 있는 수영장 문제하고 유형이 똑같은 것 같다.
- 그 때는 강의 영상을 보면서 DP로 푸는 방법을 배웠는데 까먹었다.