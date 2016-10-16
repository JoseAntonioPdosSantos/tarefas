package br.com.tarefas.model.persistence.entity;

import org.json.JSONObject;

import lombok.Data;

public @Data class UsuarioFacebook {

	private Long id;
	private String name;
	private String email;
//	private Integer timezone;
//	private Boolean verified;
//	private String middleName;
//	private String gender;
//	private String lastName;
//	private String link;
//	private String locale;
//	private String name;
//	private String updatedTime;
	
	public UsuarioFacebook(JSONObject jsonUsuario){
		 
		id = jsonUsuario.getLong("id");
		name = jsonUsuario.getString("name");
		email = jsonUsuario.getString("email");
//		timezone = jsonUsuario.getInt("timezone");
//		verified = jsonUsuario.getBoolean("verified");
//		middleName = jsonUsuario.getString("middle_name");
//		gender = jsonUsuario.getString("gender");
//		lastName = jsonUsuario.getString("last_name");
//		link = jsonUsuario.getString("link");
//		locale = jsonUsuario.getString("locale");
//		name = jsonUsuario.getString("name");
//		updatedTime = jsonUsuario.getString("updated_time");
 
	}
	
}
