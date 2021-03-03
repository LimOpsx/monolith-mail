package cloud.dowhat.monolith.web.mapper;

import cloud.dowhat.monolith.model.Message;
import cloud.dowhat.monolith.model.vo.MailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IMessageMapper {

    void insert(@Param("ew") Message message);

    List<MailVO> getMessages(String address);
}
