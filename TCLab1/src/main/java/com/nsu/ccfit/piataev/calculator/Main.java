package com.nsu.ccfit.piataev.calculator;

import java.io.IOException;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        try {
            String expression = args[0];
            StringReader reader = new StringReader(expression);
            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer);
            System.out.println("count: " + parser.parseExpression());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Need string with expression to calculate!");
        }
    }
}
