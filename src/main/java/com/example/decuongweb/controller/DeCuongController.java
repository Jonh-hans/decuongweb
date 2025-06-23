package com.example.decuongweb.controller;

import java.util.List;
import java.util.ArrayList;
import com.example.decuongweb.model.DeCuong;
import com.example.decuongweb.repository.DeCuongRepository;
import com.example.decuongweb.repository.SubjectRepository;
import com.example.decuongweb.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class DeCuongController {

    @Autowired
    private DeCuongRepository deCuongRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    @GetMapping("/dang")
    public String hienFormDang(Model model) {
        model.addAttribute("subjects", subjectRepo.findAll());
        return "dang"; // Trang HTML upload
    }


    @PostMapping("/dang")
    public String xuLyDangDeCuong(@RequestParam("subjectId") Long subjectId,
                                  @RequestParam("moTa") String moTa,
                                  @RequestParam("file") MultipartFile file,
                                  Model model) {
        try {
            // 1. Tạo thư mục uploads nếu chưa có
            String uploadPath = new File("uploads").getAbsolutePath();
            File directory = new File(uploadPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 2. Lưu file thật
            String fileName = file.getOriginalFilename();
            File dest = new File(uploadPath, fileName);
            file.transferTo(dest);

            // 3. Lưu thông tin vào DB
            Optional<Subject> optionalSubject = subjectRepo.findById(subjectId);
            if (optionalSubject.isPresent()) {
                DeCuong dc = new DeCuong();
                dc.setSubject(optionalSubject.get());
                dc.setMoTa(moTa);
                dc.setFilePath("uploads/" + fileName); // đường dẫn tương đối
                deCuongRepo.save(dc);
                model.addAttribute("thongBao", "Đăng đề cương thành công!");
            } else {
                model.addAttribute("thongBao", "Không tìm thấy môn học.");
            }
        } catch (IOException e) {
            model.addAttribute("thongBao", "Lỗi upload file: " + e.getMessage());
        }

        model.addAttribute("subjects", subjectRepo.findAll());
        return "dang";
    }

    @GetMapping("/timkiem")
    public String timKiemDeCuong(@RequestParam(value = "keyword", required = false) String keyword,
                                 Model model) {
        List<DeCuong> ketQua;
        if (keyword != null && !keyword.isEmpty()) {
            ketQua = deCuongRepo.findBySubject_TenMonContainingIgnoreCase(keyword);
        } else {
            ketQua = new ArrayList<>();
        }
        model.addAttribute("ketQua", ketQua);
        return "timkiem";
    }



}

