package com.nic.master.controller.process;

import com.nic.master.entity.process.MstProcessType;
import com.nic.master.request.process.mstprocesstype.ProcessTypeRequestMapper;
import com.nic.master.service.process.MstProcessTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mstProcess")
public class MstProcessTypeController {

    private final MstProcessTypeService mstProcessTypeService;

    public MstProcessTypeController(MstProcessTypeService mstProcessTypeService) {
        this.mstProcessTypeService = mstProcessTypeService;
    }

//    @RequestMapping(
//            value = "/action",
//            method = {RequestMethod.GET,RequestMethod.POST}
//    )
//    public ResponseEntity<Object> handleProcessOperation(
//            @Valid @RequestBody(required = false)ProcessTypeRequestMapper
//            )
}
