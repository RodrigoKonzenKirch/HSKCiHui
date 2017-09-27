package android.practice.com.hskcihui;

public class Words {
    private String hsk;
    private String simplified;
    private String traditional;

    public Words(String hsk, String simplified, String traditional){
        this.hsk = hsk;
        this.simplified = simplified;
        this.traditional = traditional;
    }

    public String getHsk() {
        return hsk;
    }

    public void setHsk(String hsk) {
        this.hsk = hsk;
    }

    public String getSimplified() {
        return simplified;
    }

    public void setSimplified(String simplified) {
        this.simplified = simplified;
    }

    public String getTraditional() {
        return traditional;
    }

    public void setTraditional(String traditional) {
        this.traditional = traditional;
    }
}
