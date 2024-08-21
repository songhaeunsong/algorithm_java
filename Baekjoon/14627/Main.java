// 파닭파닭

import java.util.*;
import java.io.*;

class Main {
    static long s;
    static long c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        long[] pa = new long[(int) s];
        for(int i = 0; i < (int) s; i++){
            pa[i] = Long.parseLong(br.readLine());
        }

        long paSum = 0;
        for(long p: pa){
            paSum += p;
        }
        long maxLength = binarySearch(pa);
        
        System.out.println(paSum - maxLength*c);
    }

    public static long binarySearch(long[] pa){
        long left = 0;
        long right = 1000000001l;

        while(left <= right){
            long mid = (left+right)/2;
            long paCount = 0;
            for(long p: pa){
                paCount += p/mid;
                if(paCount >= c)break;
            }
            if(paCount >= c)left = mid + 1;
            else right = mid - 1;
        }
        return right;
    }
}