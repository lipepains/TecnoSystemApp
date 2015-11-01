package comsite.tecnosystemej.tecnosystemapp;

import android.app.AlertDialog;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScriptSQL {

    //Metodo que retorna o script
    public static String getCreateMembro() {
        try {
            //Concatenacao do script "Juncao dos caracteres"
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("CREATE TABLE IF NOT EXISTS membro ( ");
            sqlBuilder.append("id_membro integer NOT NULL PRIMARY KEY AUTOINCREMENT, ");
            sqlBuilder.append("nome_membro varchar(45), ");
            sqlBuilder.append("usuario_membro varchar(45), ");
            sqlBuilder.append("senha_membro integer, ");
            sqlBuilder.append("email_membro varchar(45), ");
            sqlBuilder.append("data_ingresso_membro date, ");
            sqlBuilder.append("cargo_membro varchar(45), ");
            sqlBuilder.append("especialidade_membro varchar(45), ");
            sqlBuilder.append("descricao_membro varchar(45), ");
            sqlBuilder.append("projetos_realizados varchar(45) ");
            sqlBuilder.append("); ");

            return sqlBuilder.toString();
        } catch (SQLException e) {
            return String.valueOf(e);
        }
    }

    public static String getCreateProjetos() {
        try {
            //Concatenacao do script "Juncao dos caracteres"
            StringBuilder sqlBuilder1 = new StringBuilder();
            sqlBuilder1.append("CREATE TABLE IF NOT EXISTS projetos ( ");
            sqlBuilder1.append("id_projeto integer NOT NULL PRIMARY KEY AUTOINCREMENT, ");
            sqlBuilder1.append("nome_projeto varchar(45), ");
            sqlBuilder1.append("descricao_projeto varchar(45) ");
            sqlBuilder1.append("tipo_projeto integer, ");
            sqlBuilder1.append("); ");
            return sqlBuilder1.toString();
        } catch (SQLException e) {
            return String.valueOf(e);
        }
    }

    public static String getCreateEventos() {
        try {
            //Concatenacao do script "Juncao dos caracteres"
            StringBuilder sqlBuilder2 = new StringBuilder();
            sqlBuilder2.append("CREATE TABLE IF NOT EXISTS evento ( ");
            sqlBuilder2.append("id_evento integer NOT NULL PRIMARY KEY AUTOINCREMENT, ");
            sqlBuilder2.append("nome_evento varchar(45), ");
            sqlBuilder2.append("descricao_evento integer, ");
            sqlBuilder2.append("data_evento date, ");
            sqlBuilder2.append("local_evento varchar(100) ");
            sqlBuilder2.append("); ");
            return sqlBuilder2.toString();
        } catch (SQLException e) {
            return String.valueOf(e);
        }
    }


}