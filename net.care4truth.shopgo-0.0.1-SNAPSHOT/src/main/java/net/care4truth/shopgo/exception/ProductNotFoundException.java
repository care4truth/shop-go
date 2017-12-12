package net.care4truth.shopgo.exception;

public class ProductNotFoundException extends Exception {
	
	private String message;
	
	public ProductNotFoundException(){
		this("Product is not available");
	}
	
	public ProductNotFoundException(String message){
	
		this.message = System.currentTimeMillis() + " : " + message;
	}

	public String getMessage() {
		return message;
	}
}
