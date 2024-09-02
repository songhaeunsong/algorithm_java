// 외판원 순회 2

import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int[][] costs;
    static boolean[] visited;
    static int min;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        costs = new int[N][N];
        visited = new boolean[N];
        min = Integer.MAX_VALUE;
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < N; i++){
            visited[i] = true;
            backtracking(i, i, 0, 1);
            visited[i] = false;
        }

        System.out.println(min);
    }

    static void backtracking(int start, int prev, int cost, int count){
        if(count == N){
            if(costs[prev][start] == 0)return;
            min = Math.min(min, cost+ costs[prev][start]);
            return;
        }
        for(int i = 0; i < N; i++){
            if(!visited[i] && costs[prev][i] != 0){
                visited[i] = true;
                backtracking(start, i, cost + costs[prev][i], count+1);
                visited[i] = false;
            }
            
        }
    }
}