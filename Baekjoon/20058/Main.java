import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int Q;
    static int len;
    static int remainIce;
    static int[][] visited;
    static int[][] board;
    static int[] stages;
    static int totalSum;
    static int max;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        stages = new int[Q];
        len = (int) Math.pow(2, N);
        board = new int[len][len];
        remainIce = 0;
        max = 0;
        totalSum = 0;

        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < len; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int level = Integer.parseInt(st.nextToken());

            fireStorm((int) Math.pow(2, level));
            remove();
        }

        visited = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (visited[i][j] == 0 && board[i][j] != 0) {

                    visited[i][j] = 1;
                    int[] tmp = bfs(i, j);
                    int count = tmp[0];
                    int sum = tmp[1];

                    totalSum += sum;
                    max = Math.max(max, count);
                }
            }
        }

        System.out.println(totalSum);
        System.out.println(max);

    }

    static void fireStorm(int level) {
        int[][] tmpBoard = new int[len][len];
        for (int i = 0; i < len; i += level) {
            for (int j = 0; j < len; j += level) {
                rotate(j, i, tmpBoard, level);
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                board[i][j] = tmpBoard[i][j];
            }
        }
    }

    static void rotate(int sx, int sy, int[][] tmpBoard, int level) {

        for (int i = 0; i < level; i++) {
            for (int j = 0; j < level; j++) {
                tmpBoard[j + sx][level - i - 1 + sy] = board[i + sx][j + sy];
            }
        }
    }

    static void remove() {
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {

                if (board[i][j] == 0)
                    continue;
                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int ni = i + dx[d];
                    int nj = j + dy[d];

                    if (ni < 0 || ni >= len || nj < 0 || nj >= len)
                        continue;

                    if (board[ni][nj] > 0)
                        count++;
                }

                if (count < 3) {
                    list.add(new int[] { i, j });
                }
            }
        }

        for (int[] coor : list) {
            board[coor[0]][coor[1]]--;
        }
    }

    static int[] bfs(int r, int c) {
        Deque<int[]> dq = new ArrayDeque<>();

        int count = 1;
        int sum = board[r][c];
        dq.addLast(new int[] { r, c });

        while (!dq.isEmpty()) {
            int[] tmp = dq.pollFirst();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= len || ny >= len || visited[nx][ny] == 1 || board[nx][ny] == 0)
                    continue;

                visited[nx][ny] = 1;
                count++;
                sum += board[nx][ny];
                dq.addLast(new int[] { nx, ny });

            }
        }

        return new int[] { count, sum };
    }

}
