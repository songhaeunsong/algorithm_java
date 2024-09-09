import java.io.*;
import java.util.*;
 
public class Main {
 
    static int N;
    static int[] parent;
 
    public static void main(String[] args) throws IOException{
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
 
        PriorityQueue<int[]> downhill = new PriorityQueue<>((o1, o2) -> o2[2] - o1[2]);
        PriorityQueue<int[]> uphill = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
 
        int A, B, C;
        int[] tmp;
        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
 
            tmp = new int[] {A, B, C};
            downhill.add(tmp);
            uphill.add(tmp);
        }
 
        System.out.println(kruskal(uphill) - kruskal(downhill));
    }
 
    private static int kruskal(PriorityQueue<int[]> pq) {
 
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
 
        int count = 0;
        int[] now;
        while(!pq.isEmpty()) {
            now = pq.poll();
 
            if(union(now[0], now[1])) {
                if(now[2] == 0) count++;
            }
        }
 
        return count * count;
    }
 
    private static int find(int a) {
        if(parent[a] == a) return a;
 
        return parent[a] = find(parent[a]);
    }
 
    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
 
        if(a == b) return false;
 
        if(a < b) parent[b] = a;
        else parent[a] = b;
        return true;
    }
}