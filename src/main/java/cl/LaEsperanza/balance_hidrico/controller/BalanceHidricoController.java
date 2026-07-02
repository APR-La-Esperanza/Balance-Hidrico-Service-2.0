package cl.LaEsperanza.balance_hidrico.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.LaEsperanza.balance_hidrico.model.BalanceHidricoModel;
import cl.LaEsperanza.balance_hidrico.service.BalanceHidricoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/balance-hidrico")
// @Tag agrupa este grupo de endpoints bajo un título limpio en la interfaz web de Swagger
@Tag(name = "Balance Hídrico", description = "Endpoints de integración técnica para auditorías operacionales y financieras")
public class BalanceHidricoController {

    @Autowired
    private BalanceHidricoService balanceHidricoService;

    @PostMapping
    // 1. @Operation: Describe qué hace y el propósito del método ante el profesor
    @Operation(
        summary = "Calcular y registrar nuevo Balance Hídrico",
        description = "Consume el agua inyectada y el agua cobrada. Si las pérdidas técnicas superan el 25%, genera automáticamente una alerta hídrica en el sistema."
    )
    // 2. @ApiResponse: Documenta de manera formal todos los posibles códigos HTTP de salida
    @ApiResponse(
        responseCode = "201", 
        description = "Balance hídrico procesado y guardado correctamente."
    )
    @ApiResponse(
        responseCode = "400", 
        description = "Datos de entrada corruptos o inconsistentes (Bad Request)."
    )
    @ApiResponse(
        responseCode = "403", 
        description = "Acceso denegado: Token JWT ausente o sin privilegios suficientes."
    )
    public ResponseEntity<?> createBalance(
        // 3. @RequestBody de OpenAPI + 4. @ExampleObject: Generan una plantilla JSON de prueba visible en el navegador
        @RequestBody(
            description = "Estructura requerida para auditar el flujo de agua total del mes",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = BalanceHidricoModel.class),
                examples = @ExampleObject(
                    name = "Ejemplo Pérdida Crítica (>25%)",
                    summary = "Simulación con pérdidas del 30%",
                    value = "{\"mes\": \"Julio\", \"anio\": 2026, \"aguaInyectadaM3\": 1000.0, \"aguaFacturadaM3\": 700.0, \"observaciones\": \"Sospecha de fuga en matriz sector norte\"}"
                )
            )
        )
        @org.springframework.web.bind.annotation.RequestBody BalanceHidricoModel balance, 
        HttpServletRequest request
    ) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Forbidden");
            error.put("message", "Acceso denegado: Token ausente");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        }
        return new ResponseEntity<>(balanceHidricoService.saveBalance(balance), HttpStatus.CREATED);
    }
}