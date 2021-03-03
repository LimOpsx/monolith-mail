package cloud.dowhat.monolith.web.service.impl;

import cloud.dowhat.monolith.core.config.MailConfig;
import cloud.dowhat.monolith.model.Recipient;
import cloud.dowhat.monolith.web.mapper.IRecipientMapper;
import cloud.dowhat.monolith.web.service.IRecipientService;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author linen
 */
@Service
@AllArgsConstructor
public class RecipientServiceImpl implements IRecipientService {

    private final IRecipientMapper iRecipientMapper;

    private final MailConfig mailConfig;

    /**
     * get a new address
     *
     * @param address address address
     * @return new address
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public String getNewRecipient(String address) {
        Recipient recipient = new Recipient();
        recipient.setRecipientAddress(address);
        String newAddress = RandomUtil.randomString(6);
        newAddress = newAddress + " <" + newAddress + "@" + mailConfig.getHost() + ">";
        if (StrUtil.isBlank(address)) {
            recipient.setRecipientAddress(newAddress);
            iRecipientMapper.insert(recipient);
            return newAddress;
        }
        //query is have
        if (Objects.isNull(iRecipientMapper.selectOne(recipient))) {
            throw new RuntimeException("get new address error...");
        }
        //create new address
        recipient.setRecipientAddress(newAddress);
        iRecipientMapper.insert(recipient);
        return newAddress;
    }

    /**
     * closed ws delete email
     *
     * @param address email
     */
    @Override
    public void update(String address) {
        Recipient recipient = new Recipient();
        recipient.setRecipientAddress(address);
        iRecipientMapper.update(recipient);
    }

}
