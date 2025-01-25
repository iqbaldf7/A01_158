package com.example.pamtugasakhir_158.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Proyek(
    @SerialName("id_proyek")
    val id_proyek: String,

    @SerialName("nama_proyek")
    val nama_proyek: String,

    @SerialName("deskripsi_proyek")
    val deskripsi_proyek: String,

    @SerialName("tanggal_mulai")
    val tanggal_mulai: String,

    @SerialName("tanggal_berakhir")
    val tanggal_berakhir: String,

    @SerialName("status_proyek")
    val status_proyek: String,
)

@Serializable
data class Tugas(
    @SerialName("id_tugas")
    val id_tugas: String,

    @SerialName("id_proyek")
    val id_proyek: String,

    @SerialName("id_tim")
    val id_tim: String,

    @SerialName("nama_tugas")
    val nama_tugas: String,

    @SerialName("deskripsi_tugas")
    val deskripsi_tugas: String,

    @SerialName("prioritas")
    val prioritas: String,

    @SerialName("status_tugas")
    val status_tugas: String,
)

@Serializable
data class Tim(
    @SerialName("id_tim")
    val id_tim: String,

    @SerialName("nama_tim")
    val nama_tim: String,

    @SerialName("deskripsi_tim")
    val deskripsi_tim: String,
)


@Serializable
data class AnggotaTim(
    @SerialName("id_anggota")
    val id_anggota: String,

    @SerialName("id_tim")
    val id_tim: String,

    @SerialName("nama_anggota")
    val nama_anggota: String,

    @SerialName("peran")
    val peran: String,
)

