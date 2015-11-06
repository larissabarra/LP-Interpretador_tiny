package tiny;

public class InvalidTokenException extends Exception {
	private String token;
	private int linha;
	
	public InvalidTokenException(String message) {
        super(message);
    }
	
	public void setToken (String token) {
		this.token = token;
	}
	
	public void setLinha (int linha) {
		this.linha = linha;
	}
	
	public String getToken() {
		return token;
	}
	
	public int getLinha() {
		return linha;
	}
}



