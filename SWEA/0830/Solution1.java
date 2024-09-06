// 최소 스패닝 트리

import java.io.*;
import java.util.*;

public class Solution1 {
    static int T;
    static int V;
    static int E;
    static int[] parent;
    static int[][] edges;
    static int count;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            edges = new int[E][3];
            long minCost = 0;
            count = 0;
            parent = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
                edges[i][2] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(edges, (a, b) -> a[2] - b[2]);
            
            for (int[] edge : edges) {
                int a = find(edge[0]);
                int b = find(edge[1]);

                if (a != b) {
                    count++;
                    union(a, b);
                    minCost += edge[2];
                }

                if (count == V - 1) {
                    break;
                }
            }

            sb.append("#").append(tc).append(" ").append(minCost).append("\n");

        }

        System.out.print(sb);
    }
    
    public static int find(int n) {
        if (n == parent[n])
            return n;
        else
            return parent[n] = find(parent[n]);
    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        
        if (fa > fb)
            parent[fa] = fb;
        else parent[fb] = fa;
    }
}
