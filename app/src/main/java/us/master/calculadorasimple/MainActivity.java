package us.master.calculadorasimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonBorrar;
    private TextView operacion, resultado, textViewAciertos, textViewFallos;

    private int resultadoOperacion = 0;
    private int numAciertos        = 0;
    private int numFallos          = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recuperamos controles gráficos
        buttonBorrar     = findViewById(R.id.buttonBorrar);
        operacion        = findViewById(R.id.textViewOperacion);
        resultado        = findViewById(R.id.textViewResultado);

        textViewAciertos = findViewById(R.id.textViewAciertos);
        textViewAciertos.setText("Aciertos: " + Integer.toString(numAciertos));
        textViewFallos   = findViewById(R.id.textViewFallos);
        textViewFallos.setText("Fallos: " + Integer.toString(numFallos));

        final Integer resultado = generarOperacion();
        Log.d("CalculadoraSimple", "Operacion Resultado: " +  Integer.toString(resultadoOperacion));

        buttonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "boton borrar", Toast.LENGTH_SHORT).show();

                eliminarDigito();
            }
        });

    }

    public void eliminarDigito(){
        String menosUno = eliminarUltimoElemento(resultado.getText().toString());
        //Log.d("CalculadoraSimple", menosUno);
        resultado.setText(menosUno);;
    }

    public String eliminarUltimoElemento(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public void digitoPulsado(View view) {
        //Incluirle al texto de resultado el texto del botón.
        resultado.setText(resultado.getText().toString()+((Button)view).getText().toString());

        boolean longi = comprobarLongitudDigitos();
        if (longi) {
            comprobarResultado();
        }
    }

    private boolean comprobarLongitudDigitos() {
        boolean res = false;
        int longResultOperacion = Integer.toString(resultadoOperacion).length();
        int longResultCalc      = resultado.getText().toString().length();

        if (longResultCalc == longResultOperacion){
            res = true;
        }
        return res;
    }

    private void comprobarResultado() {

        int resTmp = Integer.parseInt(resultado.getText().toString());
        if (resTmp == resultadoOperacion) {
            Toast.makeText(MainActivity.this, "Correcto!!!", Toast.LENGTH_SHORT).show();
            numAciertos = numAciertos + 1;
            generarOperacion();
            resultado.setText("");
        } else {
            Toast.makeText(MainActivity.this, "Upss, inténtalo otra vez!!", Toast.LENGTH_SHORT).show();
            resultado.setText("");
            numFallos = numFallos + 1;
        }

        textViewAciertos.setText("Aciertos: " + Integer.toString(numAciertos));
        textViewFallos.setText("Fallos: " + Integer.toString(numFallos));

    }

    private int generarOperacion(){
        int sumando1,sumando2,sumaTotal;
        Random r=new Random();
        sumando1=r.nextInt(9);
        sumando2=r.nextInt(9);
        operacion.setText("" + sumando1 + " + "+sumando2);
        sumaTotal = sumando1 + sumando2;
        resultadoOperacion = sumaTotal;
        return sumaTotal;
    }
}
