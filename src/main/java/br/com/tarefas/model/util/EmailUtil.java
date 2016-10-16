package br.com.tarefas.model.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {

	public static void enviarEmail(String destinatario, String nomeDestinatario, String remetente, String nomeRemetente, String assunto, String conteudo) throws EmailException{
		SimpleEmail from = new SimpleEmail();
		from.setHostName("smtp.office365.com"); // o servidor SMTP para envio do e-mail
		from.addTo(destinatario, nomeDestinatario); //destinatário
		from.setFrom(remetente, nomeRemetente); // remetente
		from.setSubject(assunto); // assunto do e-mail
		from.setMsg(conteudo); //conteudo do e-mail
		from.send(); //envia o e-mail
	}
}
