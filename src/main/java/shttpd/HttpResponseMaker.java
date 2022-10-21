package shttpd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpResponseMaker {
    private static final String httpVersion="HTTP/1.1";
    private static String startLine;
    private String header;
    private String body;


    public String assembleMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startLine);
        stringBuilder.append(this.header);
        stringBuilder.append(this.body);
        String requestMessage=stringBuilder.toString();
        return requestMessage;
    }

    public static void makeStartLine(boolean serverStatus) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpVersion);
        if (serverStatus) {
            stringBuilder.append(" 200 OK\n");
            startLine= stringBuilder.toString();
        }else{
            stringBuilder.append(" 404 NOT FOUND\n");
            startLine= stringBuilder.toString();
        }
    }

    public void makeHeader(String path, String bodyContents, boolean checkFileType) {
        StringBuilder stringBuilder = new StringBuilder();
        if (checkFileType) { //파일이면
            try {
                Path filePath = Paths.get("." + path);
                String contentType = Files.probeContentType(filePath);
                stringBuilder.append("Content-Type: "+contentType+"\n");
                long size = Files.size(filePath);
                stringBuilder.append("Content-Length: " + (int)size+"\n\n");
                this.header=stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{  //디렉터리이면
            String contentType = "text/html;";
            stringBuilder.append("Content-Type: "+contentType+"\n");
            int contentLength=bodyContents.length();
            stringBuilder.append("Content-Length: " + contentLength+"\n\n");
            this.header= stringBuilder.toString();
        }

    }

    public void makeBody(String bodyContents) {
        this.body = bodyContents;
    }
}