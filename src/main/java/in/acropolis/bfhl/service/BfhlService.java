package in.acropolis.bfhl.service;

import in.acropolis.bfhl.dto.BfhlRequest;
import in.acropolis.bfhl.dto.BfhlResponse;

public interface BfhlService {

    BfhlResponse process(BfhlRequest request);
}
