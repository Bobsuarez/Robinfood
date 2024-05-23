package com.robinfood.localserver.commons.dtos.security;

public class PayloadDataDTO {

    private String iss;
    private String aud;
    private String[] mod;
    private String[] per;
    private String jti;
    private Long exp;
    private Long iat;
    private String nbf;
    private Integer company_id ;

    /**
     * @return the iss
     */
    public String getIss() {
        return iss;
    }

    /**
     * @param iss the iss to set
     */
    public void setIss(String iss) {
        this.iss = iss;
    }

    /**
     * @return the aud
     */
    public String getAud() {
        return aud;
    }

    /**
     * @param aud the aud to set
     */
    public void setAud(String aud) {
        this.aud = aud;
    }

    /**
     * @return the mod
     */
    public String[] getMod() {
        return mod;
    }

    /**
     * @param mod the mod to set
     */
    public void setMod(String[] mod) {
        this.mod = mod;
    }

    /**
     * @return the per
     */
    public String[] getPer() {
        return per;
    }

    /**
     * @param per the per to set
     */
    public void setPer(String[] per) {
        this.per = per;
    }

    /**
     * @return the jti
     */
    public String getJti() {
        return jti;
    }

    /**
     * @param jti the jti to set
     */
    public void setJti(String jti) {
        this.jti = jti;
    }

    /**
     * @return the exp
     */
    public Long getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(Long exp) {
        this.exp = exp;
    }

    /**
     * @return the iat
     */
    public Long getIat() {
        return iat;
    }

    /**
     * @param iat the iat to set
     */
    public void setIat(Long iat) {
        this.iat = iat;
    }

    /**
     * @return the nbf
     */
    public String getNbf() {
        return nbf;
    }

    /**
     * @param nbf the nbf to set
     */
    public void setNbf(String nbf) {
        this.nbf = nbf;
    }

    /**
     * @return the company_id
     */
    public Integer getCompany_id() {
        return company_id;
    }

    /**
     * @param company_id the company_id to set
     */
    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

}
