// 볼 모으기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int min;
        char[] balls= br.readLine().toCharArray();
        int ballLen = balls.length;
        int first = balls[0];
        int last = balls[ ballLen - 1];
        int rCount = 0;
        int bCount = 0;
        
        for(char ball: balls){
            if(ball == 'R') rCount++;
            else bCount++;
        }

        min = Math.min(bCount, rCount);
        
        int i = 0;
        int count = 0;
        while(i < ballLen){
            
            if(balls[i] == first) count++;
            else {
                if(first == 'R'){
                    min = Math.min(min, rCount - count);
                }
                else {
                    min = Math.min(min, bCount - count);
                }
                break;
            }
            i++;
        }

        i = ballLen - 1;
        count = 0;
        
        while(i >= 0){
            if(balls[i] == last) count++;
            else {
                if(last == 'R'){
                    min = Math.min(min, rCount - count);
                }
                else {
                    min = Math.min(min, bCount - count);
                }
                break;
            }
            i--;
        }
            System.out.println(min);
    }
}