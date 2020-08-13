# [13458] 시험 감독 - C++

## 분류
> 시뮬레이션

## 코드
```c++
#include <iostream>

using namespace std;

int arr[1000001];
int ans[1000001];

int main(void) {
	int N, A, B;
	cin >> N;

	long long sum = 0;
	for (int i = 0; i < N; i++)
		cin >> arr[i];

	cin >> A >> B;
	sum += N;
	for (int i = 0; i < N; i++) {
		if (ans[arr[i]] != 0) {
			sum += ans[arr[i]];
			continue;
		}

		int ret = 0;
		int tmp = arr[i] - A;
		if (tmp > 0) {
			ret += (tmp / B);
			if (tmp % B != 0)
				ret++;
		}

		ans[arr[i]] = ret;
		sum += ret;
	}

	cout << sum;
	return 0;
}
```

## 풀이방법
각 시험장에는 무조건 총감독관 1명씩 들어간다고 했을 때 미리 시험장 수 만큼 sum 값에 더해주고
해당 시험장에 있는 응시자의 수에서 총감독관이 감시하는 수만큼 빼준 값을 tmp에 저장한다.
그리고 tmp가 0보다 크다면 tmp를 부감독관이 감시하는 인원 수만큼 나누고 몫을 임시 결과에 저장하고
만약에 나머지 값이 있다면 부감독관의 수를 하나 더 늘려주면 된다.
그 다음에 임시 결과 값을 메모제이션하기 위해서 배열에 저장하고
출력 값에 해당 시험장에 필요한 감독관 수를 더하면서 작업을 끝내거나 다시 또 작업을 시작한다.
  
## 후기
문제는 어렵지 않았으나, 문제를 잘못 읽어서 헤매었다.
`문제를 잘 읽고 잘 이해하자.`