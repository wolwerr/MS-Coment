package com.example.mscoment.consumers;

import com.example.mscoment.dtos.ComentDto;
import com.example.mscoment.models.ComentModel;
import com.example.mscoment.services.ComentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ComentConsumer {
    @Autowired
    ComentService comentService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload ComentDto comentDto) {
        ComentModel comentModel = new ComentModel();
        BeanUtils.copyProperties(comentDto, comentModel);
        comentService.sendComent(comentModel);
        System.out.println("Coment Status: " + comentModel.getStatusComent().toString());
    }
}
