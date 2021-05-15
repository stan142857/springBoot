package salamander.standstill.demo.model;

import okhttp3.internal.Internal;

public class User {
    private Internal ID;
    private String ACCOUNT_ID;
    private String NAME;
    private String TOKEN;
    private Long GMT_CREATE;
    private Long GMT_MODIFIED;

    public Internal getID() {
        return ID;
    }

    public void setID(Internal ID) {
        this.ID = ID;
    }

    public String getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(String ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    public Long getGMT_CREATE() {
        return GMT_CREATE;
    }

    public void setGMT_CREATE(Long GMT_CREATE) {
        this.GMT_CREATE = GMT_CREATE;
    }

    public Long getGMT_MODIFIED() {
        return GMT_MODIFIED;
    }

    public void setGMT_MODIFIED(Long GMT_MODIFIED) {
        this.GMT_MODIFIED = GMT_MODIFIED;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", ACCOUNT_ID='" + ACCOUNT_ID + '\'' +
                ", NAME='" + NAME + '\'' +
                ", TOKEN='" + TOKEN + '\'' +
                ", GMT_CREATE=" + GMT_CREATE +
                ", GMT_MODIFIED=" + GMT_MODIFIED +
                '}';
    }
}
