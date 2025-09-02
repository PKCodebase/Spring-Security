package com.nic.master.controller.adm;

import com.nic.master.param.StatusParam;
import com.nic.master.request.adm.rolerequest.RoleAddRequest;
import com.nic.master.request.adm.rolerequest.RoleRequestWrapper;
import com.nic.master.request.adm.rolerequest.RoleUpdateRequest;
import com.nic.master.service.admservice.MstRoleService;
import com.nic.master.util.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.OnClose;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import com.nic.master.request.adm.rolerequest.RoleRequestWrapper;


@RestController
@RequestMapping("/roles")
public class MstRoleController {

    private final MstRoleService mstRoleService;

    public MstRoleController(MstRoleService mstRoleService) {
        this.mstRoleService = mstRoleService;
    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Object> addRole(@Valid @RequestBody RoleAddRequest roleAddRequest, HttpServletRequest httpServletRequest){
//        try {
//            StatusParam response = mstRoleService.addRole(roleAddRequest);
//            return ResponseBuilder.buildOk(response,response,httpServletRequest);
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(),ex.getMessage());
//        }
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<Object> getAllRoles(HttpServletRequest httpServletRequest){
//        try{
//            return ResponseEntity.ok(mstRoleService.getAllRoles());
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }
//
//    @GetMapping("/getByCode/{roleCode}")
//    public ResponseEntity<Object> getRoleByCode(@PathVariable String roleCode,HttpServletRequest httpServletRequest){
//        try{
//            return ResponseEntity.ok(mstRoleService.getRoleByCode(roleCode));
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }
//
//    @GetMapping("/getByGuid/{roleGuid}")
//    public ResponseEntity<Object> getRoleByGuid(@PathVariable String roleGuid,HttpServletRequest httpServletRequest){
//        try{
//            return ResponseEntity.ok(mstRoleService.getRoleByGuid(roleGuid));
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.NOT_FOUND,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }
//
//    @PutMapping("/update/{roleGuid}")
//    public ResponseEntity<Object> updateRoleByGuid(@PathVariable String roleGuid, @Valid @RequestBody RoleUpdateRequest roleUpdateRequest, HttpServletRequest httpServletRequest){
//        try{
//            StatusParam response = mstRoleService.updateRoleByGuid(roleGuid,roleUpdateRequest);
//            return ResponseBuilder.buildOk(response,response,httpServletRequest);
//
//        }catch (Exception ex){
//            return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,httpServletRequest.getRequestURI(), ex.getMessage());
//        }
//    }

//    @PostMapping("/action")
//    public ResponseEntity<Object> handleRoleActions(@RequestBody RoleRequestWrapper requestWrapper,
//                                                    HttpServletRequest httpServletRequest) {
//        try {
//            switch (requestWrapper.getOperation().toUpperCase()) {
//                case "ADD":
//                    StatusParam addResponse = mstRoleService.addRole(requestWrapper.getRoleAddRequest());
//                    return ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
//
//                case "GETALL":
//                    return ResponseEntity.ok(mstRoleService.getAllRoles());
//
//                case "GETBYCODE":
//                    return ResponseEntity.ok(mstRoleService.getRoleByCode(requestWrapper.getRoleCode()));
//
//                case "GETBYGUID":
//                    return ResponseEntity.ok(mstRoleService.getRoleByGuid(requestWrapper.getRoleGuid()));
//
//                case "UPDATE":
//                    StatusParam updateResponse = mstRoleService.updateRoleByGuid(
//                            requestWrapper.getRoleGuid(),
//                            requestWrapper.getRoleUpdateRequest()
//                    );
//                    return ResponseBuilder.buildOk(updateResponse, updateResponse, httpServletRequest);
//
//                default:
//                    return ResponseBuilder.buildError(HttpStatus.BAD_REQUEST,
//                            httpServletRequest.getRequestURI(),
//                            "Invalid operation: " + requestWrapper.getOperation());
//            }
//        } catch (Exception ex) {
//            return ResponseBuilder.buildError(HttpStatus.INTERNAL_SERVER_ERROR,
//                    httpServletRequest.getRequestURI(),
//                    ex.getMessage());
//        }
//    }
@RequestMapping(
        value = "/action",
        method = {RequestMethod.GET, RequestMethod.POST}
)
public ResponseEntity<Object> handleRoleActions(
        @Valid @RequestBody(required = false) RoleRequestWrapper requestWrapper,
        @RequestParam(value = "operation", required = false) String operation,
        @RequestParam(value = "roleCode", required = false) String roleCode,
        @RequestParam(value = "roleGuid", required = false) String roleGuid,
        HttpServletRequest httpServletRequest) {

    String roleOperation = null;

    if (requestWrapper != null && requestWrapper.getOperation() != null) {
        roleOperation = requestWrapper.getOperation();
    } else if (operation != null) {
        roleOperation = operation;
    }

    if (roleOperation == null) {
        return ResponseBuilder.buildError(
                HttpStatus.BAD_REQUEST,
                httpServletRequest.getRequestURI(),
                "Operation is required"
        );
    }

    return switch (roleOperation.toUpperCase().trim()) {
        case "ADD" -> {
            StatusParam addResponse = null;
            if (requestWrapper != null) {
                addResponse = mstRoleService.addRole(requestWrapper.getRoleAddRequest());
            }
            yield ResponseBuilder.buildOk(addResponse, addResponse, httpServletRequest);
        }
        case "GETALL" -> ResponseEntity.ok(mstRoleService.getAllRoles());
        case "GETBYCODE" -> ResponseEntity.ok(mstRoleService.getRoleByCode(roleCode));
        case "GETBYGUID" -> ResponseEntity.ok(mstRoleService.getRoleByGuid(roleGuid));
        case "UPDATE" -> {
            StatusParam updateResponse = null;
            if (requestWrapper != null) {
                updateResponse = mstRoleService.updateRoleByGuid(
                        requestWrapper.getRoleGuid(),
                        requestWrapper.getRoleUpdateRequest()
                );
            }
            yield ResponseBuilder.buildOk(updateResponse, updateResponse, httpServletRequest);
        }
        default -> ResponseBuilder.buildError(
                HttpStatus.BAD_REQUEST,
                httpServletRequest.getRequestURI(),
                "Invalid operation: " + roleOperation
        );
    };
}


}
