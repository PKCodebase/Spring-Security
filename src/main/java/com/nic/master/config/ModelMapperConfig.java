package com.nic.master.config;

import com.nic.master.entity.process.ProcessAlert;
import com.nic.master.request.process.processalertrequest.AddProcessAlertRequest;
import com.nic.master.request.process.processalertrequest.UpdateProcessAlertRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true); // null fields skip for update operations


        // ✅ Add mapping for AddProcessAlertRequest → ProcessAlert
        modelMapper.addMappings(new PropertyMap<AddProcessAlertRequest, ProcessAlert>() {
            @Override
            protected void configure() {
                skip(destination.getProcessAlertId());   // 👈 prevent auto-matching
                skip(destination.getProcessAlertGuid()); // generated in service
            }
        });

        // ✅ Add mapping for UpdateProcessAlertRequest → ProcessAlert
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


        return modelMapper;
    }
}
