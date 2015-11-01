package comsite.tecnosystemej.tecnosystemapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static comsite.tecnosystemej.tecnosystemapp.R.layout.tecno_system;


public class layout_inicio extends Activity {

    public static int log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_inicio);
    }

    public void btnLogin(View View){

       log =1;
        Intent it = new Intent(this, layout_login.class);
        startActivity(it);

    }

    public void btnVisitante(View View) {
        log = 2 ;
        Intent it = new Intent(this, layout_visitante.class);
        startActivity(it);

    }

    @Override
    public void onBackPressed() {
        System.out.println("Oii");
        System.exit(0);
        finish();

    }





}
