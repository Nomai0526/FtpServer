package Server;

import Worker.Parser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int controlPort = 80;
    private ServerSocket welcomeSocket;
    boolean serverRunning = true;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {
            //创建服务器socket
            welcomeSocket = new ServerSocket(controlPort);
        } catch (IOException e) {
            System.out.println("Could not create server socket");
            System.exit(-1);
        }

        System.out.println("FTP Server started listening on port " + controlPort);

        //当前线程数量
        int noOfThreads = 0;

        //监听
        while (serverRunning) {
            try {

                //收到连接后
                Socket client = welcomeSocket.accept();

                // Port for incoming dataConnection (for passive mode) is the controlPort + number of created threads + 1
                //建立数据端口
                int dataPort = controlPort + noOfThreads + 1;

                // Create new worker thread for new connection
                //建立指令解释器
                Parser w = new Parser(client, dataPort);

                System.out.println("New connection received. Worker was created.");
                noOfThreads++;
                w.start();
            } catch (IOException e) {
                System.out.println("Exception encountered on accept");
                e.printStackTrace();
            }

        }
        try {
            welcomeSocket.close();
            System.out.println("Server was stopped");

        } catch (IOException e) {
            System.out.println("Problem stopping server");
            System.exit(-1);
        }

    }
}
