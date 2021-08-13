package com.improving.testing_20.others

object RegistrationUtil {

    private val userList = listOf("carl", "john")

    /**
     * Um input é valido se:
     * o username não estiver vazio
     * o confirmpassword for igual ao password
     * o password ter mais de 2 digitos
     * */
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if(username.isEmpty()  || password.isEmpty() || confirmPassword.isEmpty()){
            return false
        }

        if(username in userList){
            return false
        }
        if( password != confirmPassword){
            return false
        }
        if(password.length <=2){
            return false
        }
        return true
    }

}