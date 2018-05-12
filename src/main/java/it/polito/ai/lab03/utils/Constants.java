package it.polito.ai.lab03.utils;

/**
 * Per login e ottenere i tokens, cazzi e mazzi, chiedere a:
 * 1) [/oauth/token],methods=[GET] --> per ottenere i tuoi token date le tue credenziali
 * 2) [/oauth/token],methods=[POST] --> per farti fabbricare nuovo token + refresh token date le credenziali
 */
public class Constants {
    // Oauth2 rules
    public static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";

    // Endpoints url
    public static final String SECURE_GENERAL = "/secured/**";
    public static final String SECURED_USER_PATTERN = "/secured/user/positions/**"; // Solo per user, get e post positions
    public static final String SECURED_CUSTOMER_PATTERN = "/secured/customer/**"; // Solo per customer, solo per post, per comprare posizioni
    public static final String SECURED_ADMIN_PATTERN = "/secured/admin/**"; // No post!

    // Roles
    private static final String ADMIN = "ADMIN";
    public static final String SECURED_WRITE_SCOPE_ADMIN = "#oauth2.hasRole(" + ADMIN + ").hasScope('write')";
    public static final String SECURED_READ_SCOPE_ADMIN = "#oauth2.hasRole(" + ADMIN + ").hasScope('read')";
    private static final String CUSTOMER = "CUSTOMER";
    public static final String SECURED_WRITE_SCOPE_CUSTOMER = "#oauth2.hasRole(" + CUSTOMER + ").hasScope('write')";
    public static final String SECURED_READ_SCOPE_CUSTOMER = "#oauth2.hasRole(" + CUSTOMER + ").hasScope('read')";
    private static final String USER = "USER";
    public static final String SECURED_READ_SCOPE_USER = "#oauth2.hasRole(" + USER + ").hasScope('read')";
    public static final String SECURED_WRITE_SCOPE_USER = "#oauth2.hasRole(" + USER + ").hasScope('write')";

    // Db informations
    public static final String DATABASE_URI = "mongodb://localhost:27017";
    public static final String DATABASE_NAME = "db";
}
