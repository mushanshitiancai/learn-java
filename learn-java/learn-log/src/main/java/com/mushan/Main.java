package com.mushan;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by mazhibin on 16/9/27
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
//        test2();

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(1);
        HashSet<Integer> set = new HashSet<Integer>(list);
        System.out.println(set);

        System.out.println(isDuplicate(list));
        System.out.println(isDuplicate(set));
    }

    public static <T> boolean isDuplicate(Collection<T> collection){
        Set<T> uniques = new HashSet<T>();

        for (T t : collection) {
            if(uniques.contains(t)){
                return true;
            }else{
                uniques.add(t);
            }
        }

        return false;
    }

    public static void test1() throws IOException{
        //        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        // print logback's internal status
//        StatusPrinter.print(lc);

        logger.trace("======trace");
        logger.debug("======debug");
        logger.info("======info");
        logger.warn("======warn");
        logger.error("======error");

        LoggerContext context = (LoggerContext)LoggerFactory.getILoggerFactory();
        for (ch.qos.logback.classic.Logger logger : context.getLoggerList()) {
            for (Iterator<Appender<ILoggingEvent>> index = logger.iteratorForAppenders(); index.hasNext();) {
                Appender<ILoggingEvent> appender = index.next();
                if(appender instanceof FileAppender){
                    FileAppender fileAppender = (FileAppender) appender;
                    File file = new File(fileAppender.getFile());
                    String canonicalPath = file.getCanonicalPath();
                    System.out.println(canonicalPath);
                }
            }
        }
    }

    public static void test2(){
        logger.error("hello {} ","world","2");
        logger.error("hello {} ","world",new RuntimeException("fuck"));
        logger.error("hello {} e:{}","world",new RuntimeException("fuck"));

        logger.info("hello {} ","world",new RuntimeException("fuck"));
        logger.info("hello {} e:{}","world",new RuntimeException("fuck"));
    }
}
