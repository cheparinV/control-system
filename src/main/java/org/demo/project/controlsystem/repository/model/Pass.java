package org.demo.project.controlsystem.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(schema = "access_system")
public class Pass {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pass_seq")
    @SequenceGenerator(name = "pass_seq", schema = "access_system", sequenceName = "pass_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    @Min(1)
    private Long roomId;

    @Column(unique = true, nullable = false)
    @Min(1)
    private Long userId;

    public Pass(Long roomId, Long userId) {
        this.roomId = roomId;
        this.userId = userId;
    }

    public Pass() {
    }

    public Long getId() {
        return id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public Long getUserId() {
        return userId;
    }
}
