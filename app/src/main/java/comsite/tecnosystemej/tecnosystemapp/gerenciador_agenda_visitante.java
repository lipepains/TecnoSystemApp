package comsite.tecnosystemej.tecnosystemapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;



public class gerenciador_agenda_visitante extends ActionBarActivity /*vai aparecer o menu*/ {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciador_agenda_visitante);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id== R.id.G_membros){
            Intent it = new Intent(this, gerenciador_agenda_membro.class);
            startActivity(it);
        }
        if(id== R.id.G_agenda){
            Intent it = new Intent(this, gerenciador_agenda.class);
            startActivity(it);
        }
        if (id == R.id.G_projetos) {
            Intent it = new Intent(this, gerenciador_projetos.class);
            startActivity(it);
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        Intent it = new Intent(this, gerenciador_agenda.class);
        startActivity(it);
    }
}
