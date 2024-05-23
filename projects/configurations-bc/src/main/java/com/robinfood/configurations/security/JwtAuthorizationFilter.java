package com.robinfood.configurations.security;

import static com.robinfood.configurations.constants.ConfigurationsBCConstants.AUDIENCE_PUBLIC;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.AUDIENCE_SERVICE;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.BEARER;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.JSON_DATA;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.USER_NODE;
import static com.robinfood.configurations.constants.ConfigurationsBCConstants.USER_SERVICE;
import static com.robinfood.configurations.constants.PermissionsConstants.PUBLIC;
import static com.robinfood.configurations.constants.PermissionsConstants.SERVICE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinfood.configurations.dto.v1.ApiResponseDTO;
import com.robinfood.configurations.dto.v1.security.CompanyDTO;
import com.robinfood.configurations.dto.v1.security.UserDTO;
import com.robinfood.configurations.dto.v1.security.UserRequestResponse;
import com.robinfood.configurations.exceptions.BusinessRuleException;
import com.robinfood.configurations.utils.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final String ROLE = "ROLE_";

    private final Environment environment;
    private final ObjectMapper objectMapper;

    public JwtAuthorizationFilter(@NotNull final Environment environment,
        ObjectMapper objectMapper) {
        this.environment = environment;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain)
        throws IOException, ServletException {

        if (!existJwtToken(request)) {
            log.info("Token does not exist.");
            denyRequest(response,  HttpStatus.UNAUTHORIZED, "Token does not exist.");
            return;
        }

        log.info("Starting authentication process...");

        log.info("Getting JWT token from request.");
        String tokenPrefix = BEARER;
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION)
            .substring(tokenPrefix.length());

        UserRequestResponse userRequestResponse;

        try {
            log.info("Getting claims from JWT");
            Claims claims = getClaims(jwtToken);
            log.info("Claims obtained successfully");

            userRequestResponse = validateUserInfoAndRequest(request, claims);

            if (!isValidClaims(claims)) {
                denyRequest(response,  HttpStatus.UNAUTHORIZED, "Insufficient permissions.");
                return;
            }

            log.info("Token is valid.");
            log.info("Authenticate User");
            UsernamePasswordAuthenticationToken auth = authenticateUser(claims, userRequestResponse.getUser());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("Authenticated user successfully");

        } catch (JwtException e) {
            log.error(e.getMessage(), e);
            denyRequest(response, HttpStatus.UNAUTHORIZED, e.getMessage());
            return;
        } catch (BusinessRuleException e) {
            log.error(e.getMessage(), e);
            denyRequest(response, HttpStatus.BAD_REQUEST, e.getMessage());
            return;
        }

        filterChain.doFilter(userRequestResponse.getRequest(), response);
        log.info("Authentication process ended successfully.");

    }

    private Claims getClaims(String token) {
        String tokenSecret = environment.getRequiredProperty("jwt.token.secret");
        return Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
            .parseClaimsJws(token).getBody();
    }

    private boolean isValidAudience(Claims claims) throws JsonProcessingException {

        log.info("Getting system audiences.");
        String tokenAudValues = environment.getRequiredProperty("jwt.token.aud");
        List<String> tokenAudValidValues = Arrays.asList(tokenAudValues.split(","));
        log.info("Audiences obtained successfully: {}",
            objectMapper.writeValueAsString(tokenAudValidValues));

        log.info("Getting token audience.");
        String tokenAud = claims.getAudience();
        log.info("Token audience obtained successfully: {}", tokenAud);

        log.info("Validating audience");
        if (tokenAud == null || !tokenAudValidValues.contains(tokenAud)) {
            log.info("Audience no valid ");
            return false;
        }
        log.info("Valid audience");
        return true;
    }

    private boolean isValidModules(Claims claims) throws JsonProcessingException {
        log.info("Getting system modules.");
        String tokenModValidValue = environment.getRequiredProperty("jwt.token.mod");
        List<String> listMods = Arrays.asList(tokenModValidValue.split(","));

        log.info("Getting token modules.");
        List<String> tokenMods = claims.get("mod", ArrayList.class);
        log.info("Token modules obtained successfully: {}",
            objectMapper.writeValueAsString(tokenMods));

        log.info("Validating modules");
        if (tokenMods == null || tokenMods.stream().noneMatch(listMods::contains)) {
            log.info("Modules no valid ");
            return false;
        }
        log.info("Valid modules");
        return true;
    }

    private boolean isValidUser(Claims claims) throws JsonProcessingException {
        log.info("Getting token user.");
        Map<String, Object> user = claims.get("user", Map.class);
        if (user == null || user.get("user_id") == null) {
            log.info("User objet no valid");
            return false;
        }
        log.info("Token user obtained successfully: {}", objectMapper.writeValueAsString(user));
        return true;
    }

    private boolean isValidClaims(Claims claims) throws JsonProcessingException {

        log.info("claims.getAudience(): {}", objectMapper.writeValueAsString(claims.getAudience()));

        if (AUDIENCE_SERVICE.equals(claims.getAudience()) ||
            AUDIENCE_PUBLIC.equals(claims.getAudience())) {
            return isValidAudience(claims);
        } else {
            if (isValidUser(claims) && isValidModules(claims)) {
                return this.isValidAudience(claims);
            }
        }

        return false;
    }

    private boolean existJwtToken(@NotNull final HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        return !(authHeader == null || !authHeader.startsWith(BEARER));
    }

    public UsernamePasswordAuthenticationToken authenticateUser(Claims claims,  UserDTO userDTO)
        throws JsonProcessingException {
        Set<SimpleGrantedAuthority> grantedValues = getGrantedValues(claims);
        return new UsernamePasswordAuthenticationToken(userDTO, "", grantedValues);
    }

    private static Set<SimpleGrantedAuthority> getGrantedValues(Claims claims)
        throws JsonProcessingException {

        String audience = claims.getAudience();

        log.info("Audience to granted values: {}", audience);

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        switch (audience) {

            case AUDIENCE_SERVICE:
                authorities.add(new SimpleGrantedAuthority(
                    ROLE + SERVICE.toUpperCase(Locale.getDefault())));
                break;

            case AUDIENCE_PUBLIC:
                authorities.add(new SimpleGrantedAuthority(
                    ROLE + PUBLIC.toUpperCase(Locale.getDefault())));
                break;
            default:
                log.info("Getting token permissions.");
                List<String> scopes = claims.get("per", ArrayList.class);
                log.info("Token permissions obtained successfully: {}",
                    JsonUtils.convertToJson(scopes));

                if (scopes != null) {
                    scopes.forEach(
                        x -> authorities.add(
                            new SimpleGrantedAuthority(ROLE + x
                                .toUpperCase(Locale.getDefault()))));
                }
        }

        log.info("Authorities granted values: {}", JsonUtils.convertToJson(authorities));

        return authorities;
    }

    private UserRequestResponse validateUserInfoAndRequest(HttpServletRequest request,
        Claims claims) throws IOException, BusinessRuleException {
        UserRequestResponse userRequestResponse = UserRequestResponse.builder()
            .user(UserDTO.builder().id(USER_SERVICE).build()).request(request).build();

        if (!claims.getAudience().equals(AUDIENCE_SERVICE)) {
            userRequestResponse.getUser().setId(null);
            Map<String, Object> tokenUser = claims.get("user", Map.class);

            if (tokenUser != null && tokenUser.get("id") != null) {
                log.trace("Getting userDTO from token");
                userRequestResponse.setUser(getUserDTOFromToken(tokenUser));
                log.trace("userDTO obtained from token successfully: {}",
                    userRequestResponse.getUser());
            }
        } else {
            log.trace("Identifying the type of request...");

            if(request.getContentType() != null) {

                if (request.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                    log.trace("The type of request is: {}", MediaType.MULTIPART_FORM_DATA_VALUE);

                    JsonNode jsonData = getJsonNode(request);

                    log.trace("Getting userDTO from jsonData");
                    userRequestResponse.setUser(getUserFromJsonNode(jsonData));
                    log.trace("userDTO obtained from jsonData successfully: {}",
                        userRequestResponse.getUser());
                } else if (request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)) {
                    log.trace("The type of request is: {}", MediaType.APPLICATION_JSON_VALUE);
                    setRequestUser(request, userRequestResponse);
                } else {
                    throw new BusinessRuleException("Unsupported Content-Type");
                }
            }else {
                if (HttpMethod.GET.name().equals(request.getMethod()) ||
                    HttpMethod.DELETE.name().equals(request.getMethod())) {
                    setRequestUser(request, userRequestResponse);
                }
                else {
                    throw new BusinessRuleException("Content-Type must be specified.");
                }
            }

        }

        return userRequestResponse;
    }

    private JsonNode getJsonNode(HttpServletRequest request)
        throws JsonProcessingException, BusinessRuleException {
        log.trace("Getting jsonData from Body");

        if (request.getParameter(JSON_DATA) == null) {
            throw new BusinessRuleException("Required request part 'jsonData' is not present.");
        }

        JsonNode jsonData = objectMapper.readTree(request.getParameter(JSON_DATA));
        log.trace("jsonData obtained successfully: {}", jsonData);
        return jsonData;
    }

    private static UserDTO getUserDTOFromToken(Map<String, Object> tokenUser) {
        log.trace("Getting UserDTO from token {}", tokenUser);
        UserDTO userDTO = UserDTO.builder()
            .id(Long.valueOf(tokenUser.get("id").toString())).build();
        List<CompanyDTO> companyList = getCompanyListFromTokenCompanies(
            (List<Map<String, String>>) tokenUser.get("companies"));

        try {
            userDTO.setPhone((tokenUser.get("phone").toString()));
            userDTO.setAllowAdmin(Boolean.valueOf(tokenUser.get("allow_admin").toString()));
            userDTO.setDefaultCompanyId(
                Long.valueOf(tokenUser.get("default_company_id").toString()));
            userDTO.setLegacyId(Long.valueOf(tokenUser.get("legacy_id").toString()));
            userDTO.setFirstName(tokenUser.get("first_name").toString());
            userDTO.setLastName(tokenUser.get("last_name").toString());
            userDTO.setEmail(tokenUser.get("email").toString());
            userDTO.setCountryId(Long.valueOf(tokenUser.get("country_id").toString()));
            userDTO.setCompanies(companyList);
        } catch (NullPointerException exception) {
            log.error("The user information is not complete");
        }

        return userDTO;
    }

    private static List<CompanyDTO> getCompanyListFromTokenCompanies(
        List<Map<String, String>> tokenCompanies) {
        List<CompanyDTO> resultSet = new ArrayList<>();

        try {
            tokenCompanies.forEach((Map<String, String> setCompany) -> {
                CompanyDTO company = CompanyDTO.builder()
                    .id(Long.valueOf(String.valueOf(setCompany.get("id"))))
                    .name(setCompany.get("name"))
                    .timezone(setCompany.get("timezone"))
                    .build();
                resultSet.add(company);
            });
        } catch (NullPointerException exception) {
            log.error("The user's company information is not complete");
        }

        return resultSet;
    }

    private UserDTO getUserFromJsonNode(JsonNode jsonNode) throws JsonProcessingException {
        UserDTO userDTO = UserDTO.builder().id(USER_SERVICE).build();

        if (jsonNode.get(USER_NODE) != null) {
            userDTO = objectMapper.readValue(jsonNode.get(USER_NODE).toString(), UserDTO.class);
            log.trace("UserDTO: {}", userDTO);
        }

        return userDTO;
    }

    private void setRequestUser(HttpServletRequest request,UserRequestResponse userRequestResponse)
        throws IOException {

        CachedBodyHttpServletRequest cachedBodyHttpServletRequest =
            new CachedBodyHttpServletRequest(request);

        userRequestResponse.setRequest(cachedBodyHttpServletRequest);

        String body = getBodyFromInputStream(cachedBodyHttpServletRequest);
        log.trace("cachedBodyHttpServletRequest: {}", body);

        JsonNode jsonNode = objectMapper.readTree(body);
        log.trace("jsonNode: {}", jsonNode);

        log.trace("Getting userDTO from Body");
        userRequestResponse.setUser(getUserFromJsonNode(jsonNode));
        log.trace("userDTO obtained from Body successfully: {}",
            userRequestResponse.getUser());

    }

    private String getBodyFromInputStream(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;

        try (InputStream inputStream = request.getInputStream()) {
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        }

        return stringBuilder.toString();
    }

    private void denyRequest(@NotNull final HttpServletResponse response, HttpStatus status,
        String... errorMessages) {
        try (PrintWriter writer = response.getWriter()) {
            ApiResponseDTO<Object> apiResponse = new ApiResponseDTO<>();
            apiResponse.setMessage(status.getReasonPhrase());
            apiResponse.setCode(status.value());
            apiResponse.setError(List.of(errorMessages));
            String dataResponse = objectMapper.writeValueAsString(apiResponse);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            writer.write(dataResponse);
            writer.flush();
        } catch (IOException e) {
            log.trace("Error while writing response to deny request.");
            log.error(e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}

