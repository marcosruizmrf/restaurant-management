package com.restaurant.management.exception;

public final class ExceptionMessages {

    private ExceptionMessages() {}

    public static final String NAME_REQUIRED = "Nome é obrigatório";
    public static final String NAME_MAX_LENGTH = "Nome deve ter no máximo 255 caracteres";

    public static final String EMAIL_INVALID = "E-mail inválido";
    public static final String EMAIL_REQUIRED = "E-mail é obrigatório";
    public static final String EMAIL_MAX_LENGTH = "E-mail deve ter no máximo 255 caracteres";

    public static final String LOGIN_REQUIRED = "Login é obrigatório";
    public static final String LOGIN_MAX_LENGTH = "Login deve ter no máximo 255 caracteres";

    public static final String PASSWORD_REQUIRED = "Senha é obrigatória";
    public static final String PASSWORD_LENGTH = "Senha deve ter entre 6 e 72 caracteres";

    public static final String TYPE_REQUIRED = "Tipo é obrigatório";

    public static final String CURRENT_PASSWORD_REQUIRED = "Senha atual é obrigatória";
    public static final String NEW_PASSWORD_REQUIRED = "Nova senha é obrigatória";
    public static final String NEW_PASSWORD_LENGTH = "Nova senha deve ter entre 6 e 72 caracteres";

    public static final String INVALID_CURRENT_PASSWORD = "Senha atual inválida";
    public static final String NEW_PASSWORD_SAME_AS_CURRENT = "A nova senha deve ser diferente da senha atual";
    public static final String INVALID_LOGIN_CREDENTIALS = "Login ou senha inválidos";
    public static final String LOGIN_SUCCESS = "Login válido";
}