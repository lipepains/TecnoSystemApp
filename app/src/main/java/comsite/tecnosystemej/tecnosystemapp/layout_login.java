package comsite.tecnosystemej.tecnosystemapp;


import android.app.AlertDialog;
import android.content.Intent;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageButton;

import java.sql.SQLException;


public class layout_login extends Activity /*vai aparecer o menu*/ {

    EditText edtUsuario, edtSenha;
    private ImageButton Img_btnEntrar;
    DataBase db = new DataBase(this);
    private DataBase database;
    private SQLiteDatabase conexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        Img_btnEntrar = (ImageButton) findViewById(R.id.Img_btnEntrar);

        try {
            database = new DataBase(this);
            conexao = database.getWritableDatabase();
            displayMessage("Conectou");

        } catch (android.database.SQLException ex) {
            showMessage("Nao conectou  ","" + ex);
        }

        Img_btnEntrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor c = conexao.rawQuery("SELECT * FROM membro WHERE usuario_membro ='" + edtUsuario.getText() + "' and senha_membro ='" + edtSenha.getText() + "'", null);

                if (edtUsuario == null || edtUsuario.getText().toString().trim().length()==0) {
                    displayMessage("Login obg");
                    return;
                }
                if (edtSenha == null || edtSenha.getText().toString().trim().length()==0) {
                    displayMessage("Senha obg");
                    return;
                } else if (edtUsuario.getText().toString().equals("a") && edtSenha.getText().toString().equals("a")) {
                    Toast.makeText(getApplicationContext(), "Redirecionando...", Toast.LENGTH_SHORT).show();
                    admin();
                }else if (c.moveToFirst()) {
                    displayMessage("Bem vindo " + c.getString(1));
                    membro();
                }else {
                    displayMessage("Acesso restrito a membros");
                    normal();
                }
            }
        });
    }


            public void admin() {
                Intent it = new Intent(this, gerenciador_inicial.class);
                startActivity(it);
            }

            public void membro() {
                Intent it = new Intent(this, tecno_system.class);
                startActivity(it);
            }

            public void normal() {
                Intent it = new Intent(this, layout_visitante.class);
                startActivity(it);
            }

            public void onBackPressed() {
                Intent it = new Intent(this, layout_inicio.class);
                startActivity(it);
            }

            public void displayMessage(String msg) {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
        }

   // displayMessage("Your Passwords doesn't match");
