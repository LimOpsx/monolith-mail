package cloud.dowhat.monolith.web.mapper;

import cloud.dowhat.monolith.model.Recipient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IRecipientMapper {

    void updateById(@Param("id")Long id );

    void insert(@Param("ew")Recipient recipient);

    List<Recipient> selectListByAddress(String[] addresses);

    Recipient selectOne(@Param("ew") Recipient recipient);

    void update( @Param("ew") Recipient recipient);
}
