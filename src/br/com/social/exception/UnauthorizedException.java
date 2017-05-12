package br.com.social.exception;

public class UnauthorizedException extends Exception {
	private static final long serialVersionUID = -8709647826719716696L;

	public UnauthorizedException() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public UnauthorizedException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
	

	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}