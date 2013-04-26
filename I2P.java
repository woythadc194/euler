/*
 * Utility to convert infix integer expression strings to postfix
 * Author: T. Fossum
 * Date: 02/20/2011
 */

/*
 * Using the I2P class:
 *   new I2P(<infix String>).parse() -> <postfix String>
 *   The parse() method throws a ParseException if the input string
 *   cannot be parsed
 *
 *   <integer> represents a non-negative decimal integer
 *   <expr> ::= <term> <moreTerms>
 *   <term> ::= <factor> <moreFactors>
 *   <factor> ::= <integer>
 *   <factor> ::= ( <expr> )
 *   <factor> ::= - <factor>
 *   <moreTerms> ::= + <term> <moreTerms>
 *   <moreTerms> ::= - <term> <moreTerms>
 *   <moreTerms> ::= 
 *   <moreFactors> ::= * <factor> <moreFactors>
 *   <moreFactors> ::= / <factor> <moreFactors>
 *   <moreFactors> ::= % <factor> <moreFactors>
 *   <moreFactors> ::=
 */

public class I2P {

    private String iexp; // string to convert
    private String pexp; // converted string
    private String token; // next token

    // constructor
    public I2P (String iexp) {
	    this.iexp = iexp;
    	this.pexp = "";
	    token = null;
    }

    // skip over whitespace
    public void skip() {
	    while (iexp.length() > 0 && Character.isWhitespace(iexp.charAt(0)))
	        iexp = iexp.substring(1);
    }

    // advance to the next token in the infix expression
    public void advance() throws ParseException{
	    // clobber the current token
	    token = null;
	    skip();
	    // if nothing left, the token is still null. 
	    if (iexp.length() == 0)
	        return;
	    // we have liftoff!
	    token = "";
	    char c = iexp.charAt(0);
	    // first check to see if it's the start of an integer
	    if (Character.isDigit(c))
	        // it's an integer, so grab the whole thing
	        token = getInt();
	    else {
	        // it's some other one-character token, so just grab it 
	        token = "" + c;
	        iexp = iexp.substring(1);
	    }
	    // System.err.println(">>> Token: " + token);
    }

    // return the current token
    public String tok() throws ParseException {
	    // if we don't have a token yet, advance to the next one
	    if (token == null)
	        advance();
	    return token;
    }
    
    // returns true if and only if the token string is an integer
    public boolean isInt(String s) {
	    return Character.isDigit(s.charAt(0));
    }

    // accumulate decimal digits from the input string
    // and return the resulting integer token string
    // NOTE: an integer string of length greater than one
    // cannot have leading zeros
    public String getInt() throws ParseException {
	    // System.err.println("... in getInt");
	    String s = "";
	    while (true) {
	        if (iexp.length() == 0)
	        	break; // no more characters to process
            char c = iexp.charAt(0);
	        if (Character.isDigit(c)) {
	        	// it's a digit -- consume it
	        	s += c;
	        	iexp = iexp.substring(1);
	        } else
	        	// no more digits
	        	break;
	    }
	    // an integer string of length > 1 must not start with zero
	    if (s.length() > 1 && s.charAt(0) == '0')
	        throw new ParseException("malformed integer: " + s);
        // System.err.println("... returning " + s);
        return s;
        }
    
    // match the string t with the current token --
    // throw an exception if the match fails,
    // otherwise advance to the next token
    public void match(String t) throws ParseException {
	    if (t == null)
	        throw new ParseException("nothing to match!");
	    // the current token
	    String tt = tok();
	    if (! t.equals(tt))
	        throw new ParseException("matching "+t+", found "+tt);
	    // advance to the next token
	    advance();
    } 
    
    // append the string s to the postfix string, followed by a space
    public void append(String s) {
	    pexp += s + " ";
    }

    // return the parsed input string --
    // if there are characters following the string,
    // throw an exception
    public String parse() throws ParseException {
	    // build the postfix expression in pexp
	    expr();
	    // check to see if there's anything left in the input
	    String s = tok();
	    if (s != null)
	        throw new ParseException("illegal token at end of expression: "+s);
	    return pexp;
    }

    // consume the infix string in iexp and deposit
    // the resulting postfix string into pexp
    public void expr() throws ParseException{
	    // <expr> ::= <term> <moreTerms>
	    term();
	    moreTerms();
    }

    // <term> ::= <factor> <moreFactors>
    public void term() throws ParseException {
	    factor();
	    moreFactors();
    }

    // <factor> ::= <integer>
    // <factor> ::= ( <expr> )
    // <factor> ::= - <factor>
    public void factor() throws ParseException {
    	// current token
	    String s = tok();
	    if (s == null)
	        throw new ParseException("illegal end of expression");
	    if (isInt(s)) {
	        // System.err.println("... <integer> " + s);
	        append(s);
	        advance();
	    } else if (s.equals("(")) {
	        // System.err.println("... ( <expr> )");
	        match("(");
	        expr();
	        match(")");
	    } else if (s.equals("-")) {
	        // System.err.println("... - <factor>"); 
	        advance();
	        factor();
	        // '!' is the unary minus postfix operator
	        // in postfix, the operator follows its operand
	        append("!");
	    } else
	        throw new ParseException("illegal token: " + s);
    }

    // <moreTerms> ::= + <term> <moreTerms>
    // <moreTerms> ::= - <term> <moreTerms>
    // <moreTerms> ::= 
    public void moreTerms() throws ParseException {
	    String s = tok();
	    if (s == null)
	        return;
	    if (s.equals("+") || s.equals("-")) {
	            advance();
	        term();
	    // in postfix, the operator follows its operands
	        append(s);
	        moreTerms();
	    }
    }
    
    // <moreFactors> ::= * <factor> <moreFactors>
    // <moreFactors> ::= / <factor> <moreFactors>
    // <moreFactors> ::= % <factor> <moreFactors>
    // <moreFactors> ::=
    public void moreFactors() throws ParseException {
	    String s = tok();
	    if (s == null)
	        return;
	    if (s.equals("*") || s.equals("/") || s.equals("%")) {
	        advance();
	        factor();
	        // in postfix, the operator follows its operands
	        append(s);
	        moreFactors();
	    }
    }

    // test driver for the I2P class:
    // to parse an infix string s and return its postfix equivalent p,
    // do the following:
    //   p = new I2P(s).parse()
    // you must check for a ParseException, which is raised
    // when the string s is not a legal infix expression
    public static void main(String [] args) {
    	String s = null;
    	String p = null;
    	// process each string in the command line
    	for (int i=0 ; i<args.length ; i++) {
    	    s = args[i];
    	    try {
          		p = new I2P(s).parse();
          		System.out.println("infix: " + s);
          		System.out.println("postfix: " + p);
       	    } catch (ParseException e) {
           		System.err.println(e);
    	    }
    	}
   }
}
