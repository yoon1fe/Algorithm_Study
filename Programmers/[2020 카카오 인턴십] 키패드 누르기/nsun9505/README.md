# [2020 카카오 인턴십] 키패드 누르기 - C++

## 분류
> 단순 구현

## 코드
```c++
#include <vector>
#include <string>
#include <map>

#define STAR 10
#define SHAP 11

using namespace std;

int getDistance(pair<int, int> a, pair<int, int> b) {
    return abs(a.first - b.first) + abs(a.second - b.second);
}

string solution(vector<int> numbers, string hand) {
    string answer = "";
    map<int, pair<int, int>> keypad;
    keypad[0] = { 3, 1 };
    keypad[1] = { 0, 0 };
    keypad[2] = { 0, 1 };
    keypad[3] = { 0, 2 };
    keypad[4] = { 1, 0 };
    keypad[5] = { 1, 1 };
    keypad[6] = { 1, 2 };
    keypad[7] = { 2, 0 };
    keypad[8] = { 2, 1 };
    keypad[9] = { 2, 2 };
    keypad[10] = { 3, 0 };
    keypad[11] = { 3, 2 };

    pair<int, int> curLeft = keypad[STAR];
    pair<int, int> curRight = keypad[SHAP];

    for (int idx = 0; idx < numbers.size(); idx++) {
        if (numbers[idx] == 1 || numbers[idx] == 4 || numbers[idx] == 7) {
            answer += "L";
            curLeft = keypad[numbers[idx]];
        }
        else if (numbers[idx] == 3 || numbers[idx] == 6 || numbers[idx] == 9) {
            answer += "R";
            curRight = keypad[numbers[idx]];
        }
        else {
            int ldist = getDistance(curLeft, keypad[numbers[idx]]);
            int rdist = getDistance(curRight, keypad[numbers[idx]]);
            if (ldist < rdist) {
                curLeft = keypad[numbers[idx]];
                answer += "L";
            }
            else if (ldist > rdist) {
                curRight = keypad[numbers[idx]];
                answer += "R";
            }
            else if(hand == "left"){
                curLeft = keypad[numbers[idx]];
                answer += "L";
            }
            else if(hand == "right") {
                curRight = keypad[numbers[idx]];
                answer += "R";
            }
        }
    }

    return answer;
}
```

## 문제풀이
단순 구현문제!

4x3 이차원 배열이라 생각하고 키패드를 눌렀을 때 
- 1,3,7은 왼손으로 누르기 위해 위치를 변경한다.
- 3,6,9는 오른손으로 누르기 위해 위치를 변경한다.
- 나머지 숫자의 경우에는 현재 오른속과 완손의 위치를 보고 가장 가까운 것으로 누른다.
   - 거리가 같은 경우 오른손 잡이이면 오른손으로, 왼손잡이이면 왼손으로 누르기 위해 위치를 변경한다.

## 후기
간단해서 편안한 문제