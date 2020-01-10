package com.bnpinnovation.tcp.receiver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public interface OverlordDto {

    @ToString
    @NoArgsConstructor
    @Getter
    @Setter
    class Ap {
        private String transmitterId;
        private int battery;
        private List<WorkerLog> workers;
    }

    @ToString
    @NoArgsConstructor
    @Getter
    @Setter
    class WorkerLog {
        private String mappingId;
        private Float distance;
    }
}
