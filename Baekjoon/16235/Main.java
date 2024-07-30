import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int K;

	static int[] dr = { 0, 1, 0, -1, 1, 1, -1, -1 };
	static int[] dc = { 1, 0, -1, 0, 1, -1, 1, -1 };
	static Stack<int[]> winter = new Stack<>();
	static Stack<int[]> dead = new Stack<>();
	static Stack<int[]> spring = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[][] board = new int[N][N];
		int[][] plus = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = 5;
				plus[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int[] input = { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) };
			
			winter.add(input);
//			
		}

		for (int k = 0; k < K; k++) {
			// 봄
			Collections.sort(winter, Comparator.comparingInt(o -> o[2]));
               while(!winter.isEmpty()){
               int[] w = winter.pop();
			   int r = w[0];
			   int c = w[1];
			   int age = w[2];
			   if (board[r][c] >= age) {
		   		   board[r][c] -= age;
				   spring.push(new int[] { r, c, age + 1 });
			   } else {
				   dead.push(new int[] { r, c, age });
			   }
            }
			
			// 여름
			while (!dead.isEmpty()) {
				int[] d = dead.pop();
				int r = d[0];
				int c = d[1];
				int age = d[2];
				board[r][c] += Math.floor(age / 2);
			}

			// 가을
			while (!spring.isEmpty()) {
				int[] s = spring.pop();
				int r = s[0];
				int c = s[1];
				int age = s[2];
				if (age % 5 == 0) {
					for (int i = 0; i < 8; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];

						if (nr >= 0 && nc >= 0 && nr < N && nc < N)
							winter.push(new int[] { nr, nc, 1 });
					}
				}
				winter.push(new int[] { r, c, age });
			}

			// 겨울

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					board[i][j] += plus[i][j];
				}
			}
		}
        System.out.println(winter.size());
	}
    
}