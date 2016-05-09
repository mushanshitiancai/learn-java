package com;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;

/**
 * Created by mazhibin on 16/5/6
 */
public class IOTest {

    String inputFilePath = getClass().getClassLoader().getResource("hello_input.txt").getPath();

    @Test
    public void bufferedFileTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));

        String s;
        StringBuilder sb = new StringBuilder();
        while((s = bufferedReader.readLine()) != null){
            sb.append(s).append("\n");
        }
        bufferedReader.close();

        Assert.assertEquals(sb.toString(),"hello world.\n");
    }

    @Test
    public void stringReaderTest() throws IOException {
        String input = "hello world.";
        StringReader stringReader = new StringReader(input);

        StringBuilder stringBuilder = new StringBuilder();
        int c;
        while((c = stringReader.read()) != -1){
            stringBuilder.append((char) c);
        }

        Assert.assertEquals(stringBuilder.toString(),input);
    }

    @Test
    public void formattedMemoryInputTest() throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream("input".getBytes("UTF-16BE")));

        Assert.assertEquals(dataInputStream.readChar(), 'i');
    }

    @Test
    public void printWriterTest() throws IOException {
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));

        printWriter.print(100);
        printWriter.close();

        PrintWriter printWriter2 = new PrintWriter("out.txt");

        printWriter2.print(false);
        printWriter2.close();
    }

    @Test
    public void dataOutputStreamTest() throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("out.txt")));
        dataOutputStream.writeBoolean(false);
        dataOutputStream.close();
    }

    @Test
    public void availableTest() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(inputFilePath);

        int available = fileInputStream.available();
        Assert.assertEquals(available, 12);
    }

    @Test
    public void randomAccessFile() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.inputFilePath,"r");

        System.out.println(randomAccessFile.readChar());
    }

    @Test
    public void stdinTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println((bufferedReader.readLine()));
    }

    @Test
    public void processBuilderTest() throws IOException {
        Process process = new ProcessBuilder("ls","-a").start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String s;
        while((s = reader.readLine()) != null){
            System.out.println(s);
        }
    }

    @Test
    public void stdoutTest(){
        PrintWriter printWriter = new PrintWriter(System.out,true);
        printWriter.println("hello");
    }

    @Test
    public void Test(){
        new AA("1");
    }
}

class A{
    public A(){
        System.out.println("A");
    }
    public A(String name){
        System.out.println("A+"+name);
    }
}

class AA extends A{
    public AA(){
        System.out.println("AA");
    }
    public AA(String name){
//        super(name);
        System.out.println("A+"+name);
    }
}