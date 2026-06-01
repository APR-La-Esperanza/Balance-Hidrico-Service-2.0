package cl.LaEsperanza.BalanceHidrico.Controller;

import cl.LaEsperanza.BalanceHidrico.model.BalanceHidrico;
import cl.LaEsperanza.BalanceHidrico.Service.BalanceHidricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/balance-hidrico")
public class BalanceHidricoController {

    @Autowired
    private BalanceHidricoService balanceHidricoService;

    @PostMapping
    public ResponseEntity<?> createBalance(@RequestBody BalanceHidrico balance, HttpServletRequest request) {
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