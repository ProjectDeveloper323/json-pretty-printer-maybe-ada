package src.fmt;

import org.antlr.v4.* ;
import org.antlr.v4.runtime.*;
import src.parse.JSONLexer;
import java.util.Arrays;
import java.util.ArrayList ;
import java.util.List ;
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
	public static void main (String args[]) throws Exception{

		System.out.println("tetsing");
		CharStream str = new ANTLRInputStream("[{\"key\" : true}]");
		JSONLexer lex = new JSONLexer(str);
		CommonTokenStream tok = new CommonTokenStream(lex);
		tok.fill();
		System.out.println(tok.size());
		//System.out.println("string is" + tok.toString());

		/*
		ArrayList<String> allTokens = tok.getTokens();
		Object[] objectList = allTokens.toArray();
		String[] stringArray =  Arrays.copyOf(objectList,objectList.length,String[].class);
		for(int i = 0 ; i<stringArray.length();i++){
			
			System.out.println(stringArray[i]);
		}
*/

		String[] lines = new String[tok.size()]; 
		for(int i =0 ;i<lines.length ;i++){
			lines[i] = tok.get(i).getText();
			System.out.println(lines[i]);
			System.out.println("");
		}

	}
}