package com.robinfood.core.helpers

import java.io.ByteArrayOutputStream
import java.io.InputStream
import net.sf.jasperreports.engine.JREmptyDataSource
import net.sf.jasperreports.engine.JasperExportManager.exportReportToPdfStream
import net.sf.jasperreports.engine.JasperFillManager
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

const val JASPER: String = ".jasper"

@Component
class JasperReportManagerHelper {
    fun export(
        reportPath: String,
        params: HashMap<String, Any>,
        dataReport: Collection<Any>?
    ): ByteArrayOutputStream {

        val stream = ByteArrayOutputStream()
        val resource = ClassPathResource(reportPath + JASPER)

        val inputStream = resource.inputStream
        var jasperPrint: JasperPrint?

        if (dataReport != null && !dataReport.isEmpty()) {
            jasperPrint = JasperFillManager.fillReport(
                inputStream,
                params,
                JRBeanCollectionDataSource(dataReport)
            )
        } else {
            jasperPrint = JasperFillManager.fillReport(
                inputStream,
                params,
                JREmptyDataSource()
            )
        }

        exportReportToPdfStream(jasperPrint, stream)

        return stream
    }

    fun convertCollectionToJRBeanCollectionDataSource(
        collectionReport: List<*>?
    ): JRBeanCollectionDataSource {
        return JRBeanCollectionDataSource(collectionReport)
    }

    fun setSubReportToParam(
        params: MutableMap<String, Any>,
        keyObject: String,
        urlSubReport: String
    ) {
        val resource = ClassPathResource(urlSubReport + JASPER)
        val inputStream = resource.inputStream
        params[keyObject] = inputStream

    }

    fun getSubReportToInputStream(urlSubReport: String): InputStream {
        val resource = ClassPathResource(urlSubReport + JASPER)
        return resource.inputStream
    }
}

