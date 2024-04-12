package com.ccifuentes.equifax.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccifuentes.equifax.models.entity.Document;

public interface IDocumentRepository extends JpaRepository<Document, Long> {

}
