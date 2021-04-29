package com.example.learningrxjava.Model

data class Character(val name : String,
                     val gender : String){

    override fun toString(): String {
        return "${name} / ${gender}"
    }
}