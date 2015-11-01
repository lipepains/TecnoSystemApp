package comsite.tecnosystemej.tecnosystemapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.app.Activity;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class gerenciador_inicial extends ActionBarActivity {

    private DataBase database;
    private SQLiteDatabase conexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciador_inicial);

        try {
            database = new DataBase(this);
            conexao = database.getWritableDatabase();
            showMessage("FUNFOU", null);

        } catch (SQLException ex) {
            showMessage("NÃO FUNFOU  ",""+ ex);
        }
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
            Intent it = new Intent(this, gerenciador_membro.class);
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

    //BOTOES

    public void btnGerenciador_projetos(View View){

        Intent It = new Intent(this, gerenciador_projetos.class);
        startActivity(It);
    }
    public void btnGerenciador_agenda(View View){

        Intent It = new Intent(this, gerenciador_agenda.class);
        startActivity(It);
    }
    public void btnGerenciador_membros(View View){

        Intent It = new Intent(this, gerenciador_membro.class);
        startActivity(It);
    }

    public void onBackPressed() {
        Intent it = new Intent(this, tecno_system.class);
        startActivity(it);
    }
    public void showMessage (String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
