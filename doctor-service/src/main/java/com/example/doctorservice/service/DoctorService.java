package com.example.doctorservice.service;

import com.example.doctorservice.dto.DoctorResponseDto;
import com.example.doctorservice.entity.Doctor;
import com.example.doctorservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctor -> new DoctorResponseDto(
                        doctor.getId(),
                        doctor.getName(),
                        doctor.getSpecialization()
                ))
                .collect(Collectors.toList());
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public java.util.Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }
}
