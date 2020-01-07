package com.bcp.controller;

import com.axell.reactive.service.book.BookService;
import com.axell.reactive.servicedto.request.AddBookRequest;
import com.axell.reactive.servicedto.request.UpdateBookRequest;
import com.axell.reactive.servicedto.response.BookResponse;
import com.axell.reactive.webdto.request.AddBookWebRequest;
import com.axell.reactive.webdto.request.UpdateBookWebRequest;
import com.bcp.dto.response.BaseWebResponse;
import com.axell.reactive.webdto.response.BookWebResponse;
import com.bcp.dto.request.TipoCambioRequest;
import com.bcp.dto.response.TipoCambioResponse;
import com.bcp.service.tipocambio.TipoCambioService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/tipo-cambio")
public class TipoCambioRestController {

    @Autowired
    private TipoCambioService tipoCambioService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebResponse<TipoCambioResponse>>> getBookDetail(@RequestBody TipoCambioRequest tipoCambioRequest) {
        return tipoCambioService.getTipoCambio(tipoCambioRequest)
                .subscribeOn(Schedulers.io())
                .map(tipoCambioResponse -> ResponseEntity.ok(BaseWebResponse.successWithData(toTipoCambioResponse(tipoCambioResponse))));
    }
    
    private TipoCambioResponse toTipoCambioResponse(TipoCambioResponse tipoCambioResponse) {
        return tipoCambioResponse;
    }

}
