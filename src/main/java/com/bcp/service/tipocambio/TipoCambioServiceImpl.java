package com.bcp.service.tipocambio;

import com.bcp.repository.TipoCambioRepository;
import com.bcp.dto.request.TipoCambioRequest;
import com.bcp.dto.response.TipoCambioResponse;
import com.bcp.entity.TipoCambio;

import io.reactivex.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Override
    public Single<TipoCambioResponse> getTipoCambio(TipoCambioRequest req) {
        return findTipoCambioInRepository(req);
    }
    
    private Single<TipoCambioResponse> findTipoCambioInRepository(TipoCambioRequest req) {
        return Single.create(singleSubscriber -> {
            Optional<TipoCambio> optionalTCO = tipoCambioRepository.findById(req.getMonedaOrigen());
            
            Optional<TipoCambio> optionalTCD = tipoCambioRepository.findById(req.getMonedaDestino());
            if (!optionalTCO.isPresent())
                singleSubscriber.onError(new EntityNotFoundException());
            else {
            	TipoCambioResponse tipoCambioResponse = toTipoCambioResponse(optionalTCD.get());
                singleSubscriber.onSuccess(tipoCambioResponse);
            }
        });
    }
    
    private TipoCambioResponse toTipoCambioResponse(TipoCambio tc) {
    	TipoCambioResponse tipoCambioResponse = new TipoCambioResponse();
        BeanUtils.copyProperties(tc, tipoCambioResponse);
        tipoCambioResponse.setMonedaOrigen(tc.getConversion());
        return tipoCambioResponse;
    }
}
