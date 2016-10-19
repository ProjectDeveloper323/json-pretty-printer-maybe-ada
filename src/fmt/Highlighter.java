package src.fmt;

import org.antlr.v4.* ;
import org.antlr.v4.runtime.*;
import src.parse.JSONLexer;
import javax.swing.text.StyledDocument ;
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
	Highlighter(StyledDocument doc){
		String docString = doc.toString();
	}

	public static void main (String args[]) throws Exception{

		System.out.println("tetsing");
		CharStream str = new ANTLRInputStream("[{\"key\" : true}]");
		JSONLexer lex = new JSONLexer(str);
		CommonTokenStream tok = new CommonTokenStream(lex);
		tok.fill();
	//	System.out.println(tok.size());


		String[] lines = new String[tok.size()]; 
		int [] tokenIndex = new int[tok.size()-1];
		int [] tokenEndIndex = new int[tok.size()-1];

		for(int i =0 ;i<lines.length ;i++){
			lines[i] = tok.get(i).getText();
			tokenIndex[i]= tok.get(i).getTokenIndex();
			tokenEndIndex[i] = lines[i].length();
			System.out.println(lines[i]);
			System.out.println("");
		}

	}
}