package com.example.mscoment.repositories;

import com.example.mscoment.models.ComentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComentRepository extends JpaRepository<ComentModel, UUID>  {
}

