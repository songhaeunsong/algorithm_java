import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static List<int[][]> combination;
    static int[][] board;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int min = Integer.MAX_VALUE;
    static int emptySpace;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        emptySpace = 0;
        combination = new ArrayList<>();

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    emptySpace++;
                }
            }
        }

        if (emptySpace == 0)
            System.out.println(0);

        else {

            combinate(0, 0);

            for (int[][] c : combination) {
                bfs(c);
            }

            System.out.println(min == Integer.MAX_VALUE ? -1 : min);

        } // test

    }

    static void bfs(int[][] startLaocation) {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        for (int[] location : startLaocation) {
            dq.addLast(new int[] { location[0], location[1], 0 });
            visited[location[0]][location[1]] = true;
        }
        int count = 0;
        while (!dq.isEmpty()) {
            int[] current = dq.pollFirst();
            int x = current[0];
            int y = current[1];
            int depth = current[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || board[nx][ny] == 1)
                    continue;

                if (board[nx][ny] == 0) {
                    count++;

                    if (count == emptySpace) {
                        min = Math.min(min, depth + 1);
                        return;
                    }

                }
                visited[nx][ny] = true;
                dq.addLast(new int[] { nx, ny, depth + 1 });
            }
        }

    }

    static void combinate(int start, int count) {
        if (count == M) {
            int[][] active = new int[M][2];
            int index = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 3) {
                        active[index][0] = i;
                        active[index][1] = j;
                        index++;
                    }

                }
            }
            combination.add(active);
            return;
        }
        for (int ij = start; ij < N * N; ij++) {

            int i = ij / N;
            int j = ij % N;

            if (board[i][j] == 2) {

                board[i][j] = 3;
                combinate(ij + 1, count + 1);
                board[i][j] = 2;
            }
        }
    }
}