package com.robinfood.core.exceptions

import java.lang.Exception

class AuthException(errorMessage: String?) : Exception(errorMessage)