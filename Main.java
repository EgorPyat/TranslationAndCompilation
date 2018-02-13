package com.company;

import java.io.IOException;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        try {
            String expression = "1-(2+4) 4/2";
            StringReader reader = new StringReader(expression);
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
            System.out.println("count: " + parser.parseExpression());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
