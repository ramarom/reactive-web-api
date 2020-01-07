package com.bcp.service.tipocambio;

import java.util.List;
import com.bcp.dto.request.TipoCambioRequest;
import com.bcp.dto.response.TipoCambioResponse;

import io.reactivex.Single;

public interface TipoCambioService {
    
	Single<List<TipoCambioResponse>> getTipoCambio(TipoCambioRequest req);
	
}
