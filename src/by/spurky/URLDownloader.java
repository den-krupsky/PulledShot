package by.spurky;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLDownloader {
    private String outputPath = "";
    private String source = "<meta name=\"twitter:image:src\" content=\"(.*?)\"/>";
    private Downloader downloader = new Downloader();

    URLDownloader(String link) {
        setConnection(link);
    }

    private void setConnection(String link) {
        try {
            URL url = new URL(link);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");


            InputStreamReader content = new InputStreamReader(connection.getInputStream());
            findImageLink(new BufferedReader(content));

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException. Connection failed");
        }
    }

    private void findImageLink(BufferedReader content){
        StringBuffer textBuffer = new StringBuffer();
        Matcher imgClass;
        Matcher availableLink;
        try {
            textBuffer.append(content.readLine());

            do {
                imgClass = Pattern.compile(source).matcher(textBuffer);
                if(imgClass.find()) {
                    availableLink = Pattern.compile("https://(.*?).png").matcher(imgClass.group(0));
                    if (availableLink.find()) {
                        //System.out.println((availableLink.group(0)));
                        downloader.loadFromLink(availableLink.group(0));
                    }
                    break;
                }

            } while (content.readLine() != null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOutputPath(String path) {
        this.outputPath = path;
    }

    public String getOutputPath() {
        return this.outputPath;
    }
}
