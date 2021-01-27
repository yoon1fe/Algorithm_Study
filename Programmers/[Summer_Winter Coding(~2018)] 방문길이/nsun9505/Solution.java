class Solution {

    public int solution(String dirs) {
        int answer = 0;
        int N = 10;
        boolean[][][][] map = new boolean[N+1][N+1][N+1][N+1];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int row = 5;
        int col = 5;
        for(int i=0; i<dirs.length(); i++) {
            int dir = getDir(dirs.charAt(i));
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if(nx < 0 || ny < 0 || nx > N || ny > N)
                continue;

            if(map[row][col][nx][ny] || map[nx][ny][row][col]) {
                row = nx;
                col = ny;
                continue;
            }

            map[row][col][nx][ny] = true;
            map[nx][ny][row][col] = true;
            row = nx;
            col = ny;
            answer++;
        }

        return answer;
    }

    public static int getDir(char dir){
        if(dir == 'U')
            return 0;
        else if(dir == 'R')
            return 1;
        else if(dir == 'D')
            return 2;
        return 3;
    }
}