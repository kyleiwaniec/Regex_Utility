package edu.mccc.cos210.regex;

public interface RegexInt{

	/** @return true if the regularExpression represented by this instance is found in the target beginning at 0 if no previous find or at the last end() position in the target.
		throws NullPointerException if target is NULL.
	*/
	boolean find(String target) throws NullPointerException;
		
	/** @return true if the regularExpression represented by this instance is found in the target beginning at position start
	*/	
	boolean find(String target, int start) throws NullPointerException;

	boolean match(String target);
		// returns true if the regularExpression represented by this instance matches the target
		// throws NullPointerException if target is NULL
	int start(); 
		// after the last successful find returns the offset into the target that the regular expression was found after a match returns 0
		// throws IllegalStateException If no match has yet been attempted, or if the previous match operation failed.
	int end(); 
		// after the last successful find returns returns the offset after the last character found.
		// after a match returns length of the target.
		// throws IllegalStateException If no match has yet been attempted, or if the previous match operation failed.

	String getCapture(int n); 
		// after last successful match or find return the nth capture group. A value of 0 	returns the entire found or matched  string.
		// throws IllegalStateException If no match has yet been attempted, or if the previous match operation failed.
		// throws IndexOutOfBoundsException If there is no capturing group in the pattern with the given index
	int captureCount();
		// after last successful match returns the number of strings captured in the target.
		// throws IllegalStateException If no match has yet been attempted, or if the previous match operation failed.
	void reset();
		// resets the state of the Regex object so that it may be reused. 
}

