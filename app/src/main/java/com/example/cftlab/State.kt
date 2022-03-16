package com.example.cftlab

sealed interface State<TYPE> {
    class Success<TYPE> : State<TYPE>
    data class Fail<TYPE>(var error: Throwable?) : State<TYPE>
    class Pending<TYPE> : State<TYPE>
}