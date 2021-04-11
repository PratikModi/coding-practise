package com.java.amazon.dynamic.google;

import java.util.Iterator;

/**
 * Created by Pratik1 on 20-06-2020.
 * This problem was asked by Google.
 *
 * Given an iterator with methods next() and hasNext(), create a wrapper iterator, PeekableInterface,
 * which also implements peek(). peek shows the next element that would be returned on next().
 *
 * Here is the interface:
 *
 * class PeekableInterface(object):
 *     def __init__(self, iterator):
 *         pass
 *
 *     def peek(self):
 *         pass
 *
 *     def next(self):
 *         pass
 *
 *     def hasNext(self):
 *         pass
 *
 */
public class PeekableIteratorProblem implements Iterator<Integer> {

    public Integer peek;
    public Iterator<Integer> iterator;

    public PeekableIteratorProblem(Iterator<Integer> iterator) {
        this.iterator = iterator;
        this.peek = iterator.hasNext()?iterator.next():null;
    }

    public Integer peek(){
        if(peek==null) throw new RuntimeException("No More Elements");
        return peek;
    }

    @Override
    public boolean hasNext() {
        return peek!=null;
    }

    @Override
    public Integer next() {
        if(peek==null) throw new RuntimeException("No More Elements");
        Integer ret = peek;
        peek = iterator.hasNext()?iterator.next():null;
        return ret;
    }
}
