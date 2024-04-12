package com.ccifuentes.equifax.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccifuentes.equifax.models.entity.FileDetail;

public interface IFileDetailRepository extends JpaRepository<FileDetail, Long> {

}
