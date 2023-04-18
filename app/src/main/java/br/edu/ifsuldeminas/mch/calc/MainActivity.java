package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ifsuldeminas.mch.calc";
    private Button buttonIgual;
    private TextView textViewResultado;
    private TextView textViewUltimaExpressao;
    private Double resultado = 0.0;

    private Button botao1, botao2, botao3, botao4, botao5, botao6, botao7, botao8, botao9, botao0, botaoVirgula, botaoSoma, botaoSubtracao,
            botaoPorcento, botaoMultiplicacao, botaoDivisao, botaoReset, botaoDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResultado = findViewById(R.id.textViewResultadoID);
        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);

       buttonIgual = findViewById(R.id.buttonIgualID);
        botao1 = findViewById(R.id.buttonUmID);
        botao2 = findViewById(R.id.buttonDoisID);
        botao3 = findViewById(R.id.buttonTresID);
        botao4 = findViewById(R.id.buttonQuatroID);
        botao5 = findViewById(R.id.buttonCincoID);
        botao6 = findViewById(R.id.buttonSeisID);
        botao7 = findViewById(R.id.buttonSeteID);
        botao8 = findViewById(R.id.buttonOitoID);
        botao9 = findViewById(R.id.buttonNoveID);
        botao0 = findViewById(R.id.buttonZeroID);
        botaoVirgula = findViewById(R.id.buttonVirgulaID);
        botaoSoma = findViewById(R.id.buttonSomaID);
        botaoSubtracao = findViewById(R.id.buttonSubtracaoID);
        botaoPorcento = findViewById(R.id.buttonPorcentoID);
        botaoMultiplicacao = findViewById(R.id.buttonMultiplicacaoID);
        botaoDivisao = findViewById(R.id.buttonDivisaoID);
        botaoReset = findViewById(R.id.buttonResetID);
        botaoDelete = findViewById(R.id.buttonDeleteID);

        RefID(botao1,R.id.buttonUmID);
        RefID(botao2, R.id.buttonDoisID);
        RefID(botao3, R.id.buttonTresID);
        RefID(botao4, R.id.buttonQuatroID);
        RefID(botao5, R.id.buttonCincoID);
        RefID(botao6, R.id.buttonSeisID);
        RefID(botao7, R.id.buttonSeteID);
        RefID(botao8, R.id.buttonOitoID);
        RefID(botao9, R.id.buttonNoveID);
        RefID(botao0, R.id.buttonZeroID);
        RefID(botaoVirgula, R.id.buttonVirgulaID);
        RefID(botaoSoma, R.id.buttonSomaID);
        RefID(botaoSubtracao, R.id.buttonSubtracaoID);
        RefID(botaoPorcento, R.id.buttonPorcentoID);
        RefID(botaoMultiplicacao, R.id.buttonMultiplicacaoID);
        RefID(botaoDivisao, R.id.buttonDivisaoID);
        RefID(botaoReset, R.id.buttonResetID);
        RefID(botaoDelete, R.id.buttonDeleteID);
        RefID(buttonIgual, R.id.buttonIgualID);


        botaoReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewResultado.setText("");
                textViewUltimaExpressao.setText("");
            }
        });

        botaoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView expressao = findViewById(R.id.textViewUltimaExpressaoID);
                String string = expressao.getText().toString();

                if(!string.isEmpty()){
                    byte var0 = 0;
                    int var1 = string.length()-1;
                    String txtExpressao = string.substring(var0,var1);
                    expressao.setText(txtExpressao);
                }
                textViewResultado.setText("");
            }
        });

    }
    void RefID(Button button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);
    }

    public void AcrescentarExpressao(String string, boolean limpar){
        if(!textViewResultado.getText().equals("")){
            textViewUltimaExpressao.setText("");
        }
        if(limpar){
            textViewResultado.setText("");
            textViewUltimaExpressao.append(string);
        }
        else{
            textViewUltimaExpressao.append(textViewResultado.getText());
            textViewUltimaExpressao.append(string);
            textViewResultado.setText("");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.buttonUmID:
                AcrescentarExpressao("1", true);
                break;

            case  R.id.buttonDoisID:
                AcrescentarExpressao("2", true);
                break;

            case  R.id.buttonTresID:
                AcrescentarExpressao("3", true);
                break;

            case  R.id.buttonQuatroID:
                AcrescentarExpressao("4", true);
                break;

            case  R.id.buttonCincoID:
                AcrescentarExpressao("5", true);
                break;

            case  R.id.buttonSeisID:
                AcrescentarExpressao("6", true);
                break;

            case  R.id.buttonSeteID:
                AcrescentarExpressao("7", true);
                break;

            case  R.id.buttonOitoID:
                AcrescentarExpressao("8", true);
                break;

            case  R.id.buttonNoveID:
                AcrescentarExpressao("9", true);
                break;

            case  R.id.buttonZeroID:
                AcrescentarExpressao("0", true);
                break;

            case  R.id.buttonVirgulaID:
                AcrescentarExpressao(".", true);
                break;

            case R.id.buttonSomaID:
                AcrescentarExpressao("+", false);
                break;

            case R.id.buttonSubtracaoID:
                AcrescentarExpressao("-", false);
                break;

            case R.id.buttonMultiplicacaoID:
                AcrescentarExpressao("*", false);
                break;

            case R.id.buttonDivisaoID:
                AcrescentarExpressao("/", false);
                break;

        }

        if (view.getId() == R.id.buttonIgualID) {
            String calculo = textViewUltimaExpressao.getText().toString();
            try {
                Calculable calc = new ExpressionBuilder(calculo).build();

                double resultado = calc.calculate();
                String resultadoString = Double.toString(resultado);
                if(resultadoString.endsWith(".0")){
                    resultadoString = resultadoString.replace(".0", "");
                }
                textViewResultado.setText(resultadoString);
            } catch (Exception e) {
                Log.e(TAG, "Erro" + calculo, e);
                textViewResultado.setText("Erro");
            }
        }
    }
}