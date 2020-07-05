package org.demo.project.controlsystem.service.impl;

import org.demo.project.controlsystem.config.ServiceConfiguration;
import org.demo.project.controlsystem.repository.PassRepository;
import org.demo.project.controlsystem.repository.model.Pass;
import org.demo.project.controlsystem.service.PassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Реализация сервиса пропусков
 */
@Service
public class PassServiceImpl implements PassService {

    private final static Logger LOG = LoggerFactory.getLogger(PassServiceImpl.class);

    private final PassRepository passRepository;

    private final ServiceConfiguration config;

    @Autowired
    public PassServiceImpl(PassRepository passRepository, ServiceConfiguration config) {
        this.passRepository = passRepository;
        this.config = config;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public boolean passUser(Long userId, Long roomId, Boolean entrance) {
        if (!isValidRoomAndUser(userId, roomId)) {
            LOG.info(String.format("Access denied, user: %d, room: %d", userId, roomId));
            return false;
        }
        if (entrance) {
            return enterUser(userId, roomId);
        } else {
            return exitUser(userId, roomId);
        }
    }

    /**
     * Валидация соответствия пользователя и комнаты
     *
     * @param userId идентификатор пользователя
     * @param roomId идентификатор комнаты
     */
    private boolean isValidRoomAndUser(Long userId, Long roomId) {
        return roomId % userId == 0 && roomId <= config.getRoomsMax() && userId <= config.getUsersMax();
    }

    /**
     * Пропуск для входа пользователя
     *
     * @param userId идентификатор пользователя
     * @param roomId идентификатор комнаты
     */
    private synchronized boolean enterUser(Long userId, Long roomId) {
        if (passRepository.findPassByUserId(userId) == null) {
            passRepository.save(new Pass(roomId, userId));
            LOG.info(String.format("User entering into room, user: %d, room: %d", userId, roomId));
            return true;
        }
        LOG.info(String.format("User tried to open room, but already in another room, user: %s, room: %s", userId,
                roomId));
        return false;
    }

    /**
     * Пропуск для выхода пользователя
     *
     * @param userId идентификатор пользователя
     * @param roomId идентификатор комнаты
     */
    private synchronized boolean exitUser(Long userId, Long roomId) {
        final Optional<Pass> passByUserId = Optional.ofNullable(passRepository.findPassByUserId(userId));
        if (passByUserId.map(Pass::getRoomId)
                .map(roomId::equals)
                .orElse(false)) {
            passRepository.delete(passByUserId.get());
            LOG.info(String.format("User exiting from room, user: %d, room: %d", userId, roomId));
            return true;
        }
        LOG.info(String.format("User tried to exit from not his current room, user: %d, room: %d", userId, roomId));
        return false;
    }
}
