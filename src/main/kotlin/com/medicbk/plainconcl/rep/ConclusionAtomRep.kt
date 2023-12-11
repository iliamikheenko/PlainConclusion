package com.medicbk.plainconcl.rep

import com.medicbk.plainconcl.entity.ConclusionAtom
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ConclusionAtomRep : JpaRepository<ConclusionAtom, Int> {
    fun findByHashValue(hashValue: Int): Optional<ConclusionAtom>
}