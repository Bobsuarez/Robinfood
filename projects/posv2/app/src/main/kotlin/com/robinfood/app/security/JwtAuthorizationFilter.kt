package com.robinfood.app.security

import com.robinfood.core.constants.APIConstants.UNAUTHORIZED_MESSAGE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_LOCALE
import com.robinfood.core.constants.GlobalConstants.DEFAULT_STRING_VALUE
import com.robinfood.core.dtos.ApiResponseDTO
import com.robinfood.core.dtos.UserDTO
import com.robinfood.core.exceptions.AuthException
import com.robinfood.core.extensions.toJson
import com.robinfood.core.extensions.tryCast
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.apache.http.entity.ContentType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.Locale
import java.util.function.Consumer
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.HashSet

/**
 * Class that verifies the JWT Token of every single request and checks if the user has the correct
 * permissions
 */
class JwtAuthorizationFilter(
    private val customEnvironment: Environment
): OncePerRequestFilter() {

    private val log: Logger = LoggerFactory.getLogger(JwtAuthorizationFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            if (!existJwtToken(request)) {
                denyRequest(response)
                return
            }
            val tokenPrefix = customEnvironment.getRequiredProperty("jwt-token-prefix")
            val jwtToken = request
                .getHeader(HttpHeaders.AUTHORIZATION)
                .replace(tokenPrefix, DEFAULT_STRING_VALUE)
            val claims = getTokenClaims(jwtToken)
            if (claims == null) {
                denyRequest(response)
                return
            }
            val auth = authenticateUser(claims)
            auth.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = auth
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            log.debug(e.localizedMessage)
            denyRequest(response)
        }
    }

    /**
     * Method called when there is a problem authenticating the user and the request cannot be processed
     * @param response the response that will be returned to the client
     */
    private fun denyRequest(response: HttpServletResponse) {
        try {
            response.writer.use { writer ->
                val apiResponse = ApiResponseDTO<String>(
                    null,
                    UNAUTHORIZED_MESSAGE,
                    DEFAULT_LOCALE
                )
                val backOfficeResponse = apiResponse.toJson()
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.contentType = ContentType.APPLICATION_JSON.mimeType
                writer.write(backOfficeResponse)
                writer.flush()
            }
        } catch (e: IOException) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }
    }

    /**
     * Sets the grant access to the current user
     * @param claims the role, module and audience this user will have
     * @return the authenticated user with these properties
     */
    private fun authenticateUser(claims: Claims): UsernamePasswordAuthenticationToken {
        val grantedValues = getGrantedValues(claims)
        val user = UserDTO(claims.subject, DEFAULT_STRING_VALUE, grantedValues)
        return UsernamePasswordAuthenticationToken(user, DEFAULT_STRING_VALUE, grantedValues)
    }

    /**
     * Checks if the jwt token exists in the request
     * @param request the request made by the connecting client
     * @return true if the jwt token prefix exists, false otherwise
     */
    private fun existJwtToken(request: HttpServletRequest): Boolean {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        val tokenPrefix = customEnvironment.getRequiredProperty("jwt-token-prefix")
        return !(authHeader == null || !authHeader.startsWith(tokenPrefix))
    }

    /**
     * Gets the granted values a user will have, such as the current role and the permissions
     * @param claims contains the permissions of the current user
     * @return a set of authorities for the current user
     */
    private fun getGrantedValues(claims: Claims): Set<SimpleGrantedAuthority> {
        val authorities: MutableSet<SimpleGrantedAuthority> = HashSet()
        try {
            val scopes = claims["per"].tryCast<List<String>>()
            if (scopes != null) {
                scopes.forEach(
                    Consumer { scope: String ->
                        authorities.add(
                            SimpleGrantedAuthority("ROLE_" + scope.toUpperCase(Locale.getDefault()))
                        )
                    }
                )
            } else {
                throw AuthException("Scopes variable is null")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return authorities
    }

    /**
     * Gets the authorization properties a JWT token must have
     * @param jwtToken the current JWT token
     * @return returns the authorization claims
     */
    private fun getTokenClaims(jwtToken: String): Claims? {
        val tokenSecret = customEnvironment.getRequiredProperty("jwt.token.secret")
        val tokenAudValues = customEnvironment.getRequiredProperty("jwt-token-aud")
        val tokenAudValidValues = listOf(*tokenAudValues.split(",".toRegex()).toTypedArray())
        val claims = Jwts
            .parser()
            .setSigningKey(tokenSecret.toByteArray(StandardCharsets.ISO_8859_1))
            .parseClaimsJws(jwtToken)
            .body
        val tokenAud = claims.audience
        if (tokenAud == null || !tokenAudValidValues.contains(tokenAud)) {
            return null
        }
        val tokenMods = claims["mod"].tryCast<List<String>>()
        val tokenModValidValue = customEnvironment.getRequiredProperty("jwt-token-mod")
        return if (tokenMods == null || !tokenMods.contains(tokenModValidValue)) {
            null
        } else claims
    }
}