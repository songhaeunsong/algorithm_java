import java.io.*;
import java.util.*;

public class Solution3 {
	static int N;
	static int start;
	static ArrayList<Integer>[] edges;
	static Deque<int[]> dq = new ArrayDeque<>();
	static boolean[] visited;
	static int deepest;
	static int curDepth;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			//
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			edges = new ArrayList[101];
			visited = new boolean[101];
			deepest = 0;
			curDepth = 0;
			
			for(int i = 0; i < 101; i++) {
				edges[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i = i+2) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				edges[from].add(to);
			}
			
			visited[start] = true;
			dq.addLast(new int[] {start, 0});
			
			// bfs
			while(!dq.isEmpty()) {
				int[] temp = dq.pollFirst();
				int n = temp[0];
				int depth = temp[1];
				if(depth == curDepth) {
					deepest = Math.max(deepest, n);
					
				}
				else {
					curDepth = depth;
					deepest = n;
				}
				for(int next : edges[n]) {
					if(!visited[next]) {
						visited[next] = true;
						dq.addLast(new int[] {next, depth+1});
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(deepest).append("\n");

		}
		System.out.print(sb);
	}

}