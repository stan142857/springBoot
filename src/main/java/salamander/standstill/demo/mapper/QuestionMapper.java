package salamander.standstill.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import salamander.standstill.demo.model.Question;

@Repository
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,tag,gmt_create,creator,gmt_modified) values (#{title},#{description},#{tag},#{gmt_create},#{creator},#{gmt_modified})")
    void create(Question question);

}
