import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int edgeNum;
    static int count;
    static ArrayList<Integer>[] edges;
    static Queue<Integer> q;
    static int[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        edgeNum = Integer.parseInt(br.readLine());
        q = new LinkedList<>();
        edges = new ArrayList[N+1];
        visited = new int[N+1];
        count = 0;
        
        for(int i = 0; i < N+1; i++){
            edges[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edgeNum; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edges[from].add(to);
            edges[to].add(from);
        }

        q.add(1);
        visited[1] = 1;

        while(!q.isEmpty()){
            int n = q.poll();

            for(int nn : edges[n]){
                if(visited[nn] == 0){
                    visited[nn] = 1;
                    count++;
                    q.add(nn);
                }
            }
        }
        
        
        System.out.println(count);

        
    }
}