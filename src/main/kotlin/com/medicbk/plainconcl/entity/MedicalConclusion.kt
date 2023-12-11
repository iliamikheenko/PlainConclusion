package com.medicbk.plainconcl.entity


import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "conclusion", schema = "plain_conclusion")
data class MedicalConclusion(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "patient_id")
    val patientId: String,

    @Column(name = "conclusion_uuid")
    val conclusionUuid: String,

    @ManyToMany(cascade = [(CascadeType.ALL)])
    @JoinTable(
        name = "conclusion_link",
        joinColumns = [(JoinColumn(name = "conclusion_id"))],
        inverseJoinColumns = [(JoinColumn(name = "concl_atom_id"))]
    )
    var conclusions: MutableList<ConclusionAtom> = mutableListOf()

)