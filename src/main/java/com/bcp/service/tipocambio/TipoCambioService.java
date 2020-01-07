package com.bcp.service.tipocambio;

import com.bcp.dto.request.TipoCambioRequest;
import com.bcp.dto.response.TipoCambioResponse;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface TipoCambioService {
    
	Single<TipoCambioResponse> getTipoCambio(TipoCambioRequest req);
	
	Completable updateTipoCambio(TipoCambioRequest tipoCambioRequest);
	
	Single<String> addTipoCambio(TipoCambioRequest addTipoCambioRequest);
	
}
