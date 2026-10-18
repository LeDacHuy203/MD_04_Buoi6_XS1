package com.example.appointmentservice.service;

import com.example.appointmentservice.entity.Appointment;
import com.example.appointmentservice.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final RestTemplate restTemplate;

    public Appointment createAppointment(Appointment appointment) {
        if (appointment.getPatientId() == null) {
            throw new IllegalArgumentException("patientId không được để trống!");
        }
        if (appointment.getDoctorId() == null) {
            throw new IllegalArgumentException("doctorId không được để trống!");
        }

        // 1. Kiểm tra Bệnh nhân có tồn tại hay không qua Eureka (tên service: patient-service)
        try {
            String patientUrl = "http://patient-service/api/v1/patients/" + appointment.getPatientId();
            restTemplate.getForObject(patientUrl, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Bệnh nhân với ID " + appointment.getPatientId() + " không tồn tại trong hệ thống!");
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi kiểm tra Bệnh nhân: " + e.getMessage());
        }

        // 2. Kiểm tra Bác sĩ có tồn tại hay không qua Eureka (tên service: doctor-service)
        try {
            String doctorUrl = "http://doctor-service/api/v1/doctors/" + appointment.getDoctorId();
            restTemplate.getForObject(doctorUrl, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Bác sĩ với ID " + appointment.getDoctorId() + " không tồn tại trong hệ thống!");
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi kiểm tra Bác sĩ: " + e.getMessage());
        }

        // 3. Gán giá trị mặc định nếu cần
        if (appointment.getStatus() == null || appointment.getStatus().isBlank()) {
            appointment.setStatus("PENDING");
        }
        if (appointment.getAppointmentDate() == null) {
            appointment.setAppointmentDate(LocalDateTime.now());
        }

        // 4. Lưu lịch khám vào cơ sở dữ liệu
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
