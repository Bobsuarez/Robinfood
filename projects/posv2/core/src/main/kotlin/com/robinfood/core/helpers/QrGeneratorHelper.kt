package com.robinfood.core.helpers

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageConfig
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.robinfood.core.constants.GlobalConstants.QR_HEIGHT
import com.robinfood.core.constants.GlobalConstants.QR_WIDTH
import java.io.ByteArrayOutputStream
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object QrGeneratorHelper {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun getQRCodeImage(content: String): ByteArray {

        val qrCodeWriter = QRCodeWriter()
        val bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT)

        val pngOutputStream = ByteArrayOutputStream()
        val con = MatrixToImageConfig(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con)

        return pngOutputStream.toByteArray()
    }
}
