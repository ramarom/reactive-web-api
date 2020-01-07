package com.bcp.service.tipocambio;

import com.axell.reactive.entity.Author;
import com.axell.reactive.repository.AuthorRepository;
import com.axell.reactive.servicedto.request.AddAuthorRequest;
import com.axell.reactive.servicedto.response.BookResponse;

import io.reactivex.Single;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Single<TipoCambioResponse> getTipoCambio(String id) {
        return findBookDetailInRepository(id);
    }
}
