package br.com.fedablio.qcs;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ResultadoActivity extends Activity {

    private String valor = "";
    private EditText etValor;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        etValor = (EditText) findViewById(R.id.etValorActivity);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            valor = bundle.getString("_VALOR_");
            if (valor.substring(0, 4).equals("http")) {
                etValor.setText(valor.toString());
                Linkify.addLinks(etValor, Linkify.WEB_URLS);
                etValor.setLinksClickable(true);
            }else{
                etValor.setText(valor.toString());
            }
        }
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.color.meu_cinza1))));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_resultado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mnPrincipalResultado) {
            Intent intent = new Intent(ResultadoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}