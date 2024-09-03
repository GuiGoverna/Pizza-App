package com.example.pizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextQuantidadeMargherita, editTextQuantidadePepperoni, editTextQuantidadeQuatroQueijos, editTextQuantidadeCalabresa;
    private Button btnFazerPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa os campos de quantidade
        editTextQuantidadeMargherita = findViewById(R.id.editTextQuantidadeMargherita);
        editTextQuantidadePepperoni = findViewById(R.id.editTextQuantidadePepperoni);
        editTextQuantidadeQuatroQueijos = findViewById(R.id.editTextQuantidadeQuatroQueijos);
        editTextQuantidadeCalabresa = findViewById(R.id.editTextQuantidadeCalabresa);

        // Botão para fazer o pedido
        btnFazerPedido = findViewById(R.id.btnFazerPedido);
        btnFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica e obtém a quantidade de cada sabor
                int quantidadeMargherita = getQuantidadeFromEditText(editTextQuantidadeMargherita);
                int quantidadePepperoni = getQuantidadeFromEditText(editTextQuantidadePepperoni);
                int quantidadeQuatroQueijos = getQuantidadeFromEditText(editTextQuantidadeQuatroQueijos);
                int quantidadeCalabresa = getQuantidadeFromEditText(editTextQuantidadeCalabresa);

                if (quantidadeMargherita == 0 && quantidadePepperoni == 0 && quantidadeQuatroQueijos == 0 && quantidadeCalabresa == 0) {
                    Toast.makeText(MainActivity.this, "Por favor, selecione pelo menos uma pizza.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calcula o preço total
                double totalPrice = (quantidadeMargherita * 25.00) + (quantidadePepperoni * 30.00) + (quantidadeQuatroQueijos * 28.00) + (quantidadeCalabresa * 27.00);

                // Cria a string de resumo do pedido
                StringBuilder resumoPedido = new StringBuilder();
                if (quantidadeMargherita > 0) {
                    resumoPedido.append("Marguerita: ").append(quantidadeMargherita).append(" x R$25,00\n");
                }
                if (quantidadePepperoni > 0) {
                    resumoPedido.append("Pepperoni: ").append(quantidadePepperoni).append(" x R$30,00\n");
                }
                if (quantidadeQuatroQueijos > 0) {
                    resumoPedido.append("Quatro Queijos: ").append(quantidadeQuatroQueijos).append(" x R$28,00\n");
                }
                if (quantidadeCalabresa > 0) {
                    resumoPedido.append("Calabresa: ").append(quantidadeCalabresa).append(" x R$27,00\n");
                }

                // Intent para iniciar a OrderSummaryActivity e passar os dados
                Intent intent = new Intent(MainActivity.this, OrderSummaryActivity.class);
                intent.putExtra("resumoPedido", resumoPedido.toString());
                intent.putExtra("precoTotal", totalPrice);
                startActivity(intent);
            }
        });
    }

    // Método auxiliar para obter a quantidade de um EditText
    private int getQuantidadeFromEditText(EditText editText) {
        String quantidadeStr = editText.getText().toString();
        if (quantidadeStr.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(quantidadeStr);
        }
    }
}

