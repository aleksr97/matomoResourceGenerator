package com.aleksR97.matomoResourceGeneratorBackend.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException::class)
    fun handleResourceAlreadyExists(exception: ResourceAlreadyExistsException) : ResponseEntity<String>{
       return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.message)
    }



}