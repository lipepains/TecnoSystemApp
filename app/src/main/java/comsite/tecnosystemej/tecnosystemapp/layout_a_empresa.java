package comsite.tecnosystemej.tecnosystemapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class layout_a_empresa extends ActionBarActivity /*vai aparecer o menu*/ {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a_empresa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tsap, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(comsite.tecnosystemej.tecnosystemapp.layout_inicio.log == 1) {
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.login) {
                Intent it = new Intent(this, layout_inicio.class);
                startActivity(it);
            }
            if (id == R.id.tecnosystem)  {
                Intent it = new Intent(this, tecno_system.class);
                startActivity(it);
            }
            if (id == R.id.agenda) {
                Intent it = new Intent(this, layout_agenda.class);
                startActivity(it);
            }
            if (id == R.id.membros) {
                Intent it = new Intent(this, layout_membros.class);
                startActivity(it);
            }
            if (id == R.id.projetos) {
                Intent it = new Intent(this, layout_projetos.class);
                startActivity(it);
            }
            if (id == R.id.empresa) {
                Intent it = new Intent(this, layout_a_empresa.class);
                startActivity(it);
            }
            if (id == R.id.sair) {
                System.exit(0);
            }
        }
        else
        {
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.login) {
                Intent it = new Intent(this, layout_inicio.class);
                startActivity(it);
            }
            if (id == R.id.visitante) {
                Intent it = new Intent(this, layout_visitante.class);
                startActivity(it);
            }
            if (id == R.id.agenda) {
                Intent it = new Intent(this, layout_agenda_visitante.class);
                startActivity(it);
            }
            if (id == R.id.membros) {
                Intent it = new Intent(this, layout_membros.class);
                startActivity(it);
            }
            if (id == R.id.projetos) {
                Intent it = new Intent(this, layout_projetos_c.class);
                startActivity(it);
            }
            if (id == R.id.empresa) {
                Intent it = new Intent(this, layout_a_empresa.class);
                startActivity(it);
            }
            if (id == R.id.sair) {
                System.exit(0);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    //AQUI COMECA OS LINKS
    public void btnHome(View View){
        String url = "http://tecnosystemej.com/site/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void btnFacebook(View View){
        String url = "https://www.facebook.com/tecnosystemej?fref=ts";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void btnInstagran(View View){
        String url = "https://instagram.com/tecnosystemej/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void btnTwitter(View View){
        String url = "https://twitter.com/tecno_system?lang=pt";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    public void onBackPressed() {
        Intent it = new Intent(this, tecno_system.class);
        startActivity(it);
    }
}
