# [12015] 가장 긴 증가하는 부분 수열 2 - C++

## 분류
> 이분탐색

## 코드
```c++
#include <iostream>
#include <vector>
using namespace std;
int N;

int getIndex(vector<int> arr, int value) {
	int left = 0;
	int right = arr.size() - 1;
	while (left < right) {
		int mid = (left + right) / 2;
		if (arr[mid] < value)
			left = mid + 1;
		else
			right = mid;
	}
	return left;
}

int main(void) {
	ios_base::sync_with_stdio(0);
	vector<int> arr;
	
	int num;
	cin >> N;
	cin >> num;
	arr.push_back(num);
	for (int i = 1; i < N; i++) {
		cin >> num;
		if (arr[arr.size() - 1] < num)
			arr.push_back(num);
		else {
			int index = getIndex(arr, num);
			arr[index] = num;
		}
	}
	cout << arr.size();
	return 0;
}
```

## 문제 풀이
이분 탐색을 활용하는 문제!

입력을 받으면서 진행할 수 있는 문제

가장 긴 증가하는 부분 수열을 저장하는 배열(vector)에 현재 입력 받은 값과 비교를 한다.
- 전체적으로 비교하는 것이 아니라, 배열에서 가장 큰 값은 맨 뒤에 있기에 맨 뒤에 있는 값과 현재 입력 받은 값을 비교한다.
- 만약 현재 입력 받은 값이 더 크다면 그냥 배열 끝부분에 넣어주면 된다.

하지만 배열의 끝 원소보다 현재 입력 받은 원소가 작은 경우
- 부분 수열을 저장하는 배열에서 현재 입력 받은 값이 어디에 위치할지 결정한다.
- 이분탐색을 사용해서 현재 가장 긴 증가하는 부분 수열을 담고 있는 배열에서 현재 입력 받은 값이 들어갈 위치를 찾아서 넣어주면 된다.

## 후기
이분탐색을 어떻게 사용해야 하는지 몰랐다.

그래서 처음에는 N^2으로 생각하고 거기서 줄여보자는 생각으로 했지만 생각이 잘 안 났다!

구글링에서 코드를 살펴보니 부분 수열이 담기는 배열에서 입력 받은 값의 올바른 위치를 찾아주면 되는 것이였다!