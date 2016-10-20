package src.fmt;

import org.antlr.v4.* ;
import org.antlr.v4.runtime.*;
import src.parse.JSONLexer;
import javax.swing.text.StyledDocument ;
import java.awt.Color ;
import java.awt.style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyleConstants;

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
	String docString ;
	Highlighter(StyledDocument doc){
		docString = doc.toString();
	}

	public static void main (String args[]) throws Exception{

		System.out.println("tetsing");
		CharStream str = new ANTLRInputStream("[{\"key\" = true}]");
		JSONLexer lex = new JSONLexer(str);
		CommonTokenStream tok = new CommonTokenStream(lex);
		tok.fill();
	//	System.out.println(tok.size());


		String[] lines = new String[tok.size()];
		int [] tokenIndex = new int[tok.size()];
		int [] tokenEndIndex = new int[tok.size()];

		for(int i =0 ;i<lines.length ;i++){
			lines[i] = tok.get(i).getText();
			tokenIndex[i]= tok.get(i).getTokenIndex();
			tokenEndIndex[i] = tokenIndex[i]+lines[i].length();
			System.out.println(lines[i]);
			System.out.println("");
		}//end of for

		//style for curly brackets
		StyleContext greenContext = new StyleContext();
		Style greenStyle = greenContext.addStyle("greenStyle", null);
		StyleConstants.setForeground(greenStyle, Color.green);

		//style for square bracket
		StyleContext yellowContext = new StyleContext();
		Style yellowStyle = yellowContext.addStyle("yellowStyle", null);
		StyleConstants.setForeground(yellowStyle, Color.yellow);

		//style for keywords ;
		StyleContext redContext = new StyleContext();
		Style redStyle = redContext.addStyle("redStyle", null);
		StyleConstants.setForeground(redStyle, Color.red);

		//style for string , numbers
		StyleContext blueContext = new StyleContext();
		Style blueStyle = blueContext.addStyle("blueStyle", null);
		StyleConstants.setForeground(blueStyle, Color.blue);



		for(int i=0;i<lines.length ;i++){
			switch(lines[i]){
				case "{" : case "}"  :{
					System.out.println("this is {");
					docString.setCharacterAttributes(tokenIndex[i],lines[i].length() , greenStyle, false);
					break ;
				}
				case "[" : case "]"  :{
					System.out.println("this is [");
					docString.setCharacterAttributes(tokenIndex[i],lines[i].length() , yellowStyle, false);
					break ;
				}
				case "null" : case "true"  : case "false" :{
					System.out.println("keyword");
					docString.setCharacterAttributes(tokenIndex[i],lines[i].length() , redStyle, false);
					break ;
				}
				default :
					System.out.println("String OR int OR float");
					docString.setCharacterAttributes(tokenIndex[i],lines[i].length() , blueStyle, false);
					break ;

}//end of switch

		}//end of for

	}//end of main
}//end of class