package edu.mccc.cos210.ex;

public class NoSuchElementException extends Exception{
	private String description;
	public NoSuchElementException(){
		printStackTrace(System.out);
	}
	public NoSuchElementException(String description){
		super(description);
		this.description = description;
	}
	public NoSuchElementException(String description, Throwable t){
		super(description, t);
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
}