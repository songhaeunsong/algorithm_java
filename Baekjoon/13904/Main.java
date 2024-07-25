import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
//	static List<Integer>[] input;

	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
		N = Integer.parseInt(br.readLine());
		List<int[]> input = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			int[] temp = new int[2];
			temp[0] = t;
			temp[1] = s;
			input.add(temp);
		}

		minHeap.add(0);
		Collections.sort(input, (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(b[1], a[1]));

		for (int i = 0; i < N; i++) {
			int[] temp = input.get(i);
			int time = temp[0];
			int score = temp[1];
            
			if (minHeap.size() < time) {
				minHeap.add(score);
				continue;
			}
            
			int target = minHeap.poll();
			minHeap.add(target > score ? target : score);
		}

        int sum = 0;
        for (int num : minHeap) {
            sum += num;
        }

        System.out.println(sum);
	}
}