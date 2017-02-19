//Xavier Horton
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.*;
public class Markov_Chain{
    public static void main(String[] args){
        String s = "";
        try{
            File f = new File("raindrop.txt");
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                s+=" " + sc.next();
            }
        }catch(Exception e){

        }
        //System.out.println(ngrams(3,s));
        //System.out.println(split(0,s));
        System.out.println(generate(40,split(0,s)));
    }
    public static class obj{
        String val;
        ArrayList<Integer> othr = new ArrayList();
        public obj(String v){
            val = v;
        }
        public String toString(){
            return  "(" + val + " ," + othr +")";
        }
    }
    public static ArrayList<obj> split(int a, String s){
        ArrayList<obj> ans = new ArrayList<obj>();
        ArrayList<String> strs = new ArrayList<String>();
        if(a > 0){
            for(int i = 0; i <= s.length()-a; i ++){
                obj tmp = new obj(" ");
                tmp.val = s.substring(i,i+a);
                tmp.othr.add(i);
                ans.add(tmp);
                strs.add(tmp.val);
            }
            return ans;
        } else {
            Scanner sc = new Scanner(s);
            int i = 0;
            while(sc.hasNext()){
                obj tmp = new obj(" ");
                tmp.val = sc.next();
                tmp.othr.add(i+1);
                int c = strs.indexOf(tmp.val);
                if(c > -1){
                    ans.get(c).othr.add(i);
                }else{
                    ans.add(tmp);
                    i++;
                    strs.add(tmp.val);
                }
            }
            obj x  = new obj(" ");
            x.othr.add(0);
            ans.add(x);
            return ans;
        }
    }
    public static String generate(int length, ArrayList<obj> L){
        Random r = new Random();
        int c = 0;
        String s = "";
        for(int i = 0; i < length; i++){
            obj word = L.get(c);
            int k = word.othr.size();
            c = word.othr.get(r.nextInt(k));
            s += " " + word.val;
        }
        return s;
    }
}
