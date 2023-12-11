package com.medicbk.plainconcl

import org.jdom2.input.SAXBuilder
import java.io.File
import com.medicbk.plainconcl.entity.ConclusionAtom
import com.medicbk.plainconcl.entity.MedicalConclusion
import com.medicbk.plainconcl.rep.ConclusionAtomRep
import com.medicbk.plainconcl.rep.MedicalConclusionRep
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ParserPC {

    @Autowired
    private lateinit var medicalConclusionRep: MedicalConclusionRep

    @Autowired
    private lateinit var conclusionAtomRep: ConclusionAtomRep

    fun parse(path: String) {

        var medicalConclusion : MedicalConclusion? = null
        val file = File(path)
        val document = SAXBuilder().build(file)

        val patientId = document.rootElement.getAttributeValue("id")
        val conclusionUuid = document.rootElement.getAttributeValue("uuid")

        document.rootElement.getChild("nosologies").getChildren("nosology").forEach { nosology ->
            val nosologyId = nosology.getAttributeValue("id")
            val nosologyName = nosology.getAttributeValue("name")

            nosology.getChild("therapies").getChildren("recommended").forEach { therapyType ->
                val therapyTypeName = therapyType.name

                therapyType.getChildren("therapy").forEach { therapy ->
                    val therapyName = therapy.getAttributeValue("name")

                    val therapyOrd = therapy.getAttributeValue("ord")

                    var therapyMessagePayload: String? = null
                    therapy.getChild("messages")?.getChildren("message")?.forEach { message ->
                        therapyMessagePayload = message.getChild("payload")?.textTrim
                    }

                    therapy.getChild("drugGroups")?.getChildren("drugGroup")?.forEach { drugGroup ->
                        val drugGroupId = drugGroup.getAttributeValue("id")
                        val drugGroupOrd = drugGroup.getAttributeValue("ord")

                        drugGroup.getChild("drugs")?.getChildren("drug")?.forEach { drug ->
                            val drugId = drug.getAttributeValue("id")
                            val drugName = drug.getAttributeValue("name")
                            val drugOrd = drug.getAttributeValue("ord")


                            var parsedConclusionAtom = ConclusionAtom(
                                drugName = drugName,
                                nosologyId = nosologyId,
                                nosologyName = nosologyName,
                                therapyOrd = therapyOrd,
                                therapyName = therapyName,
                                therapyTypeName = therapyTypeName,
                                drugOrd = drugOrd,
                                drugId = drugId,
                                drugGroupId = drugGroupId,
                                therapyMessagePayload = therapyMessagePayload,
                                drugGroupOrd = drugGroupOrd,
                                patientId = patientId,
                                conclusionUUID = conclusionUuid,
                                id = null
                            )

                            conclusionAtomRep.save(parsedConclusionAtom)

                        }
                    }
                }
            }
        }
    }
}
