package dk.cphbusiness.demo02_multiplerequests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient
{

    private static final int PORT = 9090;
    private static final String IP = "127.0.0.1";

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String response;

    public static void main(String[] args)
    {
        EchoClient client = new EchoClient();
        client.startConnection(IP, PORT);
        int msgnr = 1;
        String reply = "";
        boolean msgr = true;
        while(msgr){
            reply = client.getString();
        client.sendMessage(reply);
        System.out.println("Response "+msgnr+": " + client.response);
        msgnr = msgnr+1;
        if (reply.equals("bye")){
            msgr = false;
        }
        }
        client.stopConnection();
    }

    public void startConnection(String ip, int port)
    {
        try
        {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    void sendMessage(String msg)
    {
        try
        {
            out.println(msg);
            response = in.readLine();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    void stopConnection()
    {
        try
        {
            System.out.println("Closing down echo client connection");
            in.close();
            out.close();
            clientSocket.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String getResponse()
    {
        return response;
    }

    public String getString(){
        String res = "";
        Scanner scanner = new Scanner(System.in);

        return scanner.next();
    }
}
