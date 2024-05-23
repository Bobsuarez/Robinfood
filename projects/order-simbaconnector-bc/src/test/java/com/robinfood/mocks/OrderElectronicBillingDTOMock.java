package com.robinfood.mocks;

import com.robinfood.dtos.response.OrderElectronicBillingDTO;
import com.robinfood.util.ObjectMapperSingleton;

import static com.robinfood.constants.simba.TercerosConstants.NIT;

public class OrderElectronicBillingDTOMock {

    private static final String payloadWithAceptedAndNotIssuable = """
            {
                "DatosDeControl": {
                    "FechaRespuesta": "2024-05-22T10:33:44.059-05:00",
                    "FechaRespuestaSpecified": true,
                    "CodigoRastreo": "dAlw0UKGtgLM3RhNOsgGe439NpoP7h9+8piC89A9jp8M4oXFGeq7q5OfoCZEv2S8xkCQkU42U7pWdV6kGBL0Nfksa0iInKwe7r7cPoZaflL/bEpzXF4PGd0OaTkCweJ7R5lUYzDMWbA02wvG5MwS3ZPjxhnH2RuntRyGsR6qun9HqBYImRucPLi+2KXFkAAHeeEd5JR1LBQE4tZ0FFeg==",
                    "SistemaSolicitante": {
                        "Tipo": "EXTERNO",
                        "Nombre": "RobinFood System",
                        "Version": "1.0.13"
                    },
                    "SistemaRespuesta": {
                        "Tipo": "SMB-TX",
                        "Abrev": "TX-TX",
                        "Nombre": "TX-4.0",
                        "Version": "4.0.7",
                        "Proceso": "TX-TRANSMISION-EDOC"
                    }
                },
                "TipoRespuesta": "UNITARIA",
                "RespuestaUnitaria": {
                    "EncabezadoRespuesta": {
                        "SolicitudAceptada": true,
                        "ExistenNovedades": true,
                        "ExistenInfracciones": false,
                        "ExistenNovedadesCriticas": true,
                        "ExistenExcepciones": false,
                        "TextoResumenCorto": "SMB-TX 4.0.7n---------------------------------------------------------------------------------------n1. Solicitud al Proveedor Tecnológico: TX-TRANSMISION-EDOCnn[ACEPTADA]nnEl documento fue aceptado pese a que existen novedades. nnResumen de cantidad de Novedades encontradas!n[ ----- ]tt[2] tADVERTENCIAn[ FALLÓ ]tt[9] tRESPUESTA DIANnn",
                        "TextoResumenNovsCrit": "Este es un resumen de las novedades más importantes y críticas para su lectura rápida. nNo reemplaza a las novedades expuestas en el contenido de ésta misma respuestann[ADVERTENCIA - ADVERTENCIA] Validación de homologaciones, listas y validaciones lógicas omitidas por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marchann[ADVERTENCIA - ADVERTENCIA] Validación de campos y estructuras requeridas omitida por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marchann[RESPUESTA DIAN - DIAN STATUS CODE] 99nn[RESPUESTA DIAN - DIAN STATUS DESCR] Validación contiene errores en campos mandatorios.nn[RESPUESTA DIAN - DIAN STATUS MESSAGE] Documento con errores en campos mandatorios.nn[RESPUESTA DIAN - DIAN DOC KEY] 6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985bnn[RESPUESTA DIAN - DIAN DOC FILENAME] fv09011313170182400000309nn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E1 Regla: DEAB10b, Rechazo: El prefijo no corresponde al prefijo de la autorización de numeraciónnn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E2 Regla: DEAB12b, Rechazo: Valor final del rango de numeración informado no corresponde a un valor final de los rangos vigentes para el contribuyente emisor.nn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E3 Regla: DEAD10, Notificación: Debe ser informada la hora en una zona horaria -5, que es la zona horaria oficial de Colombia.nn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E4 Regla: DEAJ40, Notificación: El contenido de este elemento no corresponde a un contenido válido según lista correspondienten",
                        "EstadosGenerales": {
                            "UltimoEstadoSys": "2600",
                            "UltimoProcesoSys": "2600|PROCESAR-DOCS-UBL-RESPONSE|RSP.GeneracionRespuesta",
                            "IndicadorProceso": [
                                {
                                    "Nombre": "IND_ENVIADO_DIAN",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_CONTINGENCIA_DIAN",
                                    "Value": false
                                },
                                {
                                    "Nombre": "IND_ENVIO_SINCRONO",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_ACEPTADO_SMB",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_VALIDADO_DIAN",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_ACEPTADO_DIAN",
                                    "Value": false
                                },
                                {
                                    "Nombre": "IND_EMISIBLE",
                                    "Value": false
                                },
                                {
                                    "Nombre": "IND_REENVIABLE",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_INCONSISTENCIA",
                                    "Value": false
                                }
                            ]
                        },
                        "Novedad": [
                            {
                                "TipoLogCodigo": "ADVERTENCIA",
                                "TipoLogTexto": "Mensajes de Advertencia del Sistema. Por favor revisar",
                                "IndicaFallo": false,
                                "IndicaFalloSpecified": true,
                                "NivelImportancia": "7000",
                                "CantMensajes": "2",
                                "Mensaje": [
                                    {
                                        "Nro": "1",
                                        "Tipo": "ADVERTENCIA",
                                        "Value": "Validación de homologaciones, listas y validaciones lógicas omitidas por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marcha"
                                    },
                                    {
                                        "Nro": "2",
                                        "Tipo": "ADVERTENCIA",
                                        "Value": "Validación de campos y estructuras requeridas omitida por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marcha"
                                    }
                                ],
                                "Adicional": "Proc: 400|COMPLEMENTO-DATOS-1|CD1.IndicadoresPorPeticion"
                            },
                            {
                                "TipoLogCodigo": "RESPUESTA DIAN",
                                "TipoLogTexto": "Mensajes que se generaron por parte de la DIAN después de enviar la factura. Por favor revise",
                                "IndicaFallo": true,
                                "IndicaFalloSpecified": true,
                                "NivelImportancia": "7000",
                                "CantMensajes": "9",
                                "Mensaje": [
                                    {
                                        "Nro": "1",
                                        "Tipo": "DIAN STATUS CODE",
                                        "Value": "99"
                                    },
                                    {
                                        "Nro": "2",
                                        "Tipo": "DIAN STATUS DESCR",
                                        "Value": "Validación contiene errores en campos mandatorios."
                                    },
                                    {
                                        "Nro": "3",
                                        "Tipo": "DIAN STATUS MESSAGE",
                                        "Value": "Documento con errores en campos mandatorios."
                                    },
                                    {
                                        "Nro": "4",
                                        "Tipo": "DIAN DOC KEY",
                                        "Value": "6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b"
                                    },
                                    {
                                        "Nro": "5",
                                        "Tipo": "DIAN DOC FILENAME",
                                        "Value": "fv09011313170182400000309"
                                    },
                                    {
                                        "Nro": "6",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E1 Regla: DEAB10b, Rechazo: El prefijo no corresponde al prefijo de la autorización de numeración"
                                    },
                                    {
                                        "Nro": "7",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E2 Regla: DEAB12b, Rechazo: Valor final del rango de numeración informado no corresponde a un valor final de los rangos vigentes para el contribuyente emisor."
                                    },
                                    {
                                        "Nro": "8",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E3 Regla: DEAD10, Notificación: Debe ser informada la hora en una zona horaria -5, que es la zona horaria oficial de Colombia."
                                    },
                                    {
                                        "Nro": "9",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E4 Regla: DEAJ40, Notificación: El contenido de este elemento no corresponde a un contenido válido según lista correspondiente"
                                    }
                                ],
                                "Adicional": "Proc: 2500|PROCESAR-RESPUESTA-DIAN|TxDian.ProcesarRespuesta"
                            }
                        ],
                        "Etapa": [
                            {
                                "Orden": "1",
                                "Codigo": "RECEPCION",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:38.413-05:00",
                                "Mensaje": ""
                            },
                            {
                                "Orden": "2",
                                "Codigo": "PROCESO",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:41.32-05:00",
                                "Mensaje": ""
                            },
                            {
                                "Orden": "3",
                                "Codigo": "FIRMA",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:42.981-05:00",
                                "Mensaje": ""
                            },
                            {
                                "Orden": "5",
                                "Codigo": "ENVIODIAN",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:43.753-05:00",
                                "Mensaje": "[C=99] [D=Validación contiene errores en campos mandatorios.] [M=Documento con errores en campos mandatorios.] "
                            },
                            {
                                "Orden": "5",
                                "Codigo": "VALIDDIAN",
                                "Estado": "99",
                                "FechaHora": "2024-05-22T10:33:43.753-05:00",
                                "Mensaje": "[C=99] [D=Validación contiene errores en campos mandatorios.] [M=Documento con errores en campos mandatorios.] "
                            }
                        ]
                    },
                    "DocElectronicoExtendido": {
                        "DocumentoValido": false,
                        "DatosBasicos": {
                            "NitEmisor": "901131317",
                            "Prefijo": "SETT",
                            "Numero": "42137",
                            "CUDE": "6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b",
                            "FechaYHoraEmision": "2024-05-22T15:33:37.0915905-05:00",
                            "FechaYHoraEmisionSpecified": true
                        },
                        "DatosAdicionales": {
                            "DatoEmisor": [
                                {
                                    "Nombre": "TIP_AMBS",
                                    "Valor": "2"
                                }
                            ]
                        },
                        "ArchivoPrincipal": [
                            {
                                "UID": "XMLF",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "fv09011313170182400000309.xml",
                                "RutaAux": "XML_FIRM",
                                "ClaseGenerica": "SMB-TX"
                            },
                            {
                                "UID": "APPR",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "ar09011313170182400000309.xml",
                                "RutaAux": "XML_APPR",
                                "ClaseGenerica": "SMB-TX"
                            },
                            {
                                "UID": "ATTD",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "ad09011313170182400000309.xml",
                                "RutaAux": "XML_ATTD",
                                "ClaseGenerica": "SMB-TX"
                            }
                        ],
                        "ArchivoAdicional": [
                            {
                                "UID": "XMLS",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "fv09011313170182400000309.xml",
                                "RutaAux": "XML_SINF",
                                "ClaseGenerica": "SMB-TX"
                            },
                            {
                                "UID": "EDOC",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "fv09011313170182400000309_EDOC_20240522_103338_592.xml",
                                "RutaAux": "XML_PETI",
                                "ClaseGenerica": "SMB-TX"
                            }
                        ]
                    }
                }
            }
                        
            """;

