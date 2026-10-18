package com.example.appointmentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId; // ID của bệnh nhân (lấy từ Patient-Service)

    private Long doctorId; // ID của bác sĩ (lấy từ Doctor-Service)

    private LocalDateTime appointmentDate; // Ngày giờ hẹn khám

    private String reason; // Lý do khám bệnh

    private String status; // Trạng thái lịch hẹn (PENDING, CONFIRMED, CANCELLED)
}
