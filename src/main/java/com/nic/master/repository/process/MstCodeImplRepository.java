package com.nic.master.repository.process;

import com.nic.master.entity.process.MstCodeImpl;
import com.nic.master.enums.ImplType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MstCodeImplRepository extends JpaRepository<MstCodeImpl,Long> {

    Optional<MstCodeImpl> findByCodeImplGuid(String codeImplGuid);

    boolean existsByQualifiedClassNameAndImplType(String qualifiedClassName, ImplType implType);}
