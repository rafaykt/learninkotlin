package com.improving.testing_20


import com.google.common.truth.Truth.assertThat
import com.improving.testing_20.others.RegistrationUtil
import org.junit.Test

class RegistrationUtilTest {


    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly password and confirmpassword returns true`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "rafaykt",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists return false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "john",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "john",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password and confirmpassword doesnt match returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "john",
            "123",
            "321"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password and confirmpassword with less than 3 digits returns false`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "john",
            "12",
            "12"
        )
        assertThat(result).isFalse()
    }

}