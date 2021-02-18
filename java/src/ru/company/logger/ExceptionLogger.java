package ru.company.logger;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.ExecutorService;
//Singleton
public class ExceptionLogger {
    private  static FileOutputStream fileOutputStream;
    private  static  PrintStream stream;
    private static  volatile  ExceptionLogger instance;
    private ExceptionLogger(){
        try {

            fileOutputStream = new FileOutputStream("java/resources/error.log");
            stream = new PrintStream(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        public void logException(Exception e){
            System.setErr(stream);
            e.printStackTrace();
            stream.flush();
        }



    public static ExceptionLogger getLogger() {
        if (instance != null) {
            return instance;
        }
        synchronized (ExceptionLogger.class) {
            if (instance == null) {
                instance = new ExceptionLogger();
            }
            return instance;

        }
    }

}
