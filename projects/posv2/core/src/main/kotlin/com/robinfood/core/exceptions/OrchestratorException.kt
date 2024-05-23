package com.robinfood.core.exceptions

import java.lang.Exception

class OrchestratorException(val data: Any?, errorMessage: String) : Exception(errorMessage) {
    constructor(errorMessage: String) : this(null, errorMessage)
}