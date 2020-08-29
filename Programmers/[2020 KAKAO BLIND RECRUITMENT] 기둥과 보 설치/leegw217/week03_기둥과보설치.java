import java.util.ArrayList;
import java.util.Comparator;

public class week03_기둥과보설치 {
	public boolean insert(boolean[][][] map, int[] input) {
		int x = input[0];
		int y = input[1];
		int a = input[2];
		
		if(a == 0) { // 기둥 설치
			if(y == 0) return true; // 바닥에 설치하는 건지 체크
			if(y>0 && map[x][y-1][0]) return true; // 바로 밑에 기둥이 있는지  체크
			if((x>0 && map[x-1][y][1]) || map[x][y][1]) return true; // 밑에 보가 있는지 체크
		} else { // 보 설치
			if(map[x][y-1][0] || map[x+1][y-1][0]) return true; // 양쪽 끝에 기둥이 있는지 체크
			if(x>0 && map[x-1][y][1] && map[x+1][y][1]) return true; // 양쪽 끝에 보가 있는지 체크
		}
		return false;
	}
	
	public boolean delete(int n, boolean[][][] map, int[] input) {
		int x = input[0];
		int y = input[1];
		int a = input[2];
		
		if(a == 0) { // 기둥 삭제
			if(y<n-1 && map[x][y+1][0]) // 위에 기둥이 있을 때 -> 위 기둥 밑에 보가 있는지 검사
				if(!(x<n && map[x][y+1][1] || (x>0 && map[x-1][y+1][1]))) return false;
			if(map[x][y+1][1]) // 위 오른쪽 보 있을 때 -> 오른쪽 아래 기둥이 있는지, 양옆에 보가 있는지 검사
				if(!(map[x+1][y][0] || (x>0 && x<n-1 && map[x-1][y+1][1] && map[x+1][y+1][1]))) return false;
			if(x>0 && map[x-1][y+1][1]) // 위 왼쪽 보 있을 때 -> 왼쪽 아래 기둥이 있는지, 양옆에 보가 있는지 검사
				if(!(map[x-1][y][0] || (x>1 && map[x-2][y+1][1] && map[x][y+1][1]))) return false;
		} else { // 보 삭제
			if(map[x][y][0]) // 왼족 위 기둥이 있을 때 -> 왼쪽 아래 기둥, 왼쪽 보 검사
				if(!(map[x][y-1][0] || (x>0 && map[x-1][y][1]))) return false;
			if(map[x+1][y][0]) // 오른쪽 위 기둥 있을 때 -> 오른쪽 아래 기둥, 오른쪽 보 검사
				if(!(map[x+1][y-1][0] || map[x+1][y][1])) return false;
			if(x>0 && map[x-1][y][1]) // 왼쪽 보 있을 때 -> 왼쪽 아래 기둥, 더 왼쪽 아래 기둥 검사
				if(!(map[x][y-1][0] || map[x-1][y-1][0])) return false;
			if(map[x+1][y][1]) // 오른쪽 보 있을 때 -> 오른쪽 아래 기둥, 더 오른쪽 아래 기둥 검사
				if(!(map[x+1][y-1][0] || map[x+2][y-1][0])) return false;
		}
		return true;
	}
	
	public int[][] solution(int n, int[][] build_frame) {
		boolean[][][] map = new boolean[n+1][n+1][2];
		ArrayList<int[]> answer = new ArrayList<int[]>();
		
		for(int i=0; i<build_frame.length; i++) {
			if(build_frame[i][3] == 1) { // 설치
				if(insert(map, build_frame[i])) {
					map[build_frame[i][0]][build_frame[i][1]][build_frame[i][2]] = true;
					answer.add(new int[] {build_frame[i][0], build_frame[i][1], build_frame[i][2]});
				}
			} else { // 삭제
				if(delete(n, map, build_frame[i])) {
					map[build_frame[i][0]][build_frame[i][1]][build_frame[i][2]] = false;
					int x = build_frame[i][0];
					int y = build_frame[i][1];
					int a = build_frame[i][2];
					for(int j=0; j<answer.size(); j++) {
						if(answer.get(j)[0] == x && answer.get(j)[1] == y && answer.get(j)[2] == a)
							answer.remove(j);
					}
				}
			}
		}
		answer.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] - o2[0] == 0) {
					if(o1[1] - o2[1] == 0) {
						return o1[2] - o2[2];
					}
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
			
		});
		int[][] result = new int[answer.size()][3];
		result = answer.toArray(result);
		return result;
	}
}
