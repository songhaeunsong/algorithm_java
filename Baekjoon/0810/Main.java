import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringBuilder sb = new StringBuilder();
        
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < input.length(); i++){
            
            char key = input.charAt(i);
            map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
        }

        Map<Character, Integer> sortedMap = new TreeMap<>(map);
        
        int oddCount = 0;
        char oddChar = '\0';
        

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            char key = entry.getKey();

            if (value % 2 != 0) {
                oddCount++;
                oddChar = key;
            }
            
            for (int i = 0; i < value / 2; i++) {
                sb.append(key);
            }
            if (oddCount > 1) {
                System.out.println("I'm Sorry Hansoo");
                return;
            }

            
            
        }


        String result = sb.toString();
        String reverseResult = sb.reverse().toString();

        System.out.println(oddCount == 1 
            ? result + oddChar + reverseResult
            : result + reverseResult
        );
            
    }
}