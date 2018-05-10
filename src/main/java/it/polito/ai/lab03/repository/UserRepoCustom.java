package it.polito.ai.lab03.repository;

public interface UserRepoCustom {

    /*
    * se volessimo scrivere dei metodi custom, qui scriviamo i metodi
    * e poi si implementano in UserRepoImpl
    * questo metodo è solo un esempio, non è necessario, ma può servire
    */

    void updateRole(User user, User.Role role);

}
