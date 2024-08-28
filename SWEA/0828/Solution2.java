// 3289. 서로소 집합

import java.util.*;
import java.io.*;
public class Solution2 {
    
        static int T;
        static int N;
        static int M;
        static int[] unf;
        
        public static void main(String[] args) throws NumberFormatException, IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            StringBuilder sb = new StringBuilder();
            
            T = Integer.parseInt(br.readLine());
    
            for(int tc = 1; tc <= T; tc++) {
                st = new StringTokenizer(br.readLine());
                sb.append("#").append(tc).append(" ");
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                unf = new int[N+1];
                for(int node = 1; node <= N; node++) {
                    unf[node] = node;
                }
                
                for(int i = 0; i < M; i++) {
                    st = new StringTokenizer(br.readLine());
                    int type = Integer.parseInt(st.nextToken());
                    int from = Integer.parseInt(st.nextToken());
                    int to = Integer.parseInt(st.nextToken());
                    
                    if(type == 1) sb.append(Check(from, to));
                    else Union(from, to);
                }
                sb.append("\n");
            }
            
            System.out.print(sb);
        }

        public static void Union(int a, int b) {
            int fa = Find(a);
            int fb = Find(b);
            if (fa != fb) {
                unf[fa] = fb;
            }
        }
        
        public static int Find(int n) {
            if(n == unf[n]) return n;
            else return unf[n] = Find(unf[n]);
        }
        
        public static int Check(int a, int b) {
        	int fa = Find(a);
            int fb = Find(b);
            
            if(fa == fb) return 1;
            else return 0;
        }        
        
    }