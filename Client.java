// A very simple example of a client.
// It will receive from the command line an integer as a parameter
// and will send that parameter to the server.
// The server will send back the square of the parameter sent in the first place.

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String arg[]) throws IOException {
        Socket sock;
        BufferedReader dis;
        PrintWriter dat;
        boolean hasValue = true;

        System.out.println("Please give a server IP address");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        //System.out.println("You entered string "+s);
        // Open our connection to localhost, at port 4444
        // if you try this on your system, insert your system name
        // in place of "localhost". For instance:
        // sock = new Socket("eos02.csis.gvsu.edu",4444);
        //sock = new Socket("localhost",4444);
        sock = new Socket(s,4444);

        // Get I/O streams from the socket

        // Declare server response and string that will be sent to server
        String fromServer = "";
        String parameter = "";

        dis = new BufferedReader( new InputStreamReader(sock.getInputStream()) );
        fromServer = dis.readLine();
        System.out.println("Got this from server: " + fromServer);

        while(hasValue) {
            String msg = in.nextLine();

            dis = new BufferedReader( new InputStreamReader(sock.getInputStream()) );
            dat = new PrintWriter( sock.getOutputStream() );
            System.out.println("You entered string "+msg);
            parameter = msg;
            dat.println(parameter);
            dat.flush();

            hasValue = !msg.isEmpty();
            if (hasValue) {
                fromServer = dis.readLine();
                System.out.println("Got this from server: " + fromServer);
            }

        }

        sock.close();
    }
}