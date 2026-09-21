package ru.nto.storage.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.nto.storage.exception.EmployeeNotFoundException
import ru.nto.storage.exception.EquipmentNotAvailableException
import ru.nto.storage.exception.EquipmentNotFoundException

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException::class)
    fun handleEmployeeNotFoundException(e: EmployeeNotFoundException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(EquipmentNotAvailableException::class)
    fun handleEquipmentNotAvailableException(e: EquipmentNotAvailableException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(EquipmentNotFoundException::class)
    fun handleEquipmentNotFoundException(e: EquipmentNotFoundException): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception): ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }
}
