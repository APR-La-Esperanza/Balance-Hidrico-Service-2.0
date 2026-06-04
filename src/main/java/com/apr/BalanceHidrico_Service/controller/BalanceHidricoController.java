package com.apr.BalanceHidrico_Service.controller;

import com.apr.BalanceHidrico_Service.dto.BalanceHidricoDTO;
import com.apr.BalanceHidrico_Service.dto.BalanceHidricoResponseDTO;
import com.apr.BalanceHidrico_Service.service.BalanceHidricoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balance")
public class BalanceHidricoController {

    private final BalanceHidricoService service;

    public BalanceHidricoController(BalanceHidricoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BalanceHidricoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BalanceHidricoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/periodo/{periodo}")
    public ResponseEntity<BalanceHidricoResponseDTO> buscarPorPeriodo(@PathVariable String periodo) {
        return ResponseEntity.ok(service.buscarPorPeriodo(periodo));
    }

    @PostMapping
    public ResponseEntity<BalanceHidricoResponseDTO> guardar(@Valid @RequestBody BalanceHidricoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(dto));
    }

    @PostMapping("/generar")
    public ResponseEntity<BalanceHidricoResponseDTO> generarBalanceParaPeriodo(@RequestParam String periodo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generarBalanceParaPeriodo(periodo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BalanceHidricoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody BalanceHidricoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
