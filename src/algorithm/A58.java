package algorithm;

import java.io.BufferedInputStream;
import java.util.*;

/**
 * Created by zihua on 16-11-17.
 */
public class A58 {

    private Scanner input=new Scanner(new BufferedInputStream(System.in));

    public void solve(){

        String s=input.nextLine();
        Character []set={'h','e','l','l','o'};
        int i=0;
        boolean ans=true;
        while(i<set.length){
            int index=s.indexOf(set[i]);
            if(index>=0){
                s=s.substring(index+1);
            }
            else {
                ans=false;
                break;
            }
            i++;
        }
        System.out.println(ans?"YES":"NO");

    }

    public static void main(String[]args){
        A58 a=new A58();
        a.solve();
    }

}