    private static final String payloadWithAceptedAndIssuable = """
            {
                "DatosDeControl": {
                    "FechaRespuesta": "2024-05-22T10:33:44.059-05:00",
                    "FechaRespuestaSpecified": true,
                    "CodigoRastreo": "dAlw0UKGtgLM3RhNOsgGe439NpoP7h9+8piC89A9jp8M4oXFGeq7q5OfoCZEv2S8xkCQkU42U7pWdV6kGBL0Nfksa0iInKwe7r7cPoZaflL/bEpzXF4PGd0OaTkCweJ7R5lUYzDMWbA02wvG5MwS3ZPjxhnH2RuntRyGsR6qun9HqBYImRucPLi+2KXFkAAHeeEd5JR1LBQE4tZ0FFeg==",
                    "SistemaSolicitante": {
                        "Tipo": "EXTERNO",
                        "Nombre": "RobinFood System",
                        "Version": "1.0.13"
                    },
                    "SistemaRespuesta": {
                        "Tipo": "SMB-TX",
                        "Abrev": "TX-TX",
                        "Nombre": "TX-4.0",
                        "Version": "4.0.7",
                        "Proceso": "TX-TRANSMISION-EDOC"
                    }
                },
                "TipoRespuesta": "UNITARIA",
                "RespuestaUnitaria": {
                    "EncabezadoRespuesta": {
                        "SolicitudAceptada": true,
                        "ExistenNovedades": true,
                        "ExistenInfracciones": false,
                        "ExistenNovedadesCriticas": true,
                        "ExistenExcepciones": false,
                        "TextoResumenCorto": "SMB-TX 4.0.7n---------------------------------------------------------------------------------------n1. Solicitud al Proveedor Tecnológico: TX-TRANSMISION-EDOCnn[ACEPTADA]nnEl documento fue aceptado pese a que existen novedades. nnResumen de cantidad de Novedades encontradas!n[ ----- ]tt[2] tADVERTENCIAn[ FALLÓ ]tt[9] tRESPUESTA DIANnn",
                        "TextoResumenNovsCrit": "Este es un resumen de las novedades más importantes y críticas para su lectura rápida. nNo reemplaza a las novedades expuestas en el contenido de ésta misma respuestann[ADVERTENCIA - ADVERTENCIA] Validación de homologaciones, listas y validaciones lógicas omitidas por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marchann[ADVERTENCIA - ADVERTENCIA] Validación de campos y estructuras requeridas omitida por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marchann[RESPUESTA DIAN - DIAN STATUS CODE] 99nn[RESPUESTA DIAN - DIAN STATUS DESCR] Validación contiene errores en campos mandatorios.nn[RESPUESTA DIAN - DIAN STATUS MESSAGE] Documento con errores en campos mandatorios.nn[RESPUESTA DIAN - DIAN DOC KEY] 6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985bnn[RESPUESTA DIAN - DIAN DOC FILENAME] fv09011313170182400000309nn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E1 Regla: DEAB10b, Rechazo: El prefijo no corresponde al prefijo de la autorización de numeraciónnn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E2 Regla: DEAB12b, Rechazo: Valor final del rango de numeración informado no corresponde a un valor final de los rangos vigentes para el contribuyente emisor.nn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E3 Regla: DEAD10, Notificación: Debe ser informada la hora en una zona horaria -5, que es la zona horaria oficial de Colombia.nn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E4 Regla: DEAJ40, Notificación: El contenido de este elemento no corresponde a un contenido válido según lista correspondienten",
                        "EstadosGenerales": {
                            "UltimoEstadoSys": "2600",
                            "UltimoProcesoSys": "2600|PROCESAR-DOCS-UBL-RESPONSE|RSP.GeneracionRespuesta",
                            "IndicadorProceso": [
                                {
                                    "Nombre": "IND_ENVIADO_DIAN",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_CONTINGENCIA_DIAN",
                                    "Value": false
                                },
                                {
                                    "Nombre": "IND_ENVIO_SINCRONO",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_ACEPTADO_SMB",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_VALIDADO_DIAN",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_ACEPTADO_DIAN",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_EMISIBLE",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_REENVIABLE",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_INCONSISTENCIA",
                                    "Value": false
                                }
                            ]
                        },
                        "Novedad": [
                            {
                                "TipoLogCodigo": "ADVERTENCIA",
                                "TipoLogTexto": "Mensajes de Advertencia del Sistema. Por favor revisar",
                                "IndicaFallo": false,
                                "IndicaFalloSpecified": true,
                                "NivelImportancia": "7000",
                                "CantMensajes": "2",
                                "Mensaje": [
                                    {
                                        "Nro": "1",
                                        "Tipo": "ADVERTENCIA",
                                        "Value": "Validación de homologaciones, listas y validaciones lógicas omitidas por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marcha"
                                    },
                                    {
                                        "Nro": "2",
                                        "Tipo": "ADVERTENCIA",
                                        "Value": "Validación de campos y estructuras requeridas omitida por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marcha"
                                    }
                                ],
                                "Adicional": "Proc: 400|COMPLEMENTO-DATOS-1|CD1.IndicadoresPorPeticion"
                            },
                            {
                                "TipoLogCodigo": "RESPUESTA DIAN",
                                "TipoLogTexto": "Mensajes que se generaron por parte de la DIAN después de enviar la factura. Por favor revise",
                                "IndicaFallo": true,
                                "IndicaFalloSpecified": true,
                                "NivelImportancia": "7000",
                                "CantMensajes": "9",
                                "Mensaje": [
                                    {
                                        "Nro": "1",
                                        "Tipo": "DIAN STATUS CODE",
                                        "Value": "99"
                                    },
                                    {
                                        "Nro": "2",
                                        "Tipo": "DIAN STATUS DESCR",
                                        "Value": "Validación contiene errores en campos mandatorios."
                                    },
                                    {
                                        "Nro": "3",
                                        "Tipo": "DIAN STATUS MESSAGE",
                                        "Value": "Documento con errores en campos mandatorios."
                                    },
                                    {
                                        "Nro": "4",
                                        "Tipo": "DIAN DOC KEY",
                                        "Value": "6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b"
                                    },
                                    {
                                        "Nro": "5",
                                        "Tipo": "DIAN DOC FILENAME",
                                        "Value": "fv09011313170182400000309"
                                    },
                                    {
                                        "Nro": "6",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E1 Regla: DEAB10b, Rechazo: El prefijo no corresponde al prefijo de la autorización de numeración"
                                    },
                                    {
                                        "Nro": "7",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E2 Regla: DEAB12b, Rechazo: Valor final del rango de numeración informado no corresponde a un valor final de los rangos vigentes para el contribuyente emisor."
                                    },
                                    {
                                        "Nro": "8",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E3 Regla: DEAD10, Notificación: Debe ser informada la hora en una zona horaria -5, que es la zona horaria oficial de Colombia."
                                    },
                                    {
                                        "Nro": "9",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E4 Regla: DEAJ40, Notificación: El contenido de este elemento no corresponde a un contenido válido según lista correspondiente"
                                    }
                                ],
                                "Adicional": "Proc: 2500|PROCESAR-RESPUESTA-DIAN|TxDian.ProcesarRespuesta"
                            }
                        ],
                        "Etapa": [
                            {
                                "Orden": "1",
                                "Codigo": "RECEPCION",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:38.413-05:00",
                                "Mensaje": ""
                            },
                            {
                                "Orden": "2",
                                "Codigo": "PROCESO",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:41.32-05:00",
                                "Mensaje": ""
                            },
                            {
                                "Orden": "3",
                                "Codigo": "FIRMA",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:42.981-05:00",
                                "Mensaje": ""
                            },
                            {
                                "Orden": "5",
                                "Codigo": "ENVIODIAN",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:43.753-05:00",
                                "Mensaje": "[C=99] [D=Validación contiene errores en campos mandatorios.] [M=Documento con errores en campos mandatorios.] "
                            },
                            {
                                "Orden": "5",
                                "Codigo": "VALIDDIAN",
                                "Estado": "99",
                                "FechaHora": "2024-05-22T10:33:43.753-05:00",
                                "Mensaje": "[C=99] [D=Validación contiene errores en campos mandatorios.] [M=Documento con errores en campos mandatorios.] "
                            }
                        ]
                    },
                    "DocElectronicoExtendido": {
                        "DocumentoValido": false,
                        "DatosBasicos": {
                            "NitEmisor": "901131317",
                            "Prefijo": "SETT",
                            "Numero": "42137",
                            "CUDE": "6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b",
                            "FechaYHoraEmision": "2024-05-22T15:33:37.0915905-05:00",
                            "FechaYHoraEmisionSpecified": true
                        },
                        "DatosAdicionales": {
                            "DatoEmisor": [
                                {
                                    "Nombre": "TIP_AMBS",
                                    "Valor": "2"
                                }
                            ]
                        },
                        "ArchivoPrincipal": [
                            {
                                "UID": "XMLF",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "fv09011313170182400000309.xml",
                                "RutaAux": "XML_FIRM",
                                "ClaseGenerica": "SMB-TX"
                            },
                            {
                                "UID": "APPR",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "ar09011313170182400000309.xml",
                                "RutaAux": "XML_APPR",
                                "ClaseGenerica": "SMB-TX"
                            },
                            {
                                "UID": "ATTD",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "ad09011313170182400000309.xml",
                                "RutaAux": "XML_ATTD",
                                "ClaseGenerica": "SMB-TX"
                            }
                        ],
                        "ArchivoAdicional": [
                            {
                                "UID": "XMLS",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "fv09011313170182400000309.xml",
                                "RutaAux": "XML_SINF",
                                "ClaseGenerica": "SMB-TX"
                            },
                            {
                                "UID": "EDOC",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "fv09011313170182400000309_EDOC_20240522_103338_592.xml",
                                "RutaAux": "XML_PETI",
                                "ClaseGenerica": "SMB-TX"
                            }
                        ]
                    }
                }
            }
            """;
    private static final String payloadWithAceptedAndIssuableNotFound = """
            {
                "DatosDeControl": {
                    "FechaRespuesta": "2024-05-22T10:33:44.059-05:00",
                    "FechaRespuestaSpecified": true,
                    "CodigoRastreo": "dAlw0UKGtgLM3RhNOsgGe439NpoP7h9+8piC89A9jp8M4oXFGeq7q5OfoCZEv2S8xkCQkU42U7pWdV6kGBL0Nfksa0iInKwe7r7cPoZaflL/bEpzXF4PGd0OaTkCweJ7R5lUYzDMWbA02wvG5MwS3ZPjxhnH2RuntRyGsR6qun9HqBYImRucPLi+2KXFkAAHeeEd5JR1LBQE4tZ0FFeg==",
                    "SistemaSolicitante": {
                        "Tipo": "EXTERNO",
                        "Nombre": "RobinFood System",
                        "Version": "1.0.13"
                    },
                    "SistemaRespuesta": {
                        "Tipo": "SMB-TX",
                        "Abrev": "TX-TX",
                        "Nombre": "TX-4.0",
                        "Version": "4.0.7",
                        "Proceso": "TX-TRANSMISION-EDOC"
                    }
                },
                "TipoRespuesta": "UNITARIA",
                "RespuestaUnitaria": {
                    "EncabezadoRespuesta": {
                        "SolicitudAceptada": true,
                        "ExistenNovedades": true,
                        "ExistenInfracciones": false,
                        "ExistenNovedadesCriticas": true,
                        "ExistenExcepciones": false,
                        "TextoResumenCorto": "SMB-TX 4.0.7n---------------------------------------------------------------------------------------n1. Solicitud al Proveedor Tecnológico: TX-TRANSMISION-EDOCnn[ACEPTADA]nnEl documento fue aceptado pese a que existen novedades. nnResumen de cantidad de Novedades encontradas!n[ ----- ]tt[2] tADVERTENCIAn[ FALLÓ ]tt[9] tRESPUESTA DIANnn",
                        "TextoResumenNovsCrit": "Este es un resumen de las novedades más importantes y críticas para su lectura rápida. nNo reemplaza a las novedades expuestas en el contenido de ésta misma respuestann[ADVERTENCIA - ADVERTENCIA] Validación de homologaciones, listas y validaciones lógicas omitidas por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marchann[ADVERTENCIA - ADVERTENCIA] Validación de campos y estructuras requeridas omitida por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marchann[RESPUESTA DIAN - DIAN STATUS CODE] 99nn[RESPUESTA DIAN - DIAN STATUS DESCR] Validación contiene errores en campos mandatorios.nn[RESPUESTA DIAN - DIAN STATUS MESSAGE] Documento con errores en campos mandatorios.nn[RESPUESTA DIAN - DIAN DOC KEY] 6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985bnn[RESPUESTA DIAN - DIAN DOC FILENAME] fv09011313170182400000309nn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E1 Regla: DEAB10b, Rechazo: El prefijo no corresponde al prefijo de la autorización de numeraciónnn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E2 Regla: DEAB12b, Rechazo: Valor final del rango de numeración informado no corresponde a un valor final de los rangos vigentes para el contribuyente emisor.nn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E3 Regla: DEAD10, Notificación: Debe ser informada la hora en una zona horaria -5, que es la zona horaria oficial de Colombia.nn[RESPUESTA DIAN - DIAN ERROR MESSAGE] E4 Regla: DEAJ40, Notificación: El contenido de este elemento no corresponde a un contenido válido según lista correspondienten",
                        "EstadosGenerales": {
                            "UltimoEstadoSys": "2600",
                            "UltimoProcesoSys": "2600|PROCESAR-DOCS-UBL-RESPONSE|RSP.GeneracionRespuesta",
                            "IndicadorProceso": [
                                {
                                    "Nombre": "IND_ENVIADO_DIAN",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_CONTINGENCIA_DIAN",
                                    "Value": false
                                },
                                {
                                    "Nombre": "IND_ENVIO_SINCRONO",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_ACEPTADO_SMB",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_VALIDADO_DIAN",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_ACEPTADO_DIAN",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_REENVIABLE",
                                    "Value": true
                                },
                                {
                                    "Nombre": "IND_INCONSISTENCIA",
                                    "Value": false
                                }
                            ]
                        },
                        "Novedad": [
                            {
                                "TipoLogCodigo": "ADVERTENCIA",
                                "TipoLogTexto": "Mensajes de Advertencia del Sistema. Por favor revisar",
                                "IndicaFallo": false,
                                "IndicaFalloSpecified": true,
                                "NivelImportancia": "7000",
                                "CantMensajes": "2",
                                "Mensaje": [
                                    {
                                        "Nro": "1",
                                        "Tipo": "ADVERTENCIA",
                                        "Value": "Validación de homologaciones, listas y validaciones lógicas omitidas por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marcha"
                                    },
                                    {
                                        "Nro": "2",
                                        "Tipo": "ADVERTENCIA",
                                        "Value": "Validación de campos y estructuras requeridas omitida por el emisor.nPrecaución!. Esta característica puede deshabilitarse futuro y entonces se habilitarán sin previo aviso todas las validaciones. nUso del salto!. Este Skip se otorga para saltar las validaciones proactivas del proveedor tecnológico respecto a las de la DIAN en caso de cambios de reglas sobre la marcha"
                                    }
                                ],
                                "Adicional": "Proc: 400|COMPLEMENTO-DATOS-1|CD1.IndicadoresPorPeticion"
                            },
                            {
                                "TipoLogCodigo": "RESPUESTA DIAN",
                                "TipoLogTexto": "Mensajes que se generaron por parte de la DIAN después de enviar la factura. Por favor revise",
                                "IndicaFallo": true,
                                "IndicaFalloSpecified": true,
                                "NivelImportancia": "7000",
                                "CantMensajes": "9",
                                "Mensaje": [
                                    {
                                        "Nro": "1",
                                        "Tipo": "DIAN STATUS CODE",
                                        "Value": "99"
                                    },
                                    {
                                        "Nro": "2",
                                        "Tipo": "DIAN STATUS DESCR",
                                        "Value": "Validación contiene errores en campos mandatorios."
                                    },
                                    {
                                        "Nro": "3",
                                        "Tipo": "DIAN STATUS MESSAGE",
                                        "Value": "Documento con errores en campos mandatorios."
                                    },
                                    {
                                        "Nro": "4",
                                        "Tipo": "DIAN DOC KEY",
                                        "Value": "6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b"
                                    },
                                    {
                                        "Nro": "5",
                                        "Tipo": "DIAN DOC FILENAME",
                                        "Value": "fv09011313170182400000309"
                                    },
                                    {
                                        "Nro": "6",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E1 Regla: DEAB10b, Rechazo: El prefijo no corresponde al prefijo de la autorización de numeración"
                                    },
                                    {
                                        "Nro": "7",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E2 Regla: DEAB12b, Rechazo: Valor final del rango de numeración informado no corresponde a un valor final de los rangos vigentes para el contribuyente emisor."
                                    },
                                    {
                                        "Nro": "8",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E3 Regla: DEAD10, Notificación: Debe ser informada la hora en una zona horaria -5, que es la zona horaria oficial de Colombia."
                                    },
                                    {
                                        "Nro": "9",
                                        "Tipo": "DIAN ERROR MESSAGE",
                                        "Value": "E4 Regla: DEAJ40, Notificación: El contenido de este elemento no corresponde a un contenido válido según lista correspondiente"
                                    }
                                ],
                                "Adicional": "Proc: 2500|PROCESAR-RESPUESTA-DIAN|TxDian.ProcesarRespuesta"
                            }
                        ],
                        "Etapa": [
                            {
                                "Orden": "1",
                                "Codigo": "RECEPCION",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:38.413-05:00",
                                "Mensaje": ""
                            },
                            {
                                "Orden": "2",
                                "Codigo": "PROCESO",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:41.32-05:00",
                                "Mensaje": ""
                            },
                            {
                                "Orden": "3",
                                "Codigo": "FIRMA",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:42.981-05:00",
                                "Mensaje": ""
                            },
                            {
                                "Orden": "5",
                                "Codigo": "ENVIODIAN",
                                "Estado": "100",
                                "FechaHora": "2024-05-22T10:33:43.753-05:00",
                                "Mensaje": "[C=99] [D=Validación contiene errores en campos mandatorios.] [M=Documento con errores en campos mandatorios.] "
                            },
                            {
                                "Orden": "5",
                                "Codigo": "VALIDDIAN",
                                "Estado": "99",
                                "FechaHora": "2024-05-22T10:33:43.753-05:00",
                                "Mensaje": "[C=99] [D=Validación contiene errores en campos mandatorios.] [M=Documento con errores en campos mandatorios.] "
                            }
                        ]
                    },
                    "DocElectronicoExtendido": {
                        "DocumentoValido": false,
                        "DatosBasicos": {
                            "NitEmisor": "901131317",
                            "Prefijo": "SETT",
                            "Numero": "42137",
                            "CUDE": "6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b",
                            "FechaYHoraEmision": "2024-05-22T15:33:37.0915905-05:00",
                            "FechaYHoraEmisionSpecified": true
                        },
                        "DatosAdicionales": {
                            "DatoEmisor": [
                                {
                                    "Nombre": "TIP_AMBS",
                                    "Valor": "2"
                                }
                            ]
                        },
                        "ArchivoPrincipal": [
                            {
                                "UID": "XMLF",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "fv09011313170182400000309.xml",
                                "RutaAux": "XML_FIRM",
                                "ClaseGenerica": "SMB-TX"
                            },
                            {
                                "UID": "APPR",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "ar09011313170182400000309.xml",
                                "RutaAux": "XML_APPR",
                                "ClaseGenerica": "SMB-TX"
                            },
                            {
                                "UID": "ATTD",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "ad09011313170182400000309.xml",
                                "RutaAux": "XML_ATTD",
                                "ClaseGenerica": "SMB-TX"
                            }
                        ],
                        "ArchivoAdicional": [
                            {
                                "UID": "XMLS",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "fv09011313170182400000309.xml",
                                "RutaAux": "XML_SINF",
                                "ClaseGenerica": "SMB-TX"
                            },
                            {
                                "UID": "EDOC",
                                "FechaHoraSpecified": false,
                                "FechaMaximaCustodiaSpecified": false,
                                "FechaExpiracionSpecified": false,
                                "HoraExpiracionSpecified": false,
                                "NombreArchivo": "fv09011313170182400000309_EDOC_20240522_103338_592.xml",
                                "RutaAux": "XML_PETI",
                                "ClaseGenerica": "SMB-TX"
                            }
                        ]
                    }
                }
            }
            """;


