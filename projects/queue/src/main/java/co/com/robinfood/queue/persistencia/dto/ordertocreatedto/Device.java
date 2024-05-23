package co.com.robinfood.queue.persistencia.dto.ordertocreatedto;

import lombok.Data;

@Data
public class Device {
    private Object timezone;
    private String ip;
    private Object version;
    private int platform;
}