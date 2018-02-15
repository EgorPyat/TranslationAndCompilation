import java.io.IOException;

public class Parser {
    Lexer lexer;
    Lexeme current;

    public Parser(Lexer lexer) throws IOException{
        this.lexer = lexer;
        this.current = this.lexer.getLexeme();
    }

    public int parseExpression() throws IOException{
        int count = this.parseTerm();
        LexemeType type = this.current.getType();

        while (type == LexemeType.PLS || type == LexemeType.MNS){
            this.current = this.lexer.getLexeme();
            if (type == LexemeType.PLS) {
                count += this.parseTerm();
            }
            else if (type == LexemeType.MNS) {
                count -= this.parseTerm();
            }
            type = this.current.getType();
        }

        return count;
    }

    public int parseTerm() throws IOException{
        int count = this.parseFactor();
        LexemeType type = this.current.getType();

        while (type == LexemeType.MUL || type == LexemeType.DIV){
            this.current = this.lexer.getLexeme();
            if (type == LexemeType.MUL) {
                count *= this.parseFactor();
            }
            else if (type == LexemeType.DIV) {
                count /= this.parseFactor();
            }
            type = this.current.getType();
        }

        return count;
    }

    public int parseFactor() throws IOException{
        int count = this.parsePower();
        if(this.current.getType() == LexemeType.POW){
            current = this.lexer.getLexeme();
            int power = this.parseFactor();
            count = (int)Math.pow(count, power);
        }
        return count;
    }

    public int parsePower() throws IOException{
        int sign = this.current.getType() == LexemeType.MNS ? -1 : 1;
        if(sign == -1) {
            current = lexer.getLexeme();
            return sign * this.parseAtom();
        }
        return this.parseAtom();
    }

    public int parseAtom() throws IOException{
        LexemeType type = this.current.getType();
        if(type == LexemeType.NUM){
            Lexeme exlexeme = this.current;
            this.current = this.lexer.getLexeme();
            return new Integer(exlexeme.getValue());
        }
        else if(type == LexemeType.OBR){
            this.current = this.lexer.getLexeme();
            int count = this.parseExpression();
            if(this.current.getType() != LexemeType.CBR){
                throw new IOException("error - close bracket was expected");
            }
            this.current = this.lexer.getLexeme();
            return count;
        }
        else{
            throw new IOException("error - atom parsing failed");
        }
    }
}
