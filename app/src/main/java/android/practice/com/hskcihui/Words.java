package android.practice.com.hskcihui;

public class Words {
    private String id;
    private String hsk;
    private String simplified;
    private String traditional;
    private String pinyin;
    private String english;
    private String type;
    private String level;
    private String info;

    public Words(String id, String hsk, String simplified, String traditional, String pinyin,
                 String english, String type, String level, String info){
        this.id = id;
        this.hsk = hsk;
        this.simplified = simplified;
        this.traditional = traditional;
        this.pinyin = pinyin;
        this.english = english;
        this.type = type;
        this.level = level;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
