/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;
*/
//import for Scanner and other utility  classes
import java.util.*;


class TestClass {
    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
*/
     
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();

        for (int i = 0; i < N; i++) {
            
            int t=s.nextInt();
            s.nextLine();
            int arr[]=new int[t];
            int min[]=new int[t];
             arr[0]=s.nextInt();
            for(int j=1;j<t;j++){
                arr[i]=s.nextInt();
                if(arr[i]<min[i-1])
                    min[i]=arr[i];
                else min[i]=min[i-1];
            }
           int ans=0;
            for(int j=t-2;j>=0;j++){
                int temp=j*min[i];
                if(temp>ans)
                    ans=temp;
            }
            
            System.out.println(ans);
        }
        

    }
    
}
