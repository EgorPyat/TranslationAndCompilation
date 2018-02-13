package com.company;

enum LexemeType{
    NUM,
    PLS,
    MNS,
    OBR,
    CBR,
    POW,
    MUL,
    DIV,
    EOF
}

public class Lexeme {
    private String value;
    private LexemeType type;

    public Lexeme(String value, LexemeType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public LexemeType getType() {
        return type;
    }
}
