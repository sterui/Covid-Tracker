package com.java.covid.service;

import com.java.covid.model.Entry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidService {

    private static String COVID_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

    private List<Entry> allEntries = new ArrayList<>();
    private int total = 0;
    private int totalNew = 0;

    public int getTotalNew() {
        return totalNew;
    }

    public List<Entry> getAllEntries() {
        return allEntries;
    }

    public int getTotal() {
        return total;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData() {

        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL(COVID_DATA_URL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches (false);

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            String res = response.toString();
            parseCSV(res);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }

    public void parseCSV(String toParse) throws IOException {
        List<Entry> newEntries = new ArrayList<>();

        StringReader in = new StringReader(toParse);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            Entry entry = new Entry();
            entry.setState(record.get("Province/State"));
            entry.setCountry(record.get("Country/Region"));
            entry.setLatestCases(Integer.parseInt(record.get(record.size() - 1)));
            entry.setDeltaPreviousDate(Integer.parseInt(record.get(record.size() - 1)) - Integer.parseInt(record.get(record.size() - 2)));
            newEntries.add(entry);
        }
        total = newEntries.stream().mapToInt(count -> count.getLatestCases()).sum();
        totalNew = newEntries.stream().mapToInt(count -> count.getDeltaPreviousDate()).sum();
        allEntries = newEntries;
    }
}
