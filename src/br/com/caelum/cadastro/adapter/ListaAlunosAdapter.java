package br.com.caelum.cadastro.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

public class ListaAlunosAdapter extends BaseAdapter{
	private List<Aluno> alunos;
	private Activity activity;

	public ListaAlunosAdapter(List<Aluno> alunos, Activity activity){
		this.alunos = alunos;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Object getItem(int posicao) {
		return alunos.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		return alunos.get(posicao).getId();
	}

	@Override
	public View getView(int posicao, View view, ViewGroup parent){
		View linha = activity.getLayoutInflater().inflate(R.layout.linha_listagem, null);
		Aluno aluno = alunos.get(posicao);
		
		View fundo = linha.findViewById(R.id.fundo);
		
		if(posicao % 2 == 0){
			fundo.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
		} else{
			fundo.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
		}
		
		TextView nome = (TextView) linha.findViewById(R.id.nome);
		nome.setText(aluno.toString());
		
		ImageView foto = (ImageView) linha.findViewById(R.id.foto);
		
		if(aluno.getLocalDaFoto() != null){
			Bitmap imagem = BitmapFactory.decodeFile(aluno.getLocalDaFoto());
			Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 100, 100, true);
			foto.setImageBitmap(imagemReduzida);
		} else {
			Drawable semFoto = activity.getResources().getDrawable(R.drawable.ic_no_image);
			foto.setImageDrawable(semFoto);
		}
		
		TextView telefone = (TextView) linha.findViewById(R.id.telefone);
		if(telefone != null){
			telefone.setText(aluno.getTelefone());
		}
		
		TextView site = (TextView) linha.findViewById(R.id.site);
		if(site != null){
			site.setText(aluno.getSite());
		}
		return linha;
	}

}
