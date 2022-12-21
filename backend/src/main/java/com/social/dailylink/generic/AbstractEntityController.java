package com.social.dailylink.generic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


public abstract class AbstractEntityController<DM extends AbstractEntity, DTO extends AbstractEntityDTO> {
    protected AbstractEntityService<DM> service;
    protected DTOMapper<DM, DTO> mapper;

    public AbstractEntityController(AbstractEntityService<DM> service, DTOMapper<DM, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * This endpoint return all the entries of the entity
     *
     * @return the list of entries of the entity
     */
    @GetMapping
    public ResponseEntity<Collection<DTO>> findAll() {
        Collection<DM> dms = service.findAll();

        return new ResponseEntity<>(mapper.toDTOs(dms), HttpStatus.OK);
    }

    /**
     * This endpoint searches for an entry with the id
     *
     * @param id the id to be searched for
     * @return the found entry or null
     */
    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable String id) {
        DM dm = service.findById(id);

        return new ResponseEntity<>(mapper.toDTO(dm), HttpStatus.OK);
    }

    /**
     * This endpoint is used to create an entry from the dto
     *
     * @param dto the dto to create the entry
     * @return the entry that got created
     */
    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTO dto) {
        DM dm = service.create(mapper.fromDTO(dto));

        return new ResponseEntity<>(mapper.toDTO(dm), HttpStatus.CREATED);
    }

    /**
     * This endpoint updates a entry by the id
     *
     * @param id  the id from the existing entry
     * @param dto the data from the new entry
     * @return the updated entry
     */
    @PutMapping("/{id}")
    public ResponseEntity<DTO> updateById(@PathVariable String id, @RequestBody DTO dto) {
        DM dm = service.updateById(id, mapper.fromDTO(dto));

        return new ResponseEntity<>(mapper.toDTO(dm), HttpStatus.OK);
    }

    /**
     * This endpoint deletes an entry by the id
     *
     * @param id is the id of the entry that will be deleted
     * @return an empty response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
