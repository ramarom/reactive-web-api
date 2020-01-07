package com.bcp.service.tipocambio;

import com.bcp.repository.TipoCambioRepository;
import com.bcp.dto.request.TipoCambioRequest;
import com.bcp.dto.response.TipoCambioResponse;
import com.bcp.entity.TipoCambio;

import io.reactivex.Completable;
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
            	if (!optionalTCD.isPresent())
            		singleSubscriber.onError(new EntityNotFoundException());
                else {
	            	TipoCambioResponse tipoCambioResponse = toTipoCambioResponse(optionalTCO.get(), optionalTCD.get(), req);
	                singleSubscriber.onSuccess(tipoCambioResponse);
                }
            }
        });
    }
    
    private TipoCambioResponse toTipoCambioResponse(TipoCambio tco, TipoCambio tcd, TipoCambioRequest req) {
    	TipoCambioResponse tipoCambioResponse = new TipoCambioResponse();
    	Double origen = tco.getConversion();
    	Double destino = tcd.getConversion();
    	Double tipoCambio = destino/origen;
    	Double montoConversion = tipoCambio*req.getMonto();
    	
        //BeanUtils.copyProperties(tc, tipoCambioResponse);
        tipoCambioResponse.setMonedaOrigen(req.getMonedaOrigen());
        tipoCambioResponse.setMonedaDestino(req.getMonedaDestino());
        tipoCambioResponse.setMonto(req.getMonto());
        tipoCambioResponse.setMontoTipoCambio(montoConversion);
        tipoCambioResponse.setTipoCambio(tipoCambio);
        return tipoCambioResponse;
    }

    @Override
    public Completable updateTipoCambio(TipoCambioRequest tipoCambioRequest) {
        return updateTipoCambioToRepository(tipoCambioRequest);
    }

    private Completable updateTipoCambioToRepository(TipoCambioRequest updateTipoCambioRequest) {
        return Completable.create(completableSubscriber -> {
            Optional<TipoCambio> optionalTipoCambio = tipoCambioRepository.findById(updateTipoCambioRequest.getMoneda());
            if (!optionalTipoCambio.isPresent())
                completableSubscriber.onError(new EntityNotFoundException());
            else {
            	TipoCambio tipoCambio = optionalTipoCambio.get();
            	tipoCambio.setConversion(updateTipoCambioRequest.getTipoCambio());
                tipoCambioRepository.save(tipoCambio);
                completableSubscriber.onComplete();
            }
        });
    }
    
    @Override
    public Single<String> addTipoCambio(TipoCambioRequest addTipoCambioRequest) {
        return saveTipoCambioToRepository(addTipoCambioRequest);
    }
    
    private Single<String> saveTipoCambioToRepository(TipoCambioRequest addTipoCambioRequest) {
        return Single.create(singleSubscriber -> {
            Optional<TipoCambio> optionalTipoCambio = tipoCambioRepository.findById(addTipoCambioRequest.getMoneda());
            if (optionalTipoCambio.isPresent())
                singleSubscriber.onError(new Exception("Moneda ya existente"));
            else {
                String addedTCMon = tipoCambioRepository.save(toTipoCambio(addTipoCambioRequest)).getMoneda();
                singleSubscriber.onSuccess(addedTCMon);
            }
        });
    }

    private TipoCambio toTipoCambio(TipoCambioRequest addTipoCambioRequest) {
    	TipoCambio tc = new TipoCambio();
        tc.setMoneda(addTipoCambioRequest.getMoneda());
        tc.setConversion(addTipoCambioRequest.getTipoCambio());
        return tc;
    }
}
