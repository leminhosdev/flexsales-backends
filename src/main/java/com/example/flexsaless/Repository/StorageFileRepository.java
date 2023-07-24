package com.example.flexsaless.Repository;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.ExcelFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageFileRepository extends JpaRepository<ExcelFile, Long> {

}
