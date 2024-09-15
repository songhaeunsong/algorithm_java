import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int M;
    static List<int[]> fishes;
    static Map<Integer, Set<Integer>> starts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fishes = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            fishes.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
        }

        int half = K / 2;
        int totalCaught = 0;

        // 물고기 2개 혹은 1개로부터 시작점 찾고 set에 저장
        starts = new HashMap<>();

        for (int i = 0; i < fishes.size(); i++) {
            for (int j = 0; j < fishes.size(); j++) {

                int[] first = fishes.get(i);
                int[] second = fishes.get(j);

                int r = Math.min(first[0], second[0]);
                int c = Math.min(first[1], second[1]);

                Set<Integer> set;

                set = starts.containsKey(r) ? starts.get(r) : new HashSet<>();

                set.add(c);
                starts.put(r, set);
            }
        }

        // 그물 규격 정하기
        for (int i = 1; i < half; i++) {
            if (i > N || half - i > N)
                continue;

            int caught = simulate(i, half - i);

            totalCaught = Math.max(totalCaught, caught);
        }

        System.out.println(totalCaught);

    }

    // 구해놓은 시작점으로부터 정해진 규격의 그물을 펼치면서 물고기 수 세기

    static int simulate(int row, int column) {
        int max = 0;

        for (int key : starts.keySet()) {
            Set<Integer> set = starts.get(key);

            for (int value : set) {
                int count = countFishes(key, value, key + row, value + column);

                max = Math.max(max, count);
            }

        }
        return max;
    }

    static int countFishes(int startr, int startc, int endr, int endc) {
        int count = 0;
        for (int[] fish : fishes) {
            if (fish[0] >= startr && fish[0] <= endr && fish[1] >= startc && fish[1] <= endc)
                count++;
        }

        return count;
    }

}