package com.dreamsoftware.tvnexa.utils

interface ISimpleOneSideMapper<IN, OUT> {
    fun mapInToOut(input: IN): OUT
}