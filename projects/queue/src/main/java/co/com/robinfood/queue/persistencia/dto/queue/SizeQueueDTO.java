package co.com.robinfood.queue.persistencia.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class SizeQueueDTO implements Serializable {

    private static final long serialVersionUID = -8133003200203333168L;

    private Long id;

    private String name;

}
