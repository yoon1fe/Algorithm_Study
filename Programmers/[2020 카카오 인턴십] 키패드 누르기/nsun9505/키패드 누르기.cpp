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