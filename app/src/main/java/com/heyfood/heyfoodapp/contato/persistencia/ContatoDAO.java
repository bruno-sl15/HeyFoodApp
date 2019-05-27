package com.heyfood.heyfoodapp.contato.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.heyfood.heyfoodapp.contato.dominio.Contato;
import com.heyfood.heyfoodapp.infra.persistencia.AbstractDAO;
import com.heyfood.heyfoodapp.infra.persistencia.DBHelper;

public class ContatoDAO extends AbstractDAO{
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public ContatoDAO(Context context){
        this.context = context;
        helper = new DBHelper(context);
    }

    public Contato getContato(int id) {
        Contato result = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_CONTATO+ " WHERE " + DBHelper.CAMPO_ID_CONTATO + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(id)});
        if (cursor.moveToFirst()) {
            result = createContato(cursor);
        }
        super.close(cursor, db);
        return result;
    }

    public int cadastrar(Contato contato) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAMPO_EMAIL, contato.getEmail());
        values.put(DBHelper.CAMPO_TELEFONE, contato.getTelefone());
        values.put(DBHelper.CAMPO_SITE, contato.getSite());

        long retorno = db.insert(DBHelper.TABELA_CONTATO, null, values);
        super.close(db);

        return (int) retorno;
    }

    private Contato createContato(Cursor cursor){
        Contato result = new Contato();
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID_CONTATO);
        result.setId(Integer.parseInt(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_EMAIL);
        result.setEmail(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_TELEFONE);
        result.setTelefone(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_SITE);
        result.setSite(cursor.getString(columnIndex));
        return result;
    }
}
