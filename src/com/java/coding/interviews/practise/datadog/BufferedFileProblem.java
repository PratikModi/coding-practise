package com.java.coding.interviews.practise.datadog;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Your task is to create a new file type with a “Buffer”.
 * Every time you write data to this file, you use the Write method like write(“hello world”),
 * but if you have enough buffer in memory (buffer_size), you write to buffer first.
 * Only write to disk when the buffer is full (using the flush method to write to disk).
 * Implement both the Write and flush functions
 */
public class BufferedFileProblem {

    private File file;
    private byte[] buffer;

    private BufferedOutputStream stream;

    private int bufferSize;

    private int count;

    public BufferedFileProblem(File file, int bufferSize){
        this.file = file;
        this.bufferSize=bufferSize;
        this.buffer = new byte[bufferSize];
        this.count=0;
        try {
            this.stream = new BufferedOutputStream(new FileOutputStream(file));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void write(byte[] array) throws Exception{
        if(count<bufferSize){
            System.arraycopy(array,0,buffer,count,array.length);
            count+=array.length;
        }else{
            flush();
            stream.write(array);
            buffer = new byte[bufferSize];
        }
    }

    public void flush() throws Exception{
        if(count>0){
            stream.write(buffer);
            count=0;
        }
    }

}
