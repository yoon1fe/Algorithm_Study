# [2019 KAKAO BLIND RECRUITMENT] 오픈채팅방 - C++

## 분류
> 문자열 처리

## 코드
```c++
#include <iostream>
#include <string>
#include <vector>
#include <map>

using namespace std;

string getCmd(string record) {
    string cmd = "";
    for (int i = 0; i < record.length(); i++) {
        if (record[i] == ' ')
            break;
        cmd += record[i];
    }
    return cmd;
}

string getToken(string record, int start) {
    string ret = "";
    for (int i = start; i < record.length(); i++) {
        if (record[i] == ' ')
            break;
        ret += record[i];
    }
    return ret;
}

vector<string> solution(vector<string> record) {
    vector<string> answer;
    map<string, string> users;
    vector<pair<string, string>> ret;

    for (int i = 0; i < record.size(); i++) {
        string rec = record[i];
        string cmd = getCmd(rec);
        string userId = getToken(rec, cmd.length()+1);
        string username = "";
        
        if (cmd == "Enter" || cmd == "Change") {
            username = getToken(rec, cmd.length() + userId.length() + 2);
            users[userId] = username;
            if(cmd == "Enter")
                ret.push_back({ userId, "님이 들어왔습니다." });
        }
        else {
            ret.push_back({ userId, "님이 나갔습니다." });
        }
    }

    for (int i = 0; i < ret.size(); i++) {
        string tmp = users[ret[i].first] + ret[i].second;
        answer.push_back(tmp);
    }

    return answer;
}

int main(void) {
    vector<string> record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan" };
    vector<string> ret = solution(record);
    for (string str : ret)
        cout << str << '\n';
    return 0;
}
```

## 문제 풀이
입력된 문자열을 Enter, Change은 닉네임까지 포함하여 자르고, Leave는 userId까지만 record로부터 추출한다.

핵심은 userId를 사용하는 것 같다.

Enter, Change 명령
- username(닉네임)을 record에서 추출
- users라는 map에 키값을 userId으로 username을 value로 매핑한다.
- 결과로 넘겨줄 "[닉네임]님이 들어왔습니다./나갔습니다."의 경우 닉네임 자리에 userId를 저장하고
- answer에 저장할 때 users map에서 userId에 해당하는 닉네임을 가져오면 된다.

Change 명령
- users map에서 변경하는 userId에 대한 값을 바꿔주면 된다.
- 나중에 answer에 출력 문자열을 저장할 때는 username이 아닌 userId로 매핑을 해두었으므로 닉네임이 아무리 바뀌어도 괜찮다.

## 후기
문자열 자르기 검색...

재미있는 문제였다!