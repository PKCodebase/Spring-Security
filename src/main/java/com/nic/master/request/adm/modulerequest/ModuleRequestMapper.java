package com.nic.master.request.adm.modulerequest;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class ModuleRequestMapper {
    private String operation;
    private  String moduleGuid;
    private String moduleCode;

    @Valid
    private MstModuleAddRequest moduleAddRequest;

    @Valid
    private MstModuleUpdateRequest moduleUpdateRequest;


}
