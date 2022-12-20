package com.social.dailylink.generic;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AbstractEntityRepository<T extends AbstractEntity> extends JpaRepository<T, UUID> {
}
