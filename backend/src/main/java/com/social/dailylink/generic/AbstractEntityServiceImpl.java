package com.social.dailylink.generic;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Service
public abstract class AbstractEntityServiceImpl<T extends AbstractEntity> implements AbstractEntityService<T> {
    protected AbstractEntityRepository<T> repository;
    protected Logger logger;
    private String className;


    public AbstractEntityServiceImpl(AbstractEntityRepository<T> repository) {
        this.repository = repository;
        this.logger = LoggerFactory.getLogger(getClass());
        initClassName();
    }

    /**
     * This method gets the class from the class that extends this class
     */
    private void initClassName() {
        try {
            this.className = Class.forName(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName()).getSimpleName();
        } catch (ClassNotFoundException e) {
            this.className = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
        }
    }

    /**
     * This method returns all entries from the extended class
     *
     * @return a list of objects
     */
    @Override
    @Transactional
    public List<T> findAll() {
        return repository.findAll();
    }

    /**
     * This method returns the object with the id from the parameter
     *
     * @param id the id to be searched for
     * @return the found object
     * @throws EntityNotFoundException will be thrown if the object could not be found
     */
    @Override
    @Transactional
    public T findById(String id) throws EntityNotFoundException {
        logger.debug("Attempting to find " + className + " with ID '" + id + "'");
        Optional<T> optional = repository.findById(UUID.fromString(id));

        if (optional.isPresent()) {
            logger.debug("Found " + className + " with ID '" + id + "'");
            return optional.get();
        } else {
            logger.debug(className + " with ID '" + id + "' not found");
            throw new EntityNotFoundException(className + " with ID '" + id + "' not found");
        }
    }

    /**
     * This method creates a new entry from the object
     *
     * @param entity the object to be created
     * @return the created object
     */
    @Override
    @Transactional
    public T create(T entity) {
        logger.debug("Attempting to create " + className);
        entity.setId(null);

        entity = save(entity);
        logger.debug("Created " + className + ". New ID is '" + entity.getId() + "'");

        return entity;
    }

    /**
     * This method saves the object
     *
     * @param entity is the object to be saved
     * @return the saved object
     */
    @Override
    @Transactional
    public T save(T entity) {
        logger.debug("Attempting to save " + className);

        entity = postSave(repository.save(preSave(entity)));

        logger.debug("Saved " + className + " with ID '" + entity.getId() + "'");

        return entity;
    }

    /**
     * This method updates the entry by the id with the new object
     *
     * @param id     the entry to be updated
     * @param entity the object with the new data
     * @return the updated object
     * @throws EntityNotFoundException              will be thrown if the entry could not be found
     */
    @Override
    @Transactional
    public T updateById(String id, T entity) throws EntityNotFoundException {
        logger.debug("Attempting to update " + className + " with ID '" + id + "'");

        T foundEntity = findById(id);
        if (foundEntity != null) {
            BeanUtils.copyProperties(entity, foundEntity, getNullPropertyNames(entity));
            foundEntity.setId(UUID.fromString(id));

            entity = repository.save(foundEntity);
            logger.debug("Updated " + className + " with ID '" + id);

            return entity;
        } else {
            logger.debug(className + " with ID '" + id + "' not found");
            throw new EntityNotFoundException(className + " with ID '" + id + "' not found");
        }
    }

    /**
     * This method return a list of all the fields which are null
     *
     * @param source the object to be checked
     * @return list of strings of the fields that are null
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        emptyNames.add("id");
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * This method is meant to be overridden for a custom method before the object will be saved
     *
     * @param newEntity the object to be saved
     * @return the object to be saved
     */
    @Transactional
    protected T preSave(T newEntity) {
        return newEntity;
    }

    @Transactional
    protected T postSave(T newEntity) {
        return newEntity;
    }

    /**
     * This method deletes the entry by the id
     *
     * @param id the id of the entry to be deleted
     * @throws EntityNotFoundException will be thrown if the entry could not be found
     */
    @Override
    @Transactional
    public void deleteById(String id) throws EntityNotFoundException {
        logger.debug("Attempting to delete " + className + " with ID '" + id + "'");
        preDelete(id);
        repository.deleteById(UUID.fromString(id));
        logger.debug("Deleted " + className + " with ID '" + id + "'");
    }

    /**
     * This method checks if the entry with the id exists
     *
     * @param id the id to be checked
     * @return returns true if the element exists else false
     */
    @Override
    @Transactional
    public boolean existsById(String id) {
        return repository.existsById(UUID.fromString(id));
    }

    @Override
    @Transactional
    public T createIfNotExist(T entity) {
        if(entity == null) return null;

        T newEntity = null;
        if(entity.getId() != null) newEntity = findById(entity.getId().toString());
        else newEntity = findByValue(entity);

        if(newEntity == null) newEntity = create(entity);

        return newEntity;
    }

    @Override
    @Transactional
    public T findByValue(T entity) {
        return null;
    }

    public void preDelete(String id){}
}
