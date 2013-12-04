package edu.mccc.cos210.ex;

public class GrumpyCatError extends Exception{
	private String description;
	public GrumpyCatError(){
		printStackTrace(System.out);
	}
	public GrumpyCatError(String description){
		super(description);
		this.description = description;
	}
	public GrumpyCatError(String description, Throwable t){
		super(description, t);
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
}