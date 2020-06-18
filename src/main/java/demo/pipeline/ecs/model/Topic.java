package demo.pipeline.ecs.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema
@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "topics")
public class Topic {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "{topic.notBlank}")
    @Size(min = 5, max = 255, message = "{topic.Size}")
    private String message;
}




