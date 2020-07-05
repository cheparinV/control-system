package org.demo.project.controlsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Объект с конфигурируемыми переменными
 */
@ConfigurationProperties("access.system")
public class ServiceConfiguration {

    /** Максимальное количество пользователей */
    private Long usersMax;

    /** Максимальное количество комнат */
    private Long roomsMax;

    public Long getUsersMax() {
        return usersMax;
    }

    public Long getRoomsMax() {
        return roomsMax;
    }

    public void setUsersMax(Long usersMax) {
        this.usersMax = usersMax;
    }

    public void setRoomsMax(Long roomsMax) {
        this.roomsMax = roomsMax;
    }
}
