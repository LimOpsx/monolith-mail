package cloud.dowhat.monolith.web.mapper;

import cloud.dowhat.monolith.model.MessageRecipient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IMessageRecipientMapper {

    void save(@Param("ew")MessageRecipient messageRecipient);

    void insert(@Param("ew") MessageRecipient messageRecipient);
}
