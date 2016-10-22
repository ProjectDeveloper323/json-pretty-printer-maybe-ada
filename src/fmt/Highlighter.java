package src.fmt;

import org.antlr.v4.* ;
import org.antlr.v4.runtime.*;
import src.parse.JSONLexer;
import javax.swing.text.StyledDocument ;
import java.awt.Color ;
import javax.swing.text.StyleContext;
import javax.swing.text.StyleConstants;
import javax.swing.text.Style;

// TODO: Make a use of src.parse.JSONLexer to tokenize the input text
// then colorize each token with the approporiate color

// IMPORTANT NOTE:
//
// You can use the following code to higligh specific word in the document:
//
//      doc.setCharacterAttributes(start,len, style, false);
//
//      Where:
//          doc: is variable of class Document
//          start: index from which we should start coloring
//          len: how many characters we want to apply this style to ?
//          style: our style, i.e. color, font, font size ... etc
//              we can create style like this:
//
//              StyleContext context = new StyleContext();
//              Style mystyle = context.addStyle("mystyle", null);
//              StyleConstants.setForeground(mystyle, Color.red);
//

public class Highlighter {
	public static CommonTokenStream tok ;
	public static String docString ;
	public StyledDocument doc ;
	String[] lines ;
	int [] tokenIndex ;
    String number;
    String floatf;

	public static StyleContext greenContext ;
	public static Style greenStyle ;
	public static StyleContext yellowContext ;
	public static Style yellowStyle ;
	public static StyleContext redContext;
	public static Style redStyle ;
	public static StyleContext blueContext;
	public static Style blueStyle;

	public Highlighter(String docString ,StyledDocument doc){
		this.doc = doc ;
		this.docString = docString ;
		System.out.println("testing");
		CharStream str = new ANTLRInputStream(docString);
		System.out.println(docString);
		JSONLexer lex = new JSONLexer(str);
		tok = new CommonTokenStream(lex);
		tok.fill();

	}
	public void tokenize(){

		lines = new String[tok.size()];
		tokenIndex = new int[tok.size()-1];

		//got an array of tokens
		for(int i =0 ;i<lines.length ;i++){
			lines[i] = tok.get(i).getText();
			System.out.println(lines[i] );
			System.out.println("");
		}//end of for

		//get an array of token indexes
		try{
			int startIndex = 0;
			for (int i = 0 ; i<lines.length ; i++){
				tokenIndex[i] = docString.indexOf(lines[i] ) ;
				System.out.println("indexes >>> " + tokenIndex[i]);
				tokenIndex[i] = docString.indexOf(lines[i] , startIndex ) ;
				startIndex += lines[i].length() ;
				System.out.print("test");
        	}//end of for
        	}catch( Exception e){
	    		System.out.println("error in getting the indexs");
			}


	}//end of tokenize method

	public void colorize() throws Exception {

		//style for curly brackets
		greenContext = new StyleContext();
		greenStyle = greenContext.addStyle("greenStyle", null);
		StyleConstants.setForeground(greenStyle, Color.green);

		//style for square bracket
		yellowContext = new StyleContext();
		yellowStyle = yellowContext.addStyle("yellowStyle", null);
		StyleConstants.setForeground(yellowStyle, Color.yellow);

		//style for keywords ;
		StyleContext redContext = new StyleContext();
		Style redStyle = redContext.addStyle("redStyle", null);
		StyleConstants.setForeground(redStyle, Color.red);

		//style for integer ;
		StyleContext orangeContext = new StyleContext();
		Style orangeStyle = orangeContext.addStyle("orangeStyle", null);
		StyleConstants.setForeground(orangeStyle, Color.orange);


        //style for float ;
		StyleContext magentaContext = new StyleContext();
		Style magentaStyle = magentaContext.addStyle("magentaStyle", null);
		StyleConstants.setForeground(magentaStyle, Color.magenta);

		//style for string
		blueContext = new StyleContext();
		blueStyle = blueContext.addStyle("blueStyle", null);
		StyleConstants.setForeground(blueStyle, Color.blue);




		for(int i=0;i<lines.length ;i++){
			switch(lines[i]){
				case "{" : case "}"  :{
					System.out.println("this is {}");
					this.doc.setCharacterAttributes(tokenIndex[i],lines[i].length() , greenStyle, false);
					break ;
				}
				case "[" : case "]"  :{
					System.out.println("this is []");
					this.doc.setCharacterAttributes(tokenIndex[i],lines[i].length() , yellowStyle, false);
					break ;
				}
				case "null" : case "true"  : case "false" :{
					System.out.println("keyword");
					this.doc.setCharacterAttributes(tokenIndex[i],lines[i].length() , redStyle, false);

				}

				default :
				try {
					Integer.parseInt(lines[i]);
					System.out.println("This is Integer");
					this.doc.setCharacterAttributes(tokenIndex[i], lines[i].length(), orangeStyle, false);
				}
				catch (ArithmeticException e1) {
					try {
						Float.parseFloat(lines[i]);
						System.out.println("this is flaot");
						this.doc.setCharacterAttributes(tokenIndex[i], lines[i].length(), magentaStyle, false);
					}
					catch(ArithmeticException e2) {
						System.out.println("String");
						this.doc.setCharacterAttributes(tokenIndex[i],lines[i].length() , blueStyle, false);
					}

				}
			}//end of switch

		}//end of for

	}//end of colorize method




}//end of class