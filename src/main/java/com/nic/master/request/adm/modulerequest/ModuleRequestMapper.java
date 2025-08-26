package com.nic.master.request.adm.modulerequest;

import lombok.Data;

@Data
public class ModuleRequestMapper {
    private String operation;
    private  String moduleGuid;
    private String moduleCode;
    private MstModuleAddRequest moduleAddRequest;
    private MstModuleUpdateRequest moduleUpdateRequest;
}
