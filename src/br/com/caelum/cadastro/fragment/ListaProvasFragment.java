package br.com.caelum.cadastro.fragment;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.caelum.cadastro.Provas;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

public class ListaProvasFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutProvas = inflater.inflate(R.layout.provas_lista, container, false);
		
		ListView listViewProvas = (ListView) layoutProvas.findViewById(R.id.provas);
		
		Prova prova1 = new Prova("25/03/2013", "Matematica");
		prova1.setTopicos(Arrays.asList("Algebra Linear","Integral", "Derivada"));
		
		Prova prova2 = new Prova("25/04/2013", "Java");
		prova2.setTopicos(Arrays.asList("Orientação a Objetos","Inner Class", "JVM"));
		
		List<Prova> provas = Arrays.asList(prova1, prova2);
		
		int layout = android.R.layout.simple_list_item_1;
		
		ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getActivity(), layout, provas);
		
		listViewProvas.setAdapter(adapter);
		
		listViewProvas.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				Prova selecionada = (Prova) adapter.getItemAtPosition(posicao);
				Provas provas = (Provas) getActivity();
				provas.selecionaProvas(selecionada);
			}
		});
		
		return layoutProvas;
	}
}
