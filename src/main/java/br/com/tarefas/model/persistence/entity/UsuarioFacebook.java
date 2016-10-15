package br.com.tarefas.model.persistence.entity;

import org.json.JSONObject;

import lombok.Data;

public @Data class UsuarioFacebook {

	private Long id;
	private String firstName;
	private Integer timezone;
	private String email;
	private Boolean verified;
	private String middleName;
	private String gender;
	private String lastName;
	private String link;
	private String locale;
	private String name;
	private String updatedTime;
	
	public UsuarioFacebook(JSONObject jsonUsuario){
		 
		id = jsonUsuario.getLong("id");
		firstName = jsonUsuario.getString("first_name");
		timezone = jsonUsuario.getInt("timezone");
		email = jsonUsuario.getString("email");
		verified = jsonUsuario.getBoolean("verified");
		middleName = jsonUsuario.getString("middle_name");
		gender = jsonUsuario.getString("gender");
		lastName = jsonUsuario.getString("last_name");
		link = jsonUsuario.getString("link");
		locale = jsonUsuario.getString("locale");
		name = jsonUsuario.getString("name");
		updatedTime = jsonUsuario.getString("updated_time");
 
	}
	
}
