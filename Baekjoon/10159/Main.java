// 태상이의 훈련소 생활

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] input = new int[N];
		int[] prefix = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int[] order = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()) };

			prefix[order[0] - 1] += order[2];
			if (order[1] < N)
				prefix[order[1]] -= order[2];
		}

		input[0] += prefix[0];
		sb.append(input[0]);
		sb.append(" ");

		for (int i = 1; i < N; i++) {
			prefix[i] = prefix[i] + prefix[i - 1];
			input[i] += prefix[i];
			sb.append(input[i]);
			sb.append(" ");

		}
		System.out.println(sb.toString().trim());
	}
}
