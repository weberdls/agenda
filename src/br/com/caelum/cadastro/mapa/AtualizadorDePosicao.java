package br.com.caelum.cadastro.mapa;

import com.google.android.gms.maps.model.LatLng;

import br.com.caelum.cadastro.fragment.MapaFragment;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class AtualizadorDePosicao implements LocationListener {
	
	private LocationManager locationManager;
	private MapaFragment mapa;

	public AtualizadorDePosicao(Activity activity, MapaFragment mapa){
		this.mapa = mapa;
		locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		String provider = LocationManager.GPS_PROVIDER;
		long tempoMinimo = 20000; //mili segundo
		float distanciaMinima = 20; //metros
		locationManager.requestLocationUpdates(provider, tempoMinimo, distanciaMinima, this);
	}
	
	
	public void cancelar() {
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location novaLocalizacao) {
		LatLng local = new LatLng(novaLocalizacao.getLatitude(), novaLocalizacao.getLongitude());
		mapa.centralizaNo(local);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
