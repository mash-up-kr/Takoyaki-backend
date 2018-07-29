package org.mashup.takoyaki.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mashup.takoyaki.entity.composite.key.FavoriteKey;

import javax.persistence.*;

@Entity
@Table(name = "TB_FAVORITES")
@Getter @Setter
@ToString
public class Favorite {

    @EmbeddedId
    private FavoriteKey id;

}