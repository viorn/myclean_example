package com.viorn.gf.common

interface Converter<T,R> {
    fun convert(t: T): R
}