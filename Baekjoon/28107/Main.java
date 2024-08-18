import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int M;
    static Map<Integer, Integer> sushiMap;
    static int[] eaten;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<List<Integer>> orders = new ArrayList<>();
        sushiMap = new HashMap<>();
        eaten = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int orderN = Integer.parseInt(st.nextToken());
            List<Integer> orderList = new ArrayList<>();
            for (int o = 0; o < orderN; o++) {
                orderList.add(Integer.parseInt(st.nextToken()));
            }
            orders.add(orderList);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int sushi = Integer.parseInt(st.nextToken());
            sushiMap.put(sushi, sushiMap.getOrDefault(sushi, 0) + 1);
        }

        for (int i = 0; i < N; i++) {
            for (int sushi : orders.get(i)) {
                if (sushiMap.containsKey(sushi) && sushiMap.get(sushi) > 0) {
                    sushiMap.put(sushi, sushiMap.get(sushi) - 1);
                    eaten[i]++;
                }
            }
        }

        for (int count : eaten) {
            sb.append(count).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
