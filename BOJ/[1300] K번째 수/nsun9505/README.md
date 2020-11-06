# [1300] K 번째 수 - C++

## 분류
> 이분탐색

## 코드
```c++
#include <iostream>
using namespace std;

int getMin(int a, int b) {
	if (a < b)
		return a;
	return b;
}

int main(void) {
	int N;
	cin >> N;

	long long target;
	cin >> target;

	int start = 1;
	int end = target;
	int ret = 0;
	while (start <= end) {
		int mid = (start + end) / 2;
		long long num = 0;
		for (int i = 1; i <= N; i++)
			num += getMin(mid / i, N);

		if (num < target)
			start = mid + 1;
		else {
			ret = mid;
			end = mid - 1;
		}
	}

	cout << ret;
	return 0;
}
```

## 문제 풀이
이분탐색으로 푸는 문제!

이분탐색이니깐 mid가 있을 것이다. 그러면 이 mid를 사용해서 문제를 풀어나가야 하는 것인데

일단 만들어지는 행렬의 i번째 행은 i의 배수들로 이루어진다.

그러면 mid/i를 하게된다면 i번째 행에서 mid보다 작은 숫자들의 개수가 나온다!
- 이분탐색을 시작하기 전에 left = 1, right = K로 하면 된다.

그렇다면 1~N까지 mid/i를 해서 각 행에서 나오는 수들을 카운트하여 더한다.
- 만약 모두 더한 값이 K보다 작다면 답에 모자른 것이므로 start를 mid + 1해서 다시 이분탐색을 한다
- 만약 모두 더한 값이 K보다 크다면 답에 근접한 것이므로 mid를 답에 저장하고, 범위를 줄여서 답이 있을 수 있으므로 end를 mid-1로 바꿔서 다시 이분탐색!

주의해야 할 점은 mid/i가 N보다 클 수 있다.
- 예를 들어, N = 1000 보다 커지면 mid / i가 N보다 커질 수 있으므로 mid/i와 N 중에 작은 값을 취해서 각 행에서 mid보다 작은 숫자들의 개수를 파악해야 한다.

## 후기
이분탐색을 어떻게 적용할지가 어려웠던 문제 같다.

구글링을 좀 많이 해서 찾아본 문제

후아후아!