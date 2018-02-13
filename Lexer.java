package com.company;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private Reader reader;
    private int current;

    public Lexer(Reader reader) throws IOException{
        this.reader = reader;
        this.current = this.reader.read();
    }

    public Lexeme getLexeme() throws IOException{
        while(Character.isWhitespace(current)) {
            current = reader.read();
        }

        if(Character.isDigit(current)) {
            StringBuilder bString = new StringBuilder(current);
            do {
                bString.append(current);
                current = reader.read();
            }
            while (Character.isDigit(current));
            return new Lexeme(bString.toString(), LexemeType.NUM);
        }

        int excurrent = current;
        current = reader.read();

        switch(excurrent){
            case '+':
                return new Lexeme("+", LexemeType.PLS);
            case '-':
                return new Lexeme("-", LexemeType.MNS);
            case '*':
                return new Lexeme("*", LexemeType.MUL);
            case '/':
                return new Lexeme("/", LexemeType.DIV);
            case '(':
                return new Lexeme("(", LexemeType.OBR);
            case ')':
                return new Lexeme(")", LexemeType.CBR);
            case '^':
                return new Lexeme("+", LexemeType.POW);
            case -1:
                return new Lexeme("+", LexemeType.EOF);
            default:
                throw new IOException("illegal symbol: " + current);
        }
    }

}
