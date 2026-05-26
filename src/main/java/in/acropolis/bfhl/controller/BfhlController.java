package in.acropolis.bfhl.controller;

import in.acropolis.bfhl.dto.BfhlRequest;
import in.acropolis.bfhl.dto.BfhlResponse;
import in.acropolis.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> processBfhl(@Valid @RequestBody BfhlRequest request) {
        return ResponseEntity.ok(bfhlService.process(request));
    }
}
