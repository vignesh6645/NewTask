package com.example.hospital.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Appointment")
@SQLDelete(sql = "UPDATE Appointment SET is_delete =1 WHERE appointment_id= ?")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Integer appointmentId;

    @Column(name = "appointment_name",nullable = false)
    private String appointmentName;

    @Column(name = "appointment_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd 'T' HH:mm:ss")
    private Date appointmentTime;

    @Column(name = "is_active",columnDefinition = "integer default 0")
    private int isActive;

    @Column(name = "is_delete",columnDefinition = "integer default 0")
    private int isDelete;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime updateDateTime;

    @ManyToOne
    @JoinColumn(name = "fk_doctor_id")
    private Doctor doctorId;

    @ManyToOne
    @JoinColumn(name = "fk_patient_id")
    private Patient patientId;

    @OneToOne
    @JoinColumn(name = "fk_disease_id")
    private Disease diseaseId;
}
