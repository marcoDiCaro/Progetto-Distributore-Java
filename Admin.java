/*
 * Classe Admin
 * ATTRIBUTI:
 * - email: stringa non vuota
 * - password: stringa non vuota
 * 
 * METODI:
 * - costruttore che prende gli attributi in input: controlla i valori, eventualmente sollevando eccezioni
 * - getters: metodi per restituire gli attributi singolarmente
 * - setters: metodi per modificare gli attributi singoalrmente, controlla opportunamente valori, eventualmente sollevando eccezioni
 * - toString: rappresentazione a stringa dell'oggetto Admin
 * - isValidEmail: metodo ausiliario che verifica se l'email fornita è valida
 * - isValidPassword: metodo ausiliario che verifica se la password fornita è valida
 */

public class Admin {
    private String email;
    private String password;

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email.matches(regex)) {
            return true;
        }
        return false;
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+<>?/\\|]).{8,}$";
        if (password.matches(regex)) {
            return true;
        }
        return false;
    }

    public Admin(String email, String password) {
        if (!this.isValidEmail(email)) {
            throw new IllegalArgumentException(
                    String.format("Il parametro %s deve essere un indirizzo email valido.", email));
        }
        this.email = email;
        if (!this.isValidPassword(password)) {
            throw new IllegalArgumentException(
                    String.format("Il parametro %s deve essere una password valida.", password));
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!this.isValidEmail(email)) {
            throw new IllegalArgumentException(
                    String.format("Il parametro %s deve essere un indirizzo email valido.", email));
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!this.isValidPassword(password)) {
            throw new IllegalArgumentException(
                    String.format("Il parametro %s deve essere una password valida.", password));
        }
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("Email: %s", email);
    }
}