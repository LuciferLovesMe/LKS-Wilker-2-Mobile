package com.abim.pc_05;

public class DetailVaksin {
    String tgl_vaksin, nama, nama_vaksin;
    int periode;

    public DetailVaksin(String tgl_vaksin, String nama, String nama_vaksin, int periode) {
        this.tgl_vaksin = tgl_vaksin;
        this.nama = nama;
        this.nama_vaksin = nama_vaksin;
        this.periode = periode;
    }

    public String getTgl_vaksin() {
        return tgl_vaksin;
    }

    public String getNama() {
        return nama;
    }

    public String getNama_vaksin() {
        return nama_vaksin;
    }

    public int getPeriode() {
        return periode;
    }
}
