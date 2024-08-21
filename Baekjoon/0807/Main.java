// 패턴

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sx =Integer.parseInt(st.nextToken());
        int sy =Integer.parseInt(st.nextToken());
        int ex =Integer.parseInt(st.nextToken());
        int ey =Integer.parseInt(st.nextToken());

        long count = 0;
        int max = Math.max(ex, ey);
        int squareStart = max % 2 == 0 ? max-1: max ;
        int squareEnd = Math.min(sx, sy);
        
        for(int i = squareStart; i >= 1; i-=2){
            
            if(i >= sx && i < ex && i >= sy&& i < ey){
                count+= i-sx + i-sy +1;
            }
            else if(i >= sx && i < ex && i >= ey-1){
                 count+= ey-sy;
            }
            else if(i >= sy && i < ey&& i >= ex-1){
                 count+= ex-sx;
            }
        }
        System.out.println(count);
    }
}