    public static OrderElectronicBillingDTO getDefaultAcceptedAndNotIssuable() {

        return OrderElectronicBillingDTO.builder()
                .statusCode(400)
                .prefijo("SETR")
                .cufe("6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b")
                .qr("https://catalogo-vpfe-hab.dian.gov.co/document/searchqr?documentkey=6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b")
                .numero("12345")
                .nitEmisor("91111")
                .fechaYHoraEmision("2024-05-22T15:33:37.0915905-05:00")
                .fechaYHoraEmisionSpecified(Boolean.TRUE)
                .payload(ObjectMapperSingleton.stringToJsonNode(payloadWithAceptedAndNotIssuable))
                .tipoDocumento(NIT)
                .build();
    }

    public static OrderElectronicBillingDTO getDefaultAcceptedAndIssuable() {

        return OrderElectronicBillingDTO.builder()
                .statusCode(200)
                .prefijo("SETR")
                .cufe("6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b")
                .qr("https://catalogo-vpfe-hab.dian.gov.co/document/searchqr?documentkey=6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b")
                .numero("12345")
                .nitEmisor("91111")
                .fechaYHoraEmision("2024-05-22T15:33:37.0915905-05:00")
                .fechaYHoraEmisionSpecified(Boolean.TRUE)
                .payload(ObjectMapperSingleton.stringToJsonNode(payloadWithAceptedAndIssuable))
                .tipoDocumento(NIT)
                .build();
    }

    public static OrderElectronicBillingDTO getDefaultAcceptedAndIssuableNotFound() {

        return OrderElectronicBillingDTO.builder()
                .statusCode(500)
                .prefijo("SETR")
                .cufe("6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b")
                .qr("https://catalogo-vpfe-hab.dian.gov.co/document/searchqr?documentkey=6e68b1137f7d1dc57f100a33c937dddb066f301271db784622f9bf1e4f5f5acd258197a7bab2286e810263aeef9c985b")
                .numero("12345")
                .nitEmisor("91111")
                .fechaYHoraEmision("2024-05-22T15:33:37.0915905-05:00")
                .fechaYHoraEmisionSpecified(Boolean.TRUE)
                .payload(ObjectMapperSingleton.stringToJsonNode(payloadWithAceptedAndIssuableNotFound))
                .tipoDocumento(NIT)
                .build();
    }
}
