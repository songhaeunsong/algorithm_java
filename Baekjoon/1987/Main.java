package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int R;
	static int C;
	static char[][] matrix;
	static Map<Character, Integer> map;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		matrix = new char[R][C];
		max = 0;

		map = new HashMap<>();
		for (char c = 'A'; c <= 'Z'; c++) {
			map.put(c, 0);
		}

		for (int i = 0; i < R; i++) {
			char[] charArr = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {

				matrix[i][j] = charArr[j];
			}
		}

		map.put(matrix[0][0], 1);
		dfs(0, 0, 1);
		System.out.println(max);

	}

	static public void dfs(int r, int c, int count) {

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr < 0 || nr >= R || nc < 0 || nc >= C || map.get(matrix[nr][nc]) != 0) {
				max = Math.max(max, count);
				continue;
			}

			map.put(matrix[nr][nc], 1);

			dfs(nr, nc, count + 1);
			map.put(matrix[nr][nc], 0);

		}
	}
}