package br.com.jmsdevel.sisresapi.exception;

public class RecursoNaoEncontrado extends RuntimeException {
	
	private static final long serialVersionUID = 4897155267563687086L;
	
	private static final String mensagem = "Recurso não encontrado";
	
	public RecursoNaoEncontrado() {
		super(mensagem);
	}

	public RecursoNaoEncontrado(String message) {
		super(message);
	}
	
}
