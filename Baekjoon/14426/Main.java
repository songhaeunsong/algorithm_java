// 접두사 찾기

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] words = new String[N];
        String[] queries = new String[M];
        int totalCount = 0;
        
        for(int i = 0; i < N; i++){
            words[i] = br.readLine();
        }

        Arrays.sort(words);
        
        for(int i = 0; i < M; i++){
            queries[i] = br.readLine();
        }

        for(String prefix : queries){
            if(binarySearch(words, prefix)){
                totalCount++;
            }
        }
        
        System.out.println(totalCount);
        
    }

    public static boolean binarySearch(String[] arr, String prefix){
        
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            boolean isPrefix = arr[mid].startsWith(prefix);

            if(isPrefix)return true;
            else if(arr[mid].compareTo(prefix) < 0) left = mid + 1;
            else right = mid - 1;
        }

        return false;
    }
}