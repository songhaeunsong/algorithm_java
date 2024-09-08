import java.io.*;
import java.util.*;


class Main {
    static String[] hexagram = new String[5];
    static int[] hexaNums = new int[12]; 
    static int[] smallest = new int[12];
    static char[] smallestChar = new char[12];
    static boolean[] usedNum = new boolean[13];
    static boolean found = false;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));       
        StringBuilder sb = new StringBuilder();


        for(int i = 0; i < 5; i++){
            hexagram[i] = br.readLine();
        }
        

        hexaNums[0] = hexagram[0].charAt((4));
        hexaNums[1] = hexagram[1].charAt((1));
        hexaNums[2] = hexagram[1].charAt((3));
        hexaNums[3] = hexagram[1].charAt((5));
        hexaNums[4] = hexagram[1].charAt((7));
        hexaNums[5] = hexagram[2].charAt((2));
        hexaNums[6] = hexagram[2].charAt((6));
        hexaNums[7] = hexagram[3].charAt((1));
        hexaNums[8] = hexagram[3].charAt((3));
        hexaNums[9] = hexagram[3].charAt((5));
        hexaNums[10] = hexagram[3].charAt((7));
        hexaNums[11] = hexagram[4].charAt((4));

        for(int i = 0; i < 12; i++){
            if(hexaNums[i] != 120){
                hexaNums[i] -= 64;
                usedNum[hexaNums[i]] = true;
            }
        }
        backtraking(0);

        for(int i = 0; i < 12; i++){
            smallestChar[i] = (char) ('A' + (smallest[i] - 1));
        }
        
        sb.append("....").append(smallestChar[0]).append("....").append("\n").
        append(".").append(smallestChar[1]).append(".").append(smallestChar[2]).append(".").append(smallestChar[3]).append(".").append(smallestChar[4]).append(".").append("\n").
        append("..").append(smallestChar[5]).append("...").append(smallestChar[6]).append("..").append("\n").
        append(".").append(smallestChar[7]).append(".").append(smallestChar[8]).append(".").append(smallestChar[9]).append(".").append(smallestChar[10]).append(".").append("\n").
        append("....").append(smallestChar[11]).append("....");

        System.out.print(sb);
    }

    static void backtraking(int place){
        if(found) return;
        
        if(place == 12) {
            if(
            !found && 
            isMagicStar(1,2,3,4) &&
            isMagicStar(7,8,9,10) &&
            isMagicStar(0,3,6,10) &&
            isMagicStar(0,2,5,7) &&
            isMagicStar(1,5,8,11) &&
            isMagicStar(4,6,9,11)){
                found = true;
                for(int s = 0; s < 12; s++){
                    smallest[s] = hexaNums[s];
                }
            }
            return;
        }
        
        if(hexaNums[place] != 120) {
            backtraking(place + 1);
            return;
        }

        for(int i = 1; i <= 12; i++){ // i: 알파벳
            if(!usedNum[i]){
                hexaNums[place] = i;
                usedNum[i] = true;
                backtraking(place + 1);
                hexaNums[place] = 120;
                usedNum[i] = false;

            }

        }
    }

    static boolean isMagicStar(int i1, int i2, int i3, int i4) {
        return hexaNums[i1] + hexaNums[i2] + hexaNums[i3] + hexaNums[i4] == 26;
    }
}