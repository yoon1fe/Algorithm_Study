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