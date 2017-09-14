package com.shermansthill.nagla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ssthill on 4/29/2017.
 */

public class UrlConnection {

    public String readUrl(String stringUrl) {

        String data = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(stringUrl);

            // Create an http connection to communication with the URL
            httpURLConnection = (HttpURLConnection) url.openConnection();

            // Connect to the URL
            httpURLConnection.connect();

            // Read data from the URL
            inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer stringBuffer = new StringBuffer();

            String line = "";
            while ((line = bufferedReader.readLine()) != null)
                stringBuffer.append(line);

            data = stringBuffer.toString();

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpURLConnection.disconnect();
        }
        return data;
    }
}
