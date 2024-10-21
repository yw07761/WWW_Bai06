package dao;

import java.util.List;

import entities.DeTai;


public interface DeTaiDAO {
	public List<DeTai> layDeTaiTheoGiangVien(int maDT);
	public void themDeTai(DeTai deTai);
	 public void xoaDeTai(int maDT);
	public List<DeTai> layTatCaDeTai();
}