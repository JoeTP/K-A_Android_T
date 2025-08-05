package com.example.khatibalamytask.core.utils

interface UseCase <in Params, out Result>{
    suspend operator fun invoke(params: Params) : Result
}