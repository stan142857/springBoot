package salamander.standstill.demo.model;

import lombok.Data;

@Data
public class Question {
    private Integer ID;
    private String title;
    private String description;
    private Long gmt_create;
    private Integer creator;
    private Long gmt_modified;
    private Integer view_count;
    private Integer like_count;
    private String tag;
    private Integer comment_count;
}
