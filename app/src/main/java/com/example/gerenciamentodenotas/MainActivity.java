package com.example.gerenciamentodenotas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText idade;
    private EditText materia;
    private EditText notaUm;
    private EditText notaDois;
    private Button btnCalcular;
    private Button btnLimpar;
    private TextView resultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        idade = findViewById(R.id.idade);
        materia = findViewById(R.id.materia);
        notaUm = findViewById(R.id.notaUm);
        notaDois = findViewById(R.id.notaDois);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        resultado = findViewById(R.id.resultado);


        btnCalcular.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                exibirDados();
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    public boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public double calcularMedia(double notaUm, double notaDois){
        return (notaUm + notaDois) / 2;
    }

    public String validarNota(double media){
        if(media >= 60){
            return "Aprovado";
        }else{
            return "Reprovado";
        }
    }

    public void exibirDados(){

        if(nome.getText().toString().isEmpty()){
            resultado.setText("Por favor, informe o seu nome.");
            return;
        }

        if(email.getText().toString().isEmpty()){
            resultado.setText("Por favor, informe o seu e-mail.");
            return;
        }

        if(idade.getText().toString().isEmpty()){
            resultado.setText("Informe sua idade.");
            return;
        }

        if(materia.getText().toString().isEmpty()){
            resultado.setText("Preencha o campo da disciplina.");
            return;
        }

        if(notaUm.getText().toString().isEmpty()){
            resultado.setText("Insira a nota do 1° bimestre.");
            return;
        }

        if(notaDois.getText().toString().isEmpty()){
            resultado.setText("Insira a nota 2° bimestre.");
            return;
        }

        if(!isValidEmail(email.getText().toString())){
            resultado.setText("Por favor, informe um e-mail válido.");
            return;
        }

        try {
            double input2 = Double.parseDouble(notaUm.getText().toString());
            double input1 = Double.parseDouble(notaDois.getText().toString());
            double media = calcularMedia(input1, input2);

            String mensagem =
                    "Nome: " + nome.getText().toString() +
                            "\nE-mail: " + email.getText().toString() +
                            "\nIdade: " + idade.getText().toString() +
                            "\nMatéria: " + materia.getText().toString() +
                            "\nNota 1° Bimestre: " + notaUm.getText().toString() +
                            "\nNota 2° Bimestre: " + notaDois.getText().toString() +
                            "\nMédia final: " + media +
                            "\nResultado: " + validarNota(media);

            resultado.setText(mensagem);
        } catch (NumberFormatException e){
            resultado.setText("Informe notas válidas");
        }
    }
}