package com.model;

public class ModelUser {
	  private int id;

      private String  nome;
      
      private String  cognome;
      
      private String  email;

      private String  password;

      private String bottoni = "<a style='display:none;' class='editAdd btn add' title='Add' data-toggle='tooltip' data-original-title='Add'><i class='material-icons'>&#xE03B;</i></a>"+ //add viene fuori quando si modifica qualcosa e si deve approvare
    		    "<a class='edit btn' title='Edit' data-toggle='tooltip'><i class='material-icons'>&#xE254;</i></a>"+
    		    "<a class='classeelimina delete btn btn-default'  title='Delete' data-toggle='tooltip'><i class='material-icons'>&#xE872;</i></a>";
      
      
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBottoni() {
		return bottoni;
	}

	public void setBottoni(String bottoni) {
		this.bottoni = bottoni;
	}


	
}
