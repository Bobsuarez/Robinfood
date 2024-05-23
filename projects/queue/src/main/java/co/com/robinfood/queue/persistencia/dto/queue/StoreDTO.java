package co.com.robinfood.queue.persistencia.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO implements Serializable {

    private static final long serialVersionUID = -4401210888449376257L;

    private Long id;

    private String name;
}
