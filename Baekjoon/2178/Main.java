import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char[][] board;
    static boolean[][] visited;
    static Deque<Maze> dq = new ArrayDeque<>();
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static int minCount;
    
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M];
        minCount = 0;
        
        for(int i = 0 ; i < N; i++){
            String temp = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = temp.charAt(j);
            }
        }
        
        dq.addLast(new Maze(0,0,1));
        visited[0][0] = true;

        while(!dq.isEmpty()){
            Maze maze = dq.pollFirst();
            int r = maze.row;
            int c = maze.column;
            int d = maze.depth;

            if(r == N-1 && c == M-1){
                minCount = d;
                break;
            }

            for(int i = 0; i < 4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == '0') continue;

                if(!visited[nr][nc]){
                    visited[nr][nc] = true;
                    dq.addLast(new Maze(nr, nc, d+1));
                }
            }
        }
        System.out.println(minCount);
    }
}

class Maze {
    int row;
    int column;
    int depth;

    Maze(int row, int column, int depth){
        this.row = row;
        this.column = column;
        this.depth = depth;
    }
}