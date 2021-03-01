import java.util.ArrayList;

public class Solution {
	static ArrayList<int[][]> keyList = new ArrayList<>();
    public boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;
        int size = N + (M-1)*2;
        int[][] map = new int[size][size];
        for(int i=M-1, row=0; i<N+(M-1); i++,row++) {
        	for(int j=M-1, col=0; j<N+(M-1); j++, col++) {
        		map[i][j] = lock[row][col];
        	}
        }
        
        keyList.add(key);
        for(int i=1; i<4; i++)
        	keyList.add(getRotate(keyList.get(i-1), M));
        
        for(int row=0; row<N+(M-1); row++) {
        	for(int col=0; col<N+(M-1); col++) {
        		for(int[][] rotateKey : keyList) {
        			applyKey(rotateKey, map, row, col);
        			boolean ret = check(M-1, N+(M-1), map);
        			if(ret)
        				return true;
        			recoveryKey(rotateKey, map, row, col);
        		}
        	}
        }
        
        
        return false;
    }
    
    public static boolean check(int start, int end, int[][] map) {
    	for(int i=start; i<end; i++) {
    		for(int j=start; j<end; j++) {
    			if(map[i][j] == 0 || map[i][j] == 2)
    				return false;
    		}
    	}
    	return true;
    }
    
    public static void applyKey(int[][] key, int[][] map, int row, int col) {
    	for(int i=0; i<key.length; i++) {
    		for(int j=0; j<key.length; j++) {
    			map[row+i][col+j] += key[i][j];
    		}
    	}
    }
    
    public static void recoveryKey(int[][] key, int[][] map, int row, int col) {
    	for(int i=0; i<key.length; i++) {
    		for(int j=0; j<key.length; j++) {
    			map[row+i][col+j] -= key[i][j];
    		}
    	}
    }
    
    public static int[][] getRotate(int[][] origin, int M){
    	int[][] ret = new int[M][M];
    	for(int i=0, col = M-1; i<M; i++, col--) {
    		for(int j=0, row=0; j<M; j++, row++) {
    			ret[row][col] = origin[i][j];
    		}
    	}
    	return ret;
    }
}