class ParseException extends Exception {

    String e;

    public ParseException(String e) {
    	this.e = e;
    }

    public String toString() {
	    return ": " + e;
    }
} 
