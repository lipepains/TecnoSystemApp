package comsite.tecnosystemej.tecnosystemapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import java.sql.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
public class DataBase extends SQLiteOpenHelper  {

    private DataBase database;
    private SQLiteDatabase conexao;


    //Construtor para a classe pai
    public DataBase (Context context){
        super(context, "TecnoSystem", null, 1);
    }

    // Criação das tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateMembro());
        db.execSQL(ScriptSQL.getCreateProjetos());
        db.execSQL(ScriptSQL.getCreateEventos());
    }

    //Alterações nas tabelas
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public DataBase open() throws SQLException {
        conexao = database.getWritableDatabase();
        return this;
    }

    public void close() {

        database.close();
    }
}
