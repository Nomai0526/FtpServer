package Worker;

import Worker.SubSystem.Authority;
import Worker.SubSystem.FileSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Parser extends Thread {
    private Authority authority;
    private FileSystem fileSystem;

    private ServerSocket dataSocket;
    private Socket dataConnection;
    private PrintWriter dataOutWriter;
    //24234
    //24234

    private Socket controlSocket;
    private PrintWriter controlOutWriter;
    private BufferedReader controlIn;

    private int dataPort;

    private boolean quitCommandLoop = false;

    public Parser(Socket client, int dataPort) {
        super();
        this.controlSocket = client;
        this.dataPort = dataPort;
        this.authority = new Authority();
        this.fileSystem = new FileSystem();
    }

    public void run() {

        try {
            // Input from client
            controlIn = new BufferedReader(new InputStreamReader(controlSocket.getInputStream()));

            // Output to client, automatically flushed after each print
            controlOutWriter = new PrintWriter(controlSocket.getOutputStream(), true);

            // Greeting
            sendMsgToClient("220 Welcome to the COMP4621 FTP-Server");

            // Get new command from client
            while (!quitCommandLoop) {
                executeCommand(controlIn.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up
            try {
                controlIn.close();
                controlOutWriter.close();
                controlSocket.close();
                debugOutput("Sockets closed and worker stopped");
            } catch (IOException e) {
                e.printStackTrace();
                debugOutput("Could not close sockets");
            }
        }

    }

    //向客户端打印信息的方法
    private void sendMsgToClient(String msg) {
        controlOutWriter.println(msg);
    }

    //处理指令的方法
    private void executeCommand(String c) {
        // split command and arguments
        int index = c.indexOf(' ');
        String command = ((index == -1) ? c.toUpperCase() : (c.substring(0, index)).toUpperCase());
        String args = ((index == -1) ? null : c.substring(index + 1, c.length()));


        debugOutput("Command: " + command + " Args: " + args);

        // dispatcher mechanism for different commands
        switch (command) {
            case "USER":
//                handleUser(args);
                break;

            case "PASS":
//                handlePass(args);
                break;

            case "CWD":
//                handleCwd(args);
                break;

            case "LIST":
//                handleNlst(args);
                break;

            case "NLST":
//                handleNlst(args);
                break;

            case "PWD":
//                handlePwd();
                break;

            case "QUIT":
//                handleQuit();
                break;

            case "PASV":
//                handlePasv();
                break;

            case "EPSV":
//                handleEpsv();
                break;

            case "SYST":
//                handleSyst();
                break;

            case "FEAT":
//                handleFeat();
                break;

            case "PORT":
//                handlePort(args);
                break;

            case "EPRT":
//                handlePort(parseExtendedArguments(args));
                break;

            case "RETR":
//                handleRetr(args);
                break;

            case "MKD":
//                handleMkd(args);
                break;

            case "RMD":
//                handleRmd(args);
                break;

            case "TYPE":
//                handleType(args);
                break;

            case "STOR":
//                handleStor(args);
                break;

            default:
                sendMsgToClient("501 Unknown command");
                break;

        }

    }

    private void debugOutput(String msg) {
//        调试的方法
//            System.out.println("Thread " + this.getId() + ": " + msg);
    }
}
