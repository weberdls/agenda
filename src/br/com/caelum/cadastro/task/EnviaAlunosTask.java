package br.com.caelum.cadastro.task;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.util.AlunoConverter;
import br.com.caelum.cadastro.util.WebClient;

public class EnviaAlunosTask extends AsyncTask<Integer, Double, String>{
	private Activity context;
	private ProgressDialog progress;

	public EnviaAlunosTask(Activity context){
		this.context = context;
	}
	
	@Override
	protected String doInBackground(Integer... arg0) {
		String urlServidor = "http://caelum.com.br/mobile";
		AlunoDAO dao = new AlunoDAO(context);
		List<Aluno> alunos = dao.getLista();
		dao.close();
		String dadosJson = new AlunoConverter().toJSON(alunos);
		WebClient client = new WebClient(urlServidor);
		String respostaJson = client.post(dadosJson);
		return respostaJson;
	}
	
	@Override
	protected void onPostExecute(String respostaJson) {
		progress.dismiss();
		Toast.makeText(context, respostaJson, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(context, "Aguarde...", "Enviando Dados", true, true);
	}
	
}
