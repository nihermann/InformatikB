package RegExEBNF;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEX {
    // Modellieren Sie folgenden Sachverhalt mit der in der Vorlesung behandelten EBNF-Syntax in möglichst kurzer Formulierung:
    //Die Syntax eines Universal Resource Identifiers (URI) ist im Webstandard RFC 1630 festgelegt. Eine
    //vereinfachte Beschreibung sei folgende:
    // Jede URI besteht aus einem sogenannten Schema, gefolgt
    //von einem Doppelpunkt und einem optionalen Pfad. Danach kann ein Fragezeichen (’?’) und ein
    //Suchstring folgen.
    //• Ein Schema besteht aus einem Buchstaben, gefolgt von beliebig vielen Buchstaben, Ziffern
    //  oder Sonderzeichen.
    //• Ein Pfad besteht aus beliebig vielen Buchstaben, Ziffern oder Sonderzeichen, optional gefolgt
    //  von einem Schrägstrich ’/’ und einem weiterem Pfad.
    //• Ein Suchstring ist wie ein Pfad aufgebaut, allerdings mit dem Trennzeichen ’+’

    // EBNF:
    // URI ::= Schema ':' [Pfad] ['?' Suchstring]

    // Buchstaben ::=  'A' | 'B' | 'C' |  'D' | .. | 'X' |  'Y' | 'Z'
    // Ziffern ::=  '0' | '1' | '2' |  '3' | '4' | '5' |  '6' | '7' | '8' |  '9' | '10'
    // Sonderzeichen ::= '*' | '#' | '%' | '$'
    // String ::= {Buchstaben} {Ziffern} {Sonderzeichen}

    // Schema ::= Buchstaben String
    // Pfad ::= String ['/' Pfad]
    // Suchstring ::= String ['+' Suchstring]

    public static void main(String[] args) {

        // RegEx:
        String string = "\\w*";
        String Schema = "[a-zA-Z]"+string;
        String Pfad = string+"(/"+string+")*";
        String Suchstring = string+"(\\+"+string+")*";

        Pattern URI = Pattern.compile(Schema + ':' + Pfad + '?' + "\\?" + Suchstring + '?');
        Matcher matcher = URI.matcher("Michael41836:documents/osnabrueck?informatik+machinelearning");

        System.out.println(matcher.matches());
    }
}
