// 6109. 추억의 2048게임

import java.io.*;
import java.util.*;

public class Solution1 {
	static int T;
	static int N;
	static String direction;
	static int[][] matrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			direction = st.nextToken();
			matrix = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());

				}
			}
			
			// 방향에 따른 함수 호출
			if (direction.equals("up"))
				pushUp();
			else if (direction.equals("down"))
				pushDown();
			else if (direction.equals("left"))
				pushLeft();
			else if (direction.equals("right"))
				pushRight();

			// 매트릭스 순회하며 출력
			sb.append("#").append(tc).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(matrix[i][j]);
					sb.append(" ");
				}
				sb.setLength(sb.length() - 1);
				sb.append("\n");
			}

		}
		System.out.println(sb.toString().trim());
	}

	public static void pushUp() {
		List<Integer> newList;
		int f;
		int s;
		for (int i = 0; i < N; i++) {
			newList = new ArrayList<>();
			f = 0; // 비교할
			s = 1; // 두 인덱스
			while (s < N) {
				int first = matrix[f][i];
				int second = matrix[s][i];

				if (first == 0) { // ex) (미는빙향 <=) 0 2 ...
					f++;
					s++;
					continue;
				}
				if (second == 0) { // ex) (미는빙향 <=) 4 0 4 ...
					s++;
					continue;
				}
				if (first == second) { // ex) 2 2 ...
					newList.add(first * 2);
					f = s + 1;
					s = f + 1;
				} else { // ex) 4 2 ... , 4 0 2...
					newList.add(first);
					f = s;
					s = f + 1;
				}
			}
			for (int r = f; r < N; r++) { // s가 범위에서 벗어나 검사하지 못한 부분을 검사
				if (matrix[r][i] != 0)
					newList.add(matrix[r][i]);

			}
			for (int j = 0; j < N; j++) { // 리스트에 저장된 숫자까지는 차례대로 작성, 나머지는 0 입력
				matrix[j][i] = j >= newList.size() ? 0 : newList.get(j);
			}
		}

	}

	public static void pushDown() {
		List<Integer> newList;
		int f;
		int s;
		for (int i = N - 1; i >= 0; i--) {
			newList = new ArrayList<>();
			f = N - 1;
			s = N - 2;
			while (s >= 0) {
				int first = matrix[f][i];
				int second = matrix[s][i];

				if (first == 0) {
					f--;
					s--;
					continue;
				}
				if (second == 0) {
					s--;
					continue;
				}
				if (first == second) {
					newList.add(first * 2);
					f = s - 1;
					s = f - 1;
				} else {
					newList.add(first);
					f = s;
					s = f - 1;
				}
			}
			for (int r = f; r >= 0; r--) {
				if (matrix[r][i] != 0)
					newList.add(matrix[r][i]);

			}
			for (int j = 0; j < N; j++) {
				matrix[N - j - 1][i] = j >= newList.size() ? 0 : newList.get(j);
			}
		}
	}

	public static void pushLeft() {
		List<Integer> newList;
		int f;
		int s;
		for (int i = 0; i < N; i++) {
			newList = new ArrayList<>();
			f = 0;
			s = 1;
			while (s < N) {
				int first = matrix[i][f];
				int second = matrix[i][s];

				if (first == 0) {
					f++;
					s++;
					continue;
				}
				if (second == 0) {
					s++;
					continue;
				}
				if (first == second) {
					newList.add(first * 2);
					f = s + 1;
					s = f + 1;
				} else {
					newList.add(first);
					f = s;
					s = f + 1;
				}
			}
			for (int r = f; r < N; r++) {
				if (matrix[i][r] != 0)
					newList.add(matrix[i][r]);

			}
			for (int j = 0; j < N; j++) {
				matrix[i][j] = j >= newList.size() ? 0 : newList.get(j);
			}
		}
	}

	public static void pushRight() {
		List<Integer> newList;
		int f;
		int s;
		for (int i = N - 1; i >= 0; i--) {
			newList = new ArrayList<>();
			f = N - 1;
			s = N - 2;
			while (s >= 0) {
				int first = matrix[i][f];
				int second = matrix[i][s];

				if (first == 0) {
					f--;
					s--;
					continue;
				}
				if (second == 0) {
					s--;
					continue;
				}
				if (first == second) {
					newList.add(first * 2);
					f = s - 1;
					s = f - 1;
				} else {
					newList.add(first);
					f = s;
					s = f - 1;
				}
			}
			for (int r = f; r >= 0; r--) {
				if (matrix[i][r] != 0)
					newList.add(matrix[i][r]);

			}
			for (int j = 0; j < N; j++) {
				matrix[i][N - j - 1] = j >= newList.size() ? 0 : newList.get(j);
			}
		}
	}
}
