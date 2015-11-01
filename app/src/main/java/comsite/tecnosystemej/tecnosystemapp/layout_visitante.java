package comsite.tecnosystemej.tecnosystemapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static comsite.tecnosystemej.tecnosystemapp.R.layout.layout_login;
import static comsite.tecnosystemej.tecnosystemapp.R.layout.tecno_system;


public class layout_visitante extends  ActionBarActivity /*vai aparecer o menu*/ {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_visitante);
    }

    public void btnProjetosVisitante (View View){

        Intent it = new Intent(this,layout_projetos_c.class);
        startActivity(it);
    }

    public void btnAgendaVisitante (View View){

        Intent it = new Intent(this,layout_agenda_visitante.class);
        startActivity(it);
    }
    public void btnAEmpresaVisitante (View View){

        Intent it = new Intent(this,layout_a_empresa.class);
        startActivity(it);
    }
    public void btnMembrosVisitante (View View){

        Intent it = new Intent(this,layout_membros.class);
        startActivity(it);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        if(comsite.tecnosystemej.tecnosystemapp.layout_inicio.log == 1) {
            getMenuInflater().inflate(R.menu.menu_tsap, menu);
        }
        else{
            getMenuInflater().inflate(R.menu.menu_visitante, menu);
        }
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
            if (id == R.id.tecnosystem) {
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
    public void onBackPressed() {
        Intent it = new Intent(this, layout_inicio.class);
        startActivity(it);
    }

    //Aqui comeca o botoes



}
