package by.spurky;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.Callable;

public class ImageDownloader implements Callable {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11";
    private static long downloadsCounter = 0;
    private static long failedCounter = 0;

    private static final String output = "/home/FilesTMP/";

    private String link;


    public void loadFromLink(String link) {
        this.link = link;

    }

    @Override
    public Object call() throws Exception {
        try {
            URL website = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) website.openConnection();
            connection.setRequestProperty("User-Agent", USER_AGENT);

            ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());

            FileOutputStream fos = new FileOutputStream(output + website.hashCode() + ".png");

            long result = fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



}
