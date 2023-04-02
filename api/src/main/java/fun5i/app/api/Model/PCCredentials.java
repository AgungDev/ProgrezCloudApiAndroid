package fun5i.app.api.Model;
/**
 *
 * @author fun5i
 */
public class PCCredentials {

    private String d,s,o;

    public PCCredentials(String d, String s, String o){
        this.d = d;
        this.s = s;
        this.o = o;
    }

    public String getD(){
        return this.d;
    }

    public String getS(){
        return this.s;
    }

    public String getO(){
        return this.o;
    }

}
