package com.medicbk.plainconcl.rep

import com.medicbk.plainconcl.entity.MedicalConclusion
import org.springframework.data.jpa.repository.JpaRepository

interface MedicalConclusionRep : JpaRepository<MedicalConclusion, Int> {
}