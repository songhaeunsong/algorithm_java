// 최단경로

import java.util.*;
import java.io.*;

class Main {
    static int V;
    static int E;
    static int start;
    static int[] costs;
    static ArrayList<Node>[] edges;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        edges = new ArrayList[V+1];
        for(int i = 0; i <= V; i++){
            edges[i] = new ArrayList<>();
        }
        
        costs = new int[V+1];

        for(int i = 0; i <= V; i++){
            costs[i] = Integer.MAX_VALUE;
        }
        
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[from].add(new Node(to, w));
        }

        dijkstra(start);
        
        for(int i = 1; i < V+1; i++){
            if(costs[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(costs[i]).append("\n");
        }

        System.out.print(sb);
    }

    static void dijkstra(int s){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        
        pq.add(new Node(s, 0));
        costs[s] = 0;

        while(!pq.isEmpty()){
            Node n = pq.poll();
            int t = n.to;
            int cost = n.cost;

            if(costs[t] < cost) continue;
            for(Node nn : edges[t]){
                if(costs[nn.to] > cost+ nn.cost){
                    costs[nn.to] = cost+ nn.cost;
                    pq.add(new Node(nn.to, costs[nn.to]));
                } 
            }
        }
    }
}
class Node {
    int to;
    int cost;

    Node(int to, int cost){
        this.to = to;
        this.cost = cost;
    }
}