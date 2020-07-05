package org.demo.project.controlsystem.service;

/**
 * Сервис выдачи пропусков
 */
public interface PassService {

    /**
     * Пропустить пользователя
     *
     * @param userId идентификатор пользователя
     * @param roomId идентификатор комнаты
     * @param entrance true - вход, иначе выход
     * @return true - есть доступ, false - нет доступа
     */
    boolean passUser(Long userId, Long roomId, Boolean entrance);
}
