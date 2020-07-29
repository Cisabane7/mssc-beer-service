package guru.sfg.common.events;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {
    static final long serialVersionUID = -5084215099189811504L;
    private BeerDto beer;
}
