package com.medicbk.plainconcl.util

import com.medicbk.plainconcl.ParserPC
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path


@Component
class DirectoryUtils {

    @Autowired
    private lateinit var parser: ParserPC


    @Value("\${path.to.files}")
    private val directoryPath: String = ""

    /**
     * Processes XML files in the specified directory.
     * @param directoryPath The path of the directory containing XML files.
     */

    @EventListener(ApplicationReadyEvent::class)
    fun processXmlFilesInDirectory() {
        try {
            Files.walk(Path.of(directoryPath)).use { files ->
                files
                    .filter { path: Path ->
                        Files.isRegularFile(
                            path
                        )
                    }
                    .filter { filePath: Path ->
                        isXmlFile(
                            filePath
                        )
                    }.forEach { path -> parser.parse(path.toString()) }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun isXmlFile(filePath: Path): Boolean {
        val fileName: String = filePath.getFileName().toString()
        return fileName.endsWith(".xml")
    }
}