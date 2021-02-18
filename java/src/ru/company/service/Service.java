package ru.company.service;

import ru.company.logger.ExceptionLogger;
import ru.company.repository.MainRepository;
import ru.company.repository.SimpleRepository;
import ru.company.util.Extractor;

import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.util.UUID;

public class Service implements IService {
    private final MainRepository simpleRepository;
    private BufferedReader in;
    private final FileWriter writer;
    private String fileName;
    private final Extractor extractor;
    private int tries = 3;

    public Service() {
        fileName = new StringBuilder().append(System.getProperty("user.home")).append(File.separator).append("Documents").append(File.separator).append(UUID.randomUUID()).append("HtmlPage.html").toString();
        simpleRepository = new SimpleRepository();
        try {
            extractor = new Extractor();
            writer = new FileWriter(fileName, true);


        } catch (IOException e) {
            ExceptionLogger.getLogger().logException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void execute(String url) {
        try {
            saveUrl(url);

        } catch (IOException e) {
            if (tries > 0) {
                try {
                    fileName = new StringBuilder().append(System.getProperty("user.home")).append(File.separator).append("Documents").append(File.separator).append(UUID.randomUUID()).append("HtmlPage.html").toString();
                    saveUrl(url);
                    tries--;
                } catch (IOException ioException) {
                    //ignore
                }
            } else {
                ExceptionLogger.getLogger().logException(e);

            }
        }

    }

    @Override
    public ResultSet getResult() {
        return simpleRepository.getAllRecords();
    }


    public void saveUrl(final String urlString)
            throws IOException {
        try {
            StringBuilder line = new StringBuilder();
            in = new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL(urlString).openStream())));


            char[] data = new char[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {

                for (int i = 0; i < 1024; i++) {
                    line.append(data[i]);
                }
                simpleRepository.save((extractor.extract(line.toString())));
                writer.write(data);
                writer.flush();

            }
        } finally {
            if (in != null) {
                in.close();
            }

        }
    }

}
