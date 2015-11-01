package comsite.tecnosystemej.tecnosystemapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class gerenciador_projetos extends ActionBarActivity {

    EditText edtNomeProjeto,edtDescricaoProjeto;
    Spinner spnProjetos;
    private DataBase database;
    private SQLiteDatabase conexao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciador_projetos);

        try {
            database = new DataBase(this);
            conexao = database.getWritableDatabase();
            showMessage("Conectou", null);
        } catch (SQLException ex) {
            showMessage("Nao conectou  ",""+ ex);
        }

        edtNomeProjeto = (EditText)findViewById(R.id.edtNomeProjeto);
        edtDescricaoProjeto = (EditText)findViewById(R.id.edtDescricaoProjeto);
        
        spnProjetos = (Spinner)findViewById(R.id.spnProjetos);


        //
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.categoria_projetos,
                android.R.layout.simple_spinner_item);
                spnProjetos = (Spinner) findViewById(R.id.spnProjetos);
                spnProjetos.setAdapter(adapter);
    }

    // Função responsável pela adição de membros no banco
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void btnAdicionarProjeto (View v){

        if (edtNomeProjeto.getText().toString().trim().length()==0 || edtDescricaoProjeto.getText().toString().trim().length()==0)
        {
            showMessage("Erro","Tem campo vazio cabeça");
            return;
        }
        try {
            conexao.execSQL("INSERT INTO projetos (nome_projeto,descricao_projeto,tipo_projeto)" +
                    " VALUES ('" + edtNomeProjeto.getText() + "','" + edtDescricaoProjeto.getText() + "','" + spnProjetos.getDropDownVerticalOffset() + "');");
            showMessage("Adicionado", "");
        }catch (SQLException e){
            showMessage("Não adicionou  ",""+ e);
        }

    }

    // Função responsável por deletar um membro no banco
    public void btnDeletarProjeto (View v){

        if (edtNomeProjeto.getText().toString().trim().length()==0)
        {
            showMessage("Tem campo vazio cabeça",null);
            return;
        }
        Cursor c = conexao.rawQuery("SELECT * FROM projetos WHERE nome_projeto = '" + edtNomeProjeto.getText() + "'", null);
        if (c.moveToFirst()) {
            conexao.execSQL("DELETE FROM projetos WHERE nome_projeto = '" + edtNomeProjeto.getText() + "'");
            showMessage("Excluido", null);
        }else{
            showMessage("NÃO Excluiu  ","");
        }
    }


    //// Função responsável por editar um membro no banco
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void btnEditarProjeto (View v){

        if (edtNomeProjeto.getText().toString().trim().length()==0)
        {
            showMessage("Tem campo vazio cabeça",null);
            return;
        }
        Cursor c = conexao.rawQuery("SELECT * FROM projetos WHERE nome_projeto ='" + edtNomeProjeto.getText() + "'", null);
        if (c.moveToFirst()) {
            conexao.execSQL("UPDATE projetos SET nome_projeto = '" + edtNomeProjeto.getText() + "', tipo_projeto = '" + spnProjetos.getDropDownVerticalOffset() + "' , descricao_projeto = '" + edtDescricaoProjeto.getText() + "' WHERE nome_projeto = '" + edtNomeProjeto.getText() + "');");
            showMessage("Editado", null);
        }
    }

    // Função responsável por um membro somente no banco
    public void btnBuscarProjeto (View v) {

        if (edtNomeProjeto.getText().toString().trim().length() == 0) {
            showMessage("Tem campo vazio cabeça",null);
        }
        Cursor c = conexao.rawQuery("SELECT * FROM projetos WHERE nome_projeto ='" + edtNomeProjeto.getText() + "'", null);
        if (c.moveToFirst()) {
            c.getString(0);
            edtNomeProjeto.setText(c.getString(1));
            edtDescricaoProjeto.setText(c.getString(2));
        } else {
            showMessage("Não tem esse nome não", null);
        }
    }

    // Função responsável por procurar todos os projetos no banco
    public void btnBuscarTudoProjeto (View v) {

        try {
            Cursor c = conexao.rawQuery("SELECT * FROM projetos", null);

            if (c.getCount() == 0) {
                showMessage("Não tem projeto", null);
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                c.getString(0);
                buffer.append("ID : " + c.getString(0) + "Nome : " + c.getString(1) + "\n");
            }
            showMessage("Foi sim", buffer.toString());
        }catch (SQLException ex){
            showMessage("Nao tem tabela inserida", "");
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

    //Funçao responsável por limpar as caixas dos EditTexts
    public void limparCaixasProjeto (View v) {

        edtNomeProjeto.setText("");
        edtDescricaoProjeto.setText("");
        spnProjetos.getEmptyView();

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
    public void onBackPressed() {
        Intent it = new Intent(this, gerenciador_inicial.class);
        startActivity(it);
    }
}

