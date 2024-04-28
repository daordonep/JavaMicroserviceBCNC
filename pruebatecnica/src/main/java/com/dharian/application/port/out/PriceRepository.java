package com.dharian.application.port.out;

import com.dharian.infraestructure.repository.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

interface PriceRepository extends JpaRepository<PriceEntity, Long> {}