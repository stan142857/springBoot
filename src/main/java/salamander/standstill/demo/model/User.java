package salamander.standstill.demo.model;

import lombok.Data;

@Data
public class User {
    private int ID;
    private String ACCOUNT_ID;
    private String NAME;
    private String TOKEN;
    private Long GMT_CREATE;
    private Long GMT_MODIFIED;
    private String avatar_url;
}
