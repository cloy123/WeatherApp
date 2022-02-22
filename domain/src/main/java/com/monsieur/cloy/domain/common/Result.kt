package com.monsieur.cloy.domain.common

class Result(val isSuccessful: Boolean,val e: Exception?, val code: Int, val message: String) {
}