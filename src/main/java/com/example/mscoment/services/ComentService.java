package com.example.mscoment.services;

import com.example.mscoment.enums.StatusComent;
import com.example.mscoment.models.ComentModel;
import com.example.mscoment.repositories.ComentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComentService {
    @Autowired
    ComentRepository comentRepository;

    @Autowired
    public ComentService(ComentRepository comentRepository) {
        this.comentRepository = comentRepository;
    }

    public ComentModel sendComent(ComentModel comentModel) {
        comentModel.setSendDateComent(LocalDateTime.now());
        try {
            comentModel.setComentId(comentModel.getComentId());
            comentModel.setName(comentModel.getName());
            comentModel.setMessage(comentModel.getMessage());
            comentModel.setStatusComent(StatusComent.SENT);
            System.out.println("Coment Sented");
        } catch (MailException e) {
            comentModel.setStatusComent(StatusComent.ERROR);
            System.out.println("Coment did not sented");
        } finally {
            return comentRepository.save(comentModel);
        }
    }


        public Page<ComentModel> findAll (Pageable pageable){
            return comentRepository.findAll(pageable);
        }

        public Optional<ComentModel> findById (UUID comentId){
            return comentRepository.findById(comentId);


        }
    }
