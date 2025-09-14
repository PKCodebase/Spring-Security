package com.nic.master.request.process.ref;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefTemplateAddRequest {

    @NotNull(message = "ProcessDef GUID is required")
    private String processDefGuid;

    @NotNull(message = "SectionType GUID is required")
    private String sectionTypeGuid;

    @NotNull(message = "ActionType GUID is required")
    private String actionTypeGuid;

    @NotBlank(message = "Template name is required")
    private String refTemplateName;

    @NotBlank(message = "Template code is required")
    private String refTemplateCode;

    private Boolean havePrefillData = false;

    private String prefillDataResultQuery;

    private Boolean isQueryRaiseCheck = false;

    private String queryCheckMessage;
}
