# [2020 KAKAO BLIND RECRUITMENT] 문자열 압축 - C++
## 분류
> 문자열 처리

## 코드
```c++
#include <iostream>
#include <vector>
#include <string>
#include <stack>
using namespace std;

int solution(string s) {
	int ans = s.length();
    int len = ((int)s.length())/2;
	// 문자열 길이의 반 보다 크게 압축을 시도한다면 압축되는 문자열의 수는 2이상이 될 수 없음.
	for (int cmpSize = 1; cmpSize <= len; cmpSize++) {
		stack<string> st;
		int idx = 0;
		// 압축할 수만큼 잘라서 스택에 넣는다.
		while(true) {
			if (idx + cmpSize < s.length()) {
				st.push(s.substr(idx, cmpSize));
				idx += cmpSize;
			}
			else {
				st.push(s.substr(idx));
				break;
			}
		}
		
		// 결과 스택 cur : 압축된 각 문자열들이 압축된 문자열 수와 pair로 스택에 들어간다.
		stack<pair<string, int>> cur;
		cur.push({ st.top(), 0 });
		while(!st.empty()){
			// top과 같다면 문자열 카운트 후 pop
			if (st.top() == cur.top().first) {
				cur.top().second += 1;
				st.pop();
			}
			else {
				// 같지 않다면 cur 스택의 top을 변경
				cur.push({ st.top(), 0 });
			}
		}

		// cur에는 압축된 문자열들이 <압축 문자열, 개수> 형식으로 들어가 있다.
		int tmp = 0;
		while (!cur.empty()) {
			pair<string, int> p = cur.top();
			cur.pop();
			// 압축 문자열의 길이만큼 tmp에 더한다.
			tmp += p.first.length();
			// 압축 문자열이 1개 이상이라면 해당 압축 문자열 수를 문자열로 바꾼다.
			// 문자열롤 바꾼 수의 길이를 tmp에 더한다.
			if (p.second != 1)
				tmp += (int)(to_string(p.second).length());
		}

		// 압축 길이가 현재 답보다 작은 경우 답 변경
		if (tmp < ans)
			ans = tmp;
	}

	return ans;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie();

	string input;
	cin >> input;

	cout << solution(input);

	return 0;
}
```

## 문제풀이
- 입력 문자열 s를 1,2, ... , s.length()/2로 압축하면서 검사한다.
- 압축할 문자열 길이만큼 입력 문자열을 잘라서 스택에 넣는다.
- 스택에 들어 있는 문자열들을 비교하면서 압축 문자열의 개수를 카운트한다.
   - 중복된 것이 있는지 카운트하는 것이다.
- 압축 문자열 길이과 압축 문자열 갯수의 문자열 길이를 tmp에 더한다.
- tmp가 현재 답 ans보다 작다면 ans를 tmp로 변경한다.

## 후기
- 문자열 처리를 처음으로 접해봐서 자바가 많이 생각났다.
- C++도 비슷하지만 그래도 뭔가 문자열은 자바로 많이 해봐서 C++이 조금 낯설다.
- 확실히 삼성 기출과 다른 느낌이다.