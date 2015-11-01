package comsite.tecnosystemej.tecnosystemapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class gerenciador_agenda_membro extends ActionBarActivity {

    EditText edtNomeEvento,edtDescricaoEvento,edtLocalEvento;
    int id;
    private DataBase database;
    private SQLiteDatabase conexao;
    private int ano, mes, dia;
    private Button dataEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciador_agenda_membro);

        edtNomeEvento = (EditText)findViewById(R.id.edtNomeEvento);
        edtDescricaoEvento = (EditText)findViewById(R.id.edtDescricaoEvento);
        edtLocalEvento = (EditText)findViewById(R.id.edtLocalEvento);

        try {
            database = new DataBase(this);
            conexao = database.getWritableDatabase();
            displayMessage("Conectou");

        } catch (SQLException ex) {
            showMessage("Nao conectou  ", "" + ex);
        }

        //
        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        dataEvento = (Button) findViewById(R.id.dataEvento);
        dataEvento.setText(dia + "/" + (mes + 1) + "/" + ano);

}

    //
    public void selecionarData(View view){
        showDialog(view.getId());
    }

    //
    protected Dialog onCreateDialog(int id) {
        if(R.id.dataEvento == id){
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }

    //
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view,int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataEvento.setText(dia + "/" + (mes + 1) + "/" + ano);
        }
    };

    // Função responsável pela adição de membros no banco
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void btnAdicionarEvento (View v){

        if (edtNomeEvento.getText().toString().trim().length()==0 || edtDescricaoEvento.getText().toString().trim().length()==0)
        {
            displayMessage("Tem campo vazio cabeça");
            return;
        }
        try {
            conexao.execSQL("INSERT INTO evento (nome_evento,descricao_evento,local_evento)" +
                    " VALUES ('" + edtNomeEvento.getText() + "','" + edtDescricaoEvento.getText() + "','" + edtLocalEvento.getText() + "');");
            displayMessage("Adicionado");
        }catch (SQLException e){
            showMessage("Não adicionou  ", "" + e);
        }

    }

    // Função responsável por deletar um membro no banco
    public void btnDeletarEvento (View v){

        if (edtNomeEvento.getText().toString().trim().length()==0)
        {
            displayMessage("Tem campo vazio cabeça");
            return;
        }
        Cursor c = conexao.rawQuery("SELECT * FROM evento WHERE nome_evento = '" + edtNomeEvento.getText() + "'", null);
        if (c.moveToFirst()) {
            conexao.execSQL("DELETE FROM evento WHERE nome_evento = '" + edtNomeEvento.getText() + "'");
            displayMessage("Excluido");
        }else{
            displayMessage("NÃO Excluiu  ");
        }
    }


    //// Função responsável por editar um membro no banco
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void btnEditarEvento (View v){

        if (edtNomeEvento.getText().toString().trim().length()==0)
        {
            displayMessage("Tem campo vazio cabeça");
            return;
        }
        Cursor c = conexao.rawQuery("SELECT * FROM evento WHERE nome_evento ='" + edtNomeEvento.getText() + "'", null);
        if (c.moveToFirst()) {
            conexao.execSQL("UPDATE evento SET nome_evento = '" + edtNomeEvento.getText() + "', local_evento = '" + edtLocalEvento.getText() + "' , descricao_evento = '" + edtDescricaoEvento.getText() + "' WHERE nome_evento = '" + edtNomeEvento.getText() + "');");
            displayMessage("Editado");
        }
    }

    // Função responsável por um membro somente no banco
    public void btnBuscarEvento (View v) {

        if (edtNomeEvento.getText().toString().trim().length() == 0) {
            displayMessage("Tem campo vazio cabeça");
        }
        Cursor c = conexao.rawQuery("SELECT * FROM evento WHERE nome_evento ='" + edtNomeEvento.getText() + "'", null);
        if (c.moveToFirst()) {
            c.getString(0);
            edtNomeEvento.setText(c.getString(1));
            edtDescricaoEvento.setText(c.getString(2));
            edtLocalEvento.setText(c.getString(3));
        } else {
            displayMessage("Não tem esse nome não");
        }
    }

    // Função responsável por procurar todos os evento no banco
    public void btnBuscarTudoEvento (View v) {

        try {
            Cursor c = conexao.rawQuery("SELECT * FROM evento", null);

            if (c.getCount() == 0) {
                displayMessage("Não tem projeto");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("ID: " + c.getString(0) + "Nome : " + c.getString(1) + "\n");
            }
            showMessage("Foi sim", buffer.toString());
        }catch (SQLException ex){
            displayMessage("Nao tem tabela inserida");
        }
    }

    //Função responsável por mostrar as mensagens na tela do usuários com titulo e subtitulo
    public void showMessage (String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void displayMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //Funçao responsável por limpar as caixas dos EditTexts
    public void limparCaixasEvento (View v) {

        edtNomeEvento.setText("");
        edtDescricaoEvento.setText("");
        edtLocalEvento.setText("");

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
