package com.robinfood.core.dtos.report

class ReportResponseDTO private constructor(
    val reportByteArray: String?,
    val fileName: String?
) {
    class Builder(
        private var reportByteArray: String? = null,
        private var fileName: String? = null
    ) {
        fun reportByteArray(reportByteArray: String) = apply { this.reportByteArray = reportByteArray }
        fun fileName(fileName: String) = apply { this.fileName = fileName }

        fun build(): ReportResponseDTO {
            return ReportResponseDTO(
                reportByteArray, fileName
            )
        }
    }
}