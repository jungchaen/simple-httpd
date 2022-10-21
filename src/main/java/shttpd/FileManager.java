package shttpd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileManager {
    private File file;
    private boolean checkFile;
    private String filePath;

    public boolean isFile() {
        return checkFile;
    }

    public String getFilePath() {
        return filePath;
    }

    public String makeBody() throws IOException {
        file = new File("." + filePath);

        if (file.isDirectory()) {
           return makeDirectoryBody();
        } else {
            checkFile =true;
            return makeFileBody();
        }
    }

    public String makeDirectoryBody() {
        StringBuilder stringBuilder = new StringBuilder();
        if (file.exists()) {
            if (file.canRead()) {
                String[] dirContents = file.list();
                HttpResponseMaker.makeStartLine(true);
                stringBuilder.append("<h1> Directory List </h1>");
                stringBuilder.append("<ul>\n");
                for (String element : dirContents) {
                    stringBuilder.append("<li><a href=" + element + ">"+element+"</a></li>\n");
                }
                stringBuilder.append("<ul>\n");
                return stringBuilder.toString();
            }else{
                HttpResponseMaker.makeStartLine(false);
                return stringBuilder.toString();
            }

        } else {
            HttpResponseMaker.makeStartLine(false);
            return stringBuilder.toString();
        }
    }

    public String makeFileBody() throws IOException { //파일일 경우 만들어야하는 body를 리턴해주어야함
        StringBuilder stringBuilder = new StringBuilder();
        if (file.exists()) {
            if (file.canRead()) {
                HttpResponseMaker.makeStartLine(true);
                //Path 객체 생성
                Path path = Paths.get("." + filePath);
                String content = Files.readString(path);  //파일 내용 읽기
                stringBuilder.append(content);
                return stringBuilder.toString();
            }else{
                HttpResponseMaker.makeStartLine(false);
                return stringBuilder.toString();
            }
        } else {
            HttpResponseMaker.makeStartLine(false);
            return stringBuilder.toString();
        }
    }

    public void findPath(List<String> list) {
        String startLine = list.get(0);
        String[] splitStartLine = startLine.split(" ");
        this.filePath= splitStartLine[1];
    }
}