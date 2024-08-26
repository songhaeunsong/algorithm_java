// https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AZC1NpTagf0DFAVs&contestProbId=AV5LwsHaD1MDFAXc&probBoxId=AZGNg87a0w0DFAXd+&type=PROBLEM&problemBoxTitle=0826&problemBoxCnt=++2+
// 1868. 파핑파핑 지뢰찾기

import java.io.*;
import java.util.*;

public class Solution1 {
	static int T;
	static int N;
	static int count;

	static char[][] board;
	static int[] dx = { 0, -1, 0, 1, 1, 1, -1, -1 };
	static int[] dy = { -1, 0, 1, 0, 1, -1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 행과 열 크기
			count = 0; // 클릭 수

			board = new char[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();

				for (int j = 0; j < N; j++) {
					board[i][j] = str.charAt(j);
				}
			}

            // 위험한 영역(주변에 지뢰가 존재) d로 변경

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '*') {
						for (int d = 0; d < 8; d++) {
							int ni = i + dx[d];
							int nj = j + dy[d];

							if (ni < 0 || ni >= N || nj < 0 || nj >= N)
								continue;
							if (board[ni][nj] == '.')
								board[ni][nj] = 'd';

						}
					}
				}
			}

            // 0인 영역 돌면서 안전한 영역 최대로 찾기

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '.') {
						board[i][j] = 'c';
						count++;
						bfs(i, j);

					}
				}
			}

            // d(위험한 영역)이지만 밟지 않은 곳 카운팅

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == 'd') {
						count++;

					}
				}
			}

			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}

		System.out.println(sb.toString().trim());
	}

	static public void bfs(int r, int c) {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.addLast(new int[] { r, c });

		while (!dq.isEmpty()) {
			int[] temp = dq.pollFirst();
			int x = temp[0];
			int y = temp[1];

			for (int d = 0; d < 8; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if (board[nx][ny] == '.') { // 주변 지뢰수가 0이면
					board[nx][ny] = 'c';
					dq.addLast(new int[] { nx, ny });
				} else if (board[nx][ny] == 'd') { // 주변에 지뢰가 존재하면
					board[nx][ny] = 'c';
				}

			}
		}
		return;
	}

}