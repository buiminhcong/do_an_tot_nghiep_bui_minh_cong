package com.example.backend.repository;

import com.example.backend.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

    @Query(value = "select * from module where id = ? and deleted = 0;", nativeQuery = true)
    Module findModuleById(int id);
    @Query(value = "select * from module where deleted = 0;", nativeQuery = true)
    List<Module> getListModule();

}
