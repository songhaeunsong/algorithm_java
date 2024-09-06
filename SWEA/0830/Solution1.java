import java.util.*;
import java.io.*;

public class Solution1 {
	static int T;
	static int V;
	static int E;
	static ArrayList<Node>[] edges;
	static boolean[] visited;
	static long costs;
	
	
	public static void main(String[] arg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			costs = 0;
			
			// 간선 정보 리스트 초기화
			edges = new ArrayList[V+1];
			
			for(int i = 1; i <= V; i++) {
				edges[i] = new ArrayList<Node>();
			}
			
			// visited 초기화
			visited = new boolean[V+1];
			
			// list에 노드 정보 넣기
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edges[a].add(new Node(b,c));
				edges[b].add(new Node(a,c));
			}
			
			prim(1);
			sb.append("#").append(tc).append(" ").append(costs).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void prim(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
		pq.add(new Node(start, 0));
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int n = node.end;
			int cost = node.cost;
			
			if(visited[n]) continue;
			
			visited[n] = true;
			costs += cost;
			
			for(Node nn : edges[n]) {
				if(!visited[nn.end]) {
					pq.add(nn);
				}
			}
		}
		
		
//		pq.add(new Node(start, 0));
		
	}

}

class Node{
	int end;
	int cost;
	
	Node(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}
}