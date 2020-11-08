# [7453] 합이 0인 네 정수 - C++

## 코드
```c++
#include <iostream>
#include <algorithm>

using namespace std;
int N = 0;
int A[4001], B[4001], C[4001], D[4001];
int sumAB[16000001], sumCD[16000001];

int getLowerBound(int left, int right, int target) {
	while (left < right) {
		int mid = (left + right) / 2;
		if (sumCD[mid] < target)
			left = mid + 1;
		else
			right = mid;
	}
	return right;
}

int getUpperBound(int left, int right, int target) {
	while (left < right) {
		int mid = (left + right) / 2;
		if (sumCD[mid] <= target)
			left = mid + 1;
		else
			right = mid;
	}
	return right;
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin >> N;

	for (int i = 0; i < N; i++)
		cin >> A[i] >> B[i] >> C[i] >> D[i];

	int sumIdx = 0;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			sumAB[sumIdx++] = A[i] + B[j];

	sumIdx = 0;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			sumCD[sumIdx++] = C[i] + D[j];

	sort(sumCD, sumCD + (N*N));
	
	long long ret = 0;
	for (int i = 0; i < N * N; i++)
		ret += (getUpperBound(0, N * N, -sumAB[i]) - getLowerBound(0, N * N, -sumAB[i]));
	cout << ret;

	return 0;
}
```

## 문제 풀이
네 개의 배열을 다 각자 더한다면 N^4니깐 두 개로 나누어서 한다.
- A의 각 원소와 B의 각 원소를 더해서 sumAB[N*N]을 만든다.
- 동일하게 C와 D의 각 원소를 더해서 sumCD[N*N]을 만든다.

그 다음 sumCD를 정렬한 원소중에서 sumAB[i]와 더해서 0이 되는 원소를 찾으면 된다.
- sumCD를 정렬한 이유는 이진탐색을 하기 위함이다.
- 이진탐색을 lower bound와 upper bound를 한다.
- lower bound를 하면 찾는 처음으로 나오는 인덱스를 반환하고, upper bound를 하면 해당 원소가 끝나는 위치의 다음 인덱스를 반환한다.
- 그러면 sumCD에서 sumAB[i]와 더했을 때 0이되는 원소의 개수를 알 수 있으므로 개수를 답에 더해주면 된다.

## 후기
항상 느끼는 것이지만 이진탐색은 쉽다! 대신 이진탐색을 어떻게 활용하느냐가 어렵다ㅠ

화이팅!