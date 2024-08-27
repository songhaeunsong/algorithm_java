import java.io.*;
import java.util.*;

public class Solution {
	static int T;
	static int N;
	static int M;
	static int K;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };
	static int total;

	static int[][][] board;
	static Deque<int[]> dq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 셀의 개수
			M = Integer.parseInt(st.nextToken()); // 격리시간
			K = Integer.parseInt(st.nextToken()); // 미생물 군집의 개수
			dq = new ArrayDeque<>();
			total = 0;
			board = new int[N][N][3]; // sum, maxCount, direction
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				dq.addLast(new int[] { r, c, count, direction });
			}
			// 구현
			for (int t = 0; t < M; t++) {

				while (!dq.isEmpty()) {
					int[] micro = dq.pollFirst();
					int nx = micro[0] + dx[micro[3]];
					int ny = micro[1] + dy[micro[3]];

					if (nx == 0 || ny == 0 || nx == N - 1 || ny == N - 1) {
						board[nx][ny][0] = micro[2] / 2;
						board[nx][ny][1] = micro[2] / 2;
						board[nx][ny][2] = changeDR(micro[3]);
					} else {
						board[nx][ny][0] += micro[2];
						if (board[nx][ny][1] < micro[2]) {
							board[nx][ny][1] = micro[2];
							board[nx][ny][2] = micro[3];
						}

					}

				}

				if (t == M-1)
					break;
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (board[i][j][0] > 0) {
							dq.addLast(new int[] { i, j, board[i][j][0], board[i][j][2] });
							
							board[i][j][0] = 0;
							board[i][j][1] = 0;
							board[i][j][2] = 0;
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					total += board[i][j][0];
					
				}
			}

			sb.append("#").append(tc).append(" ").append(total).append("\n");

		}

		System.out.print(sb);
	}

	public static int changeDR(int d) {
		if (d == 1)
			return 2;
		if (d == 2)
			return 1;
		if (d == 3)
			return 4;
		else
			return 3;
	}

}