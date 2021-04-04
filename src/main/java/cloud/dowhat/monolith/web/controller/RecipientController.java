package cloud.dowhat.monolith.web.controller;

import cloud.dowhat.monolith.model.R;
import cloud.dowhat.monolith.web.service.IRecipientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * recipient rest api
 * @author linen
 */
@RestController
@RequestMapping("/recipient")
@AllArgsConstructor
public class RecipientController {

    private final IRecipientService iRecipientService;

    /**
     * get a new address
     * @param address address address
     * @return new address
     */
    @GetMapping("/getNewRecipient")
    public R<String> getNewRecipient(String address){
        return R.<String>ok(iRecipientService.getNewRecipient(address));
    }

    /**
     * close delete address
     * @param address address
     * @return void
     */
    @PostMapping("/update")
    public R<Void> update(@RequestBody String address){
        iRecipientService.update(address);
        return R.ok();
    }

}
