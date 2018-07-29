package org.mashup.takoyaki.entity.composite.key;

import lombok.*;
import org.mashup.takoyaki.entity.Report;
import org.mashup.takoyaki.entity.User;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter @Setter
@Embeddable
public class FavoriteKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report reportId;

}
