package com.techelevator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VMLog {
    public static void feedLog(double total, double input){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        File logFile = new File("logs/vending.log");

        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(logFile, true))){
            dataOutput.println();
            dataOutput.printf(dtf.format(now) + " FEED MONEY: $%.2f $%.2f", input, total);
        } catch (FileNotFoundException e) {
            System.err.print(e);
        }
    }
    public static void SaleLog(String itemName, String code, double total, double remainder){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        File logFile = new File("logs/vending.log");

        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(logFile, true))){
            dataOutput.println();
            dataOutput.printf(dtf.format(now)+ " " + itemName + " " + code + " $%.2f $%.2f", total, remainder);
        } catch (FileNotFoundException e) {
            System.err.print(e);
        }
    }
    public static void ChangeLog(double total, double input){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        File logFile = new File("logs/vending.log");

        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(logFile, true))){
            dataOutput.println();
            dataOutput.printf(dtf.format(now) + " GIVE CHANGE: $%.2f $%.2f", input, total);
        } catch (FileNotFoundException e) {
            System.err.print(e);
        }
    }
}
