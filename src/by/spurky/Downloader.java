package by.spurky;

import com.sun.net.ssl.HttpsURLConnection;
import sun.net.www.protocol.https.HttpsURLConnectionImpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader {
    private String output = "/home/sparky/Изображения/lightshot/";

    public void loadFromLink(String link) {
        try {
            URL website = new URL(link);
            HttpsURLConnectionImpl connection = (HttpsURLConnectionImpl) website.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

            ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());

            FileOutputStream fos = new FileOutputStream(output + website.hashCode() + ".png");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
