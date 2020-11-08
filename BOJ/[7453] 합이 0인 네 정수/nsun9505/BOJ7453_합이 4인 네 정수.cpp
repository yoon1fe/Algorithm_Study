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