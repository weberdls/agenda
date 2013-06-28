package br.com.caelum.cadastro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioHelper {
	private EditText editNome;
	private EditText editSite;
	private EditText editTelefone;
	private EditText editEndereco;
	private RatingBar ratingNota;
	private ImageView foto;
	private Aluno aluno;

	public FormularioHelper(Formulario formulario){
		aluno = new Aluno();
		editNome = (EditText) formulario.findViewById(R.id.nome);
		editSite = (EditText) formulario.findViewById(R.id.site);
		editTelefone = (EditText) formulario.findViewById(R.id.telefone);
		editEndereco = (EditText) formulario.findViewById(R.id.endereco);
		ratingNota = (RatingBar) formulario.findViewById(R.id.nota);
		foto = (ImageView) formulario.findViewById(R.id.foto);
	}

	public Aluno pegaAlunoDoFormulario() {
		aluno.setNome(editNome.getText().toString());
		aluno.setSite(editSite.getText().toString());
		aluno.setTelefone(editTelefone.getText().toString());
		aluno.setEndereco(editEndereco.getText().toString());
		aluno.setNota(Double.valueOf(ratingNota.getRating()));
		return aluno;
	}

	public void colocaAlunoNoFormulario(Aluno alunoParaSerAlterado) {
		aluno = alunoParaSerAlterado;
		editNome.setText(alunoParaSerAlterado.getNome());
		editEndereco.setText(alunoParaSerAlterado.getEndereco());
		editSite.setText(alunoParaSerAlterado.getSite());
		editTelefone.setText(alunoParaSerAlterado.getTelefone());
		ratingNota.setRating(alunoParaSerAlterado.getNota().floatValue());
		
		if(aluno.getLocalDaFoto() != null){
			carregaImagem(alunoParaSerAlterado.getLocalDaFoto());
		}
	}

	public ImageView getFoto() {
		return foto;
	}

	public void carregaImagem(String caminhoArquivo) {
		aluno.setLocalDaFoto(caminhoArquivo);
		Bitmap imagem = BitmapFactory.decodeFile(caminhoArquivo);
		Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 100, 100, true);
		foto.setImageBitmap(imagemReduzida);
	}
}
