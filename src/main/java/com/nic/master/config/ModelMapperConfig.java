package com.nic.master.config;

import com.nic.master.entity.process.ProcessAlert;
import com.nic.master.entity.process.ProcessDefDesc;
import com.nic.master.entity.process.ProcessDefDescAction;
import com.nic.master.requestDTO.process.processalertrequest.AddProcessAlertRequest;
import com.nic.master.requestDTO.process.processalertrequest.UpdateProcessAlertRequest;
import com.nic.master.requestDTO.process.processdefdescrequest.UpdateProcessDefDescRequest;
import com.nic.master.requestDTO.process.processdefdescriptionaction.AddProcessDefDescActionRequest;
import com.nic.master.requestDTO.process.processdefdescriptionaction.UpdateProcessDefDescActionRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true); // skip nulls during update

        // -------------------------
        // AddProcessAlertRequest → ProcessAlert
        // -------------------------
        modelMapper.addMappings(new PropertyMap<AddProcessAlertRequest, ProcessAlert>() {
            @Override
            protected void configure() {
                skip(destination.getProcessAlertId());
                skip(destination.getProcessAlertGuid()); // will be generated
            }
        });

        // -------------------------
        // UpdateProcessAlertRequest → ProcessAlert
        // -------------------------
        modelMapper.addMappings(new PropertyMap<UpdateProcessAlertRequest, ProcessAlert>() {
            @Override
            protected void configure() {
                skip(destination.getProcessAlertId());
                skip(destination.getProcessAlertGuid());
                skip(destination.getCreatedBy());
                skip(destination.getCreatedDate());
                skip(destination.getCreatedIpAddr());
                skip(destination.getCreatedMacAddr());
            }
        });

        // -------------------------
        // AddProcessDefDescActionRequest → ProcessDefDescAction
        // -------------------------
        modelMapper.addMappings(new PropertyMap<AddProcessDefDescActionRequest, ProcessDefDescAction>() {
            @Override
            protected void configure() {
                skip(destination.getProcessDefDesc());
                skip(destination.getActionType());
                skip(destination.getSectionType());

                // also skip audit fields
                skip(destination.getCreatedBy());
                skip(destination.getCreatedDate());
                skip(destination.getCreatedIpAddr());
                skip(destination.getCreatedMacAddr());
            }
        });

// UpdateProcessDefDescActionRequest → ProcessDefDescAction
        modelMapper.addMappings(new PropertyMap<UpdateProcessDefDescActionRequest, ProcessDefDescAction>() {
            @Override
            protected void configure() {
                skip(destination.getProcessDefDescActionId());
                skip(destination.getProcessDefDescActionGuid());

                skip(destination.getCreatedBy());
                skip(destination.getCreatedDate());
                skip(destination.getCreatedIpAddr());
                skip(destination.getCreatedMacAddr());
            }
        });

// UpdateProcessDefDescRequest → ProcessDefDesc
        modelMapper.addMappings(new PropertyMap<UpdateProcessDefDescRequest, ProcessDefDesc>() {
            @Override
            protected void configure() {
                skip(destination.getProcessDefDescId());
                skip(destination.getProcessDefDescGuid());

                skip(destination.getCreatedBy());
                skip(destination.getCreatedDate());
                skip(destination.getCreatedIpAddr());
                skip(destination.getCreatedMacAddr());
            }
        });

        return modelMapper;
    }
}

