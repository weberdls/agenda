package br.com.caelum.cadastro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;
import br.com.caelum.cadastro.dao.AlunoDAO;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Object[] mensagens = (Object[]) intent.getExtras().get("pdus");
		byte[] primeira = (byte[]) mensagens[0];
		SmsMessage sms = SmsMessage.createFromPdu(primeira);
		String telefone = sms.getDisplayOriginatingAddress();

		AlunoDAO dao = null;
		try {
			dao = new AlunoDAO(context);
			boolean ehAluno = dao.isAluno(telefone);
			
			if(ehAluno){
				MediaPlayer player = MediaPlayer.create(context, R.raw.super_mario_bros);
				player.start();
				Toast.makeText(context, "Tocando Música", Toast.LENGTH_LONG).show();
			}
		} finally {
			dao.close();
		}
	}

}
