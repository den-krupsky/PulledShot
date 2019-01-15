package by.spurky;

public class Main {


    public static void main(String[] args) {
	    new Main().start();
    }

    private void start() {
        String link;
        for(int i = 0; i < 100; i++) {
            link = LinkGenerator.randomLink();
            System.out.println(link);
            URLDownloader downloader = new URLDownloader(link);
        }
    }



}
