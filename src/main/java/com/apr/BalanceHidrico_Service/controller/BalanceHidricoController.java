package com.apr.BalanceHidrico_Service.controller;

import com.apr.BalanceHidrico_Service.dto.BalanceHidricoDTO;
import com.apr.BalanceHidrico_Service.dto.BalanceHidricoResponseDTO;
import com.apr.BalanceHidrico_Service.service.BalanceHidricoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balance")
@Tag(name = "Balance Hídrico", description = "Endpoints para la gestión, cálculo y consulta del balance de agua potable y detección de pérdidas.")
@SecurityRequirement(name = "bearerAuth")
public class BalanceHidricoController {

    private final BalanceHidricoService service;

    public BalanceHidricoController(BalanceHidricoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar balances hídricos", description = "Obtiene el historial de todos los balances hídricos calculados.")
    @ApiResponse(responseCode = "200", description = "Historial de balances obtenido exitosamente.")
    public ResponseEntity<List<BalanceHidricoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar balance por ID", description = "Obtiene los detalles de un registro de balance hídrico específico.")
    @ApiResponse(responseCode = "200", description = "Balance encontrado y devuelto.")
    @ApiResponse(responseCode = "404", description = "El balance solicitado no existe.")
    public ResponseEntity<BalanceHidricoResponseDTO> buscarPorId(
            @Parameter(description = "ID del registro de balance hídrico", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/periodo/{periodo}")
    @Operation(summary = "Buscar balance por período", description = "Retorna el balance hídrico asociado a un período (formato YYYY-MM).")
    @ApiResponse(responseCode = "200", description = "Balance del período encontrado.")
    @ApiResponse(responseCode = "404", description = "No existe balance hídrico para el período provisto.")
    public ResponseEntity<BalanceHidricoResponseDTO> buscarPorPeriodo(
            @Parameter(description = "Período (YYYY-MM)", required = true) @PathVariable String periodo) {
        return ResponseEntity.ok(service.buscarPorPeriodo(periodo));
    }

    @PostMapping
    @Operation(summary = "Registrar balance manual", description = "Permite guardar un balance hídrico pre-calculado de forma manual.")
    @ApiResponse(responseCode = "201", description = "Balance manual registrado exitosamente.")
    @ApiResponse(responseCode = "400", description = "Datos de entrada incorrectos.")
    public ResponseEntity<BalanceHidricoResponseDTO> guardar(
            @RequestBody(description = "Datos para el registro manual del balance", required = true,
                         content = @Content(schema = @Schema(implementation = BalanceHidricoDTO.class),
                                            examples = @ExampleObject(value = "{\n  \"periodo\": \"2025-06\",\n  \"aguaProducidaM3\": 1500.0,\n  \"aguaFacturadaM3\": 1100.0,\n  \"fechaCalculo\": \"2025-06-30\"\n}")))
            @Valid @org.springframework.web.bind.annotation.RequestBody BalanceHidricoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(dto));
    }

    @PostMapping("/generar")
    @Operation(summary = "Generar balance automático", description = "Calcula el balance hídrico del período indicado (bombeo vs. facturas emitidas). Dispara una alerta si la pérdida de agua supera el 25%.")
    @ApiResponse(responseCode = "201", description = "Balance hídrico generado e ingresado con éxito.")
    @ApiResponse(responseCode = "400", description = "Error de parámetros o problemas de comunicación WebClient con otros microservicios.")
    public ResponseEntity<BalanceHidricoResponseDTO> generarBalanceParaPeriodo(
            @Parameter(description = "Período a calcular (YYYY-MM)", required = true) @RequestParam String periodo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generarBalanceParaPeriodo(periodo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar balance", description = "Modifica los datos de un registro de balance hídrico existente.")
    @ApiResponse(responseCode = "200", description = "Registro de balance modificado exitosamente.")
    @ApiResponse(responseCode = "400", description = "Datos provistos incorrectos.")
    @ApiResponse(responseCode = "404", description = "El balance indicado no existe.")
    public ResponseEntity<BalanceHidricoResponseDTO> actualizar(
            @Parameter(description = "ID del balance a actualizar", required = true) @PathVariable Long id,
            @RequestBody(description = "Nuevos datos del balance", required = true,
                         content = @Content(schema = @Schema(implementation = BalanceHidricoDTO.class),
                                            examples = @ExampleObject(value = "{\n  \"periodo\": \"2025-06\",\n  \"aguaProducidaM3\": 1500.0,\n  \"aguaFacturadaM3\": 1200.0,\n  \"fechaCalculo\": \"2025-06-30\"\n}")))
            @Valid @org.springframework.web.bind.annotation.RequestBody BalanceHidricoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar balance", description = "Elimina permanentemente el registro de balance especificado.")
    @ApiResponse(responseCode = "204", description = "Balance eliminado correctamente.")
    @ApiResponse(responseCode = "404", description = "El balance no existe.")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del balance a eliminar", required = true) @PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
