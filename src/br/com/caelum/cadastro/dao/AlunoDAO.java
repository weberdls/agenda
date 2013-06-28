package br.com.caelum.cadastro.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.caelum.cadastro.modelo.Aluno;
	
public class AlunoDAO extends SQLiteOpenHelper {

	private static final int VERSAO = 1;
	private static final String DATABASE = "CadastroCaelum";
	
	public AlunoDAO(Context context) {
		super(context, DATABASE, null, VERSAO);
	}

	public void salva(Aluno aluno) {
		ContentValues values = toContentValues(aluno);
		getWritableDatabase().insert("Alunos", null, values);
	}
	
	public void resetDatabase(){
		String ddl1 = "DROP TABLE IF EXISTS Alunos";
		getWritableDatabase().execSQL(ddl1);
		String ddl2 = "CREATE TABLE Alunos(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nome TEXT UNIQUE NOT NULL," + "telefone TEXT,"
				+ "endereco TEXT," + "site TEXT," + "localDaFoto TEXT,"
				+ "nota REAL );";
		getWritableDatabase().execSQL(ddl2);
		Log.i("crioutabela", "executou");
	}
	
	private ContentValues toContentValues(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("site", aluno.getSite());
		values.put("endereco", aluno.getEndereco());
		values.put("telefone", aluno.getTelefone());
		values.put("nota", aluno.getNota());
		values.put("localDaFoto", aluno.getLocalDaFoto());
		return values;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("onCreate", "executou");
		String ddl = "CREATE TABLE Alunos(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nome TEXT UNIQUE NOT NULL," + "telefone TEXT,"
				+ "endereco TEXT," + "site TEXT," + "localDaFoto TEXT,"
				+ "nota REAL );";
		db.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String ddl = "DROP TABLE IF EXISTS Alunos";
		db.execSQL(ddl);
		this.onCreate(db);
	}

	public List<Aluno> getLista() {
		String[] colunas = { "id", "nome", "telefone", "endereco", "site",
				"localDaFoto", "nota" };
		Cursor cursor = getWritableDatabase().query("Alunos", colunas, null,
				null, null, null, null);
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		while (cursor.moveToNext()) {
			Aluno aluno = new Aluno();
			aluno.setId(cursor.getLong(0));
			aluno.setNome(cursor.getString(1));
			aluno.setTelefone(cursor.getString(2));
			aluno.setEndereco(cursor.getString(3));
			aluno.setSite(cursor.getString(4));
			aluno.setLocalDaFoto(cursor.getString(5));
			aluno.setNota(cursor.getDouble(6));
			alunos.add(aluno);
		}
		return alunos;
	}

	public void deletar(Aluno aluno) {
		String[] args = {aluno.getId().toString()};
		getWritableDatabase().delete("Alunos", "id=?", args);
	}

	public void alterar(Aluno aluno) {
		ContentValues values = toContentValues(aluno);
		String[] args = {aluno.getId().toString()};
		getWritableDatabase().update("Alunos", values, "id=?", args);
	}

	public boolean isAluno(String telefone) {
		String[] args = {telefone};
		Cursor cursor = getWritableDatabase().rawQuery("SELECT id FROM Alunos WHERE telefone = ?", args);
		return cursor.moveToFirst();
	}

}
