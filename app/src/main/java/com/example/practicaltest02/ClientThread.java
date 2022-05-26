package com.example.practicaltest02;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {


    int port;
    String address;
    String request_data;
    TextView responseTextView;
    String request;

    ClientThread(int port, String address, TextView responseTextView, String request) {
        this.port = port;
        this.address = address;
        this.responseTextView = responseTextView;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(address, port);
            //* Add logs and checks */

            BufferedReader bufferedReader = Utils.getReader(socket);
            PrintWriter printWriter = Utils.getWriter(socket);

            /* data;data2*/
            printWriter.println(request);
            Log.e("in client", request);

            String response = bufferedReader.readLine();
            Log.d("MyApp", response);
            Log.e("raspuns", "res is " + response);


            responseTextView.post(new Runnable() {
                @Override
                public void run() {
                    responseTextView.setText(response);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
