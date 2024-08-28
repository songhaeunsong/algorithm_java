// 7465. 창용 마을 무리의 개수

import java.util.*;
import java.io.*;
public class Solution1 {
    
        static int T;
        static int N;
        static int M;
        static int[] unf;
        static boolean[] visited;
        static int total;
        
        public static void main(String[] args) throws NumberFormatException, IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            StringBuilder sb = new StringBuilder();
            
            T = Integer.parseInt(br.readLine());
    
            for(int tc = 1; tc <= T; tc++) {
                st = new StringTokenizer(br.readLine());
                
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                unf = new int[N+1];
                visited = new boolean[N+1];
                total = 0;
                
                for(int node = 1; node <= N; node++) {
                    unf[node] = node;
                }
                
                for(int i = 0; i < M; i++) {
                    st = new StringTokenizer(br.readLine());
                    
                    int from = Integer.parseInt(st.nextToken());
                    int to = Integer.parseInt(st.nextToken());
                    
                    Union(from, to);
                }
                
                
                for (int i = 1; i <= N; i++) {
                    int tmp = Find(i);
                    if (!visited[tmp]) {
                        total++;
                        visited[tmp] = true;
                    }
                }
                
                sb.append("#").append(tc).append(" ").append(total).append("\n");
            }
            
            System.out.print(sb);
        }

        public static void Union(int a, int b) {
            int fa = Find(a);
            int fb = Find(b);
            if (fa != fb) {
                unf[fa] = unf[fb];
            }
        }
        
        public static int Find(int n) {
            if(n == unf[n]) return n;
            else return unf[n] = Find(unf[n]);
        }
        
    }