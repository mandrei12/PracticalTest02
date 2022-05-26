package com.example.practicaltest02;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class CalcThread extends Thread{
    int op1, op2;
    String type;
    int res;
    Socket client_socket;

    public CalcThread(String type, int op1, int op2,  Socket client_socket) {
        this.op1 = op1;
        this.op2 = op2;
        this.type = type;
        this.client_socket = client_socket;
        res = 0;
    }

    @Override
    public void run() {
        if (type.equals("add")) { // adunare
            res = op1 + op2;

        }

        if (type.equals("mul")) { //inmultire
            res = op1 * op2;
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        PrintWriter printWriter = null;
        try {
            printWriter = Utils.getWriter(client_socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.println(res);
        Log.e("Error", "res is" + res);

        try {
            client_socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
