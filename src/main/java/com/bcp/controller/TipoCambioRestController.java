package com.bcp.controller;

import com.bcp.dto.response.BaseWebResponse;
import com.bcp.dto.request.TipoCambioRequest;
import com.bcp.dto.response.TipoCambioResponse;
import com.bcp.service.tipocambio.TipoCambioService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/tipo-cambio")
public class TipoCambioRestController {

    @Autowired
    private TipoCambioService tipoCambioService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebResponse<TipoCambioResponse>>> getTipoCambio(@RequestBody TipoCambioRequest tipoCambioRequest) {
        return tipoCambioService.getTipoCambio(tipoCambioRequest)
                .subscribeOn(Schedulers.io())
                .map(tipoCambioResponse -> ResponseEntity.ok(BaseWebResponse.successWithData(toTipoCambioResponse(tipoCambioResponse))));
    }
    
    private TipoCambioResponse toTipoCambioResponse(TipoCambioResponse tipoCambioResponse) {
        return tipoCambioResponse;
    }
    
    
    @PostMapping(
    		path = "/actualizar",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebResponse>> updateTipoCambio(@RequestBody TipoCambioRequest tipoCambioRequest) {
        return tipoCambioService.updateTipoCambio(tipoCambioRequest)
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.ok(BaseWebResponse.successNoData()));
    }
    
    @PostMapping(
    		path = "/crear",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebResponse>> addTipoCambio(@RequestBody TipoCambioRequest addTipoCambioRequest) {
    	System.out.println("Entrando a creación de Tipo de Cambio");
        return tipoCambioService.addTipoCambio(addTipoCambioRequest)
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity.created(URI.create("/api/tipo-cambio/" + s)).body(BaseWebResponse.successNoData()));
    }


}
