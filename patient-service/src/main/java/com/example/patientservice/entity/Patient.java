package com.example.patientservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName; //(String): Họ và tên bệnh nhân.
    private LocalDate dateOfBirth; //(LocalDate): Ngày sinh.

    private String gender; //(String): Giới tính.
    private String phoneNumber; //(String):Số điện thoại liên lạc.
    private String address;//(String):Địa chỉ thường trú.
    @Column(columnDefinition = "TEXT")
    private String medicalHistory;//(Text/String):Tiền sử bệnh lý(Ví dụ:dị ứng thuốc, bệnh nền).
}
