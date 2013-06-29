package br.com.caelum.cadastro.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {
	private String url;

	public WebClient(String urlServidor) {
		this.url = urlServidor;
	}

	public String post(String dadosJson) {
		try {
			HttpPost post = new HttpPost(url);
			post.setEntity(new StringEntity(dadosJson));
			post.setHeader("Content-type", "application/json");
			post.setHeader("Accept", "application/json");
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(post);
			HttpEntity resposta = response.getEntity();
			String respostaJson = EntityUtils.toString(resposta);
			return respostaJson;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
