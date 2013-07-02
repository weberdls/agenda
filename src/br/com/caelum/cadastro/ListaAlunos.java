package br.com.caelum.cadastro;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import br.com.caelum.cadastro.adapter.ListaAlunosAdapter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.task.EnviaAlunosTask;

public class ListaAlunos extends Activity {

	private ListView lista;
	private Aluno aluno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listagem_alunos);
		lista = (ListView) findViewById(R.id.lista);

		registerForContextMenu(lista);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				Aluno alunoClicado = (Aluno) adapter.getItemAtPosition(posicao);
				Intent irParaFormulario = new Intent(ListaAlunos.this, Formulario.class);
				irParaFormulario.putExtra("alunoClicado", alunoClicado);
				startActivity(irParaFormulario);
				
			}
			
		});
		
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {


			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				
				aluno = (Aluno) adapter.getItemAtPosition(posicao);
				
				return false;
			}
		});
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);
		MenuItem ligar = menu.add("Ligar");
		ligar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaLigacao = new Intent(Intent.ACTION_CALL);
				irParaLigacao.setData(Uri.parse("tel:"+aluno.getTelefone()));
				startActivity(irParaLigacao);
				
				return false;
			}
		});
		
		
		
		MenuItem sms = menu.add("Enviar SMS");
		sms.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent enviarSMS = new Intent(Intent.ACTION_VIEW);
				enviarSMS.setData(Uri.parse("sms:" + aluno.getTelefone()));
				enviarSMS.putExtra("sms_body", "Senhor " + aluno.getNome() + "Entrar em contato com a secretaria");
				startActivity(enviarSMS);
				return false;
			}
		});
		
		
		
		MenuItem site = menu.add("Navegar no Site");
		site.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaSite = new Intent(Intent.ACTION_VIEW);
				irParaSite.setData(Uri.parse("http://" + aluno.getSite()));
				startActivity(irParaSite);
				return false;
			}
		});
		
		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				new AlertDialog.Builder(ListaAlunos.this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Deletar")
				.setMessage("Deseja mesmo deletar?")
				.setPositiveButton("Quero", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						AlunoDAO dao = null;
						try {
							dao = new AlunoDAO(ListaAlunos.this);
							dao.deletar(aluno);
						} finally {
							if(dao != null){
								dao.close();
							}
						}
						carregaLista();
					}
				})
				.setNegativeButton("Não", null).show();
				return false;
			}
		});
		
		
		MenuItem acharNoMapa = menu.add("Ver Mapa");
		acharNoMapa.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent intentMapa = new Intent(Intent.ACTION_VIEW);
				intentMapa.setData(Uri.parse("geo:0,0?z=14&q=" + aluno.getEndereco()));
				startActivity(intentMapa);
				return false;
			}
		});
		
		
		menu.add("Enviar E-mail");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}

	private void carregaLista() {
		AlunoDAO dao = null;
		List<Aluno> alunos = null;
		try {
			dao = new AlunoDAO(ListaAlunos.this);
			alunos = dao.getLista();
		} finally {
			if(dao != null){
				dao.close();
			}
		}
		
		ListaAlunosAdapter adapter = new ListaAlunosAdapter(alunos, this);
		
		lista.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lista_alunos, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int clicado = item.getItemId();
		
		switch (clicado) {
		case R.id.novo:
			Intent irParaFormulario = new Intent(this, Formulario.class);
			startActivity(irParaFormulario);
			break;
		case R.id.enviar_alunos:
			new EnviaAlunosTask(this).execute();
			break;
		case R.id.receber_provas:
			Intent irParaListagemDeProvas = new Intent(this, Provas.class);
			startActivity(irParaListagemDeProvas);
			break;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
