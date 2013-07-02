package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import br.com.caelum.cadastro.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastro.fragment.ListaProvasFragment;
import br.com.caelum.cadastro.modelo.Prova;

public class Provas extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.provas);

		if (bundle == null) {
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();

			if (isTabletEmLandscape()) {
				transaction.replace(R.id.esquerda, new ListaProvasFragment(),
						ListaProvasFragment.class.getCanonicalName());
				transaction.replace(R.id.direita, new DetalhesProvaFragment(),
						DetalhesProvaFragment.class.getCanonicalName());
			} else {
				transaction.replace(R.id.unico, new ListaProvasFragment(),
						ListaProvasFragment.class.getCanonicalName());
			}
			transaction.commit();
		}
	}

	private boolean isTabletEmLandscape() {
		return getResources().getBoolean(R.bool.ehTabletNaHorizontal);
	}

	public void selecionaProvas(Prova selecionada) {
		Bundle argumentos = new Bundle();
		argumentos.putSerializable("prova", selecionada);

		DetalhesProvaFragment detalhes = new DetalhesProvaFragment();
		detalhes.setArguments(argumentos);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.direita, detalhes,
				DetalhesProvaFragment.class.getCanonicalName());
		if (!isTabletEmLandscape()) {
			transaction.addToBackStack(null);
		}
		transaction.commit();
	}
}
