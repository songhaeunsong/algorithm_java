// https://swexpertacademy.com/main/talk/solvingClub/problemView.do?contestProbId=AWrDOdQqRCUDFARG&solveclubId=AZC1NpTagf0DFAVs&problemBoxTitle=0826&problemBoxCnt=2&probBoxId=AZGNg87a0w0DFAXd+

// 치즈 도둑

import java.io.*;
import java.util.*;

public class Solution2 {
	static int T;
	static int N;
	static int[][] board;
	static boolean[][] visited;
	static int count;
	static int max;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			max = 0;

			for(int i = 0; i < N; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++){
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

            int day = 1;
			while(day <= 100){
				visited = new boolean[N][N];
				count = 0;

				for(int i = 0; i < N; i++){
					for(int j = 0; j < N; j++){
						if(!visited[i][j] && board[i][j] >= day){
							visited[i][j] = true;
							dfs(day, i, j);
							count++;
						}
					}
				}
				if(count == 0) break;
				max = Math.max(max , count);
				day++;
			}
            sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
			System.out.println(sb.toString().trim());

	}

	static public void dfs(int day, int x, int y){
		for(int i = 0; i < 4; i++){
			int nx = x + dx[i]; 
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			if(!visited[nx][ny] && board[nx][ny] >= day){
				visited[nx][ny] = true;
				dfs(day, nx, ny);
			}
		}
	}
}



