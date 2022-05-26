package com.example.practicaltest02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.colocviu2.R;

public class PracticalTest02MainActivity extends AppCompatActivity {


    Button startService;
    private ServerThread serverThread;
    Button clientStartRequest;
    Button addButton;
    Button mulButton;
//    Button clientStartRequest;

    TextView responseTextView;
    TextView clientPortTextView;
    TextView clientAddressTextVIew;
    TextView serverPortTextView;
    TextView ClientRequestOp1TextView;
    TextView ClientRequestOp2TextView;

    Integer operationType = 0; // 1 pentru adunare 2 pentru inmultire
//    String request = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        responseTextView = findViewById(R.id.resultEditText);
        startService = findViewById(R.id.StarServerButton);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serverPortTextView = findViewById(R.id.ServerPortEditText);
                String serverPort = serverPortTextView.getText().toString();
//                serverThread = new ServerThread(4036);
                serverThread = new ServerThread(Integer.parseInt(serverPort));
                serverThread.start();

            }
        });

        clientStartRequest = findViewById(R.id.SendRequestButton);

        clientAddressTextVIew = findViewById(R.id.ClientConnectAddressEditText);
        clientPortTextView = findViewById(R.id.ClientConnectPortEditText);
        ClientRequestOp1TextView = findViewById(R.id.ClientRequestOp1EditText);
        ClientRequestOp2TextView = findViewById(R.id.ClientRequestOp2EditText);

        clientStartRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String request = "";

                String clientAddress = clientAddressTextVIew.getText().toString();
                String clientPort = clientPortTextView.getText().toString();
                String op1 = ClientRequestOp1TextView.getText().toString();
                String op2 = ClientRequestOp2TextView.getText().toString();

                if (operationType == 1) {
                    request += "add," + op1 + "," + op2;
                }
                if (operationType == 2) {
                    request += "mul," + op1 + "," + op2;
                }

//                ClientThread clientThread = new ClientThread(4036, "127.0.0.1", "Bucharest", responseTextView);
//                ClientThread clientThread = new ClientThread(Integer.parseInt(clientPort), clientAddress, "Bucharest", responseTextView);
                ClientThread clientThread = new ClientThread(Integer.parseInt(clientPort), clientAddress, responseTextView, request);
                clientThread.start();
            }
        });

        addButton = findViewById(R.id.AddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                request = "";
                operationType = 1;
            }
        });
        mulButton = findViewById(R.id.MulButton);
        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                request = "";
                operationType = 2;
            }
        });
    }
}