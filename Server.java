/**
 *  A very simple server:
 *  It will receive an integer and return the square of the parameter
 *
 */
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class Server {
    static int clientCount = 0;
    public static void main(String a[]) throws IOException {
        int maxPendingConn = 10;
        final int port = 4444;
        ServerSocket servsock = new ServerSocket(port, maxPendingConn);

        System.out.println("The server is running");

        while (true) {
            // wait for the next client connection
            Socket sock;
            sock=servsock.accept();
            clientCount++;
            new ServerThread(sock, clientCount).start();
        }
    }
}

class ServerThread extends Thread {
    protected Socket sock;
    protected int clientNumber;

    public ServerThread(Socket clientSocket, int clientNumber) {
        this.sock = clientSocket;
        this.clientNumber = clientNumber;
    }

    public void run() {
        // Get I/O streams from the socket
        PrintStream out = null;
        try {
            out = new PrintStream( sock.getOutputStream() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader isr  = null;
        try {
            isr = new InputStreamReader( sock.getInputStream() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in  = new BufferedReader( isr );

        boolean hasValue = true;
        out.print("Hello, you are client #"+ this.clientNumber+ "\r\n" );
        out.flush();

        while(hasValue) {
            String request = null;
            try {
                request = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (request.equals("time")) {
                out.print(LocalDateTime.now() + "\r\n" );
                out.flush();
            } else if (request.isEmpty()) {
                try {
                    sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                hasValue = false;
            } else {
                out.print(request.toUpperCase() + "\r\n" );
                out.flush();
            }
        }
    }
}