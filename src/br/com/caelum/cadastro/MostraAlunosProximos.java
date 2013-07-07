package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import br.com.caelum.cadastro.fragment.MapaFragment;
import br.com.caelum.cadastro.mapa.AtualizadorDePosicao;

public class MostraAlunosProximos extends FragmentActivity{
	
	private AtualizadorDePosicao atualizador;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.map_layout);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		MapaFragment mapa = new MapaFragment();
		transaction.replace(R.id.mapa, mapa);
		transaction.commit();
		
		atualizador = new AtualizadorDePosicao(this, mapa);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		atualizador.cancelar();
	}
}
