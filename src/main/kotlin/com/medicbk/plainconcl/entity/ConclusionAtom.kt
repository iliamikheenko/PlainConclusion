package com.medicbk.plainconcl.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Objects.hash

@Entity
@Table(name = "atom", schema = "plain_conclusion")
data class ConclusionAtom(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column(name = "patient_id")
    val patientId: String?,

    @Column(name = "conclusion_uuid")
    val conclusionUUID: String?,

    @Column(name = "drug_name")
    val drugName: String?,

    @Column(name = "nosology_id")
    val nosologyId: String?,

    @Column(name = "nosology_name")
    val nosologyName: String?,

    @Column(name = "therapy_type_name")
    val therapyTypeName: String?,

    @Column(name = "therapy_name")
    val therapyName: String?,

    @Column(name = "therapy_ord")
    val therapyOrd: String?,

    @Column(name = "drug_group_id")
    val drugGroupId: String?,

    @Column(name = "drug_id")
    val drugId: String?,

    @Column(name = "drug_ord")
    val drugOrd: String?,

    @Column(name = "therapy_message_payload")
    val therapyMessagePayload: String?,

    @Column(name = "drug_group_ord")
    val drugGroupOrd: String?,

    @Column(name = "hash")
    var hashValue: Int = hash(drugName, nosologyId, nosologyName, therapyOrd, therapyName, therapyTypeName, drugOrd, drugId, drugGroupId, therapyMessagePayload, drugGroupOrd)

)