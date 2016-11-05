package chapter_1;

import com.sun.xml.internal.ws.assembler.jaxws.TerminalTubeFactory;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yahaa on 16-11-5.
 */
public class Test1 {
    private File file=null;
    private FileReader fileReader=null;
    private Map<String,Integer>mp=new TreeMap<>();
    public Test1(String path){
        file=new File(path);
        try{
            fileReader=new FileReader(file);
        }catch(Exception e){
            System.out.println("can't find file");
        }
    }

    public void countWord(){
        Scanner input=new Scanner(fileReader);
        while(input.hasNext()){
            String t=input.next();
            int v=0;
            if(mp.containsKey(t)){
                v=mp.get(t);
            }
            mp.put(t,v+1);
        }

        Set<Map.Entry<String,Integer>>set=mp.entrySet();
        for(Map.Entry<String,Integer>e:set){
            System.out.println(e.getKey()+" "+e.getValue());
        }
    }

    public static void main(String[]args){
        String s="A curated list of Awesome Userscripts.\n" +
                "\n" +
                "User scripts can improve your browsing experience, and open a lot of possibilities to make the sites you visit better by adding features, making them easier to use, or taking out the annoying bits.\n" +
                "\n";
        Words a=new Words();
        showMap(a.wordFreq1(s));
    }

    public static void showMap(Map map){
        Set<Map.Entry<String,Integer>>set=map.entrySet();
        for(Map.Entry<String,Integer>e:set){
            System.out.println(e.getKey()+" "+e.getValue());
        }
    }

}

class Words{
    private Set<String>non_words=new HashSet<String>(){{
        add("the");add("and");add("of");add("to");add("a");
        add("i");add("it");add("in");add("or");add("is");
        add("d");add("you");add("fuck");add("totot");
    }};

    public Map wordFreq1(String words){
        TreeMap<String,Integer>map=new TreeMap<>();
        Matcher m= Pattern.compile("\\w+").matcher(words);
        while(m.find()){
            String word=m.group().toLowerCase();
            if(!non_words.contains(word)){
                int v=0;
                if(map.containsKey(word))v=map.get(word);
                map.put(word,v+1);
            }
        }
        return map;
    }

    private List<String>regexToList(String words,String regex){
        List<String> wordList=new ArrayList();
        Matcher m=Pattern.compile(regex).matcher(words);
        while(m.find()){
            wordList.add(m.group());
        }
        return wordList;
    }

    public Map WordRreq2(String words){
        TreeMap<String,Integer>map=new TreeMap<>();
        regexToList(words,"\\w+").stream()
                .map(w->w.toLowerCase())
                .filter(w->!non_words.contains(w))
                .forEach(w->map.put(w,map.getOrDefault(w,0)+1));
        return map;
    }
}
