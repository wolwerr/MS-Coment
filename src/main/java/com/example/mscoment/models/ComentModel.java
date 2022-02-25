package com.example.mscoment.models;

import com.example.mscoment.enums.StatusComent;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "TB_COMENT")
public class ComentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID comentId;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String message;
    private LocalDateTime sendDateComent;
    public StatusComent statusComent;

}