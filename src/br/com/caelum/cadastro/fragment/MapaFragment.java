package br.com.caelum.cadastro.fragment;

import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.mapa.Localizador;
import br.com.caelum.cadastro.modelo.Aluno;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment {

	@Override
	public void onResume() {
		super.onResume();

		AlunoDAO dao = null;
		List<Aluno> alunos = null;
		try {
			dao = new AlunoDAO(getActivity());
			alunos = dao.getLista();
		} finally {
			dao.close();
		}
		
		for (Aluno aluno : alunos) {
			LatLng localDoAluno = new Localizador(getActivity()).getCoordenada(aluno.getEndereco());
			MarkerOptions marcador = new MarkerOptions().position(localDoAluno).title(aluno.getNome()).snippet(aluno.getEndereco());
			getMap().addMarker(marcador);
		}
	}

	public void centralizaNo(LatLng local) {
		GoogleMap map = getMap();
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(local, 15));
	}
}