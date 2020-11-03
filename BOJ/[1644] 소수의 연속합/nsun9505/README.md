# [1644] 소수의 연속합 - C++

## 분류
> 투 포인터
> 
> 에라토스테네스의 체

## 코드
```c++
#include <iostream>
#include <vector>

using namespace std;
int N;
bool isNotPrime[4000001];

vector<int> getPrime() {
	vector<int> primes;

	isNotPrime[0] = true;
	isNotPrime[1] = true;

	for (int i = 2; i <= N; i++) {
		if (isNotPrime[i]) continue;

		for (int j = 2 * i; j <= N; j += i)
			if (!isNotPrime[i])
				isNotPrime[j] = true;
	}

	for (int i = 2; i <= N; i++)
		if (!isNotPrime[i])
			primes.push_back(i);

	return primes;
}

int main(void) {
	ios_base::sync_with_stdio(0);
	cin >> N;
	if (N == 1) {
		cout << 0;
		return 0;
	}
	vector<int> primes = getPrime();

	int start = 0;
	int end = 0;
	int cnt = 0;
	int sum = primes[0];
	while (true) {
		if (sum >= N) {
			if (sum == N)
				cnt++;
			sum -= primes[start++];
		}
		else {
			end++;
			if (end < primes.size())
				sum += primes[end];
			else
				break;
		}
	}
	cout << cnt;
	return 0;
}
```

## 문제 풀이
1. 입력 받은 N이하의 소수를 구한다.
   - 소수를 구하는 방법은 에라토스테네스의 체로 구한다.
   - i(= 2, 3, ... , N) i의 배수를 N이하에서 소수 목록에서 제외를 시킨다.
   - 제외시키고 남은 것은 모두 N이하의 소수이므로 vector에 담아서 리턴하면 된다.

1. 투 포인터로 구간의 합 구하기
   - 투 포인터로 소수 목록에서의 구간 합이 N과 동일하면 카운트한다.

## 후기
알고리즘 분류를 보지 않고 에라토스테네스의 체랑 투 포인터를 생각해냈다!! 

물론 에라토스테네스의 체는 구글링~

이참에 로직을 제대로 이해했고 덤으로 외우기!