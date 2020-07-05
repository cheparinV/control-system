package org.demo.project.controlsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("access.system")
public class ServiceConfiguration {

    private Long usersMax;

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
