package it.polito.ai.lab03.utils;

/**
 * Per login e ottenere i tokens, cazzi e mazzi, chiedere a:
 * 1) [/oauth/token],methods=[GET] --> per ottenere i tuoi token date le tue credenziali
 * 2) [/oauth/token],methods=[POST] --> per farti fabbricare nuovo token + refresh token date le credenziali
 */
public class Constants {

    // Endpoints url
    public static final String SECURED_USER_PATTERN = "/users/positions/**"; // Solo per user, get e post positions
    public static final String SECURED_CUSTOMER_PATTERN = "/customer/**"; // Solo per customer, solo per post, per comprare posizioni
    public static final String SECURED_ADMIN_PATTERN = "/**"; // No post!

    // Roles
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    public static final String ROLE_USER = "ROLE_USER";

    // Db informations
    public static final String DATABASE_URI = "mongodb://localhost";
    public static final String DATABASE_NAME = "db";
}
