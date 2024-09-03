package com.example.pizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderSummaryActivity extends AppCompatActivity {

    private TextView textViewResumoPedido, textViewPrecoTotal;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        // Inicializa os TextViews
        textViewResumoPedido = findViewById(R.id.textViewResumoPedido);
        textViewPrecoTotal = findViewById(R.id.textViewPrecoTotal);

        // Obtém os dados passados pela MainActivity
        Intent intent = getIntent();
        String resumoPedido = intent.getStringExtra("resumoPedido");
        double precoTotal = intent.getDoubleExtra("precoTotal", 0);

        // Define os textos no layout
        textViewResumoPedido.setText(resumoPedido);
        textViewPrecoTotal.setText("Preço Total: R$ " + String.format("%.2f", precoTotal));

        // Botão para voltar à tela principal
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> {
            Intent mainIntent = new Intent(OrderSummaryActivity.this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);
            finish();
        });
    }
}
