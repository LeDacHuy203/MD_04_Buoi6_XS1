package com.example.doctorservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Tên bác sĩ

    private String specialization; // Chuyên khoa (Ví dụ: Nội khoa, Ngoại khoa, Nhi khoa)

    private Integer experienceYears; // Số năm kinh nghiệm

    private String email; // Email công việc

    private Boolean status; // Trạng thái (Đang làm việc / Nghỉ phép)
}
