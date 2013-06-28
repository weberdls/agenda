package br.com.caelum.cadastro;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

public class Formulario extends Activity{
	private FormularioHelper helper;
	private String caminhoArquivo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		Intent intent = getIntent();
		final Aluno alunoParaSerAlterado = (Aluno) intent.getSerializableExtra("alunoClicado");
		
		helper = new FormularioHelper(this);
		
		Button botao = (Button)findViewById(R.id.gravar);

		if(alunoParaSerAlterado != null){
			botao.setText("Alterar");
			helper.colocaAlunoNoFormulario(alunoParaSerAlterado);
		}
		
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View clicada) {
				Aluno aluno = helper.pegaAlunoDoFormulario();
				
				AlunoDAO dao = null;
				try {
					dao = new AlunoDAO(Formulario.this);
					if(alunoParaSerAlterado == null){
						dao.salva(aluno);
					} else {
						aluno.setId(alunoParaSerAlterado.getId());
						dao.alterar(aluno);
					}
					
					Toast.makeText(Formulario.this, "Aluno " + aluno.getNome() + " Salvo com sucesso", Toast.LENGTH_LONG).show();
				} finally {
					if(dao != null){
						dao.close();
					}
				}
				finish();
			}
		});
		
		//clique na foto dispara camera
		ImageView foto = helper.getFoto();
		foto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				caminhoArquivo = Environment.getExternalStorageDirectory().toString() 
				+ "/" + System.currentTimeMillis() + ".png";

				File arquivo = new File(caminhoArquivo);
				
				Uri localImagem = Uri.fromFile(arquivo);
				
				irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localImagem);
				
				startActivityForResult(irParaCamera, 123);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 123){
			if(resultCode == Activity.RESULT_OK){
				helper.carregaImagem(caminhoArquivo);
			} else {
				//cancelou
				caminhoArquivo = null;
			}
		}
	}
}
