import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int K;
    static int[][] board;
    static boolean[][] visited;
    static int[][] dice;
    static int downsideNum;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        answer = 0;

        dice = new int[][] { { 0, 2, 0 }, { 4, 1, 3 }, { 0, 5, 0 } };
        downsideNum = 6;

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // simulation
        int di = 0;
        int x = 0;
        int y = 0;

        while (K > 0) {

            int nx = x + dx[di];
            int ny = y + dy[di];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                di = (di + 2) % 4;
                continue;

            }

            int newNum = move(downsideNum, di);
            downsideNum = newNum;
            x = nx;
            y = ny;

            // compare a to b
            if (downsideNum > board[x][y])
                di = (di + 1) % 4;
            if (downsideNum < board[x][y])
                di = (di + 3) % 4;

            answer += bfs(x, y);
            K--;
        }

        System.out.println(answer);
    }

    static int bfs(int r, int c) {

        Deque<int[]> dq = new ArrayDeque<>();
        visited = new boolean[N][M];
        visited[r][c] = true;
        int count = 1;
        int target = board[r][c];

        dq.addLast(new int[] { r, c });

        while (!dq.isEmpty()) {
            int[] tmp = dq.pollFirst();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] != target || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                dq.addLast(new int[] { nx, ny });
                count++;

            }

        }
        return count * target;
    }

    static int move(int a, int i) {
        if (i == 0)
            return moveRight(a);
        if (i == 1)
            return moveDown(a);
        if (i == 2)
            return moveLeft(a);
        return moveUp(a);

    }

    static int moveRight(int a) {
        int nextA = dice[1][2];
        dice[1][2] = dice[1][1];
        dice[1][1] = dice[1][0];
        dice[1][0] = a;

        return nextA;

    }

    static int moveLeft(int a) {
        int nextA = dice[1][0];
        dice[1][0] = dice[1][1];
        dice[1][1] = dice[1][2];
        dice[1][2] = a;

        return nextA;
    }

    static int moveUp(int a) {
        int nextA = dice[0][1];
        dice[0][1] = dice[1][1];
        dice[1][1] = dice[2][1];
        dice[2][1] = a;

        return nextA;
    }

    static int moveDown(int a) {
        int nextA = dice[2][1];
        dice[2][1] = dice[1][1];
        dice[1][1] = dice[0][1];
        dice[0][1] = a;
        return nextA;
    }
}
