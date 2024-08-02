package br.com.fedablio.qcs;

import android.annotation.SuppressLint; //R.color
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageButton btLer;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btLer = (ImageButton) findViewById(R.id.btLerActivity);
        btLer.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getString(R.color.meu_cinza1))));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Nothing found!", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject obj = new JSONObject(result.getContents());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
                    intent.putExtra("_VALOR_", result.getContents());
                    startActivity(intent);
                    finish();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View view) {
        IntentIntegrator iiScan = new IntentIntegrator(MainActivity.this);
        iiScan.setPrompt("Place the line over the code");
        iiScan.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        iiScan.setBeepEnabled(true);
        iiScan.setCameraId(0);
        iiScan.setBarcodeImageEnabled(false);
        iiScan.setTimeout(30000);
        iiScan.initiateScan();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.mnSobreMain){
            Intent intent = new Intent(MainActivity.this, SobreActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}