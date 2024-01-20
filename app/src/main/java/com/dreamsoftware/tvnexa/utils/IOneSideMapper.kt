package com.dreamsoftware.tvnexa.utils

interface IOneSideMapper<IN, OUT> {
    fun mapInToOut(input: IN): OUT
    fun mapInListToOutList(input: Iterable<IN>): Iterable<OUT>
}