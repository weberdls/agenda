package br.com.caelum.cadastro.mapa;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

public class Localizador {

	private Context context;

	public Localizador(Context context) {
		this.context = context;
	}

	public LatLng getCoordenada(String endereco) {
		LatLng latLng = null;
		try {
			Geocoder geocoder = new Geocoder(context);
			List<Address> enderecos = geocoder.getFromLocationName(endereco, 1);
			if (!enderecos.isEmpty()) {
				Address enderecoLocalizado = enderecos.get(0);
				double latitude = enderecoLocalizado.getLatitude();
				double longitude = enderecoLocalizado.getLongitude();
				latLng = new LatLng(latitude, longitude);
			}
		} catch (IOException e) {
			return null;
		}

		return latLng;
	}

}
