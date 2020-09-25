/**
 *  A very simple server:
 *  It will receive an integer and return the square of the parameter
 *
 */
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class Server {
    public static void main(String a[]) throws IOException {
        int maxPendingConn = 10;
        int port = 4444;
        Socket sock;
        ServerSocket servsock = new ServerSocket(port, maxPendingConn);

        System.out.println("The server is running");

        while (true) {
            // wait for the next client connection
            sock=servsock.accept();

            // Get I/O streams from the socket
            PrintStream out = new PrintStream( sock.getOutputStream() );
            InputStreamReader isr  = new InputStreamReader( sock.getInputStream() );
            BufferedReader in  = new BufferedReader( isr );

            out.print(LocalDateTime.now() + "\r\n" );
            out.flush();
            // Accept the request
            String request = in.readLine();

            System.out.println(request);
            // The request was read as a String.
            // It needs to be converted to an integer

//            int parameter = (new Integer(request)).intValue();

//            int result = parameter*parameter;
//            out.print(result + "\r\n" );
//            out.flush();

            // Close this connection, (not the overall server socket)
            sock.close();
        }
    }
}