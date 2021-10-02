//package com.techelevator.util;
//
//import org.junit.Assert;
//import org.junit.Test;
//import com.techelevator.util.VMLog;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//public class VMLogTest {
//
//    private File log = new File("D:\\repo\\capstone\\logs\\vending.log");
//
//
//    @Test
//    public void testFeedLog() {
//        String lastLine = "";
//        try (Scanner fileScanner = new Scanner(log)) {
//            while (fileScanner.hasNextLine()){
//                lastLine = fileScanner.next();
//            }
//
//            VMLog.feedLog(5, 5);
//
//            Assert.assertEquals("$5.00", lastLine);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    }
