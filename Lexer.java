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
        System.out.println("get: " + (char)this.current);

        while(Character.isWhitespace((char)this.current)) {
            this.current = this.reader.read();
        }

        if(Character.isDigit((char)current)) {
            StringBuilder bString = new StringBuilder((char)current);
            do {
                bString.append((char)this.current);
                this.current = this.reader.read();
            }
            while (Character.isDigit((char)this.current));
            return new Lexeme(bString.toString(), LexemeType.NUM);
        }

        int excurrent = this.current;
        this.current = this.reader.read();
        System.out.println("exchar: " + (char)excurrent);

        switch(excurrent){
            case '+':
                return new Lexeme("+", LexemeType.PLS);
            case '-':
                return new Lexeme("-", LexemeType.MNS);
            case '*':
                return new Lexeme("*", LexemeType.MUL);
            case '/':
                System.out.println("ret /");
                return new Lexeme("/", LexemeType.DIV);
            case '(':
                return new Lexeme("(", LexemeType.OBR);
            case ')':
                return new Lexeme(")", LexemeType.CBR);
            case '^':
                return new Lexeme("^", LexemeType.POW);
            case -1:
                return new Lexeme("-1", LexemeType.EOF);
            default:
                throw new IOException("error - illegal symbol: " + (char)this.current);
        }
    }

}
