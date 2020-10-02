package week08;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
public class week08_큐빙 {
	static class Cube {
		char[][] U = new char[3][3];
		char[][] D = new char[3][3];
		char[][] F = new char[3][3];
		char[][] B = new char[3][3];
		char[][] L = new char[3][3];
		char[][] R = new char[3][3];
		Cube() {
			for(int i=0; i<3; i++) Arrays.fill(U[i], 'w');
			for(int i=0; i<3; i++) Arrays.fill(D[i], 'y');
			for(int i=0; i<3; i++) Arrays.fill(F[i], 'r');
			for(int i=0; i<3; i++) Arrays.fill(B[i], 'o');
			for(int i=0; i<3; i++) Arrays.fill(L[i], 'g');
			for(int i=0; i<3; i++) Arrays.fill(R[i], 'b');
		}
		void rotateUD(char loc, char ord) {
			int l = (loc == 'U' ? 0 : 2);
			char[] temp = new char[3];
			System.arraycopy(F[l], 0, temp, 0, 3);
			if((loc=='U' && ord=='+') || (loc=='D' && ord=='-')) {	
				for(int i=0; i<3; i++) F[l][i] = R[l][i];
				for(int i=0; i<3; i++) R[l][i] = B[l][i];
				for(int i=0; i<3; i++) B[l][i] = L[l][i];
				for(int i=0; i<3; i++) L[l][i] = temp[i];
			} else {
				for(int i=0; i<3; i++) F[l][i] = L[l][i];
				for(int i=0; i<3; i++) L[l][i] = B[l][i];
				for(int i=0; i<3; i++) B[l][i] = R[l][i];
				for(int i=0; i<3; i++) R[l][i] = temp[i];
			}
			if(loc=='U') rotateCube(U, ord);
			else rotateCube(D, ord);
		}
		void rotateFB(char loc, char ord) {
			int a = (loc == 'F' ? 2 : 0);
			int b = (loc == 'F' ? 0 : 2);
			char[] temp = new char[3];
			System.arraycopy(U[a], 0, temp, 0, 3);
			if((loc=='F' && ord=='+') || (loc=='B' && ord=='-')) {
				for(int i=0; i<3; i++) U[a][i] = L[2-i][a];
				for(int i=0; i<3; i++) L[i][a] = D[b][i];
				for(int i=0; i<3; i++) D[b][i] = R[2-i][b];
				for(int i=0; i<3; i++) R[i][b] = temp[i];
			} else {
				for(int i=0; i<3; i++) U[a][i] = R[i][b];
				for(int i=0; i<3; i++) R[i][b] = D[b][2-i];
				for(int i=0; i<3; i++) D[b][i] = L[i][a];
				for(int i=0; i<3; i++) L[i][a] = temp[2-i];
			}
			if(loc=='F') rotateCube(F, ord);
			else rotateCube(B, ord);
		}
		void rotateLR(char loc, char ord) {
			int a = (loc == 'L' ? 2 : 0);
			int b = (loc == 'L' ? 0 : 2);
			char[][] temp = new char[3][3];
			for(int i=0; i<3; i++) System.arraycopy(U[i], 0, temp[i], 0, 3);
			if((loc=='L' && ord=='+') || (loc=='R' && ord=='-')) {
				for(int i=0; i<3; i++) U[i][b] = B[2-i][a];
				for(int i=0; i<3; i++) B[i][a] = D[2-i][b];
				for(int i=0; i<3; i++) D[i][b] = F[i][b];
				for(int i=0; i<3; i++) F[i][b] = temp[i][b];
			} else {
				for(int i=0; i<3; i++) U[i][b] = F[i][b];
				for(int i=0; i<3; i++) F[i][b] = D[i][b];
				for(int i=0; i<3; i++) D[i][b] = B[2-i][a];
				for(int i=0; i<3; i++) B[i][a] = temp[2-i][b];
			}
			if(loc=='L') rotateCube(L, ord);
			else rotateCube(R, ord);
		}
	}
	static void rotateCube(char[][] c, char ord) {
		char[][] t = new char[3][3];
		for(int i=0; i<3; i++) System.arraycopy(c[i], 0, t[i], 0, 3);
		for(int i=0; i<3; i++) 
			for(int j=0; j<3; j++) {
				if(ord=='+') c[i][j] = t[2-j][i];
				else c[i][j] = t[j][2-i];
			}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		int T = Integer.parseInt(sc.nextLine());
		for(int t=0; t<T; t++) {
			Cube cube = new Cube();
			int N = Integer.parseInt(sc.nextLine());
			st = new StringTokenizer(sc.nextLine());
			for(int n=0; n<N; n++) {
				char[] list = st.nextToken().toCharArray();
				if(list[0]=='U' || list[0]=='D') cube.rotateUD(list[0], list[1]);
				else if(list[0]=='F' || list[0]=='B') cube.rotateFB(list[0], list[1]);
				else if(list[0]=='L' || list[0]=='R') cube.rotateLR(list[0], list[1]);
			}
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) 
					System.out.print(cube.U[i][j]);
				System.out.println();
			}
		}
	}
}