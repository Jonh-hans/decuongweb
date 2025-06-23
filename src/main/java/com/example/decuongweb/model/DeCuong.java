package com.example.decuongweb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "decuongs")
public class DeCuong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "trang_thai")
    private String trangThai = "cho_duyet";

    @Column(name = "thoi_gian_dang")
    private LocalDateTime thoiGianDang = LocalDateTime.now();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public LocalDateTime getThoiGianDang() { return thoiGianDang; }
    public void setThoiGianDang(LocalDateTime thoiGianDang) { this.thoiGianDang = thoiGianDang; }
}
