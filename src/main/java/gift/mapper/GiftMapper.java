package gift.mapper;

import gift.model.GiftRequestForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GiftMapper {
    @Select("SELECT * FROM GIFTS ORDER BY timestamp DESC")
    List<GiftRequestForm> getGifts();

    @Insert("INSERT INTO GIFTS (targetName, age, relationship, status, requestMessage, aiResponse, timestamp) " +
            "VALUES(#{targetName}, #{age}, #{relationship}, #{status}, #{requestMessage}, #{aiResponse}, #{timestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "giftId")
    int insert(GiftRequestForm giftRequestForm);
}