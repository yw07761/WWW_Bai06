package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "DETAI")
public class DeTai {

    @Id
    @Column(name = "MADT")
    private String maDT;

    @Column(name = "TENDT", nullable = false)
    private String tenDT;

    @Column(name = "NAMDANGKY")
    private String namDangKy;

    @Column(name = "MOTADETAI")
    private String moTa;

    // Nhiều đề tài thuộc về một giảng viên
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGV")  // Khóa ngoại tham chiếu đến bảng GiangVien
    private GiangVien giangVien;

    // Getters and setters

    public DeTai() {}

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public String getTenDT() {
        return tenDT;
    }

    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    public String getNamDangKy() {
        return namDangKy;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public GiangVien getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(GiangVien giangVien) {
        this.giangVien = giangVien;
    }

	public void setNamDangKy(String namDangKyStr) {
		// TODO Auto-generated method stub
		
	}

	public void setDeTai(DeTai deTai) {
		// TODO Auto-generated method stub
		
	}
}
