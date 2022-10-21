package shttpd;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("포트번호를 입력하세요: ");
        String portNumber = scanner.nextLine();


        Server server = new Server();
        server.makeServerSocket(Integer.parseInt(portNumber));
        HttpResponseMaker httpRequestMessageMaker = new HttpResponseMaker();
        FileManager fileManager=new FileManager();

        server.connectServer();
            try {
                fileManager.findPath(server.read());
            } catch (IOException e) {
                e.printStackTrace();
            }

        String body = "";
        try {
            body = fileManager.makeBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpRequestMessageMaker.makeHeader(fileManager.getFilePath() ,body, fileManager.isFile());

        httpRequestMessageMaker.makeBody(body);
        String responseMessage = httpRequestMessageMaker.assembleMessage();
        server.send(responseMessage);
    }
}
