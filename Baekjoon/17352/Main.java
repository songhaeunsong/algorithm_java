// 여러분의 다리가 되어드리겠습니다.

import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		edges = new ArrayList[N + 1];

		for (int i = 0; i < N + 1; i++) {
			edges[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < N - 2; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			edges[from].add(to);
			edges[to].add(from);

		}

		// bfs
		boolean[] visited = new boolean[N + 1];
		Deque<Integer> dq = new ArrayDeque<>();
		dq.addLast(1);
		visited[1] = true;

		while (!dq.isEmpty()) {
			int node = dq.pollFirst();

			for (int next : edges[node]) {
				if (!visited[next]) {
					visited[next] = true;
					dq.addLast(next);
				}
			}
		}
		int nodeFrom = -1;
		int nodeTo = -1;

		for (int node = 1; node < N + 1; node++) {
			if (visited[node] && nodeFrom == -1) {
				nodeFrom = node;
			}
			if (!visited[node] && nodeTo == -1) {
				nodeTo = node;
			}
		}

		System.out.println(nodeFrom + " " + nodeTo);
	}
}