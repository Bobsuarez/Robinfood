package com.robinfood.core.constants

import com.robinfood.core.constants.GlobalConstants.BRAZIL
import com.robinfood.core.constants.GlobalConstants.COLOMBIA
import java.io.File

enum class ReportConstants(val value: String, val path: String) {

    CO("_co", ""),
    BR("_br", ""),

    REPORT_HOME("reports", ""),

    PATH_DAILY_VOUCHER("dailyReportVoucher", ""),
    PATH_MAGNETIC_WITNESS_TAPE("magneticWitnessTape", ""),
    PATH_CUSTOMER_INVOICE("customerinvoice", ""),
    PATH_CUSTOMER_IMAGE("invoice", ""),

    // CUSTOMER_INVOICE
    REPORT_CUSTOMER_INVOICE_DIR(
        "customer_invoice",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator
    ),

    // CUSTOMER_INVOICE
    SUB_REPORT_CUSTOMER_INVOICE_SERVICE_DIR(
        "customer_invoice_services_detail",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator
    ),

    // CUSTOMER_INVOICE
    SUB_REPORT_CUSTOMER_INVOICE_PRODUCT_DIR(
        "customer_invoice_product_detail",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator
    ),

    // DAILY_REPORT_VOUCHER
    REPORT_DAILY_VOUCHER_DIR(
        "report_daily_voucher",
        REPORT_HOME.value + File.separator + PATH_DAILY_VOUCHER.value + File.separator
    ),
    SUB_REPORT_DAILY_VOUCHER_PAYMENT_DIR(
        "report_daily_voucher_payment_method_detail",
        REPORT_HOME.value + File.separator + PATH_DAILY_VOUCHER.value + File.separator
    ),
    SUB_REPORT_DAILY_VOUCHER_CATEGORY_DIR(
        "report_daily_voucher_category_detail",
        REPORT_HOME.value + File.separator + PATH_DAILY_VOUCHER.value + File.separator
    ),
    SUB_REPORT_DAILY_VOUCHER_CATEGORY_TITLE_DIR(
        "report_daily_voucher_category_title",
        REPORT_HOME.value + File.separator + PATH_DAILY_VOUCHER.value + File.separator
    ),
    SUB_REPORT_DAILY_VOUCHER_PAYMENT_METHOD_TITLE_DIR(
        "report_daily_voucher_payment_method_title",
        REPORT_HOME.value + File.separator + PATH_DAILY_VOUCHER.value + File.separator
    ),

    // MAGNETIC_WITNESS_TAPE_REPORT
    MAGNETIC_WITNESS_TAPE_REPORT_DIR(
        "report_magnetic_witness_tape",
        REPORT_HOME.value + File.separator + PATH_MAGNETIC_WITNESS_TAPE.value + File.separator
    ),
    SUB_REPORT_MAGNETIC_WITNESS_TAPE_POS_DIR(
        "report_magnetic_witness_tape_pos",
        REPORT_HOME.value + File.separator + PATH_MAGNETIC_WITNESS_TAPE.value + File.separator
    ),
    SUB_REPORT_MAGNETIC_WITNESS_TAPE_DETAIL_DIR(
        "report_magnetic_witness_tape_detail",
        REPORT_HOME.value + File.separator + PATH_MAGNETIC_WITNESS_TAPE.value + File.separator
    ),

    //Logos
    LOGO_JUSTBURGERS(
        "logo-justburgers.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_ORIGINAL(
        "logo-original.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_PECADO(
        "logo-pecado.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_PIXI(
        "logo-pixi.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_ROBINFOOD(
        "logo-robinfood.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_THECUT(
        "logo-thecut.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_TICKET(
        "logo-ticket.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_TREMENDO(
        "logo-tremendo.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_TRIBUTO(
        "logo-tributo.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_YINYAMM(
        "logo-yinyamm.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_BOWLO(
        "logo_bowlo.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator
                + PATH_CUSTOMER_IMAGE.value
                + File.separator
    ),
    LOGO_SAMBA(
        "logo_samba.png",
        REPORT_HOME.value + File.separator + PATH_CUSTOMER_INVOICE.value + File.separator
                + PATH_CUSTOMER_IMAGE.value
                + File.separator
    );

    companion object {
        open fun getUrlReport(companyId: Int, reportCons: ReportConstants): String {

            return when (companyId) {
                COLOMBIA -> reportCons.path + reportCons.value
                BRAZIL -> reportCons.path + reportCons.value + BR.value
                else -> reportCons.path + reportCons.value
            }
        }

        open fun getUrlLogo(brandId: Int): String {

            return when (brandId) {
                1 -> LOGO_TICKET.path + LOGO_TICKET.value
                2 -> LOGO_ORIGINAL.path + LOGO_ORIGINAL.value
                4 -> LOGO_PIXI.path + LOGO_PIXI.value
                5 -> LOGO_JUSTBURGERS.path + LOGO_JUSTBURGERS.value
                6 -> LOGO_TREMENDO.path + LOGO_TREMENDO.value
                7 -> LOGO_THECUT.path + LOGO_THECUT.value
                8 -> LOGO_PECADO.path + LOGO_PECADO.value
                9 -> LOGO_ROBINFOOD.path + LOGO_ROBINFOOD.value
                11 -> LOGO_TRIBUTO.path + LOGO_TRIBUTO.value
                12 -> LOGO_YINYAMM.path + LOGO_YINYAMM.value
                17 -> LOGO_SAMBA.path + LOGO_SAMBA.value
                18 -> LOGO_BOWLO.path + LOGO_BOWLO.value
                else -> LOGO_ROBINFOOD.path + LOGO_ROBINFOOD.value
            }
        }
    }

}