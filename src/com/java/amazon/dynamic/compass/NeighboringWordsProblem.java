package com.java.amazon.dynamic.compass;

import java.util.*;

public class NeighboringWordsProblem {

    private static Map<String, List<NeighbouringWords>> INDEXER = new LinkedHashMap<>();
    private static Map<String,Integer> VISITED = new HashMap<>();

    public static void main(String[] args) {
        Page page1 = new Page(1,Arrays.asList("my fat cat cat so".split("\\s+")));
        Page page2 = new Page(2,Arrays.asList("fat oh".split("\\s+")));
        Page page3 = new Page(3,Arrays.asList("wow so wow oh cool".split("\\s+")));

        System.out.println(page1);
        System.out.println(page2);
        System.out.println(page3);

        Book book = new Book(Arrays.asList(page1,page2,page3));
        buildIndexer(book);
        //System.out.println(INDEXER);
        System.out.println(getNeighbouringWords("wow"));
    }


    public static List<NeighbouringWords> getNeighbouringWords(String word){
        return INDEXER.get(word);
    }

    public static void buildIndexer(Book book){
        List<Page> pages = book.pages;
        for(Page page : pages){
            List<String> words = page.words;
            int pageNo = page.pageNo;
            int N = words.size()-1;
            for(int i=0;i<=N;i++) {
                String word = words.get(i);
                VISITED.put(word,VISITED.getOrDefault(word,0)+1);
                int sequenceNo = VISITED.get(word);
                String previousWord=i>0?words.get(i-1):null;
                String nextWord=(i+1)<=N?words.get(i+1):null;
                INDEXER.putIfAbsent(word,new ArrayList<>());
                INDEXER.get(word).add(new NeighbouringWords(pageNo,sequenceNo,Arrays.asList(previousWord,nextWord)));
            }
        }
    }
}


class Book{
    List<Page> pages;

    public Book(List<Page> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "pages=" + pages +
                '}';
    }
}

class Page{
    int pageNo;
    List<String> words;

    public Page(int pageNo, List<String> words) {
        this.pageNo = pageNo;
        this.words = words;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", words=" + words +
                '}';
    }
}

class NeighbouringWords{
    int pageNo;
    int wordSequenceNo;
    List<String> neighbouringWords;

    public NeighbouringWords(int pageNo, int wordSequenceNo, List<String> neighbouringWords) {
        this.pageNo = pageNo;
        this.wordSequenceNo = wordSequenceNo;
        this.neighbouringWords = neighbouringWords;
    }

    @Override
    public String toString() {
        return "NeighbouringWords{" +
                "pageNo=" + pageNo +
                ", neighbouringWords=" + neighbouringWords +
                '}';
    }
}
