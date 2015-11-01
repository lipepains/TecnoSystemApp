package comsite.tecnosystemej.tecnosystemapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.*;
import android.app.AlertDialog.Builder;
import java.util.Calendar;
import java.util.Date;


public class gerenciador_membro extends ActionBarActivity {

    EditText edtNomeMembro,edtEmailMembro,edtUsuarioMembro,edtSenhaMembro,edtEspecialidadeMembro,edtDescricaoMembro,edtProjetoRealizadoMembro;
    Spinner spnCargo;
    Date edtDataIngressoMembro;
    private DataBase database;
    private SQLiteDatabase conexao;
    private int ano, mes, dia;
    private Button dataChegada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciador_membro);

        try {
            database = new DataBase(this);
            conexao = database.getWritableDatabase();
            showMessage("Conectou", null);

        } catch (SQLException ex) {
            showMessage("Nao conectou  ",""+ ex);
        }

        edtNomeMembro = (EditText)findViewById(R.id.edtNomeMembro);
        edtUsuarioMembro = (EditText)findViewById(R.id.edtUsuarioMembro);
        edtSenhaMembro = (EditText)findViewById(R.id.edtSenhaMembro);
        edtEmailMembro = (EditText)findViewById(R.id.edtEmailMembro);
        edtEspecialidadeMembro = (EditText)findViewById(R.id.edtEspecialidadeMembro);
        edtDescricaoMembro = (EditText)findViewById(R.id.edtDescricaoMembro);
        edtProjetoRealizadoMembro = (EditText)findViewById(R.id.edtProjetoRealizadoMembro);

        //
        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        dataChegada = (Button) findViewById(R.id.dataChegada);
        dataChegada.setText(dia + "/" + (mes+1) + "/" + ano);

        //
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        this, R.array.categoria_cargos,
        android.R.layout.simple_spinner_item);
        spnCargo = (Spinner) findViewById(R.id.spnCargo);
                spnCargo.setAdapter(adapter);
        }

    //
    public void selecionarData(View view){
        showDialog(view.getId());
    }

    //
    protected Dialog onCreateDialog(int id) {
        if(R.id.dataChegada == id){
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }

    //
    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view,
                              int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataChegada.setText(dia + "/" + (mes+1) + "/" + ano);
        }
    };

    // Função responsável pela adição de membros no banco
    public void btnAdicionarMembro (View v){

        if (edtNomeMembro.getText().toString().trim().length()==0 || edtUsuarioMembro.getText().toString().trim().length()==0|| edtSenhaMembro.getText().toString().trim().length()==0)
        {
            showMessage("Erro","Tem campo vazio cabeça");
            return;
        }
        try {
            conexao.execSQL("INSERT INTO membro (nome_membro,usuario_membro,senha_membro,email_membro,especialidade_membro,descricao_membro,projetos_realizados)" +
                    " VALUES ('" + edtNomeMembro.getText() + "','" + edtUsuarioMembro.getText() + "','" + edtSenhaMembro.getText() + "','" + edtEmailMembro.getText() + "','" + edtEspecialidadeMembro.getText() + "','" + edtDescricaoMembro.getText() + "','" + edtProjetoRealizadoMembro.getText() + "');");
            showMessage("Adicionado", "");
        }catch (SQLException e){
            showMessage("NÃO FUNFOU  ",""+ e);
        }

    }

    // Função responsável por deletar um membro no banco
    public void btnDeletar (View v){

        if (edtNomeMembro.getText().toString().trim().length()==0)
        {
            showMessage("Tem campo vazio cabeça",null);
            return;
        }
        Cursor c = conexao.rawQuery("SELECT * FROM membro WHERE nome_membro = '" + edtNomeMembro.getText() + "'", null);
        if (c.moveToFirst()) {
            conexao.execSQL("DELETE FROM membro WHERE nome_membro = '" + edtNomeMembro.getText() + "'");
            showMessage("Excluido", null);
            }else{
                showMessage("NÃO Excluiu  ","");
            }
        }


    //// Função responsável por editar um membro no banco
    public void btnEditarMembro (View v){

        if (edtNomeMembro.getText().toString().trim().length()==0)
        {
            showMessage("Tem campo vazio cabeça",null);
            return;
        }
        Cursor c = conexao.rawQuery("SELECT * FROM membro WHERE nome_membro ='" + edtNomeMembro.getText() + "'", null);
        if (c.moveToFirst()) {
            conexao.execSQL("UPDATE membro SET nome_membro = '" + edtNomeMembro.getText() + "', usuario_membro = '" + edtUsuarioMembro.getText() + "', email_membro = '" + edtEmailMembro.getText() + "'  WHERE nome_membro = '" + edtNomeMembro.getText() + "');");
            showMessage("Editado", null);
        }
    }

    // Função responsável por um membro somente no banco
    public void btnBuscar (View v) {

        if (edtNomeMembro.getText().toString().trim().length() == 0) {
            showMessage("Tem campo vazio cabeça",null);
        }
        Cursor c = conexao.rawQuery("SELECT * FROM membro WHERE nome_membro ='" + edtNomeMembro.getText() + "'", null);
        if (c.moveToFirst()) {
            edtNomeMembro.setText(c.getString(1));
            edtEmailMembro.setText(c.getString(2));
        } else {
            showMessage("Não tem esse nome não", null);
        }
    }

    // Função responsável por procurar todos os membros no banco
    public void btnBuscarTudo (View v) {

        try {
            Cursor c = conexao.rawQuery("SELECT * FROM membro", null);

            if (c.getCount() == 0) {
                showMessage("Não tem ninguém", null);
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("nome: " + c.getString(1) + "\n");
            }
            showMessage("Foi sim", buffer.toString());
        }catch (SQLException ex){
            showMessage("Nao tem tabela inserida", "");
        }
    }

    //Função responsável por mostrar as mensagens na tela do usuários com titulo e subtitulo
    public void showMessage (String title, String message)
    {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    //Funçao responsável por limpar as caixas dos EditTexts
    public void limparCaixas(View v) {

        edtNomeMembro.setText("");
        edtEmailMembro.setText("");
        edtUsuarioMembro.setText("");
        edtSenhaMembro.setText("");
        edtEmailMembro.setText("");
        edtEspecialidadeMembro.setText("");
        edtDescricaoMembro.setText("");
        edtProjetoRealizadoMembro.setText("");

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
