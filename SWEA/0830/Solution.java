import java.io.*;
import java.util.*;

public class Solution {
	static double E; // 환경 부담 세율
	static int N;
	static int L; // 해저터널의 길이
	static int[] xs;
	static int[] ys;
	static ArrayList<Node>[] edges;
    static boolean[] visited; 
    static double costs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		= new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			xs = new int[N];
			ys = new int[N];
            visited = new boolean[N];
            costs = 0;

			edges = new ArrayList[N + 1];

			for (int i = 0; i <= N; i++) {
				edges[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				xs[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				ys[j] = Integer.parseInt(st.nextToken());
			}
            
            E = Double.parseDouble(br.readLine());
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					double L = Math.pow(xs[j] - xs[i], 2) + Math.pow(ys[j] - ys[i], 2);
                    edges[i].add(new Node(j,L*E));
				}
			}

            prim(0);
            long answer = Math.round(costs);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");

		}
		System.out.print(sb);
	}

    static void prim(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> node.cost));
        pq.add(new Node (start, 0));

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int n = node.end;
            double cost = node.cost;

            if(visited[n]) continue;
            visited[n] = true;
            costs += cost;

            for(Node nn : edges[n]){
                if(!visited[nn.end]){
                    pq.add(nn);
                }
            }
        }
    }
}

 class Node {
     int end;
     double cost;
     
     Node (int end, double cost){
         this.end = end;
         this.cost = cost;
     }
 }