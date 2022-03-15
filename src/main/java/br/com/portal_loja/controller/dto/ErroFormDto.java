package br.com.portal_loja.controller.dto;

public class ErroFormDto {
	
	private String campo;
	private String erro;
	
	
	public ErroFormDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}


	public String getCampo() {
		return campo;
	}


	public void setCampo(String campo) {
		this.campo = campo;
	}


	public String getErro() {
		return erro;
	}


	public void setErro(String erro) {
		this.erro = erro;
	}

}
