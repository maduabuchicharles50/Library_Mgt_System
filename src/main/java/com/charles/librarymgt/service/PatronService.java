package com.charles.librarymgt.service;

import org.springframework.stereotype.Service;
import com.charles.librarymgt.dtos.PatronDto;
import com.charles.librarymgt.models.Patron;
import com.charles.librarymgt.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private PagedResourcesAssembler<PatronDto> pagedResourcesAssembler;


    public PagedModel<EntityModel<PatronDto>> getAllPatrons(Pageable paging) {
        Page<Patron> result = patronRepository.findAll(paging);

        return pagedResourcesAssembler.toModel(result.map(PatronDto::new));
    }

    public PatronDto getPatron(Long id) {
        return new PatronDto(patronRepository.findById(id).get());
    }

    public PatronDto createPatron(Patron patron) {
        return new PatronDto(patronRepository.save(patron));
    }

    public PatronDto updatePatron(Patron newPatron, Long id) {
        var patron = patronRepository.findById(id).get();

        if (!patron.getName().equals(newPatron.getName())) {
            patron.setName(newPatron.getName());
        }
        if (!patron.getContact().equals(newPatron.getContact())) {
            patron.setContact(newPatron.getContact());
        }

        return new PatronDto(patronRepository.save(patron));
    }

    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
