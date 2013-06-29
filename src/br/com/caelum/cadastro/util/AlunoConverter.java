package br.com.caelum.cadastro.util;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoConverter {

	public String toJSON(List<Aluno> alunos) {
		try {
			JSONStringer js = new JSONStringer();
			
			js.object().key("list").array();
			js.object().key("aluno").array();
			for (Aluno aluno : alunos) {
				js.object();
				js.key("nome").value(aluno.getNome());
				js.key("nota").value(aluno.getNota());
				js.endObject();
			}
			js.endArray().endObject();
			js.endArray().endObject();
			return js.toString();
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
}
